package com.beta.rsatech.churchcradle.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.beta.rsatech.churchcradle.client.UpdateService;
import com.beta.rsatech.churchcradle.server.utils.DBConnection;
import com.beta.rsatech.churchcradle.server.utils.SMSDispatchObject;
import com.beta.rsatech.churchcradle.server.utils.ServerGlobals;
import com.beta.rsatech.churchcradle.server.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.AppStatsModel;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.beta.rsatech.churchcradle.shared.DonationModel;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class UpdateServiceImpl extends RemoteServiceServlet implements UpdateService{
	private static Connection con = DBConnection.getConnection();

	@Override
	public boolean approveMarriage(int userId, MarriageModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("update m_announcements set status = 'A', is_editable = 'F', approved_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Marriage] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveFuneral(int userId, FAnnounceModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("update f_announcements set status = 'A', is_editable = 'F', approved_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Funeral] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveEvent(int userId, EAnnounceModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update e_announcements set status = 'A', is_editable = 'F', approved_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Event] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveSMS(int userId, SMSModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update sms set status = 'A', is_editable = 'F', approved_by = ?, modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, userId);
			prstmt.setInt(3, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[SMS] success is "+success);
			if(success >= 0){
				//con.close();
				pushSMSForSMS(model.getId());
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	private void pushSMSForSMS(int smsId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select message,type,msisdn,groups,church_id from sms where id = ?");
			prstmt.setInt(1, smsId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					String smsMsisdn = "";

					int churchId = results.getInt("church_id");
					String message = results.getString("message");
					String type = results.getString("type");
					String groups = results.getString("groups");
					String msisdn = results.getString("msisdn");

					if(type.equalsIgnoreCase(AppConstants.GROUPS)){
						if(groups.equalsIgnoreCase("0")){
							smsMsisdn = getAllMsisdnFromMembers(churchId);
						}else{
							smsMsisdn = getAllMsisdnFromGroups(groups, churchId, ",");
						}
					}else if(type.equalsIgnoreCase(AppConstants.MSISDN)){
						smsMsisdn = Utils.getStringifiedMsisdn(msisdn, ",");
					}

					SMSDispatchObject smsSend = new SMSDispatchObject(smsMsisdn, message);
					smsSend.send();
				}
				//con.close();
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	private void pushSMSForTithe(int titheId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.msisdn as msisdn,mem.fname as firstname,tit.amount as amount,month(tit.date) as date from members as mem, tithes as tit where tit.id = ? and tit.member_id = mem.id and tit.church_id = mem.church_id");
			prstmt.setInt(1, titheId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					String msisdn = results.getString("msisdn");
					double amount = results.getDouble("amount");
					String firstname = results.getString("firstname");
					String month = Utils.getMonthInWords(results.getString("date"));

					SMSDispatchObject smsSend = new SMSDispatchObject(Utils.getStringifiedMsisdn(msisdn, ""), Utils.getTitheGeneratedMessage(month, firstname, amount));
					smsSend.send();
				}
				//con.close();
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	private void pushSMSForMember(int memberId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.msisdn as msisdn,mem.fname as firstname,mem.lname as lastname,churches.name as church from members as mem, churches where mem.id = ? and mem.church_id = churches.id");
			prstmt.setInt(1, memberId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					String msisdn = results.getString("msisdn");
					String lastname = results.getString("lastname");
					String firstname = results.getString("firstname");
					String church = results.getString("church");

					SMSDispatchObject smsSend = new SMSDispatchObject(Utils.getStringifiedMsisdn(msisdn, ""), Utils.getMemberGeneratedMessage(firstname, lastname, msisdn, church));
					smsSend.send();
				}
				//con.close();
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
				//con.close();
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
		return Utils.getStringifiedMsisdn(msisdnSet);
	}

	private String getAllMsisdnFromGroups(String groups, int churchId, String groupDelimiter){
		String[] groupToken = groups.split(groupDelimiter);

		TreeSet<String> msisdnSet = new TreeSet<String>();
		for(String groupId : groupToken){
			PreparedStatement prstmt = null;
			con = DBConnection.getConnection();

			try{
				prstmt = (PreparedStatement) con.prepareStatement("select msisdn,organisations from members where church_id = ? and status = 'A' and organisations regexp ?");
				prstmt.setInt(1, churchId);
				prstmt.setString(2, Utils.getRegexp(",", groupId));

				ResultSet results = prstmt.executeQuery();
				if(results != null){
					while(results.next()){
						if(!Utils.getTokenList(",", results.getString("organisations")).contains(Integer.parseInt(groupId))){
							continue;
						}

						String msisdn = results.getString("msisdn");
						msisdnSet.add(msisdn);
					}
					//con.close();
				}
			}catch(SQLException sql){
				sql.printStackTrace();
			}
		}

		return Utils.getStringifiedMsisdn(msisdnSet);
	}

	@Override
	public boolean approveTithe(int userId, TitheModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update tithes set status = 'A', is_editable = 'F', approved_by = ?, modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, userId);
			prstmt.setInt(3, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Tithes] success is "+success);
			if(success >= 0){
				//con.close();
				pushSMSForTithe(model.getId());
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveOffering(int userId, OfferingModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update offerings set status = 'A', is_editable = 'F', approved_by = ?, modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, userId);
			prstmt.setInt(3, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Offering] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveBibleReading(int userId, BibleReadingModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update bible_readings set status = 'A', is_editable = 'F', approved_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Bible Readings] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveDonation(int userId, DonationModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update donations set status = 'A', is_editable = 'F', approved_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Donation] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveMember(int userId, MemberModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update members set status = 'A', approved_by = ?, modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, userId);
			prstmt.setInt(3, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Member] success is "+success);
			if(success >= 0){
				//con.close();
				pushSMSForMember(model.getId());
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean updateMember(int userId, MemberModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update members set status = 'A', fname = ?, lname = ?, is_admin = ?, can_sms = ?, modified_by = ?, entry_modules = ?, class_id = ?, approve_modules = ?, organisations = ?, occupation = ?, address = ?, dob = ?, gender = ?, employer = ?, avatar = ?, can_view_payments = ?, prefs = ? where id = ?");
			prstmt.setString(1, model.getFirstName());
			prstmt.setString(2, model.getLastName());
			prstmt.setString(3, model.isAdmin()?"Y":"N");
			prstmt.setString(4, model.isCanSMS()?"Y":"N");
			prstmt.setInt(5, userId);
			prstmt.setString(6, model.getEntryModules());
			prstmt.setInt(7, model.getClassId());
			prstmt.setString(8, model.getApproveModules());
			prstmt.setString(9, Utils.getFormattedOrganisation(model.getOrganisations()));
			prstmt.setString(10, model.getOccupation());
			prstmt.setString(11, model.getAddress());
			prstmt.setString(12, model.getDateOfBirth());
			prstmt.setString(13, model.getGender());
			prstmt.setString(14, model.getEmployer());
			prstmt.setString(15, model.getAvatar());
			prstmt.setString(16, model.isCanViewPayments()?"Y":"N");
			prstmt.setString(17, model.getOtherInfo());

			prstmt.setInt(18, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Member update] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectMarriage(int userId, MarriageModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update m_announcements set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectMarriage] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectFuneral(int userId, FAnnounceModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update f_announcements set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectFuneral] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectEvent(int userId, EAnnounceModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update e_announcements set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectEvent] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectSMS(int userId, SMSModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update sms set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectSMS] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectTithe(int userId, TitheModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update tithes set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectMarriage] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectOffering(int userId, OfferingModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update offerings set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectOfferings] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectBibleReading(int userId, BibleReadingModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update bible_readings set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectBibleReadings] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectDonation(int userId, DonationModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update donations set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectDonation] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectMember(int userId, MemberModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update members set status = 'D', modified_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectMember] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean approveSpecialOffering(int userId, SpecialOfferingModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update special_offerings set status = 'A', approved_by = ? where id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[Special Offering] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean rejectSpecialOffering(int userId, SpecialOfferingModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update special_offerings set status = 'D' where id = ?");
			prstmt.setInt(1, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[rejectSpecialOfferings] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean updateMemberPassword(int memberId, int churchId,
			String password) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update members set password = ? where id = ? and church_id = ? and status = 'A'");
			prstmt.setString(1, Utils.getSHA256(password));
			prstmt.setInt(2, memberId);
			prstmt.setInt(3, churchId);

			int success = prstmt.executeUpdate();
			System.out.println("[password set] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean updateForgotMemberPassword(int memberId, String password) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update members set password = ? where id = ? and status = 'A'");
			prstmt.setString(1, Utils.getSHA256(password));
			prstmt.setInt(2, memberId);

			int success = prstmt.executeUpdate();
			System.out.println("[password set] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean setModuleBaseUrl(String moduleBaseUrl) {
		if(moduleBaseUrl != null){
			ServerGlobals.getInstance().setModuleBaseUrl(moduleBaseUrl);
			return true;
		}
		return false;
	}

	@Override
	public boolean removePowerLeader(int memberId, int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			

			prstmt = (PreparedStatement) con.prepareStatement("update power_leaders set status = 'D' where id = ? and church_id = ?");
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);

			int success = prstmt.executeUpdate();
			System.out.println("[Power Leader] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean removeMember(int userId, int churchId, int memberId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{

			prstmt = (PreparedStatement) con.prepareStatement("update members set status = 'D', modified_by = ? where id = ? and church_id = ?");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, memberId);
			prstmt.setInt(3, churchId);

			int success = prstmt.executeUpdate();
			System.out.println("[delete Member] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

	@Override
	public void deleteOrphanBlob(String tmpBlobKey) {
	    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

		BlobKey blobKey = new BlobKey(tmpBlobKey);
		blobstoreService.delete(blobKey);
	}
	
	@Override
	public boolean updateAppStats(int userId, AppStatsModel model) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();
		try{
			prstmt = (PreparedStatement) con.prepareStatement("update churches set birth_push = ?, tithe_push = ?, modified_by = ? where id = ? and status = 'A'");
			prstmt.setString(1, model.isBirthdayPush()?"Y":"N");
			prstmt.setString(2, model.isTithePush()?"Y":"N");
			prstmt.setInt(3, userId);
			prstmt.setInt(4, model.getChurchId());

			int success = prstmt.executeUpdate();
			System.out.println("[AppStats updated] success is "+success);
			if(success >= 0){
				//con.close();
				return true;
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
		return false;
	}

}
