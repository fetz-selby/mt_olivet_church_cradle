package com.beta.rsatech.churchcradle.client.app.html.admin.popup.settings;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;

	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField Image img;
	@UiField TextBox firstNameField, lastNameField, msisdnField;
	@UiField PasswordTextBox oldPasswordField;
	
	public StageOne(MemberModel model) {
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

	private void initComponent(MemberModel model){
		if(model != null){
			firstNameField.setText(model.getFirstName());
			lastNameField.setText(model.getLastName());
			msisdnField.setText(model.getMsisdn());
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
			
			if(model.getOldPassword() != null){
				oldPasswordField.setText(model.getOldPassword());
			}else{
				oldPasswordField.getElement().setAttribute("placeholder", "Enter current password");
			}
			
			firstNameField.setEnabled(false);
			lastNameField.setEnabled(false);
			msisdnField.setEnabled(false);
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

		if(oldPasswordField.getText().trim().isEmpty()){
			doPublishError("Old Password cannot be empty.");
			return;
		}
		
		if(oldPasswordField.getText().trim().length() <= 4){
			doPublishError("Password must be five(5) or more characters long.");
			return;
		}
		
		Utils.validatePassword(oldPasswordField.getText().trim(), GlobalResources.getInstance().getModel().getChurchId(), GlobalResources.getInstance().getModel().getId(), new GeneralEventHandler<Boolean>() {
			
			@Override
			public void onSuccess(Boolean t) {
				if(t){
					if(wizardHandler != null){
						next();
					}
				}else{
					doPublishError("Wrong Password");
				}
			}
			
			@Override
			public void onError() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void doPrepareMemberModel(){
		if(model == null){
			model = new MemberModel();
		}
		
		model.setOldPassword(oldPasswordField.getText().trim());
	}
	
	

	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void next() {
		doPrepareMemberModel();
		wizardHandler.onValidateComplete(WizardStage.TWO, model);		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub

	}
}
