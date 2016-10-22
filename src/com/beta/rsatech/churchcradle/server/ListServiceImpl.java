package com.beta.rsatech.churchcradle.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.ListService;
import com.beta.rsatech.churchcradle.server.utils.DBConnection;
import com.beta.rsatech.churchcradle.server.utils.MiscStorage;
import com.beta.rsatech.churchcradle.server.utils.ServerGlobalResources;
import com.beta.rsatech.churchcradle.server.utils.Utils;
import com.beta.rsatech.churchcradle.server.utils.currency.parser.CurrencyKeeper;
import com.beta.rsatech.churchcradle.shared.AppStatsModel;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.beta.rsatech.churchcradle.shared.BirthdayModel;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.ChurchModel;
import com.beta.rsatech.churchcradle.shared.DateModel;
import com.beta.rsatech.churchcradle.shared.DonationModel;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.beta.rsatech.churchcradle.shared.ForgotPasswordModel;
import com.beta.rsatech.churchcradle.shared.GroupInfoModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.MyOfferingModel;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.beta.rsatech.churchcradle.shared.PowerLeaderModel;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.beta.rsatech.churchcradle.shared.ThirdPartyPaymentModel;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ListServiceImpl extends RemoteServiceServlet implements ListService{

	private static Connection con = DBConnection.getConnection();
	private Statement stmt;
	private int weekNumber, year;

	public ListServiceImpl(){
		initDefaults();
	}

	private void initDefaults(){
		weekNumber = getWeekNumber();
		year = getYear();
	}

	@Override
	public UserModel getUser(String username, String password, boolean isAdmin) {

		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();
		try{

			if(Utils.isUsernameMsisdn(username)){
				if(!username.contains("+")){
					username = "+" + username;
				}
				prstmt = (PreparedStatement) con.prepareStatement("select id,email,msisdn,church_id from members where msisdn = ? and password = ?");
			}else{
				prstmt = (PreparedStatement) con.prepareStatement("select id,email,msisdn,church_id from members where email = ? and password = ?");
			}

			prstmt.setString(1, username);
			prstmt.setString(2, Utils.getSHA256(password));
			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String email = results.getString("email");
					String msisdn = results.getString("msisdn");
					int churchId = results.getInt("church_id");
					ServerGlobalResources.getInstance().setChurchModel(getChurchModel(churchId));
					ServerGlobalResources.getInstance().setPaymentModel(getPaymentModel(ServerGlobalResources.getInstance().getChurchModel().getPaymentId()));
					ServerGlobalResources.getInstance().setOwnPaymentModel(getOwnerPaymentModel());

					UserModel model = new UserModel();
					model.setId(id);
					model.setEmail(email);
					model.setMsisdn(msisdn);

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
	public UserModel getFullUser(int userId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,fname,lname,email,msisdn,employer,church_id,billing_id,is_admin,can_sms,can_view_payments,created_ts,modified_ts,modified_by,approve_modules,entry_modules,class_id,organisations,tithes,avatar,occupation,address,dob,gender,prefs from members where id = ?");
			prstmt.setInt(1, userId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String fname = results.getString("fname");
					String lname = results.getString("lname");
					String email = results.getString("email");
					String msisdn = results.getString("msisdn");
					int churchId = results.getInt("church_id");
					int billingId = results.getInt("billing_id");
					boolean isAdmin = results.getString("is_admin").equalsIgnoreCase("Y");
					boolean canSMS = results.getString("can_sms").equalsIgnoreCase("Y");
					String createdTs = results.getString("created_ts");
					String modifiedTs = results.getString("modified_ts");
					int modifiedBy = results.getInt("modified_by");
					int classId = results.getInt("class_id");
					String approveModules = results.getString("approve_modules");
					String entryModules = results.getString("entry_modules");
					String organisations = results.getString("organisations");
					String tithes = results.getString("tithes");
					String occupation = results.getString("occupation");
					String address = results.getString("address");
					String dateOfBirth = results.getString("dob");
					String gender = results.getString("gender");
					String employer = results.getString("employer");
					String avatar = results.getString("avatar");
					boolean canViewPayments = results.getString("can_view_payments").equalsIgnoreCase("Y");
					//System.out.println("id "+id+", name "+user+", level "+level+", email "+email+", msisdn "+msisdn);

					UserModel model = new UserModel();
					model.setId(id);
					model.setFirstName(fname);
					model.setLastName(lname);
					model.setEmail(email);
					model.setMsisdn(msisdn);
					model.setChurchId(churchId);
					model.setBillingId(billingId);
					model.setAdmin(isAdmin);
					model.setSMSEnabled(canSMS);
					model.setCreatedTs(createdTs);
					model.setModifiedTs(modifiedTs);
					model.setModifiedBy(modifiedBy);
					model.setClassId(classId);
					model.setApproveModules(approveModules);
					model.setEntryModules(entryModules);
					model.setGroups(organisations);
					model.setTithes(tithes);
					model.setOccupation(occupation);
					model.setAddress(address);
					model.setDateOfBirth(dateOfBirth);
					model.setGender(gender);
					model.setEmployer(employer);
					model.setAvatar(avatar);
					model.setCanViewPayments(canViewPayments);

					return model;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}		return null;
	}

	@Override
	public HashMap<Integer, String> getAllOrganisationMap(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name from organisations where church_id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> orgMap = new HashMap<Integer, String>();
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("name");

					orgMap.put(id, name);
				}
				//con.close();
				return orgMap;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}
	
	@Override
	public HashMap<Integer, String> getAllOrganisationMapWithLeaders(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name from organisations where church_id = ? and status = 'A' and leader_id > 0");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> orgMap = new HashMap<Integer, String>();
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("name");

					orgMap.put(id, name);
				}
				//con.close();
				return orgMap;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<MemberModel> getAllMembersList(int churchId, int groupId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.prefs,mem.commence_year,mem.id,mem.can_view_payments,mem.fname,mem.lname,mem.avatar,mem.approved_by,mem.entry_modules,mem.created_by,mem.email,mem.dob,mem.msisdn,mem.tithes,mem.modified_by,mem.modified_ts,mem.organisations,date(mem.created_ts) as created_ts,mem.occupation,mem.address,mem.employer,mem.gender,mem.approve_modules,mem.class_id,mem.billing_id,mem.church_id,mem.is_admin,mem.can_sms from members as mem where mem.church_id = ? and mem.status = 'A' and mem.organisations regexp ? order by mem.fname asc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, Utils.getRegexp(",", groupId));

			System.out.println("Regexp : "+Utils.getRegexp(",", groupId));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<MemberModel> members = new ArrayList<MemberModel>();
				while(results.next()){
					System.out.println(Utils.getTokenList(",", results.getString("organisations")).toString());
					if(!Utils.getTokenList(",", results.getString("organisations")).contains(groupId)){
						continue;
					}

					int id = results.getInt("id");
					String fname = results.getString("fname");
					String lname = results.getString("lname");
					String email = results.getString("email");
					String msisdn = results.getString("msisdn");
					String avatar = results.getString("avatar");
					String organisations = results.getString("organisations");
					int classId = results.getInt("class_id");
					int createdBy = results.getInt("created_by");
					int billingId = results.getInt("billing_id");
					String approveModules = results.getString("approve_modules");
					String entryModules = results.getString("entry_modules");
					String tithes = results.getString("tithes");
					String occupation = results.getString("occupation");
					String address = results.getString("address");
					String gender = results.getString("gender");
					String employer = results.getString("employer");
					boolean isAdmin = results.getString("is_admin").equalsIgnoreCase("Y");
					boolean canSMS = results.getString("can_sms").equalsIgnoreCase("Y");
					String createdTs = results.getString("created_ts");
					String dateOfBirth = results.getString("dob");
					int modifiedBy = results.getInt("modified_by");
					String modifiedTs = results.getString("modified_ts");
					int approvedBy = results.getInt("approved_by");
					boolean canViewPayments = results.getString("can_view_payments").equalsIgnoreCase("Y");
					String commentYear = results.getString("commence_year");
					String otherInfo = results.getString("prefs");

					MemberModel member = new MemberModel();
					member.setId(id);
					member.setCreatedBy(createdBy);
					member.setFirstName(fname);
					member.setLastName(lname);
					member.setEmail(email);
					member.setMsisdn(msisdn);
					member.setOccupation(occupation);
					member.setOrganisations(organisations);
					member.setAddress(address);
					member.setEmployer(employer);
					member.setGender(gender);
					member.setClassId(classId);
					member.setTithes(tithes);
					member.setAdmin(isAdmin);
					member.setCanSMS(canSMS);
					member.setBillingId(billingId);
					member.setApproveModules(approveModules);
					member.setEntryModules(entryModules);
					member.setCreatedTs(createdTs);
					member.setDateOfBirth(dateOfBirth);
					member.setModifiedTs(modifiedTs);
					member.setModifiedBy(modifiedBy);
					member.setApprovedBy(approvedBy);
					member.setAvatar(avatar);
					member.setCanViewPayments(canViewPayments);
					member.setDateOfCommencement(commentYear);
					member.setOtherInfo(otherInfo);

					members.add(member);
				}
				//con.close();
				return members;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<Integer, String> getAllApproveModulesMap(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select am.id,modules.name,modules.expire_date from approve_modules as am, modules where am.church_id = ? and am.module_id = modules.id and am.status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> approveMap = new HashMap<Integer, String>();
				while(results.next()){
//					if(!Utils.isAppValid(false, results.getString("expire_date"))){
//						continue;
//					}
					int id = results.getInt("id");
					String name = results.getString("name");

					approveMap.put(id, name);
				}
				//con.close();
				return approveMap;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public HashMap<Integer, String> getAllEntryModulesMap(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select em.id,modules.name,modules.expire_date from entry_modules as em, modules where em.church_id = ? and em.module_id = modules.id and em.status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> approveMap = new HashMap<Integer, String>();
				while(results.next()){
//					if(!Utils.isAppValid(false, results.getString("expire_date"))){
//						continue;
//					}
					int id = results.getInt("id");
					String name = results.getString("name");

					approveMap.put(id, name);
				}
				//con.close();
				return approveMap;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public HashMap<Integer, String> getAllClassLeadersMap(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select classes.leader_id, members.fname, members.lname from members, classes where classes.church_id = ? and classes.leader_id = members.id and classes.status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> classesMap = new HashMap<Integer, String>();
				while(results.next()){
					int id = results.getInt("leader_id");
					String firstName = results.getString("fname");
					String lastName = results.getString("lname");


					classesMap.put(id, firstName+" "+lastName);
				}
				//con.close();
				return classesMap;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public HashMap<Integer, String> getAllMembersMap(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,fname,lname from members where church_id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> classesMap = new HashMap<Integer, String>();
				while(results.next()){
					int id = results.getInt("id");
					String firstName = results.getString("fname");
					String lastName = results.getString("lname");


					classesMap.put(id, firstName+" "+lastName);
				}
				//con.close();
				return classesMap;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<MemberModel> getMembersListWithStatusFilter(int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.id,mem.fname,mem.lname,mem.can_view_payments,mem.avatar,mem.approved_by,mem.entry_modules,mem.created_by,mem.email,mem.dob,mem.msisdn,mem.tithes,mem.modified_by,mem.modified_ts,mem.organisations,date(mem.created_ts) as created_ts,mem.occupation,mem.address,mem.employer,mem.gender,mem.approve_modules,mem.class_id,mem.billing_id,mem.church_id,mem.is_admin,mem.can_sms from members as mem where mem.church_id = ? and mem.status = ? order by mem.fname asc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<MemberModel> members = new ArrayList<MemberModel>();
				while(results.next()){
					int id = results.getInt("id");
					String fname = results.getString("fname");
					String lname = results.getString("lname");
					String email = results.getString("email");
					String msisdn = results.getString("msisdn");
					String avatar = results.getString("avatar");
					String organisations = results.getString("organisations");
					int classId = results.getInt("class_id");
					int createdBy = results.getInt("created_by");
					int billingId = results.getInt("billing_id");
					String approveModules = results.getString("approve_modules");
					String entryModules = results.getString("entry_modules");
					String tithes = results.getString("tithes");
					String occupation = results.getString("occupation");
					String address = results.getString("address");
					String gender = results.getString("gender").equalsIgnoreCase("M")?"Male":"Female";
					String employer = results.getString("employer");
					boolean isAdmin = results.getString("is_admin").equalsIgnoreCase("Y");
					boolean canSMS = results.getString("can_sms").equalsIgnoreCase("Y");
					String createdTs = results.getString("created_ts");
					String dateOfBirth = results.getString("dob");
					int modifiedBy = results.getInt("modified_by");
					String modifiedTs = results.getString("modified_ts");
					int approvedBy = results.getInt("approved_by");
					boolean canViewPayments = results.getString("can_view_payments").equalsIgnoreCase("Y");

					MemberModel member = new MemberModel();
					member.setId(id);
					member.setCreatedBy(createdBy);
					member.setFirstName(fname);
					member.setLastName(lname);
					member.setEmail(email);
					member.setMsisdn(msisdn);
					member.setOccupation(occupation);
					member.setOrganisations(organisations);
					member.setAddress(address);
					member.setEmployer(employer);
					member.setGender(gender);
					member.setClassId(classId);
					member.setTithes(tithes);
					member.setAdmin(isAdmin);
					member.setCanSMS(canSMS);
					member.setBillingId(billingId);
					member.setApproveModules(approveModules);
					member.setEntryModules(entryModules);
					member.setCreatedTs(createdTs);
					member.setDateOfBirth(dateOfBirth);
					member.setModifiedTs(modifiedTs);
					member.setModifiedBy(modifiedBy);
					member.setApprovedBy(approvedBy);
					member.setAvatar(avatar);
					member.setCanViewPayments(canViewPayments);

					members.add(member);
				}

				//con.close();
				return members;
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

					return model;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<MarriageModel> getMarriagesListWithStatusFilter(
			int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,f_occupation,m_occupation,time,description,modified_by,venue,date(`date`) as date,m_name,f_name,m_avatar,f_avatar,approved_by,created_by,date(created_ts) as created_ts,modified_ts from m_announcements where church_id  = ? and status = ? and date >= curdate() order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<MarriageModel> marriageList = new ArrayList<MarriageModel>();
				while(results.next()){
					int id = results.getInt("id");
					String maleName = results.getString("m_name");
					String femaleName = results.getString("f_name");
					String maleOccupation = results.getString("m_occupation");
					String femaleOccupation = results.getString("f_occupation");
					String mAvatar = results.getString("m_avatar");
					String fAvatar = results.getString("f_avatar");
					String description = results.getString("description");
					String venue = results.getString("venue");
					String date = results.getString("date");
					String time = results.getString("time");
					int approvedBy = results.getInt("approved_by");
					String createdTs = results.getString("created_ts");
					String modifiedTs = results.getString("modified_ts");
					int createdBy = results.getInt("created_by");
					int modifiedBy = results.getInt("modified_by");

					MarriageModel model = new MarriageModel();
					model.setId(id);
					model.setFemaleName(femaleName);
					model.setManName(maleName);
					model.setmOccupation(maleOccupation);
					model.setfOccupation(femaleOccupation);
					model.setTime(time);
					model.setfAvatar(fAvatar);
					model.setmAvatar(mAvatar);
					model.setCreatedBy(createdBy);
					model.setDescription(description);
					model.setDate(date);
					model.setApprovedBy(approvedBy);
					model.setCreatedTs(createdTs);
					model.setModifiedBy(modifiedBy);
					model.setModifiedTs(modifiedTs);
					model.setVenue(venue);

					marriageList.add(model);
				}
				//con.close();
				return marriageList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BibleReadingModel> getBibleReadingsListWithStatusFilter(
			int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,theme,description,approved_by,modified_by,created_by,date(`date`) as date,date(created_ts) as created_ts,modified_ts from bible_readings where church_id  = ? and status = ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<BibleReadingModel> bibleList = new ArrayList<BibleReadingModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					int modifiedBy = results.getInt("modified_by");
					String theme = results.getString("theme");
					String name = results.getString("name");
					String description = results.getString("description");
					String date = results.getString("date");
					String createdTs = results.getString("created_ts");
					String modifiedTs = results.getString("modified_ts");

					BibleReadingModel model = new BibleReadingModel();
					model.setId(id);
					model.setTheme(theme);
					model.setName(name);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setModifiedBy(modifiedBy);
					model.setDate(date);
					model.setCreatedBy(createdBy);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setCreatedTs(createdTs);
					model.setStatus(status);

					bibleList.add(model);
				}
				//con.close();
				return bibleList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<DonationModel> getDonationsListWithStatusFilter(
			int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,member_id,description,approved_by,modified_by,created_by,date(created_ts) as created_ts,modified_ts,amount from donations where church_id  = ? and status = ?");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<DonationModel> donationList = new ArrayList<DonationModel>();
				while(results.next()){
					int id = results.getInt("id");
					int memberId = results.getInt("member_id");
					int approvedBy = results.getInt("approved_by");
					int modifiedBy = results.getInt("modified_by");
					int createdBy = results.getInt("created_by");
					double amount = results.getDouble("amount");
					String description = results.getString("description");
					String createdTs = results.getString("created_ts");
					String modifiedTs = results.getString("modified_ts");

					DonationModel model = new DonationModel();
					model.setId(id);
					model.setMemberId(memberId);
					model.setAmount(amount);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedBy(createdBy);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setCreatedTs(createdTs);
					model.setModifiedBy(modifiedBy);
					model.setStatus(status);

					donationList.add(model);
				}
				//con.close();
				return donationList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<EAnnounceModel> getEAnnouncesListWithStatusFilter(
			int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,description,time,date(`date`) as date,venue,modified_by,approved_by,date(created_ts) as created_ts,created_by,modified_ts from e_announcements where church_id  = ? and status = ? and date >= curdate() order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<EAnnounceModel> eAnnounceList = new ArrayList<EAnnounceModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					int modifiedBy = results.getInt("modified_by");
					String description = results.getString("description");
					String modifiedTs = results.getString("modified_ts");
					String time = results.getString("time");
					String date = results.getString("date");
					String venue = results.getString("venue");
					String createdTs = results.getString("created_ts");

					EAnnounceModel model = new EAnnounceModel();
					model.setId(id);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setModifiedBy(modifiedBy);
					model.setCreatedTs(createdTs);
					model.setCreatedBy(createdBy);
					model.setTime(time);
					model.setDate(date);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setVenue(venue);
					model.setStatus(status);

					eAnnounceList.add(model);
				}
				//con.close();
				return eAnnounceList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<FAnnounceModel> getFAnnouncesListWithStatusFilter(
			int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,age,avatar,description,venue,time,date(`date`) as date,modified_by,approved_by,date(created_ts) as created_ts,date(modified_ts) as modified_ts from f_announcements where church_id  = ? and status = ? and date >= curdate() order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<FAnnounceModel> fAnnounceList = new ArrayList<FAnnounceModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int age = results.getInt("age");
					int modifiedBy = results.getInt("modified_by");
					String name = results.getString("name");
					String description = results.getString("description");
					String modifiedTs = results.getString("modified_ts");
					String date = results.getString("date");
					String avatar = results.getString("avatar");
					String venue = results.getString("venue");
					String createdTs = results.getString("created_ts");
					String time = results.getString("time");

					FAnnounceModel model = new FAnnounceModel();
					model.setId(id);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setName(name);
					model.setCreatedTs(createdTs);
					model.setAge(age);
					model.setDate(date);
					model.setTime(time);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setModifiedBy(modifiedBy);
					model.setAvatar(avatar);
					model.setStatus(status);
					model.setVenue(venue);

					fAnnounceList.add(model);
				}
				//con.close();
				return fAnnounceList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<OfferingModel> getOfferingsListWithStatusFilter(
			int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,description,amount,approved_by,date(created_ts) as created_ts,created_by,date(modified_ts) as modified_ts from offerings where church_id  = ? and status = ?");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<OfferingModel> offeringList = new ArrayList<OfferingModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					double amount = results.getDouble("amount");
					String description = results.getString("description");
					String modifiedTs = results.getString("modified_ts");
					String createdTs = results.getString("created_ts");

					OfferingModel model = new OfferingModel();
					model.setId(id);
					model.setAmount(amount);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedBy(createdBy);
					model.setCreatedTs(createdTs);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setStatus(status);

					offeringList.add(model);
				}
				//con.close();
				return offeringList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<SMSModel> getSMSListWithStatusFilter(int churchId,
			String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,is_bulk,sms_length,message,display,approved_by,date(created_ts) as created_ts,created_by,date(modified_ts) as modified_ts from sms where church_id  = ? and status = ?");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<SMSModel> SMSList = new ArrayList<SMSModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					String message = results.getString("message");
					String display = results.getString("display");
					String modifiedTs = results.getString("modified_ts");
					String createdTs = results.getString("created_ts");
					boolean isBulk = results.getString("is_bulk").trim().equalsIgnoreCase("Y");
					int smsLength = results.getInt("sms_length");

					SMSModel model = new SMSModel();
					model.setId(id);
					model.setDisplay(display);
					model.setMessage(message);
					model.setCreatedBy(createdBy);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedTs(createdTs);
					model.setModifiedTs(modifiedTs);
					model.setStatus(status);
					model.setSmsCounts(smsLength);
					model.setBulk(isBulk);

					SMSList.add(model);
				}
				//con.close();
				return SMSList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<TitheModel> getTithesListWithStatusFilter(int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,member_id,amount,approved_by,date(`date`) as date,date(created_ts) as created_ts,created_by,date(modified_ts) as modified_ts,modified_by from tithes where church_id  = ? and status = ?");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<TitheModel> titheList = new ArrayList<TitheModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					int modifiedBy = results.getInt("modified_by");
					int memberId = results.getInt("member_id");

					double amount = results.getDouble("amount");
					String modifiedTs = results.getString("modified_ts");
					String date = results.getString("date");
					String createdTs = results.getString("created_ts");

					TitheModel model = new TitheModel();
					model.setId(id);
					model.setMemberId(memberId);
					model.setAmount(amount);
					model.setModifiedBy(modifiedBy);
					model.setCreatedBy(createdBy);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedTs(createdTs);
					model.setModifiedTs(modifiedTs);
					model.setDate(date);
					model.setStatus(status);

					titheList.add(model);
				}
				//con.close();
				return titheList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ChurchModel getChurchModel(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,display,modules,expire_date,price_per_sms,annual_fee,sms_left,payment_id,member_limit,package,default_group_id from churches where id = ? and status = 'A'");
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
					int defaultGroupId = results.getInt("default_group_id");
					double memberLimit = results.getDouble("member_limit");
					String packageType = results.getString("package");
					boolean isValid = Utils.isAppValid(false, expireDate);

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
					model.setMemberLimit(memberLimit);
					model.setPackageType(packageType);
					model.setAppFee(annualCost);
					model.setValid(isValid);
					model.setDefaultGroupId(defaultGroupId);

					return model;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<GroupInfoModel> getGroupInfoList(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name from organisations where church_id = ? and status = 'A' and leader_id > 0");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<Integer, String> organisationsMap = new HashMap<Integer, String>();
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("name");

					organisationsMap.put(id, name);
				}

				ArrayList<GroupInfoModel> groupInfoList = new ArrayList<GroupInfoModel>();

				for(Integer organisationId : organisationsMap.keySet()){
					String organisationName = organisationsMap.get(organisationId);
					int memberCount = getMemberCount(organisationId);

					GroupInfoModel model = new GroupInfoModel();
					model.setId(organisationId);
					model.setGroupName(organisationName);
					model.setTotalMembers(memberCount);

					groupInfoList.add(model);

					System.out.println("Group name "+model.getGroupName()+", members are :"+model.getTotalMembers());

				}

				return groupInfoList;

			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	private int getMemberCount(int organisationId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,organisations from members where organisations regexp ? and status = 'A'");
			prstmt.setString(1, Utils.getRegexp(",", organisationId));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				int memberCounter = 0;
				while(results.next()){
					if(!Utils.getTokenList(",", results.getString("organisations")).contains(organisationId)){
						continue;
					}

					memberCounter ++;
				}

				return memberCounter;
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return 0;
	}

	@Override
	public ArrayList<DateModel> getDateList() {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,alias from sys_year where status = ?");
			prstmt.setString(1, "A");

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<DateModel> modelList = new ArrayList<DateModel>();
				while(results.next()){
					int id = results.getInt("id");
					int name = results.getInt("name");
					String alias = results.getString("alias");

					DateModel model = new DateModel();
					model.setId(id);
					model.setDate(name);
					model.setAlias(alias);
					model.setStatus("A");

					modelList.add(model);
				}
				return modelList;
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<BibleReadingModel> getBibleReading(int churchId,
			String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,theme,modified_by,description,approved_by,created_by,date(`date`) as date,date(created_ts) as created_ts,modified_ts from bible_readings where church_id = ? and status = ? and date(date)  between ? and ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, "A");
			prstmt.setString(3, Utils.getBeginDate(",", date));
			prstmt.setString(4, Utils.getEndDate(",", date));

			System.out.println("Date: "+date);
			System.out.println("select id,name,theme,description,approved_by,created_by,date(date) as date,created_ts,modified_ts from bible_readings where church_id = "+churchId+" and status = '"+"A"+"' and date(date)  between "+Utils.getBeginDate(",", date)+" and "+Utils.getEndDate(",", date)+" order by id desc");

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<BibleReadingModel> bibleList = new ArrayList<BibleReadingModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					int modifiedBy = results.getInt("modified_by");
					String theme = results.getString("theme");
					String name = results.getString("name");
					String description = results.getString("description");
					String tmpdate = results.getString("date");
					String createdTs = results.getString("created_ts");
					String modifiedTs = results.getString("modified_ts");

					BibleReadingModel model = new BibleReadingModel();
					model.setId(id);
					model.setTheme(theme);
					model.setName(name);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setDate(tmpdate);
					model.setCreatedBy(createdBy);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setCreatedTs(createdTs);
					model.setModifiedBy(modifiedBy);
					model.setStatus("A");

					bibleList.add(model);
				}
				//con.close();
				return bibleList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<OfferingModel> getOffering(int churchId, String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,description,amount,approved_by,date(created_ts) as created_ts,created_by,modified_ts from offerings where church_id = ? and status = ? and date(created_ts)  between ? and ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, "A");
			prstmt.setString(3, Utils.getBeginDate(",", date));
			prstmt.setString(4, Utils.getEndDate(",", date));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<OfferingModel> offeringList = new ArrayList<OfferingModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					double amount = results.getDouble("amount");
					String description = results.getString("description");
					String modifiedTs = results.getString("modified_ts");
					String createdTs = results.getString("created_ts");

					OfferingModel model = new OfferingModel();
					model.setId(id);
					model.setAmount(amount);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedBy(createdBy);
					model.setCreatedTs(createdTs);
					model.setDescription(description);
					model.setModifiedTs(modifiedTs);
					model.setStatus("A");

					offeringList.add(model);
				}
				//con.close();
				return offeringList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<TitheModel> getTithe(int userId, int churchId, String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,member_id,amount,approved_by,month(date) as month,date(date) as date,date(created_ts) as created_ts,created_by,date(modified_ts) as modified_ts,modified_by from tithes where church_id  = ? and year(date) = ? and status = ? and member_id = ?");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, date);
			prstmt.setString(3, "A");
			prstmt.setInt(4, userId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<TitheModel> titheList = new ArrayList<TitheModel>();
				while(results.next()){
					int id = results.getInt("id");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					int modifiedBy = results.getInt("modified_by");
					int memberId = results.getInt("member_id");

					double amount = results.getDouble("amount");
					String modifiedTs = results.getString("modified_ts");
					String tmpdate = results.getString("date");
					String createdTs = results.getString("created_ts");
					String month = results.getString("month");

					TitheModel model = new TitheModel();
					model.setId(id);
					model.setMemberId(memberId);
					model.setAmount(amount);
					model.setModifiedBy(modifiedBy);
					model.setCreatedBy(createdBy);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedTs(createdTs);
					model.setModifiedTs(modifiedTs);
					model.setDate(tmpdate);
					model.setStatus("A");
					model.setMonth(Utils.getMonthInWords(month));

					titheList.add(model);
				}
				//con.close();
				return titheList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isEmailExist(String email, int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();
		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,email from members where email = ? and church_id = ?");
			prstmt.setString(1, email);
			prstmt.setInt(2, churchId);
			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					return true;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isMsisdnExist(String msisdn, int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();
		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,email from members where msisdn = ? and church_id = ? and status regexp 'A|P'");
			prstmt.setString(1, msisdn.contains("+")?msisdn:"+"+msisdn);
			prstmt.setInt(2, churchId);
			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					return true;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

	@Override
	public String getServerDate() {
		return Utils.getTodayDateTime();
	}

	@Override
	public ArrayList<MemberModel> getAllMembersListByLeader(int churchId,
			int leaderId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.prefs,mem.commence_year,mem.id,mem.can_view_payments,mem.fname,mem.lname,mem.avatar,mem.approved_by,mem.entry_modules,mem.created_by,mem.email,mem.dob,mem.msisdn,mem.tithes,mem.modified_by,mem.modified_ts,mem.organisations,date(mem.created_ts) as created_ts,mem.occupation,mem.address,mem.employer,mem.gender,mem.approve_modules,mem.class_id,mem.billing_id,mem.church_id,mem.is_admin,mem.can_sms from members as mem where mem.church_id = ? and mem.status = 'A' and mem.class_id = ? order by mem.fname asc");
			prstmt.setInt(1, churchId);
			prstmt.setInt(2, leaderId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<MemberModel> members = new ArrayList<MemberModel>();
				while(results.next()){
					int id = results.getInt("id");
					String fname = results.getString("fname");
					String lname = results.getString("lname");
					String email = results.getString("email");
					String msisdn = results.getString("msisdn");
					String avatar = results.getString("avatar");
					String organisations = results.getString("organisations");
					int classId = results.getInt("class_id");
					int createdBy = results.getInt("created_by");
					int billingId = results.getInt("billing_id");
					String approveModules = results.getString("approve_modules");
					String entryModules = results.getString("entry_modules");
					String tithes = results.getString("tithes");
					String occupation = results.getString("occupation");
					String address = results.getString("address");
					String gender = results.getString("gender");
					String employer = results.getString("employer");
					boolean isAdmin = results.getString("is_admin").equalsIgnoreCase("Y");
					boolean canSMS = results.getString("can_sms").equalsIgnoreCase("Y");
					String createdTs = results.getString("created_ts");
					String dateOfBirth = results.getString("dob");
					int modifiedBy = results.getInt("modified_by");
					String modifiedTs = results.getString("modified_ts");
					int approvedBy = results.getInt("approved_by");
					boolean canViewPayments = results.getString("can_view_payments").equalsIgnoreCase("Y");
					String commenceYear = results.getString("commence_year");
					String otherInfo = results.getString("prefs");

					MemberModel member = new MemberModel();
					member.setId(id);
					member.setCreatedBy(createdBy);
					member.setFirstName(fname);
					member.setLastName(lname);
					member.setEmail(email);
					member.setMsisdn(msisdn);
					member.setOccupation(occupation);
					member.setOrganisations(organisations);
					member.setAddress(address);
					member.setEmployer(employer);
					member.setGender(gender);
					member.setClassId(classId);
					member.setTithes(tithes);
					member.setAdmin(isAdmin);
					member.setCanSMS(canSMS);
					member.setBillingId(billingId);
					member.setApproveModules(approveModules);
					member.setEntryModules(entryModules);
					member.setCreatedTs(createdTs);
					member.setDateOfBirth(dateOfBirth);
					member.setModifiedTs(modifiedTs);
					member.setModifiedBy(modifiedBy);
					member.setApprovedBy(approvedBy);
					member.setAvatar(avatar);
					member.setCanViewPayments(canViewPayments);
					member.setDateOfCommencement(commenceYear);
					member.setOtherInfo(otherInfo);

					members.add(member);
				}
				//con.close();
				return members;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<MyOfferingModel> getMyOffering(int userId, int churchId, String date) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,member_id,amount,date(created_date) as created_date from my_offerings where member_id = ? and church_id = ? and status = ? and date(created_date)  between ? and ? order by id desc");
			prstmt.setInt(1, userId);
			prstmt.setInt(2, churchId);
			prstmt.setString(3, "A");
			prstmt.setString(4, Utils.getBeginDate(",", date));
			prstmt.setString(5, Utils.getEndDate(",", date));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<MyOfferingModel> myOfferings = new ArrayList<MyOfferingModel>();
				while(results.next()){
					int id = results.getInt("id");
					int memberId = results.getInt("member_id");
					double amount = results.getDouble("amount");
					String createdTs = results.getString("created_date");

					MyOfferingModel model = new MyOfferingModel();
					model.setId(id);
					model.setMemberId(memberId);
					model.setAmount(amount);
					model.setDate(createdTs);
					model.setStatus("A");

					myOfferings.add(model);
				}

				//con.close();
				return myOfferings;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BirthdayModel> getBirthdays(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.id,mem.fname,mem.lname,mem.avatar,month(dob) as month, day(dob) as day, dob, (year(curdate()) - year(dob)) as years, weekday(dob) as dayofbirth, monthname(dob) as monthname from members as mem where mem.church_id = ? and mem.status = 'A' and month(dob) = month(curdate())");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<BirthdayModel> monthCelebrants = new ArrayList<BirthdayModel>();
				while(results.next()){
					String fname = results.getString("fname");
					String lname = results.getString("lname");
					int month = results.getInt("month");
					int day = results.getInt("day");
					int age = results.getInt("years");
					String avatar = results.getString("avatar");
					String monthName = results.getString("monthname");
					int dayOfBirth = results.getInt("dayofbirth");

					if(getCustomWeekNumber(day, month) == weekNumber){
						BirthdayModel member = new BirthdayModel();
						member.setName(fname+" "+lname);
						member.setDate(Utils.getWeekdayName(dayOfBirth)+", "+day+Utils.getDateSuffix(day)+" "+monthName);
						member.setAvatar(avatar);
						member.setAge(age);

						monthCelebrants.add(member);
					}
				}

				//con.close();
				return monthCelebrants;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	private String getFakeDate(int day, int month){
		return year+"-"+month+"-"+day;
	}

	private int getCustomWeekNumber(int day, int month){
		String date = getFakeDate(day, month);
		con = DBConnection.getConnection();

		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select weekofyear('"+date+"') as week");

			if(results != null){
				while(results.next()){
					int week = results.getInt("week");
					////con.close();
					return week;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		System.gc();
		return 0;
	}

	private int getWeekNumber(){
		con = DBConnection.getConnection();

		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select weekofyear(curdate()) as week");

			if(results != null){
				while(results.next()){
					int week = results.getInt("week");
					////con.close();
					return week;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		System.gc();
		return 0;
	}

	private int getYear(){
		con = DBConnection.getConnection();

		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select year(curdate()) as year");

			if(results != null){
				while(results.next()){
					int year = results.getInt("year");
					////con.close();
					return year;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		System.gc();
		return 0;
	}

	@Override
	public ArrayList<SpecialOfferingModel> getSpecialOfferingsListWithStatusFilter(int churchId, String status) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,member_id,amount,message,approved_by,created_date,created_by from special_offerings where church_id  = ? and status = ? and weekofyear(created_date) >= ? order by id desc");
			prstmt.setInt(1, churchId);
			prstmt.setString(2, status);
			prstmt.setInt(3, weekNumber - 1);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<SpecialOfferingModel> specialOfferingList = new ArrayList<SpecialOfferingModel>();
				while(results.next()){
					int id = results.getInt("id");
					int memberId = results.getInt("member_id");
					double amount = results.getDouble("amount");
					int approvedBy = results.getInt("approved_by");
					int createdBy = results.getInt("created_by");
					String createdDate = results.getString("created_date");
					String message = results.getString("message");

					SpecialOfferingModel model = new SpecialOfferingModel();
					model.setId(id);
					model.setMemberId(memberId);
					model.setAmount(amount);
					model.setMessage(message);
					model.setChurchId(churchId);
					model.setApprovedBy(approvedBy);
					model.setCreatedDate(createdDate);
					model.setCreatedBy(createdBy);
					model.setStatus(status);

					specialOfferingList.add(model);
				}
				//con.close();
				return specialOfferingList;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isPasswordValid(int memberId, int churchId, String oldPassword) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id from members where id = ? and church_id = ? and password = ? and status = 'A'");
			prstmt.setInt(1, memberId);
			prstmt.setInt(2, churchId);
			prstmt.setString(3, Utils.getSHA256(oldPassword));

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					if(id > 0)
						return true;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

	@Override
	public ForgotPasswordModel getForgotPasswordModel(String msisdn) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,lname,fname,avatar,church_id from members where msisdn = ? and status = 'A'");
			prstmt.setString(1, msisdn.contains("+")?msisdn:"+"+msisdn);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String avatar = results.getString("avatar");
					String lName = results.getString("lname");
					String fName = results.getString("fname");
					int churchId = results.getInt("church_id");
					String code = Utils.getToken(40);

					ForgotPasswordModel model = new ForgotPasswordModel();
					model.setMemberId(id);
					model.setMsisdn(msisdn);
					model.setLastname(lName);
					model.setFirstname(fName);
					model.setAvatar(avatar);
					model.setCode(code);

					ServerGlobalResources.getInstance().setChurchModel(getChurchModel(churchId));

					if(addToForgotPassword(model)){
						Utils.dispatchSMS(msisdn, "Your Church Cradle verification code is "+model.getCode());
						return model;
					}
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

	private boolean addToForgotPassword(ForgotPasswordModel model){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into forgot_passwords (member_id,msisdn,code,created_ts) values (?,?,?,?)");
			prstmt.setInt(1, model.getMemberId());
			prstmt.setString(2, model.getMsisdn());
			prstmt.setString(3, model.getCode());
			prstmt.setString(4, Utils.getTodayDateTime());

			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
				return true;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

	@Override
	public int getOrganisationsCount(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from organisations where church_id = ? and status = 'A' and leader_id > 0");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getMembersCount(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from members where church_id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getCurrentMonthSMS(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select sum(sms_count) as count from sent_sms where church_id = ? and status = 'A' and month(created_ts) = month(curdate())");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					return results.getInt("count");
				}
				//con.close();
				return 0;
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getPendingRequestCount(int churchId) {

		return getPendingSMSCount(churchId)+
				getPendingMarriageAnnouncements(churchId)+
				getPendingEventsAnnouncements(churchId)+
				getPendingHomeCallAnnouncements(churchId)+
				getPendingBibleReading(churchId)+
				getPendingOffering(churchId)+
				getPendingTithes(churchId)+
				getPendingMembers(churchId);
	}

	private int getPendingSMSCount(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from sms where church_id = ? and status = 'P'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingMarriageAnnouncements(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from m_announcements where church_id = ? and status = 'P' and date >= curdate()");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingEventsAnnouncements(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from e_announcements where church_id = ? and status = 'P' and date >= curdate()");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingHomeCallAnnouncements(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from f_announcements where church_id = ? and status = 'P' and date >= curdate()");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingBibleReading(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from bible_readings where church_id = ? and status = 'P'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingOffering(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from offerings where church_id = ? and status = 'P'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingTithes(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from tithes where church_id = ? and status = 'P'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	private int getPendingMembers(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select count(id) as count from members where church_id = ? and status = 'P'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int counts = results.getInt("count");

					return counts;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getBirthdayCelebCounts(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select mem.id,mem.fname,mem.lname,mem.avatar,month(dob) as month, day(dob) as day, dob, (year(curdate()) - year(dob)) as years, weekday(dob) as dayofbirth, monthname(dob) as monthname from members as mem where mem.church_id = ? and mem.status = 'A' and month(dob) = month(curdate())");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<BirthdayModel> monthCelebrants = new ArrayList<BirthdayModel>();
				while(results.next()){
					String fname = results.getString("fname");
					String lname = results.getString("lname");
					int month = results.getInt("month");
					int day = results.getInt("day");
					int age = results.getInt("years");
					String avatar = results.getString("avatar");
					String monthName = results.getString("monthname");
					int dayOfBirth = results.getInt("dayofbirth");

					if(getCustomWeekNumber(day, month) == weekNumber){
						BirthdayModel member = new BirthdayModel();
						member.setName(fname+" "+lname);
						member.setDate(Utils.getWeekdayName(dayOfBirth)+", "+day+Utils.getDateSuffix(day)+" "+monthName);
						member.setAvatar(avatar);
						member.setAge(age);

						monthCelebrants.add(member);
					}
				}

				//con.close();
				return monthCelebrants.size();
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getDayOfWeek() {
		con = DBConnection.getConnection();

		try{
			stmt = (Statement) con.createStatement();
			ResultSet results = stmt.executeQuery("select dayofweek(curdate()) as weekday");

			if(results != null){
				while(results.next()){
					int weekday = results.getInt("weekday");
					////con.close();
					return weekday;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		System.gc();
		return 0;
	}

	@Override
	public ArrayList<PowerLeaderModel> getPowerLeaders(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select pl.id as id,pl.created_by as created_by,mem.fname as fname,mem.lname as lname,mem.msisdn as msisdn,pl.created_ts as created_ts from power_leaders as pl,members as mem where pl.member_id = mem.id and pl.church_id = ? and pl.church_id = mem.church_id and pl.status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<PowerLeaderModel> modelList = new ArrayList<PowerLeaderModel>();
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("fname")+" "+results.getString("lname");
					String msisdn = results.getString("msisdn");
					String createdTs = results.getString("created_ts");
					int createdBy = results.getInt("created_by");

					PowerLeaderModel model = new PowerLeaderModel();
					model.setId(id);
					model.setName(name);
					model.setMsisdn(msisdn);
					model.setCreatedTs(createdTs);
					model.setCreatedBy(createdBy);

					modelList.add(model);
				}

				return modelList;
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Integer> getAllPowerLeadersList(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select member_id from power_leaders where church_id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<Integer> modelList = new ArrayList<Integer>();
				while(results.next()){
					int id = results.getInt("member_id");
					modelList.add(id);
				}

				return modelList;
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return new ArrayList<Integer>();
	}

	@Override
	public void notifySMS() {
		//		SMSGetter getter = new SMSGetter();
		//		getter.readSMS();
	}

	@Override
	public String getUploadUrl(String path) {
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

		return blobstoreService.createUploadUrl(path);

	}

	@Override
	public BlobstoreModel requestPreviewImage() {
		
		BlobstoreModel model = new BlobstoreModel();
		model.setBlobkey(MiscStorage.getInstance().getImgBlobKey().getKeyString());
		model.setUrl(MiscStorage.getInstance().getImgUrl());
		
		return model;
	}

	@Override
	public BlobstoreModel retrieveFromBlobstore(String tmpblobKey) {
		BlobKey blobKey = new BlobKey(tmpblobKey);
		
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
	
		BlobstoreModel model = new BlobstoreModel();
		model.setBlobkey(tmpblobKey);
		model.setUrl(imagesService.getServingUrl(blobKey));
		
		return model;
	}
	
	@Override
	public AppStatsModel getAppModel(int churchId) {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,display,package,sms_left,expire_date,annual_fee,member_limit,renewal_date,payment_id,price_per_sms,datediff(expire_date,curdate()) as rem_date,birth_push,tithe_push from churches where id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					int paymentId = results.getInt("id");
					String churchName = results.getString("name");
					String display = results.getString("display");
					String packageType = results.getString("package");
					String expireDate = results.getString("expire_date");
					double smsLeft = results.getDouble("sms_left");
					double memberLimit = results.getDouble("member_limit");
					double pricePerSMS = results.getDouble("price_per_sms");
					double daysLeft = results.getDouble("rem_date");
					double annualFees = results.getDouble("annual_fee");
					String renewalDate = results.getString("renewal_date");
					boolean isBirthdayPush = results.getString("birth_push").equalsIgnoreCase("Y");
					boolean isTithePush = results.getString("tithe_push").equalsIgnoreCase("Y");
					
					AppStatsModel model = new AppStatsModel();
					model.setChurchId(churchId);
					model.setDaysLeft(daysLeft);
					model.setExpireDate(expireDate);
					model.setLastRenewedDate(renewalDate);
					model.setMembersLimit(memberLimit);
					model.setPackageType(packageType);
					model.setRenewCost(annualFees);
					model.setSmsCost(pricePerSMS);
					model.setSmsLeft(smsLeft);
					model.setTotalMembers(Utils.getAllMembersCount(churchId));
					model.setBirthdayPush(isBirthdayPush);
					model.setTithePush(isTithePush);

					return model;
				}
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public double getDollarRate() {
		CurrencyKeeper keeper = new CurrencyKeeper();
		return keeper.getDollarRate();
	}

	@Override
	public ArrayList<ChurchModel> getAllChurches() {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,display,modules,expire_date,price_per_sms,annual_fee,sms_left,payment_id,member_limit,package,default_group_id from churches where datediff(expire_date,curdate()) > 0 and status = ?");
			prstmt.setString(1, "A");

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				ArrayList<ChurchModel> churches = new ArrayList<ChurchModel>();
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
					int defaultGroupId = results.getInt("default_group_id");
					double memberLimit = results.getDouble("member_limit");
					String packageType = results.getString("package");
					boolean isValid = Utils.isAppValid(false, expireDate);

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
					model.setMemberLimit(memberLimit);
					model.setPackageType(packageType);
					model.setAppFee(annualCost);
					model.setValid(isValid);
					model.setDefaultGroupId(defaultGroupId);

					churches.add(model);
				}
				
				return churches;
			}
			//con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}

	@Override
	public HashMap<String, String> getEducationalList() {
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,value from education_list where status = ?");
			prstmt.setString(1, "A");

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				HashMap<String, String> educationalMap = new HashMap<String, String>();
				while(results.next()){
					//int id = results.getInt("id");
					String name = results.getString("name");
					String value = results.getString("value");
					
					educationalMap.put(name, value);
				}
				
				return educationalMap;
			}
			////con.close();
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return null;
	}
}
