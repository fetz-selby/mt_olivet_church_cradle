package com.beta.rsatech.churchcradle.client.app.html.admin.popup.funeral;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<FAnnounceModel>{

	private FAnnounceModel model;
	private HasWizardEvent<FAnnounceModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement venueField, dateField, descriptionField;
	@UiField ImageElement maleAvatar;
	@UiField DivElement maleNameDiv;
	@UiField SmallElement maleOccupation;
	
	public ReviewStage(FAnnounceModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		Utils.retrieveFromBlobstore(model.getAvatar(), new GeneralEventHandler<BlobstoreModel>() {
			
			@Override
			public void onSuccess(BlobstoreModel t) {
				maleAvatar.setSrc(t.getUrl());				
			}
			
			@Override
			public void onError() {
				// TODO Auto-generated method stub
				
			}
		});
		
		maleNameDiv.setInnerText(model.getName());
		maleOccupation.setInnerText(""+model.getAge());
		
		venueField.setInnerText(model.getVenue());
		dateField.setInnerText(model.getDate());
		descriptionField.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.REVIEW_DESCRIPTION_LIMIT));
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
	public void setHasWizardEvent(HasWizardEvent<FAnnounceModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.DONE, model);
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.TWO, model);
	}

}
