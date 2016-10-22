package com.beta.rsatech.churchcradle.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.beta.rsatech.churchcradle.client.AddService;
import com.beta.rsatech.churchcradle.server.utils.DBConnection;
import com.beta.rsatech.churchcradle.server.utils.ServerGlobalResources;
import com.beta.rsatech.churchcradle.server.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.beta.rsatech.churchcradle.shared.ClassModel;
import com.beta.rsatech.churchcradle.shared.DonationModel;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.beta.rsatech.churchcradle.shared.GroupModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.beta.rsatech.churchcradle.shared.PowerLeaderModel;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AddServiceImpl extends RemoteServiceServlet implements AddService{
	private static Connection con = DBConnection.getConnection();

	@Override
	public int addGroup(GroupModel model, int memberId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			int accountId = getAccountId(memberId, model.getChurchId());
			if(accountId > 0){
				prstmt = (PreparedStatement) con.prepareStatement("insert into organisations (name,account_id,leader_id,church_id) values (?,?,?,?) ");
				prstmt.setString(1, Utils.getCapitalizedWord(model.getName()));
				prstmt.setInt(2, accountId);
				prstmt.setInt(3, model.getLeaderId());
				prstmt.setInt(4, model.getChurchId());

				int success = prstmt.executeUpdate();
				if(success > 0){
					//con.close();
					return success;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getAccountId(int userId, int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into accounts (balance,arreas,modified_by,church_id) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			prstmt.setInt(1, 0);
			prstmt.setInt(2, 0);
			prstmt.setInt(3, userId);
			prstmt.setInt(4, churchId);

			int success = prstmt.executeUpdate();
			if(success >= 0){
				ResultSet tmpResultSet = prstmt.getGeneratedKeys();
				while(tmpResultSet.next()){
					return tmpResultSet.getInt(1);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addSMS(SMSModel model, int memberId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("insert into sms (message,display,church_id,created_ts,created_by,modified_by,is_editable,is_bulk,sms_length,approved_by,status,type,groups,msisdn) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, model.getMessage());
			prstmt.setString(2, model.getDisplay());
			prstmt.setInt(3, model.getChurchId());
			prstmt.setString(4, Utils.getTodayDateTime());
			prstmt.setInt(5, memberId);
			prstmt.setInt(6, memberId);
			prstmt.setString(7, "F");
			prstmt.setString(8, model.isBulk()?"Y":"N");
			prstmt.setInt(9, Utils.getSMSLength(model.getMessage()));
			prstmt.setInt(10, 0);
			prstmt.setString(11, AppConstants.PENDING);
			prstmt.setString(12, model.getType());
			prstmt.setString(13, model.getGroups() != null ? model.getGroups() : "");
			prstmt.setString(14, model.getMsisdn() != null ? model.getMsisdn() : "");

			int success = prstmt.executeUpdate();
			if(success >= 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getBillingId(int userId, int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into billings (balance,modified_by,church_id) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			prstmt.setInt(1, 0);
			prstmt.setInt(2, userId);
			prstmt.setInt(3, churchId);

			int success = prstmt.executeUpdate();
			if(success >= 0){
				ResultSet tmpResultSet = prstmt.getGeneratedKeys();
				while(tmpResultSet.next()){
					return tmpResultSet.getInt(1);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addMember(MemberModel model, int memberId) {
		PreparedStatement prstmt = null;

		try{
			int billingId = getBillingId(memberId, model.getChurchId());
			if(billingId > 0){
				
				if(isMemberAddLimitReached(ServerGlobalResources.getInstance().getChurchModel().getId())){
					return 0;
				}
				
				con = DBConnection.getConnection();
				
				prstmt = (PreparedStatement) con.prepareStatement("insert into members (fname,lname,email,msisdn,avatar,password,church_id,billing_id,is_admin,can_sms,created_ts,approved_by,modified_by,created_by,approve_modules,class_id,organisations,tithes,occupation,address,dob,gender,prefs,employer,status,entry_modules,can_view_payments,commence_year) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				prstmt.setString(1, Utils.getCapitalizedWord(model.getFirstName()));
				prstmt.setString(2, Utils.getCapitalizedWord(model.getLastName()));
				prstmt.setString(3, model.getEmail() == null ? "" : model.getEmail());
				prstmt.setString(4, model.getMsisdn());
				prstmt.setString(5, model.getAvatar());
				prstmt.setString(6, Utils.getSHA256(model.getMsisdn()));
				prstmt.setInt(7, model.getChurchId());
				prstmt.setInt(8, billingId);
				prstmt.setString(9, model.isAdmin()?"Y":"N");
				prstmt.setString(10, model.isCanSMS()?"Y":"N");
				prstmt.setString(11, Utils.getTodayDateTime());
				prstmt.setInt(12, 0);
				prstmt.setInt(13, memberId);
				prstmt.setInt(14, memberId);
				prstmt.setString(15, model.getApproveModules() == null ? "" : model.getApproveModules());
				prstmt.setInt(16, model.getClassId());
				prstmt.setString(17, getFormattedOrganisation(model.getOrganisations()));
				prstmt.setString(18, "");
				prstmt.setString(19, model.getOccupation() == null || model.getOccupation().trim().isEmpty() ? "" : Utils.getCapitalizedWord(model.getOccupation()));
				prstmt.setString(20, model.getAddress());
				prstmt.setString(21, model.getDateOfBirth());
				prstmt.setString(22, model.getGender());
				prstmt.setString(23, model.getOtherInfo() != null ? model.getOtherInfo() : "");
				prstmt.setString(24, model.getEmployer() == null || model.getEmployer().trim().isEmpty() ? "" : Utils.getCapitalizedWord(model.getEmployer()));
				prstmt.setString(25, "P");
				prstmt.setString(26, model.getEntryModules());
				prstmt.setString(27, model.isCanViewPayments()?"Y":"N");
				prstmt.setString(28, model.getDateOfCommencement());
				

				int success = prstmt.executeUpdate();
				if(success > 0){
					//con.close();
					return success;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}
	
	private String getFormattedOrganisation(String org){
		if(org != null && !org.trim().isEmpty()){
			return org+","+ServerGlobalResources.getInstance().getChurchModel().getDefaultGroupId();
		}else if(org != null && org.trim().isEmpty()){
			return ServerGlobalResources.getInstance().getChurchModel().getDefaultGroupId()+"";
		}
		
		return ServerGlobalResources.getInstance().getChurchModel().getDefaultGroupId()+"";
	}
	
	private boolean isMemberAddLimitReached(int churchId){
		double allMembers = Utils.getAllMembersCount(churchId);

		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select member_limit from churches where id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					double memberLimit = results.getDouble("member_limit");
					if(memberLimit > allMembers){
						//con.close();
						return false;
					}
				}
				//con.close();
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return true;
	}

	@Override
	public int addMarriage(MarriageModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into m_announcements (description,m_name,f_name,m_occupation,f_occupation,time,church_id,date,m_avatar,f_avatar,approved_by,created_by,created_ts,venue,status,modified_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, model.getDescription() == null ? "" : model.getDescription());
			prstmt.setString(2, Utils.getCapitalizedWord(model.getManName()));
			prstmt.setString(3, Utils.getCapitalizedWord(model.getFemaleName()));
			prstmt.setString(4, model.getmOccupation() == null || model.getmOccupation().trim().isEmpty() ? "" :Utils.getCapitalizedWord(model.getmOccupation()));
			prstmt.setString(5, model.getfOccupation() == null || model.getfOccupation().trim().isEmpty() ? "" :Utils.getCapitalizedWord(model.getfOccupation()));
			prstmt.setString(6, "12 pm");
			prstmt.setInt(7, model.getChurchId());
			prstmt.setString(8, Utils.getTodayDateTime());
			prstmt.setString(9, model.getmAvatar());
			prstmt.setString(10, model.getfAvatar());
			prstmt.setInt(11, 0);
			prstmt.setInt(12, userId);
			prstmt.setString(13, Utils.getTodayDateTime());
			prstmt.setString(14, Utils.getCapitalizedWord(model.getVenue()));
			prstmt.setString(15, "P");
			prstmt.setInt(16, 0);

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addFuneral(FAnnounceModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into f_announcements (name,description,age,date,time,venue,church_id,created_ts,modified_by,approved_by,avatar,status,is_editable) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, Utils.getCapitalizedWord(model.getName()));
			prstmt.setString(2, model.getDescription());
			prstmt.setInt(3, model.getAge());
			prstmt.setString(4, model.getDate());
			prstmt.setString(5, "12 pm");
			prstmt.setString(6, Utils.getCapitalizedWord(model.getVenue()));
			prstmt.setInt(7, model.getChurchId());
			prstmt.setString(8, Utils.getTodayDateTime());
			prstmt.setInt(9, userId);
			prstmt.setInt(10, 0);
			prstmt.setString(11, model.getAvatar());
			prstmt.setString(12, "P");
			prstmt.setString(13, "T");

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addEvent(EAnnounceModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into e_announcements (description,date,time,venue,church_id,created_ts,modified_by,approved_by,status,is_editable,created_by) values (?,?,?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, model.getDescription());
			prstmt.setString(2, model.getDate());
			prstmt.setString(3, model.getTime());
			prstmt.setString(4, Utils.getCapitalizedWord(model.getVenue()));
			prstmt.setInt(5, model.getChurchId());
			prstmt.setString(6, Utils.getTodayDateTime());
			prstmt.setInt(7, userId);
			prstmt.setInt(8, 0);
			prstmt.setString(9, "P");
			prstmt.setString(10, "T");
			prstmt.setInt(11, userId);

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addOffering(OfferingModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into offerings (description,church_id,created_ts,modified_by,approved_by,status,is_editable,created_by,amount) values (?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, model.getDescription());
			prstmt.setInt(2, model.getChurchId());
			prstmt.setString(3, Utils.getTodayDateTime());
			prstmt.setInt(4, userId);
			prstmt.setInt(5, 0);
			prstmt.setString(6, "P");
			prstmt.setString(7, "Y");
			prstmt.setInt(8,  userId);
			prstmt.setDouble(9, model.getAmount());

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addTithe(TitheModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into tithes (member_id,created_by,amount,created_ts,date,approved_by,modified_by,church_id,status,is_editable) values (?,?,?,?,?,?,?,?,?,?)");
			prstmt.setInt(1, model.getMemberId());
			prstmt.setInt(2, userId);
			prstmt.setDouble(3, model.getAmount());
			prstmt.setString(4, Utils.getTodayDateTime());
			prstmt.setString(5, Utils.getTodayDateTime());
			prstmt.setInt(6, 0);
			prstmt.setInt(7, 0);
			prstmt.setInt(8,  model.getChurchId());
			prstmt.setString(9, "P");
			prstmt.setString(10, "T");

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addDonation(DonationModel model, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addClassLeader(ClassModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			int accountId = getAccountId(userId, model.getChurchId());
			if(accountId > 0){
				prstmt = (PreparedStatement) con.prepareStatement("insert into classes (account_id,leader_id,church_id) values (?,?,?) ");
				prstmt.setInt(1, accountId);
				prstmt.setInt(2, model.getLeaderId());
				prstmt.setInt(3, model.getChurchId());

				int success = prstmt.executeUpdate();
				if(success > 0){
					//con.close();
					return success;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addSpecialOffering(SpecialOfferingModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into special_offerings (message,church_id,created_date,approved_by,status,created_by,amount,member_id) values (?,?,?,?,?,?,?,?)");
			prstmt.setString(1, model.getMessage());
			prstmt.setInt(2, model.getChurchId());
			prstmt.setString(3, Utils.getTodayDate());
			prstmt.setInt(4, 0);
			prstmt.setString(5, "P");
			prstmt.setInt(6,  userId);
			prstmt.setDouble(7, model.getAmount());
			prstmt.setInt(8, model.getMemberId());

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addBibleReading(BibleReadingModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into bible_readings (name,theme,description,date,church_id,created_ts,modified_by,approved_by,status,is_editable,created_by) values (?,?,?,?,?,?,?,?,?,?,?)");
			prstmt.setString(1, model.getName());
			prstmt.setString(2, model.getTheme());
			prstmt.setString(3, model.getDescription());
			prstmt.setString(4, model.getDate());
			prstmt.setInt(5, model.getChurchId());
			prstmt.setString(6, Utils.getTodayDateTime());
			prstmt.setInt(7, userId);
			prstmt.setInt(8, 0);
			prstmt.setString(9, "P");
			prstmt.setString(10, "T");
			prstmt.setInt(11, userId);

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addPowerLeader(PowerLeaderModel model, int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into power_leaders (member_id,created_by,church_id,status) values (?,?,?,?) ");
			prstmt.setInt(1, model.getMemberId());
			prstmt.setInt(2, userId);
			prstmt.setInt(3, model.getChurchId());
			prstmt.setString(4, "A");

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return success;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

}
