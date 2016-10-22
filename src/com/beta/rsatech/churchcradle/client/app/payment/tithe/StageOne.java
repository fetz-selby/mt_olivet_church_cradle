package com.beta.rsatech.churchcradle.client.app.payment.tithe;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.PaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<PaymentModel>{

	private HasWizardEvent<PaymentModel> wizardHandler;
	private PaymentModel model;
	private String month;
	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox fullNameField, monthField, amountField;
	public StageOne(PaymentModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	public StageOne(String month) {
		model = null;
		this.month = month;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(null);
		initEvent();
	}

	private void initComponent(PaymentModel model){
		fullNameField.setText(GlobalResources.getInstance().getModel().getFirstName()+" "+GlobalResources.getInstance().getModel().getLastName());
		amountField.getElement().setAttribute("placeholder", "Amount");
		
		fullNameField.setEnabled(false);
		monthField.setEnabled(false);
		
		
		if(model != null){
			amountField.setText(model.getAmount()+"");
			monthField.setText(model.getMonth());
		}else{
			monthField.setText(month);
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
		if(amountField.getText().trim().isEmpty()){
			doPublishError("Amount field cannot be empty.");
			return;
		}
		
		if(Integer.parseInt(amountField.getText().trim()) < 1){
			doPublishError("Amount must be greater than 1.");
			return;
		}
		
		if(wizardHandler != null){
			next();
		}
	}

	private void doPreparePaymentModel(){
		if(model == null){
			model = new PaymentModel();
		}
		
		model.setAmount(Integer.parseInt(amountField.getText().trim()));
		model.setMonth(month);
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<PaymentModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void next() {
		doPreparePaymentModel();
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub

	}
}
