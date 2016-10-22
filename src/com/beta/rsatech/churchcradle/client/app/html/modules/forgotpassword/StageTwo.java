package com.beta.rsatech.churchcradle.client.app.html.modules.forgotpassword;

import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.ForgotPasswordModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageTwo extends Composite implements IsWizardable<ForgotPasswordModel>{

	private HasWizardEvent<ForgotPasswordModel> wizardHandler;
	private ForgotPasswordModel model;

	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageTwo> {
	}

	@UiField Image img;
	@UiField TextBox firstNameField, lastNameField, msisdnField;
	@UiField TextBox codeField;
	
	public StageTwo(ForgotPasswordModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(ForgotPasswordModel model){
		if(model != null){
			firstNameField.setText(model.getFirstname());
			lastNameField.setText(model.getLastname());
			msisdnField.setText(model.getMsisdn());
			
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
			
			//img.setUrl(model.getAvatar());
			
			firstNameField.setEnabled(false);
			lastNameField.setEnabled(false);
			msisdnField.setEnabled(false);
			
			if(!model.isStageTwoValidated()){
				codeField.getElement().setAttribute("placeholder", "Enter Code");
			}else{
				codeField.setText(model.getCode());
				codeField.setEnabled(false);
			}
		}
	}

	private void initEvent(){

	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void validateAndProceed() {

		if(codeField.getText().trim().isEmpty()){
			doPublishError("Code cannot be empty.");
			return;
		}
		
		if(codeField.getText().trim().equalsIgnoreCase(model.getCode())){
			model.setStageTwoValidated(true);
			next();
		}
	}

	private void doPrepareMemberModel(){
	}
	
	@Override
	public void setHasWizardEvent(HasWizardEvent<ForgotPasswordModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		back();
	}

	@Override
	public void next() {
		doPrepareMemberModel();
		wizardHandler.onValidateComplete(WizardStage.THREE, model);		
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.ONE, model);	
	}
}
