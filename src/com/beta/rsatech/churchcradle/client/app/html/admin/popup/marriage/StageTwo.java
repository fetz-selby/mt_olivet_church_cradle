package com.beta.rsatech.churchcradle.client.app.html.admin.popup.marriage;

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
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageTwo extends Composite implements IsWizardable<MarriageModel>{

	private HasWizardEvent<MarriageModel> wizardHandler;
	private MarriageModel model;

	private static StageTwoUiBinder uiBinder = GWT
			.create(StageTwoUiBinder.class);

	interface StageTwoUiBinder extends UiBinder<Widget, StageTwo> {
	}

	@UiField Image img;
	@UiField CustomFileUpload uploadWidget;
	@UiField TextBox fullNameField, occupationField;
	public StageTwo(MarriageModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(MarriageModel model){
		img.setUrl(AppConstants.NO_IMAGE);

		fullNameField.getElement().setAttribute("placeholder", "Full Name");
		occupationField.getElement().setAttribute("placeholder", "Occupation");

		if(model != null){
			fullNameField.setText(model.getFemaleName());
			occupationField.setText(model.getfOccupation());
			//img.setUrl(model.getfAvatar() == null || model.getfAvatar().trim().isEmpty() ? AppConstants.NO_IMAGE : model.getfAvatar());
			
			if(model.getfAvatar() != null && !model.getfAvatar().trim().isEmpty()){
				Utils.retrieveFromBlobstore(model.getfAvatar(), new GeneralEventHandler<BlobstoreModel>() {
					
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
				if(model.getfAvatar() != null && !model.getfAvatar().trim().isEmpty()){
					GlobalResources.getInstance().getUpdateRPC().deleteOrphanBlob(model.getfAvatar(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							img.setUrl(avatarString);
							model.setfAvatar(blobKey);							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}else{
					img.setUrl(avatarString);
					model.setfAvatar(blobKey);
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

		if(occupationField.getText().trim().isEmpty()){
			doPublishError("Occupation cannot be empty!");
			return;
		}

		if(isAvatarEmpty()){
			doPublishError("Please select a picture");
			return;
		}

		doPrepareMarriageModel();
		next();
	}

	private boolean isAvatarEmpty(){

		if(img.getUrl().contains(AppConstants.NO_IMAGE)){
			return true;
		}
		return false;
	}

	private void doPrepareMarriageModel(){
		model.setFemaleName(fullNameField.getText().trim());
		model.setfOccupation(occupationField.getText().trim());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<MarriageModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareMarriageModel();
		back();
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.THREE, model);		
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.ONE, model);		

	}

}
