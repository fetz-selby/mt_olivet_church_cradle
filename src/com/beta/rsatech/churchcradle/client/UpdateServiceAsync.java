package com.beta.rsatech.churchcradle.client;

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
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UpdateServiceAsync {

	void approveMarriage(int userId, MarriageModel model,
			AsyncCallback<Boolean> callback);

	void approveFuneral(int userId, FAnnounceModel model,
			AsyncCallback<Boolean> callback);

	void approveEvent(int userId, EAnnounceModel model,
			AsyncCallback<Boolean> callback);

	void approveSMS(int userId, SMSModel model, AsyncCallback<Boolean> callback);

	void approveTithe(int userId, TitheModel model,
			AsyncCallback<Boolean> callback);

	void approveOffering(int userId, OfferingModel model,
			AsyncCallback<Boolean> callback);

	void approveBibleReading(int userId, BibleReadingModel model,
			AsyncCallback<Boolean> callback);

	void approveDonation(int userId, DonationModel model,
			AsyncCallback<Boolean> callback);

	void approveMember(int userId, MemberModel model,
			AsyncCallback<Boolean> callback);

	void updateMember(int userId, MemberModel model,
			AsyncCallback<Boolean> callback);

	void rejectMarriage(int userId, MarriageModel model,
			AsyncCallback<Boolean> callback);

	void rejectFuneral(int userId, FAnnounceModel model,
			AsyncCallback<Boolean> callback);

	void rejectEvent(int userId, EAnnounceModel model,
			AsyncCallback<Boolean> callback);

	void rejectSMS(int userId, SMSModel model, AsyncCallback<Boolean> callback);

	void rejectTithe(int userId, TitheModel model,
			AsyncCallback<Boolean> callback);

	void rejectOffering(int userId, OfferingModel model,
			AsyncCallback<Boolean> callback);

	void rejectBibleReading(int userId, BibleReadingModel model,
			AsyncCallback<Boolean> callback);

	void rejectDonation(int userId, DonationModel model,
			AsyncCallback<Boolean> callback);

	void rejectMember(int userId, MemberModel model,
			AsyncCallback<Boolean> callback);

	void approveSpecialOffering(int userId, SpecialOfferingModel model,
			AsyncCallback<Boolean> callback);

	void rejectSpecialOffering(int userId, SpecialOfferingModel model,
			AsyncCallback<Boolean> callback);

	void updateMemberPassword(int memberId, int churchId, String password,
			AsyncCallback<Boolean> callback);

	void updateForgotMemberPassword(int memberId, String password,
			AsyncCallback<Boolean> callback);

	void setModuleBaseUrl(String moduleBaseUrl, AsyncCallback<Boolean> callback);

	void removePowerLeader(int memberId, int churchId,
			AsyncCallback<Boolean> callback);

	void removeMember(int userId, int churchId, int memberId,
			AsyncCallback<Boolean> callback);

	void deleteOrphanBlob(String blobKey, AsyncCallback<Void> callback);

	void updateAppStats(int userId, AppStatsModel model,
			AsyncCallback<Boolean> callback);

}
