package com.beta.rsatech.churchcradle.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.PaymentService;
import com.beta.rsatech.churchcradle.server.utils.DBConnection;
import com.beta.rsatech.churchcradle.server.utils.PaymentFactory;
import com.beta.rsatech.churchcradle.server.utils.SMSDispatchObject;
import com.beta.rsatech.churchcradle.server.utils.ServerGlobalResources;
import com.beta.rsatech.churchcradle.server.utils.Utils;
import com.beta.rsatech.churchcradle.server.utils.currency.parser.CurrencyKeeper;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.AppRenewModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.OnlineDonationPaymentModel;
import com.beta.rsatech.churchcradle.shared.OnlineOfferingPaymentModel;
import com.beta.rsatech.churchcradle.shared.OnlineTithePaymentModel;
import com.beta.rsatech.churchcradle.shared.PaymentGenModel;
import com.beta.rsatech.churchcradle.shared.SMSPurchaseModel;
import com.beta.rsatech.churchcradle.shared.ThirdPartyPaymentModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mpowerpayments.mpower.MPowerCheckoutInvoice;

@SuppressWarnings("serial")
public class PaymentServiceImpl extends RemoteServiceServlet implements PaymentService{
	final static String CHURCH_ID = "n";
	final static String AMOUNT = "s";
	final static String TYPE = "a";
	
	private static Connection con = DBConnection.getConnection();
	private Statement stmt;

	@Override
	public String offeringPay(int churchId, int memberId, int amount, String month, String url) {
		PaymentGenModel model = getInsertPaymentGen(memberId, churchId, "O", amount);
		if(model != null){
			ThirdPartyPaymentModel paymentModel = ServerGlobalResources.getInstance().getPaymentModel();
			MPowerCheckoutInvoice invoice = PaymentFactory.getInstance().getCheckoutInvoice(paymentModel, Utils.getReturnUrl(model.getId(), model.getToken(), model.getType(), url), Utils.getCancelUrl(model.getId(), model.getToken(), model.getType(), url), false);
			invoice.addItem("Offering for "+month, 0, amount, amount);
			invoice.setTotalAmount(amount);
			if(invoice.create()){
				System.out.println(invoice.getStatus());
				System.out.println(invoice.getResponseText());
				return invoice.getInvoiceUrl();
			} else {
				System.out.println(invoice.getResponseText());
				System.out.println(invoice.getStatus());
			}
		}
		return null;
	}

	private PaymentGenModel getInsertPaymentGen(int memberId, int churchId, String type, int amount){
		String token = Utils.getToken();

		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into payments_tracker (member_id,church_id,token,type,amount) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);
			prstmt.setString(3, token);
			prstmt.setString(4, type);
			prstmt.setDouble(5, amount);

			int success = prstmt.executeUpdate();
			if(success > 0){
				int id = 0;
				ResultSet tmpResultSet = prstmt.getGeneratedKeys();
				while(tmpResultSet.next()){
					id = tmpResultSet.getInt(1);
				}

				PaymentGenModel model = new PaymentGenModel();
				model.setId(id);
				model.setMemberId(memberId);
				model.setChurchId(churchId);
				model.setToken(token);
				model.setType(type);

				//con.close();
				return model;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	
	
	private PaymentGenModel getOwnerInsertPaymentGen(int memberId, int churchId, String type, double amount){
		String token = Utils.getToken();

		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into owner_payment_tracker (payee_id,church_id,token,type,amount,created_ts,external_token) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);
			prstmt.setString(3, token);
			prstmt.setString(4, type);
			prstmt.setDouble(5, amount);
			prstmt.setString(6, Utils.getTodayDateTime());
			prstmt.setString(7, "");

			int success = prstmt.executeUpdate();
			if(success > 0){
				int id = 0;
				ResultSet tmpResultSet = prstmt.getGeneratedKeys();
				while(tmpResultSet.next()){
					id = tmpResultSet.getInt(1);
				}

				PaymentGenModel model = new PaymentGenModel();
				model.setId(id);
				model.setMemberId(memberId);
				model.setChurchId(churchId);
				model.setToken(token);
				model.setType(type);

				//con.close();
				return model;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	

	private PaymentGenModel getSelectPaymentGen(int paymentId, String token){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select track.member_id as member_id,track.church_id as church_id,track.amount as amount,mem.msisdn as msisdn from payments_tracker as track, members as mem where track.id = ? and track.token = ? and track.member_id = mem.id and track.church_id = mem.church_id");
			prstmt.setInt(1, paymentId);
			prstmt.setString(2, token);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int memberId = results.getInt("member_id");
					int churchId = results.getInt("church_id");
					double amount = results.getDouble("amount");
					String msisdn = results.getString("msisdn");

					PaymentGenModel model = new PaymentGenModel();
					model.setChurchId(churchId);
					model.setMemberId(memberId);
					model.setAmount(amount);
					model.setMsisdn(msisdn);

					//con.close();
					return model;
				}
			}

			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	
	private PaymentGenModel getSelectPaymentGenPrimary(int paymentId, String token){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select member_id,church_id,amount from payments_tracker where id = ? and token = ?");
			prstmt.setInt(1, paymentId);
			prstmt.setString(2, token);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int memberId = results.getInt("member_id");
					int churchId = results.getInt("church_id");
					double amount = results.getDouble("amount");

					PaymentGenModel model = new PaymentGenModel();
					model.setChurchId(churchId);
					model.setMemberId(memberId);
					model.setAmount(amount);

					//con.close();
					return model;
				}
			}

			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public String tithePay(int churchId, int memberId, int amount, String month, String url) {
		PaymentGenModel model = getInsertPaymentGen(memberId, churchId, "T", amount);

		if(model != null){
			ThirdPartyPaymentModel paymentModel = ServerGlobalResources.getInstance().getPaymentModel();

			MPowerCheckoutInvoice invoice = PaymentFactory.getInstance().getCheckoutInvoice(paymentModel, Utils.getReturnUrl(model.getId(), model.getToken(), model.getType(), url), Utils.getCancelUrl(model.getId(), model.getToken(), model.getType(), url), false);
			invoice.addItem("Tithe for "+month, 0, amount, amount);
			invoice.setTotalAmount(amount);
			if(invoice.create()){
				System.out.println(invoice.getStatus());
				System.out.println(invoice.getResponseText());
				return invoice.getInvoiceUrl();
			} else {
				System.out.println(invoice.getResponseText());
				System.out.println(invoice.getStatus());
			}
		}
		return null;
	}

	@Override
	public String donationPay(int churchId, int memberId, int amount,
			String message) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String specialOffering(int churchId, int memberId, int amount,
			String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTitheBySystem(int paymentId, String token, String externalToken) {
		if(updatePaymentTracker(paymentId, token)){

			//Grab payment info
			PaymentGenModel model = getSelectPaymentGen(paymentId, token);
			if(model != null){

				//Add to tithe
				return saveTithe(model.getMemberId(), model.getChurchId(), model.getAmount(), externalToken);
			}
		}
		return false;
	}

	private boolean saveTithe(int memberId, int churchId, double amount, String externalToken){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into tithes (member_id,created_by,amount,created_ts,date,approved_by,modified_by,church_id,status,is_editable) values (?,?,?,?,?,?,?,?,?,?)");
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, 0);
			prstmt.setDouble(3, amount);
			prstmt.setString(4, Utils.getTodayDateTime());
			prstmt.setString(5, Utils.getTodayDateTime());
			prstmt.setInt(6, 0);
			prstmt.setInt(7, 0);
			prstmt.setInt(8,  churchId);
			prstmt.setString(9, "A");
			prstmt.setString(10, "F");

			int success = prstmt.executeUpdate();

			//Save to online tithes
			if(success >= 0 && saveToOnlineTithe(memberId, churchId, amount, externalToken, getMemberMsisdn(memberId, churchId))){
				//con.close();
				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private boolean saveToOnlineTithe(int memberId, int churchId, double amount, String externalToken, String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into online_tithe_payments (member_id,amount,created_ts,church_id,status,token) values (?,?,?,?,?,?)");
			prstmt.setInt(1, memberId);
			prstmt.setDouble(2, amount);
			prstmt.setString(3, Utils.getTodayDateTime());
			prstmt.setInt(4,  churchId);
			prstmt.setString(5, "A");
			prstmt.setString(6, externalToken);

			int success = prstmt.executeUpdate();
			if(success > 0){


				MemberModel member = getMember(memberId, churchId);
				SMSDispatchObject sendSMS = new SMSDispatchObject(msisdn, Utils.getTitheGeneratedMessage(Utils.getMonthInWords(getMonth()+""), member.getFirstName(), amount));
				sendSMS.send();

				//con.close();

				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private MemberModel getMember(int memberId, int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();
		try{

			prstmt = (PreparedStatement) con.prepareStatement("select id,email,msisdn,church_id,fname,lname from members where id = ? and church_id = ? and status = 'A'");

			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String email = results.getString("email");
					String msisdn = results.getString("msisdn");
					String firstname = results.getString("fname");
					String lastname = results.getString("lname");


					MemberModel model = new MemberModel();
					model.setId(id);
					model.setEmail(email);
					model.setMsisdn(msisdn);
					model.setFirstName(firstname);
					model.setLastName(lastname);
					model.setChurchId(churchId);

					return model;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	private int getMonth() {
		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select month(curdate()) as month");

			if(results != null){
				while(results.next()){
					int month = results.getInt("month");

					return month;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		System.gc();
		return 0;
	}

	private boolean saveToOnlineOffering(int memberId, int churchId, double amount, String externalToken, String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into online_offering_payments (member_id,amount,created_ts,church_id,status,token) values (?,?,?,?,?,?)");
			prstmt.setInt(1, memberId);
			prstmt.setDouble(2, amount);
			prstmt.setString(3, Utils.getTodayDateTime());
			prstmt.setInt(4,  churchId);
			prstmt.setString(5, "A");
			prstmt.setString(6, externalToken);

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();

				//Send SMS
				MemberModel model = getMember(memberId, churchId);
				SMSDispatchObject sendSMS = new SMSDispatchObject(msisdn, Utils.getOnlineGeneratedOfferingMessage(model.getFirstName(), ServerGlobalResources.getInstance().getChurchModel().getName(), amount));
				sendSMS.send();

				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private boolean saveToOnlineDonation(int churchId, double amount, String externalToken){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into online_donation_payments (amount,created_ts,church_id,status,token) values (?,?,?,?,?)");
			prstmt.setDouble(1, amount);
			prstmt.setString(2, Utils.getTodayDateTime());
			prstmt.setInt(3, churchId);
			prstmt.setString(4, "A");
			prstmt.setString(5, externalToken);

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();

				//Send SMS to leaders
				//TODO fix hard code churchId
				SMSDispatchObject sendSMS = new SMSDispatchObject(Utils.getStringifiedMsisdn(getPaymentNotifiers(1)), Utils.getGeneratedLeadersNotificationMessage(amount));
				sendSMS.send();
				
				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private ArrayList<String> getPaymentNotifiers(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select msisdn from members where church_id = ? and can_view_payments = 'Y' and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<String> msisdnList = new ArrayList<String>();
				while(results.next()){
					String msisdn = results.getString("msisdn");
					msisdnList.add(msisdn);
				}

				return msisdnList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	private boolean saveMyOffering(int memberId, int churchId, double amount, String externalToken, String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into my_offerings (member_id,amount,created_date,church_id,status) values (?,?,?,?,?)");
			prstmt.setInt(1, memberId);
			prstmt.setDouble(2, amount);
			prstmt.setString(3, Utils.getTodayDateTime());
			prstmt.setInt(4, churchId);
			prstmt.setString(5, "A");

			int success = prstmt.executeUpdate();
			if(success >= 0 && saveToOnlineOffering(memberId,churchId, amount, externalToken, msisdn)){
				//con.close();
				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private boolean saveDonation(int churchId, double amount, String externalToken){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into annoymous_donations (amount,created_date,church_id,status) values (?,?,?,?)");
			prstmt.setDouble(1, amount);
			prstmt.setString(2, Utils.getTodayDateTime());
			prstmt.setInt(3, churchId);
			prstmt.setString(4, "A");

			int success = prstmt.executeUpdate();
			if(success >= 0 && saveToOnlineDonation(churchId, amount, externalToken)){
				//con.close();
				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private boolean updatePaymentTracker(int id, String token){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("update payments_tracker set status = 'D' where id = ? and token = ?");
			prstmt.setInt(1, id);
			prstmt.setString(2, token);

			int success = prstmt.executeUpdate();
			System.out.println("[payments_tracker] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	private String getMemberMsisdn(int memberId, int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select msisdn from members where id = ? and church_id = ? and status = 'A'");
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					String msisdn = results.getString("msisdn");

					//con.close();
					return msisdn;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addMyOfferingBySystem(int paymentId, String token, String externalToken) {
		if(updatePaymentTracker(paymentId, token)){

			//Grab payment info
			PaymentGenModel model = getSelectPaymentGen(paymentId, token);
			if(model != null){

				//Add to offering
				return saveMyOffering(model.getMemberId(), model.getChurchId(), model.getAmount(), externalToken, model.getMsisdn());
			}
		}
		return false;
	}

	@Override
	public ArrayList<OnlineTithePaymentModel> getOnlineTithePayments(
			int churchId, String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,amount,member_id,token,date(created_ts) as created_ts from online_tithe_payments where church_id = ? and status = ? and date(created_ts) between ? and ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, "A");
			prstmt.setString(3, Utils.getBeginDate(",", date));
			prstmt.setString(4, Utils.getEndDate(",", date));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<OnlineTithePaymentModel> tithes = new ArrayList<OnlineTithePaymentModel>();
				while(results.next()){
					int id = results.getInt("id");
					double amount = results.getDouble("amount");
					String token = results.getString("token");
					int memberId = results.getInt("member_id");
					String createdTs = results.getString("created_ts");

					OnlineTithePaymentModel model = new OnlineTithePaymentModel();
					model.setId(id);
					model.setAmount(amount);
					model.setMemberId(memberId);
					model.setToken(token);
					model.setChurchId(churchId);
					model.setCreatedTs(createdTs);

					tithes.add(model);
				}
				//con.close();
				return tithes;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<OnlineOfferingPaymentModel> getOnlineOfferingPayments(
			int churchId, String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,amount,member_id,token,date(created_ts) as created_ts from online_offering_payments where church_id = ? and status = ? and date(created_ts) between ? and ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, "A");
			prstmt.setString(3, Utils.getBeginDate(",", date));
			prstmt.setString(4, Utils.getEndDate(",", date));

			System.out.println("Date: "+date);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<OnlineOfferingPaymentModel> offerings = new ArrayList<OnlineOfferingPaymentModel>();
				while(results.next()){
					int id = results.getInt("id");
					double amount = results.getDouble("amount");
					String token = results.getString("token");
					int memberId = results.getInt("member_id");
					String createdTs = results.getString("created_ts");

					OnlineOfferingPaymentModel model = new OnlineOfferingPaymentModel();
					model.setId(id);
					model.setAmount(amount);
					model.setMemberId(memberId);
					model.setToken(token);
					model.setChurchId(churchId);
					model.setCreatedTs(createdTs);

					offerings.add(model);
				}
				//con.close();
				return offerings;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public String annoymousPay(int churchId, int paymentId, int amount, String month, String url) {
		PaymentGenModel model = getInsertPaymentGen(0, churchId, "D", amount);
		if(model != null){
			ThirdPartyPaymentModel paymentModel = getPaymentModel(paymentId);

			MPowerCheckoutInvoice invoice = PaymentFactory.getInstance().getCheckoutInvoice(paymentModel, Utils.getAnnoymousReturnUrl(model.getId(), model.getToken(), model.getType(), url), Utils.getAnnoymousCancelUrl(model.getId(), model.getToken(), model.getType(), url), false);
			invoice.addItem("Donation for "+month, 0, amount, amount);
			invoice.setTotalAmount(amount);
			if(invoice.create()){
				System.out.println(invoice.getStatus());
				System.out.println(invoice.getResponseText());
				return invoice.getInvoiceUrl();
			} else {
				System.out.println(invoice.getResponseText());
				System.out.println(invoice.getStatus());
			}
		}else{
			System.out.println("Model was null");
		}
		return null;
	}

	@Override
	public boolean addDonationBySystem(int paymentId, String token, String externalToken) {
		if(updatePaymentTracker(paymentId, token)){
			//Grab payment info
			PaymentGenModel model = getSelectPaymentGenPrimary(paymentId, token);
			if(model != null){

				//Add to Donation
				return saveDonation(model.getChurchId(), model.getAmount(), externalToken);
			}
		}
		return false;
	}

	@Override
	public ArrayList<OnlineDonationPaymentModel> getOnlineDonationPayments(
			int churchId, String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,amount,token,date(created_ts) as created_ts from online_donation_payments where church_id = ? and status = ? and date(created_ts) between ? and ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, "A");
			prstmt.setString(3, Utils.getBeginDate(",", date));
			prstmt.setString(4, Utils.getEndDate(",", date));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<OnlineDonationPaymentModel> tithes = new ArrayList<OnlineDonationPaymentModel>();
				while(results.next()){
					int id = results.getInt("id");
					double amount = results.getDouble("amount");
					String token = results.getString("token");
					String createdTs = results.getString("created_ts");

					OnlineDonationPaymentModel model = new OnlineDonationPaymentModel();
					model.setId(id);
					model.setAmount(amount);
					model.setToken(token);
					model.setChurchId(churchId);
					model.setCreatedTs(createdTs);

					tithes.add(model);
				}
				//con.close();
				return tithes;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	public ThirdPartyPaymentModel getPaymentModel(int paymentId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,mp_private_key,mp_public_key,mp_master_key,mp_token,name,tag_line,msisdn,address,website from payment_accounts where id = ? and status = 'A'");
			prstmt.setInt(1, paymentId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("name");
					String masterKey = results.getString("mp_master_key");
					String publicKey = results.getString("mp_public_key");
					String privateKey = results.getString("mp_private_key");
					String token = results.getString("mp_token");
					String address = results.getString("address");
					String tagline = results.getString("tag_line");
					String website = results.getString("website");
					String msisdn = results.getString("msisdn");

					ThirdPartyPaymentModel model = new ThirdPartyPaymentModel();
					model.setId(id);
					model.setAddress(address);
					model.setMasterKey(masterKey);
					model.setMsisdn(msisdn);
					model.setName(name);
					model.setPrivateKey(privateKey);
					model.setPublicKey(publicKey);
					model.setTagLine(tagline);
					model.setToken(token);
					model.setWebsite(website);

					return model;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}
	
	private ThirdPartyPaymentModel getOwnerPaymentModel(){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,mp_private_key,mp_public_key,mp_master_key,mp_token,name,tagline,msisdn,address,website from owner_payment_details where status = ? order by id desc limit 1");
			prstmt.setString(1, "A");

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("name");
					String masterKey = results.getString("mp_master_key");
					String publicKey = results.getString("mp_public_key");
					String privateKey = results.getString("mp_private_key");
					String token = results.getString("mp_token");
					String address = results.getString("address");
					String tagline = results.getString("tagline");
					String website = results.getString("website");
					String msisdn = results.getString("msisdn");

					ThirdPartyPaymentModel model = new ThirdPartyPaymentModel();
					model.setId(id);
					model.setAddress(address);
					model.setMasterKey(masterKey);
					model.setMsisdn(msisdn);
					model.setName(name);
					model.setPrivateKey(privateKey);
					model.setPublicKey(publicKey);
					model.setTagLine(tagline);
					model.setToken(token);
					model.setWebsite(website);
					
					//con.close();
					return model;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}
	
	@Override
	public String buySMS(SMSPurchaseModel model, String url) {
		PaymentGenModel genModel = getOwnerInsertPaymentGen(model.getMemberId(), model.getChurchId(), AppConstants.SMS_PURCHASE, model.getAmount());

		if(model != null){
			ThirdPartyPaymentModel paymentModel = ServerGlobalResources.getInstance().getOwnPaymentModel();
			
			if(paymentModel == null){
				paymentModel = getOwnerPaymentModel();
			}
			
			MPowerCheckoutInvoice invoice = PaymentFactory.getInstance().getCheckoutInvoice(paymentModel, Utils.getReturnUrl(genModel.getId(), genModel.getToken(), genModel.getType(), url), Utils.getCancelUrl(genModel.getId(), genModel.getToken(), genModel.getType(), url), false);
			invoice.addItem("Purchase for "+model.getSmsQuantity()+" SMS", (int)model.getSmsQuantity(), ServerGlobalResources.getInstance().getChurchModel().getPricePerSMS(), model.getAmount());
			invoice.setTotalAmount(model.getAmount());
			if(invoice.create()){
				System.out.println(invoice.getStatus());
				System.out.println(invoice.getResponseText());
				return invoice.getInvoiceUrl();
			} else {
				System.out.println(invoice.getResponseText());
				System.out.println(invoice.getStatus());
			}
		}
		return null;
	}
	
	private HashMap<String, String> getPurchaseDetails(int paymentId, String type, String token){
		
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,church_id,amount from owner_payment_tracker where id = ? and type = ? and token = ? and status = 'P'");
			prstmt.setInt(1, paymentId);
			prstmt.setString(2, type);
			prstmt.setString(3, token);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<String, String> purchaseHash = new HashMap<String, String>();
				while(results.next()){
					int churchId = results.getInt("church_id");
					double amount = results.getDouble("amount");
					
					purchaseHash.put(CHURCH_ID, ""+churchId);
					purchaseHash.put(AMOUNT, ""+amount);
					purchaseHash.put(TYPE, type);
					
					System.out.println("church_id "+churchId);
					System.out.println("amount "+amount);
					System.out.println("type "+type);
					
					//con.close();
					return purchaseHash;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	
	private void updatePurchase(int paymentId, String externalToken, HashMap<String, String> payeeHash){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("update owner_payment_tracker set status = 'A', external_token = ? where id = ?");
			prstmt.setString(1, externalToken);
			prstmt.setInt(2, paymentId);

			int success = prstmt.executeUpdate();
			System.out.println("[owner_payment_tracker update] success is "+success);
			if(success > 0){
				//con.close();

				//Credit Payee
				creditPayee(payeeHash);
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
	}
	
	private void creditPayee(HashMap<String, String> payeeHash){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,sms_left,expire_date,member_limit,price_per_sms,annual_fee,package,datediff(expire_date,curdate()) as days_left from churches where id = ? and status = 'A'");
			prstmt.setInt(1, Integer.parseInt(payeeHash.get(CHURCH_ID)));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					double smsLeft = results.getDouble("sms_left");
					String expireDate = results.getString("expire_date");
					double memberLimit = results.getDouble("member_limit");
					double pricePerSMS = results.getDouble("price_per_sms");
					double annualFee = results.getDouble("annual_fee");
					String packageType = results.getString("package");
					double dayLeft = results.getDouble("days_left");
					
					//Do pay reasoning
					
					if(payeeHash.get(TYPE).equalsIgnoreCase(AppConstants.SMS_PURCHASE)){
						double amountPaid = Double.parseDouble(payeeHash.get(AMOUNT));
						double smsValue = Math.round(amountPaid / pricePerSMS)+smsLeft;
						
						creditPayeeSMS(Integer.parseInt(payeeHash.get(CHURCH_ID)), smsValue);
						
					}else if(payeeHash.get(TYPE).equalsIgnoreCase(AppConstants.APP_RENEW)){
						CurrencyKeeper keeper = new CurrencyKeeper();
						
						
						double pricePerMonth = ((annualFee / 12)*keeper.getDollarRate());
						double amountPaid = Double.parseDouble(payeeHash.get(AMOUNT));
						
						double months = Math.round((amountPaid/pricePerMonth));
						
						if(dayLeft <= 0){
							creditPayeeRenewal(Integer.parseInt(payeeHash.get(CHURCH_ID)), Utils.getDateAdded(Utils.getTodayDate(), (int)months));
						}else{
							creditPayeeRenewal(Integer.parseInt(payeeHash.get(CHURCH_ID)), Utils.getDateAdded(expireDate, (int)months));
						}
					}
				}
				//con.close();
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	
	private void creditPayeeSMS(int churchId, double smsValue){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("update churches set sms_left = ? where id = ? and status = 'A'");
			prstmt.setDouble(1, smsValue);
			prstmt.setInt(2, churchId);

			int success = prstmt.executeUpdate();
			System.out.println("[church_sms_credit update] success is "+success);
			if(success >= 0){
				////con.close();
				
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
	}
	
	private void creditPayeeRenewal(int churchId, String expireDate){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("update churches set expire_date = ?, renewal_date = ? where id = ? and status = 'A'");
			prstmt.setString(1, expireDate);
			prstmt.setString(2, Utils.getTodayDate());
			prstmt.setInt(3, churchId);

			int success = prstmt.executeUpdate();
			System.out.println("[church_renew update] success is "+success);
			if(success >= 0){
				//con.close();
				
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
	}

	@Override
	public boolean updateSMSPayment(int paymentId, String token, String externalToken) {
		System.out.println("payment_id => "+paymentId+", token => "+token);
		HashMap<String, String> purchaseDetails = getPurchaseDetails(paymentId, AppConstants.SMS_PURCHASE, token);
		
		//Check if token valid
		if(purchaseDetails != null && purchaseDetails.size() > 0){
			System.out.println("External token "+externalToken);

			//Update own payment tracker
			updatePurchase(paymentId, externalToken, purchaseDetails);
			
			return true;
		}
		
		System.out.println("Came as NULL");
		
		return false;
	}

	@Override
	public String renewApp(AppRenewModel model, String url) {
		PaymentGenModel genModel = getOwnerInsertPaymentGen(model.getMemberId(), model.getChurchId(), AppConstants.APP_RENEW, model.getAmount());

		if(model != null){
			ThirdPartyPaymentModel paymentModel = ServerGlobalResources.getInstance().getOwnPaymentModel();
			
			if(paymentModel == null){
				paymentModel = getOwnerPaymentModel();
			}
			
			MPowerCheckoutInvoice invoice = PaymentFactory.getInstance().getCheckoutInvoice(paymentModel, Utils.getReturnUrl(genModel.getId(), genModel.getToken(), genModel.getType(), url), Utils.getCancelUrl(genModel.getId(), genModel.getToken(), genModel.getType(), url), false);
			invoice.addItem("Renew for "+model.getMonths()+" Month(s)", (int)model.getMonths(), model.getPricePerMonth(), model.getAmount());
			invoice.setTotalAmount(model.getAmount());
			if(invoice.create()){
				System.out.println(invoice.getStatus());
				System.out.println(invoice.getResponseText());
				return invoice.getInvoiceUrl();
			} else {
				System.out.println(invoice.getResponseText());
				System.out.println(invoice.getStatus());
			}
		}
		return null;
	}

	@Override
	public boolean updateAppRenewPayment(int paymentId, String token,
			String externalToken) {
		System.out.println("payment_id => "+paymentId+", token => "+token);
		HashMap<String, String> purchaseDetails = getPurchaseDetails(paymentId, AppConstants.APP_RENEW, token);
		
		//Check if token valid
		if(purchaseDetails != null && purchaseDetails.size() > 0){
			System.out.println("External token "+externalToken);

			//Update own payment tracker
			updatePurchase(paymentId, externalToken, purchaseDetails);
			
			return true;
		}
		
		System.out.println("Came as NULL");
		
		return false;
	}


}
