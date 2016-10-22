package com.beta.rsatech.churchcradle.client.app.html.admin.popup.funeral;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomFileUpload;
import com.beta.rsatech.churchcradle.client.widgets.CustomFileUpload.CustomFileUploadEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<FAnnounceModel>{

	private HasWizardEvent<FAnnounceModel> wizardHandler;
	private FAnnounceModel model;

	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField Image img;
	@UiField CustomFileUpload uploadWidget;
	@UiField TextBox fullNameField, ageField;
	public StageOne(FAnnounceModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	public StageOne() {
		model = null;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(null);
		initEvent();
	}

	private void initComponent(FAnnounceModel model){
		img.setUrl(AppConstants.NO_IMAGE);

		fullNameField.getElement().setAttribute("placeholder", "Full Name");
		ageField.getElement().setAttribute("placeholder", "Age");

		if(model != null){
			fullNameField.setText(model.getName());
			ageField.setText(""+model.getAge());
			
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
		}
	}

	private void initEvent(){
		uploadWidget.setCustomFileUploadEventHandler(new CustomFileUploadEventHandler() {

			@Override
			public void onFormSubmit(final String avatarString, final String blobKey) {
				
				//Delete old blobKey

				if(model == null){
					model = new FAnnounceModel();
				}
				
				if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
					GlobalResources.getInstance().getUpdateRPC().deleteOrphanBlob(model.getAvatar(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							img.setUrl(avatarString);
							model.setAvatar(blobKey);							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}else{
					img.setUrl(avatarString);
					model.setAvatar(blobKey);
				}
			}
		});
	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void validateAndProceed() {
		if(fullNameField.getText().trim().isEmpty()){
			doPublishError("Full Name cannot be empty!");
			return;
		}
		
		if(ageField.getText().trim().isEmpty()){
			doPublishError("Age cannot be empty!");
			return;
		}
		
		if(isAvatarEmpty()){
			doPublishError("Please select a picture");
			return;
		}

		if(wizardHandler != null){
			next();
		}
	}

	private boolean isAvatarEmpty(){

		if(img.getUrl().contains(AppConstants.NO_IMAGE)){
			return true;
		}
		return false;
	}

	private void doPrepareMarriageModel(){
		if(model == null){
			model = new FAnnounceModel();
		}

		model.setName(fullNameField.getText().trim());
		model.setAge(Integer.parseInt(ageField.getText().trim()));
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<FAnnounceModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void next() {
		doPrepareMarriageModel();
		wizardHandler.onValidateComplete(WizardStage.TWO, model);		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub

	}
}
