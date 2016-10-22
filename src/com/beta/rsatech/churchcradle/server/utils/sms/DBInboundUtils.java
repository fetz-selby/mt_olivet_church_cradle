package com.beta.rsatech.churchcradle.server.utils.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.beta.rsatech.churchcradle.server.utils.DBConnection;
import com.beta.rsatech.churchcradle.server.utils.Utils;
import com.beta.rsatech.churchcradle.server.utils.sms.parser.SMSGatewayModel;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.ChurchModel;

public class DBInboundUtils {

	private Statement stmt;

	private static Connection con = DBConnection.getConnection();

	private ArrayList<Integer> messageIdList = new ArrayList<Integer>();
	private ArrayList<String> commandSetList = new ArrayList<String>();
	private String[] commantdSet = {"birth", "birthday", "sermon", "sermons", "announce", "announcement"};

	private static DBInboundUtils instance = new DBInboundUtils();

	private DBInboundUtils(){
		loadAllMessageIds();
		initCommandSetList();
	}

	public static DBInboundUtils getInstance(){

		return instance;
	}

	private void loadAllMessageIds(){
		Statement stmt = null;
		con = DBConnection.getConnection();

		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select message_id from sms_gateway_messages where status = 'A'");
			if(results != null){
				while(results.next()){
					int messageId = results.getInt("message_id");
					messageIdList.add(messageId);
				}

			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	private void initCommandSetList(){
		for(String command : commantdSet){
			commandSetList.add(command);
		}
	}

	public boolean saveGatewaySMS(SMSGatewayModel model){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into sms_gateway_messages (message_id,message,from_msisdn,posted_ts) values (?,?,?,?) ");
			prstmt.setInt(1, model.getMessageId());
			prstmt.setString(2, model.getMessage());
			prstmt.setString(3, model.getFrom());
			prstmt.setString(4, Utils.getTodayDateTime());

			int success = prstmt.executeUpdate();
			MemberModel memberModel = getMemberDetails(model.getFrom());

			if(success >= 0 && memberModel != null && isMessageValidCommand(model.getMessage(), model.getFrom(), memberModel)){
				doCommandActivities(getCommand(model.getMessage()), getMessage(model.getMessage()), model.getFrom(), memberModel);
			}else if(success >= 0 && memberModel != null && isTitheMessage(model.getMessage().trim())){
				doTitheResponse(model.getFrom(), memberModel);
			}else if(success >= 0 && memberModel != null && isClassMessage(model.getMessage().trim())){
				doClassResponse(model.getFrom(), memberModel);
			}else if(success >= 0 && memberModel != null && isHelpMessage(model.getMessage().trim())){
				doHelpResponse(model.getFrom(), memberModel);
			}else if(success >= 0 && memberModel != null && isClassBroadcast(model.getMessage().trim()) && isClassLeader(memberModel.getId(), memberModel.getChurchId())){
				doClassBroadcastResponse(model.getFrom(), model.getMessage(), memberModel);
			}else if(success >= 0 && memberModel != null){
				doHelpResponse(model.getFrom(), memberModel);
			}else{
				doGenericResponse(model.getFrom());
			}

			//con.close();

		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

	private boolean isMessageValidCommand(String message, String msisdn, MemberModel model){
		if(message != null){
			String[] messageTokens = message.split("[\\s]+");

			if(messageTokens.length >=3 && messageTokens[0].equalsIgnoreCase("set") && commandSetList.contains(messageTokens[1].toLowerCase())){
				if(isPowerLeader(model)){
					return true;
				}
			}
		}

		return false;
	}

	private void doGenericResponse(String msisdn){
		String message = "Sorry, it appears your number is not registered in the system. Please see the administrator for further information. Thank you.";

		//LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(msisdn, message, null);
		//sendSMS.send();
	}

	private void doHelpResponse(String msisdn, MemberModel model){
		String message = "Hello "+model.getName()+",\n"
				+"You can request your tithe report by texting 'TITHE', you can also find your bible class by texting 'CLASS'.\n"
				+"Please visit the website for more information. God bless you.";

		LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(msisdn, message, model.getChurch());
		sendSMS.send();
	}

	private void doClassResponse(String msisdn, MemberModel model){
		MemberModel classLeader = getClassLeader(model.getClassLeaderId(), model.getChurchId());

		String message = "Hello "+model.getName()+",\nyour class leader is "+classLeader.getName()+", and mobile number is "+classLeader.getMsisdn()+". "
				+"God richly bless you.";

		LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(msisdn, message, model.getChurch());
		sendSMS.send();
	}
	
	private void doClassBroadcastResponse(String msisdn, String message, MemberModel model){
		
		String[] messageTokens = message.split("[\\s]+");
		
		String completeMessage = "";
		for(int i = 0; i < messageTokens.length; i++){
			if(i == 0){
				continue;
			}
			
			completeMessage += messageTokens[i]+" ";
		}
		
		ArrayList<MemberModel> membersList = getAllMembers(model.getId(), model.getChurchId());
		for(MemberModel member : membersList){
			String memberMsisdn = member.getMsisdn();
			
			LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(Utils.getStringifiedMsisdn(memberMsisdn, ","), completeMessage.trim(), member.getChurch());
			sendSMS.send();
			
		}
	}

	private void doTitheResponse(String msisdn, MemberModel model){
		HashMap<String, Double> titheHash = getTitheHash(model);
		if(titheHash == null){
			titheHash = new HashMap<String, Double>();
		}

		String message = "";
		int currentMonth = getMonth() + 1;
		for(int i = 1; i <= 12; i++){

			if(i == currentMonth) break;

			String month = ""+i;
			if(titheHash.containsKey(month)){
				message += Utils.getMonthInWords(month)+": "+titheHash.get(month)+" GHS\n";
			}else{
				message += Utils.getMonthInWords(month)+": 0.0 GHS\n";
			}
		}

		String welcomeMessage = "Hello "+model.getName()+", this is your tithe report for "+getYear()+"\n\n";
		String footerMessage = "\nGod richly bless you.";

		LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(msisdn, welcomeMessage+message+footerMessage, model.getChurch());
		sendSMS.send();

	}

	private HashMap<String, Double> getTitheHash(MemberModel model){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select sum(amount) as amount,month(date) as month,date(date) as date from tithes where church_id = ? and year(date) = ? and status = 'A' and member_id = ? group by month");
			prstmt.setInt(1, model.getChurchId());
			prstmt.setString(2, getYear()+"");
			prstmt.setInt(3, model.getId());

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<String, Double> titheMap = new HashMap<String, Double>();
				while(results.next()){
					double amount = results.getDouble("amount");
					String month = results.getString("month");

					titheMap.put(month, amount);
				}


				return titheMap;

			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	private String getCommand(String message){
		if(message != null && message.contains(" ")){
			return message.split("[\\s]+")[1];
		}

		return null;
	}

	private String getMessage(String message){
		if(message != null && message.contains(" ")){
			String[] tokens = message.split("[\\s]+");

			String tmpMessage = "";
			for(int i = 0; i < tokens.length; i++){
				if(i == 0 || i == 1){
					continue;
				}

				tmpMessage += tokens[i]+" ";
			}

			return tmpMessage.trim();
		}

		return null;
	}

	private int getYear() {
		con = DBConnection.getConnection();

		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select year(curdate()) as year");

			if(results != null){
				while(results.next()){
					int month = results.getInt("year");


					return month;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		System.gc();
		return 0;
	}

	private int getMonth() {
		con = DBConnection.getConnection();

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

	private void doCommandActivities(String command, String message, String msisdn, MemberModel model){
		command = command.toLowerCase();

		if(command.equalsIgnoreCase("birth") || command.equalsIgnoreCase("birthday")){
			saveToBirthdayMessages(model, message, msisdn);
		}else if(command.equalsIgnoreCase("announce") || command.equalsIgnoreCase("announcement")){
			saveAndBroadcastMessage(model, message, msisdn);
		}
	}

	private void saveToBirthdayMessages(MemberModel model, String message, String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into birthday_messages (member_id,church_id,message) values (?,?,?)");
			prstmt.setInt(1, model.getId());
			prstmt.setInt(2, model.getChurchId());
			prstmt.setString(3, message);

			int success = prstmt.executeUpdate();
			if(success >= 0){
				LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(msisdn, "Thank you "+model.getName()+". The birthday message has been updated successfully. God bless you.", model.getChurch());
				sendSMS.send();
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	private void saveAndBroadcastMessage(MemberModel model, String message, String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into sms (message,display,church_id,created_ts,created_by,approved_by,modified_by,is_editable,is_bulk,sms_length,type,msisdn,groups,status,orig) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, message);
			prstmt.setString(2, model.getChurch() != null ? model.getChurch().getDisplay() : "c. cradle");
			prstmt.setInt(3, model.getChurchId());
			prstmt.setString(4, Utils.getTodayDateTime());
			prstmt.setInt(5, model.getId());
			prstmt.setInt(6, model.getId());
			prstmt.setInt(7, model.getId());
			prstmt.setString(8, "F");
			prstmt.setString(9, "Y");
			prstmt.setInt(10, 0);
			prstmt.setString(11, "G");
			prstmt.setString(12, "");
			prstmt.setString(13, "0");
			prstmt.setString(14, "A");
			prstmt.setString(15, "C");

			int success = prstmt.executeUpdate();
			if(success >= 0){

				//Send Broadcast SMS

				LocalSMSDispatchObject sendSMS = new LocalSMSDispatchObject(Utils.getStringifiedMsisdn(getAllMsisdnFromMembers(model.getChurchId()), ","), message, model.getChurch());
				sendSMS.send();

				LocalSMSDispatchObject ackSMS = new LocalSMSDispatchObject(msisdn, model.getName()+", broadcast message sent successfully. Thank you.", model.getChurch());
				ackSMS.send();
			}



		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	private String getAllMsisdnFromMembers(int churchId){
		TreeSet<String> msisdnSet = new TreeSet<String>();

		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select msisdn from members where church_id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					String msisdn = results.getString("msisdn");
					msisdnSet.add(msisdn);
				}

			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return Utils.getStringifiedMsisdn(msisdnSet);
	}

	private boolean isPowerLeader(MemberModel model){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id from power_leaders where member_id = ? and church_id = ? and status = 'A'");
			prstmt.setInt(1, model.getId());
			prstmt.setInt(2, model.getChurchId());

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");

					if(id > 0){

						return true;
					}
				}

				return false;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}

	private boolean isHelpMessage(String message){
		if(message.trim().equalsIgnoreCase(AppConstants.HELP)){
			return true;
		}

		return false;
	}

	private boolean isTitheMessage(String message){
		if(message.trim().equalsIgnoreCase(AppConstants.TITHE) || message.trim().equalsIgnoreCase(AppConstants.SMS_TITHES) || message.trim().equalsIgnoreCase(AppConstants.TITHS) || message.trim().equalsIgnoreCase(AppConstants.TITH) || message.trim().equalsIgnoreCase(AppConstants.TT)){
			return true;
		}
		return false;
	}

	private boolean isClassMessage(String message){
		if(message.trim().equalsIgnoreCase(AppConstants.CLASS) || message.trim().equalsIgnoreCase(AppConstants.CLAS) || message.trim().equalsIgnoreCase(AppConstants.CLASE) || message.trim().equalsIgnoreCase(AppConstants.CLS)  || message.trim().equalsIgnoreCase(AppConstants.CL) || message.trim().equalsIgnoreCase(AppConstants.CLASES) || message.trim().equalsIgnoreCase(AppConstants.SMS_CLASSES)){
			return true;
		}
		return false;
	}

	private boolean isClassBroadcast(String message){
		String[] messageTokens = message.split("[\\s]+");
		if(messageTokens.length >= 2 && (messageTokens[0].trim().equalsIgnoreCase(AppConstants.CLASS) || messageTokens[0].trim().equalsIgnoreCase(AppConstants.CLAS) || messageTokens[0].trim().equalsIgnoreCase(AppConstants.CLS))){
			return true;
		}

		return false;
	}

	private MemberModel getClassLeader(int memberId, int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,fname,lname,msisdn,class_id from members where id = ? and church_id = ? and status = 'A'");
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("fname")+" "+results.getString("lname");
					int classLeaderId = results.getInt("class_id");
					String msisdn = results.getString("msisdn");
					ChurchModel church = getChurchModel(churchId);

					MemberModel model = new MemberModel(id, name, msisdn, churchId, classLeaderId);
					model.setChurch(church);

					return model;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	
	private boolean isClassLeader(int memberId, int churchId){
		
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id from classes where leader_id = ? and church_id = ? and status = 'A'");
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					results.getInt("id");

					return true;
				}
				return false;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
		return false;
	}

	private MemberModel getMemberDetails(String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,fname,msisdn,church_id,class_id from members where msisdn = ? and status = 'A'");
			prstmt.setString(1, msisdn);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("fname");
					int churchId = results.getInt("church_id");
					int classLeaderId = results.getInt("class_id");
					ChurchModel churchModel = getChurchModel(churchId);

					MemberModel model = new MemberModel(id, name, msisdn, churchId, classLeaderId);
					model.setChurch(churchModel);
					
					return model;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	
	private ChurchModel getChurchModel(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,display,modules,expire_date,price_per_sms,annual_fee,sms_left,payment_id from churches where id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String churchName = results.getString("name");
					String display = results.getString("display");
					String modules = results.getString("modules");
					String expireDate = results.getString("expire_date");
					double smsCost = results.getDouble("price_per_sms");
					double annualCost = results.getDouble("annual_fee");
					double smsLeft = results.getDouble("sms_left");
					int paymentId = results.getInt("payment_id");

					ChurchModel model = new ChurchModel();
					model.setId(id);
					model.setName(churchName);
					model.setDisplay(display);
					model.setModules(modules);
					model.setAnnualFee(annualCost);
					model.setExpireDate(expireDate);
					model.setPricePerSMS(smsCost);
					model.setSmsLeft(smsLeft);
					model.setPaymentId(paymentId);

					return model;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	private ArrayList<MemberModel> getAllMembers(int classLeaderId, int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,fname,msisdn from members where class_id = ? and church_id = ? and status = 'A'");
			prstmt.setInt(1, classLeaderId);
			prstmt.setInt(2, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<MemberModel> modelList = new ArrayList<MemberModel>();
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("fname");
					String msisdn = results.getString("msisdn");
					
					ChurchModel churchModel = getChurchModel(churchId);

					MemberModel model = new MemberModel(id, name, msisdn, churchId, classLeaderId);
					model.setChurch(churchModel);
					
					modelList.add(model);

				}
				
				return modelList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return new ArrayList<MemberModel>();
	}
	
	public ArrayList<Integer> getMessageIdList() {
		return messageIdList;
	}

}
