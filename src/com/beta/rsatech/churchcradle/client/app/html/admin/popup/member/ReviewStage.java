package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox.CustomCheckBoxEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<MemberModel>{

	private MemberModel model;
	private HasWizardEvent<MemberModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement nameField, msisdnField, genderField, dobField, orgField, leaderField;
	@UiField Image img;
	@UiField SimplePanel checkboxPanel;

	private CustomCheckBox deleteMember;

	public ReviewStage(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		//img.setUrl(model.getAvatar());

		if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(model.getAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					img.setUrl(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		nameField.setInnerText(Utils.getTruncatedText(model.getFirstName()+" "+model.getLastName(), 16));
		msisdnField.setInnerText(model.getMsisdn());
		genderField.setInnerText(model.getGender().equalsIgnoreCase(AppConstants.MALE)?"Male":"Female");
		leaderField.setInnerText(Utils.getClassLeader(model.getClassId()));
		orgField.setInnerText(Utils.getTruncatedText(Utils.getOrganisations(",", model.getOrganisations()), AppConstants.MEMBER_ORGANISATION_LIMIT));
		dobField.setInnerText(model.getDateOfBirth());

		if(model.isUpdate()){
			deleteMember = new CustomCheckBox("delete", model.getId() != 0 ? model.getId()+"":"0");
			deleteMember.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {

				@Override
				public void onCheck(String id, String name, boolean isChecked) {
					if(isChecked){
						model.setDeleteMember(true);
					}else{
						model.setDeleteMember(false);
					}
				}
			});

			checkboxPanel.setWidget(deleteMember);
		}
	}

	private void prepareMemberModel(){
		model.setPassword(model.getMsisdn());
	}

	@Override
	public void validateAndProceed() {
		prepareMemberModel();
		next();
	}

	@Override
	public void validateAndReturn() {
		prepareMemberModel();
		back();
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.DONE, model);
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.SIX, model);
	}

}
