package com.beta.rsatech.churchcradle.client;

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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("add")

public interface AddService extends RemoteService{
	int addGroup(GroupModel model, int memberId);
	int addSMS(SMSModel model, int memberId);
	int addMember(MemberModel model, int memberId);
	int addMarriage(MarriageModel model, int userId);
	int addFuneral(FAnnounceModel model, int userId);
	int addEvent(EAnnounceModel model, int userId);
	int addOffering(OfferingModel model, int userId);
	int addTithe(TitheModel model, int userId);
	int addDonation(DonationModel model, int userId);
	int addClassLeader(ClassModel model, int userId);
	int addSpecialOffering(SpecialOfferingModel model, int userId);
	int addBibleReading(BibleReadingModel model, int userId);
	int addPowerLeader(PowerLeaderModel model, int userId);
}
