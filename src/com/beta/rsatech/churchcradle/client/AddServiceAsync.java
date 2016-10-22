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
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddServiceAsync {

	void addGroup(GroupModel model, int memberId,
			AsyncCallback<Integer> callback);

	void addSMS(SMSModel model, int memberId, AsyncCallback<Integer> callback);

	void addMember(MemberModel model, int memberId,
			AsyncCallback<Integer> callback);

	void addMarriage(MarriageModel model, int marriageId,
			AsyncCallback<Integer> callback);

	void addFuneral(FAnnounceModel model, int userId,
			AsyncCallback<Integer> callback);

	void addEvent(EAnnounceModel model, int userId,
			AsyncCallback<Integer> callback);

	void addOffering(OfferingModel model, int userId,
			AsyncCallback<Integer> callback);

	void addTithe(TitheModel model, int userId, AsyncCallback<Integer> callback);

	void addDonation(DonationModel model, int userId,
			AsyncCallback<Integer> callback);

	void addClassLeader(ClassModel model, int userId,
			AsyncCallback<Integer> callback);

	void addSpecialOffering(SpecialOfferingModel model, int userId,
			AsyncCallback<Integer> callback);

	void addBibleReading(BibleReadingModel model, int userId,
			AsyncCallback<Integer> callback);

	void addPowerLeader(PowerLeaderModel model, int userId,
			AsyncCallback<Integer> callback);

}
