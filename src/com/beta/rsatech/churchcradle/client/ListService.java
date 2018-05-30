package com.beta.rsatech.churchcradle.client;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("list")

public interface ListService extends RemoteService{
	UserModel getUser(String username, String password, boolean isAdmin);
	UserModel getFullUser(int userId);
	boolean isEmailExist(String email, int churchId);
	boolean isMsisdnExist(String msisdn, int churchId);
	boolean isPasswordValid(int memberId, int churchId, String oldPassword);
	
	HashMap<Integer, String> getAllOrganisationMap(int churchId);
	
	
	
	ArrayList<MemberModel> getAllMembersList(int churchId, int groupId);
	ArrayList<MemberModel> getAllMembersListByLeader(int churchId, int leaderId);
	HashMap<Integer, String> getAllApproveModulesMap(int churchId);
	HashMap<Integer, String> getAllEntryModulesMap(int churchId);
	HashMap<Integer, String> getAllClassLeadersMap(int churchId);
	HashMap<Integer, String> getAllMembersMap(int churchId);
	ArrayList<MemberModel> getMembersListWithStatusFilter(int churchId, String status);
	ArrayList<MarriageModel> getMarriagesListWithStatusFilter(int churchId, String status);
	ArrayList<BibleReadingModel> getBibleReadingsListWithStatusFilter(int churchId, String status);
	ArrayList<DonationModel> getDonationsListWithStatusFilter(int churchId, String status);
	ArrayList<EAnnounceModel> getEAnnouncesListWithStatusFilter(int churchId, String status);
	ArrayList<FAnnounceModel> getFAnnouncesListWithStatusFilter(int churchId, String status);
	ArrayList<OfferingModel> getOfferingsListWithStatusFilter(int churchId, String status);
	ArrayList<SpecialOfferingModel> getSpecialOfferingsListWithStatusFilter(int churchId, String status);

	ArrayList<SMSModel> getSMSListWithStatusFilter(int churchId, String status);
	ArrayList<TitheModel> getTithesListWithStatusFilter(int churchId, String status);
	ChurchModel getChurchModel(int churchId);
	ArrayList<GroupInfoModel> getGroupInfoList(int churchId);
	ArrayList<DateModel> getDateList();
	ArrayList<BibleReadingModel> getBibleReading(int churchId, String date);
	ArrayList<OfferingModel> getOffering(int churchId, String date);
	ArrayList<TitheModel> getTithe(int userId, int churchId, String date);
	ArrayList<MyOfferingModel> getMyOffering(int userId, int churchId, String date);
	ArrayList<BirthdayModel> getBirthdays(int churchId);
	ArrayList<PowerLeaderModel> getPowerLeaders(int churchId);
	
	ArrayList<Integer> getAllPowerLeadersList(int churchId);
	
	ForgotPasswordModel getForgotPasswordModel(String msisdn);
		
	String getServerDate();
	int getOrganisationsCount(int churchId);
	int getMembersCount(int churchId);
	int getCurrentMonthSMS(int churchId);
	int getPendingRequestCount(int churchId);
	int getBirthdayCelebCounts(int churchId);
	int getDayOfWeek();
	
	void notifySMS();
	
	String getUploadUrl(String path);
	BlobstoreModel requestPreviewImage();
	BlobstoreModel retrieveFromBlobstore(String blobKey);
	AppStatsModel getAppModel(int churchId);
	double getDollarRate();
	HashMap<Integer, String> getAllOrganisationMapWithLeaders(int churchId);

	ArrayList<ChurchModel> getAllChurches();
	
	HashMap<String, String> getEducationalList();
	HashMap<String, String> getRegionsList();
	
	MemberModel getMemberWithEmail(String email, int churchId);
	MemberModel getMemberWithMsisdn(String msisdn, int churchId);
	MemberModel getMemberWithId(int id, int churchId);
	ArrayList<MemberModel> getMemberWithName(String name, int churchId);
	
}
