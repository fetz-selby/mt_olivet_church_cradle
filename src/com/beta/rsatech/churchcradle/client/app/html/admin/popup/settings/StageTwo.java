package com.beta.rsatech.churchcradle.client.app.html.admin.popup.settings;

import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageTwo extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;
	private static StageTwoUiBinder uiBinder = GWT
			.create(StageTwoUiBinder.class);

	interface StageTwoUiBinder extends UiBinder<Widget, StageTwo> {
	}

	@UiField PasswordTextBox passwordField, rePasswordField;
	
	public StageTwo(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	
	private void initComponent(MemberModel model){
		passwordField.getElement().setAttribute("placeholder", "Enter new password");
		rePasswordField.getElement().setAttribute("placeholder", "Re-Enter new password");
		
		if(model != null){
			passwordField.setText(model.getPassword() != null ? model.getPassword() : "");
			rePasswordField.setText(model.getPassword() != null ? model.getPassword() : "");
		}
	}
	
	private void initEvent(){}
	
	@Override
	public void validateAndProceed() {
		if(passwordField.getText().trim().isEmpty()){
			doPublishError("Password cannot be empty.");
			return;
		}
		
		if(passwordField.getText().trim().length() <= 4){
			doPublishError("Password must be five(5) or more characters.");
			return;
		}
		
		if(!passwordField.getText().trim().equals(rePasswordField.getText().trim())){
			doPublishError("Passwords do not match.");
			return;
		}
		
		doPrepareMemberModel();
		next();
		
	}
	
	public void next(){
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);
	}
	
	public void back(){
		wizardHandler.onValidateComplete(WizardStage.ONE, model);
	}
	
	private void doPrepareMemberModel(){
		model.setPassword(passwordField.getText().trim());
	}
	
	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareMemberModel();
		back();		
	}

}
