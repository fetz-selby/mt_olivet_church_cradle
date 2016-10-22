package com.beta.rsatech.churchcradle.client.app.html.admin.popup.marriage;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<MarriageModel>{

	private MarriageModel model;
	private HasWizardEvent<MarriageModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement venueField, dateField, descriptionField;
	@UiField ImageElement maleAvatar, femaleAvatar;
	@UiField DivElement maleNameDiv, femaleNameDiv;
	@UiField SmallElement maleOccupation, femaleOccupation;
	
	public ReviewStage(MarriageModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		maleNameDiv.setInnerText(model.getManName());
		femaleNameDiv.setInnerText(model.getFemaleName());
		
		maleOccupation.setInnerText(model.getmOccupation());
		femaleOccupation.setInnerText(model.getfOccupation());
		
		venueField.setInnerText(model.getVenue());
		dateField.setInnerText(model.getDate());
		descriptionField.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.REVIEW_DESCRIPTION_LIMIT));
		
		if(model.getmAvatar()!=null && !model.getmAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(model.getmAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					maleAvatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		if(model.getfAvatar()!=null && !model.getfAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(model.getfAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					femaleAvatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		//maleAvatar.setSrc(model.getmAvatar());
		//femaleAvatar.setSrc(model.getfAvatar());
	}
	
	private void prepareMemberModel(){
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
	public void setHasWizardEvent(HasWizardEvent<MarriageModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.DONE, model);
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.THREE, model);
	}

}
