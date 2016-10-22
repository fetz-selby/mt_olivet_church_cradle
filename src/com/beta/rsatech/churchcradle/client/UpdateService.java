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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("update")
public interface UpdateService extends RemoteService{
	boolean approveMarriage(int userId, MarriageModel model);
	boolean approveFuneral(int userId, FAnnounceModel model);
	boolean approveEvent(int userId, EAnnounceModel model);
	boolean approveSMS(int userId, SMSModel model);
	boolean approveTithe(int userId, TitheModel model);
	boolean approveOffering(int userId, OfferingModel model);
	boolean approveBibleReading(int userId, BibleReadingModel model);
	boolean approveDonation(int userId, DonationModel model);
	boolean approveMember(int userId, MemberModel model);
	boolean approveSpecialOffering(int userId, SpecialOfferingModel model);
	
	boolean rejectMarriage(int userId, MarriageModel model);
	boolean rejectFuneral(int userId, FAnnounceModel model);
	boolean rejectEvent(int userId, EAnnounceModel model);
	boolean rejectSMS(int userId, SMSModel model);
	boolean rejectTithe(int userId, TitheModel model);
	boolean rejectOffering(int userId, OfferingModel model);
	boolean rejectBibleReading(int userId, BibleReadingModel model);
	boolean rejectDonation(int userId, DonationModel model);
	boolean rejectMember(int userId, MemberModel model);
	boolean rejectSpecialOffering(int userId, SpecialOfferingModel model);

	boolean removeMember(int userId, int churchId, int memberId);
	
	boolean updateMember(int userId, MemberModel model);
	boolean updateMemberPassword(int memberId, int churchId, String password);
	boolean updateForgotMemberPassword(int memberId, String password);
	
	boolean removePowerLeader(int memberId, int churchId);
	
	boolean setModuleBaseUrl(String moduleBaseUrl);
	void deleteOrphanBlob(String blobKey);
	boolean updateAppStats(int userId, AppStatsModel model);

}
