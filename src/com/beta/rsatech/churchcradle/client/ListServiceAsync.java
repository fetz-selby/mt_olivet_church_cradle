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
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ListServiceAsync {

	void getUser(String username, String password, boolean isAdmin,
			AsyncCallback<UserModel> callback);

	void getFullUser(int userId, AsyncCallback<UserModel> callback);

	void getAllOrganisationMap(int churchId,
			AsyncCallback<HashMap<Integer, String>> callback);

	void getAllMembersList(int churchId, int groupId,
			AsyncCallback<ArrayList<MemberModel>> callback);

	void getAllApproveModulesMap(int churchId,
			AsyncCallback<HashMap<Integer, String>> callback);

	void getAllClassLeadersMap(int churchId,
			AsyncCallback<HashMap<Integer, String>> callback);

	void getAllMembersMap(int churchId,
			AsyncCallback<HashMap<Integer, String>> callback);

	void getMembersListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<MemberModel>> callback);

	void getMarriagesListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<MarriageModel>> callback);

	void getBibleReadingsListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<BibleReadingModel>> callback);

	void getDonationsListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<DonationModel>> callback);

	void getEAnnouncesListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<EAnnounceModel>> callback);

	void getFAnnouncesListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<FAnnounceModel>> callback);

	void getOfferingsListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<OfferingModel>> callback);

	void getSMSListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<SMSModel>> callback);

	void getTithesListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<TitheModel>> callback);

	void getChurchModel(int churchId, AsyncCallback<ChurchModel> callback);

	void getGroupInfoList(int churchId,
			AsyncCallback<ArrayList<GroupInfoModel>> callback);

	void getDateList(AsyncCallback<ArrayList<DateModel>> callback);

	void getBibleReading(int churchId, String date,
			AsyncCallback<ArrayList<BibleReadingModel>> callback);

	void getOffering(int churchId, String date,
			AsyncCallback<ArrayList<OfferingModel>> callback);

	void getTithe(int userId, int churchId, String date,
			AsyncCallback<ArrayList<TitheModel>> callback);

	void isEmailExist(String email, int churchId,
			AsyncCallback<Boolean> callback);

	void getAllEntryModulesMap(int churchId,
			AsyncCallback<HashMap<Integer, String>> callback);

	void isMsisdnExist(String msisdn, int churchId,
			AsyncCallback<Boolean> callback);

	void getServerDate(AsyncCallback<String> callback);

	void getAllMembersListByLeader(int churchId, int leaderId,
			AsyncCallback<ArrayList<MemberModel>> callback);

	void getMyOffering(int userId, int churchId, String date,
			AsyncCallback<ArrayList<MyOfferingModel>> callback);

	void getBirthdays(int churchId,
			AsyncCallback<ArrayList<BirthdayModel>> callback);

	void getSpecialOfferingsListWithStatusFilter(int churchId, String status,
			AsyncCallback<ArrayList<SpecialOfferingModel>> callback);

	void isPasswordValid(int memberId, int churchId, String oldPassword,
			AsyncCallback<Boolean> callback);

	void getForgotPasswordModel(String msisdn,
			AsyncCallback<ForgotPasswordModel> callback);

	void getOrganisationsCount(int churchId, AsyncCallback<Integer> callback);

	void getMembersCount(int churchId, AsyncCallback<Integer> callback);

	void getCurrentMonthSMS(int churchId, AsyncCallback<Integer> callback);

	void getPendingRequestCount(int churchId, AsyncCallback<Integer> callback);

	void getBirthdayCelebCounts(int churchId, AsyncCallback<Integer> callback);

	void getDayOfWeek(AsyncCallback<Integer> callback);

	void getPowerLeaders(int churchId,
			AsyncCallback<ArrayList<PowerLeaderModel>> callback);

	void getAllPowerLeadersList(int churchId,
			AsyncCallback<ArrayList<Integer>> callback);

	void notifySMS(AsyncCallback<Void> callback);

	void getUploadUrl(String path, AsyncCallback<String> callback);

	void requestPreviewImage(AsyncCallback<BlobstoreModel> callback);

	void retrieveFromBlobstore(String blobKey,
			AsyncCallback<BlobstoreModel> callback);

	void getAppModel(int churchId, AsyncCallback<AppStatsModel> callback);

	void getDollarRate(AsyncCallback<Double> callback);

	void getAllOrganisationMapWithLeaders(int churchId,
			AsyncCallback<HashMap<Integer, String>> callback);

	void getAllChurches(AsyncCallback<ArrayList<ChurchModel>> callback);

	void getEducationalList(AsyncCallback<HashMap<String, String>> callback);

	void getRegionsList(AsyncCallback<HashMap<String, String>> callback);

	void getMemberWithEmail(String email, int churchId, AsyncCallback<MemberModel> callback);

	void getMemberWithMsisdn(String msisdn, int churchId, AsyncCallback<MemberModel> callback);

	void getMemberWithId(int id, int churchId,
			AsyncCallback<MemberModel> callback);

	void getMemberWithName(String name, int churchId,
			AsyncCallback<ArrayList<MemberModel>> callback);
}
