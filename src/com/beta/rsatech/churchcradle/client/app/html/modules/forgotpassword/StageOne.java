package com.beta.rsatech.churchcradle.client.app.html.modules.forgotpassword;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.ForgotPasswordModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<ForgotPasswordModel>{

	private HasWizardEvent<ForgotPasswordModel> wizardHandler;
	private ForgotPasswordModel model;
	private static StageTwoUiBinder uiBinder = GWT
			.create(StageTwoUiBinder.class);

	interface StageTwoUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox msisdnField;

	public StageOne(ForgotPasswordModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	
	public StageOne() {
		this.model = new ForgotPasswordModel();
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(ForgotPasswordModel model){
		msisdnField.getElement().setAttribute("placeholder", "Enter Mobile Number (eg. +233244000000)");

		if(model != null){
			if(model.isStageOneValidated()){
				msisdnField.setText(model.getMsisdn() != null ? model.getMsisdn() : "");
				msisdnField.setEnabled(false);
			}
		}
	}

	private void initEvent(){}

	@Override
	public void validateAndProceed() {
		if(msisdnField.getText().trim().isEmpty()){
			doPublishError("Mobile Number cannot be empty.");
			return;
		}

		if(!(msisdnField.getText().trim().length() > 11 && Utils.isMsisdnValid(msisdnField.getText().trim()))){
			doPublishError("Invalid Mobile Number");
			return;
		}
		
//		if(msisdnField.getText().trim().length() <= 12){
//			doPublishError("Invalid Mobile Number");
//			return;
//		}

		if(!model.isStageOneValidated()){
			GlobalResources.getInstance().getListRPC().getForgotPasswordModel(msisdnField.getText().trim(), new AsyncCallback<ForgotPasswordModel>() {

				@Override
				public void onSuccess(ForgotPasswordModel result) {
					if(result != null){
						model = result;
						model.setStageOneValidated(true);
						next();
					}else{
						doPublishError("Number does not exist in the system.");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
				}
			});
		}else{
			next();
		}
	}

	public void next(){
		doPrepareMemberModel();
		wizardHandler.onValidateComplete(WizardStage.TWO, model);
	}

	public void back(){
		//wizardHandler.onValidateComplete(WizardStage.ONE, model);
	}

	private void doPrepareMemberModel(){
		if(model == null){
			model = new ForgotPasswordModel();
		}
		model.setMsisdn(msisdnField.getText().trim());
	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<ForgotPasswordModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareMemberModel();
		back();		
	}

}
