package com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.sms;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.SMSPurchaseModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<SMSPurchaseModel>{

	private HasWizardEvent<SMSPurchaseModel> wizardHandler;
	private SMSPurchaseModel model;
	private double smsRate, price;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox amountField, quantityField;

	public StageOne(SMSPurchaseModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	
	public StageOne() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(SMSPurchaseModel model){

		smsRate = GlobalResources.getInstance().getChurchModel().getPricePerSMS();
		amountField.getElement().setAttribute("placeholder", "0.0 GHS");
		quantityField.getElement().setAttribute("placeholder", "SMS Quantity ...");

		if(model != null){
			amountField.setText(""+model.getAmount());
			quantityField.setText(""+model.getSmsQuantity());
		}
		
		amountField.setEnabled(false);
	}

	private void initEvent(){
		quantityField.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(Utils.isNumbers(quantityField.getText().trim())){
					price = (Double.parseDouble(quantityField.getText().trim())*smsRate);
					amountField.setText(price+" GHS");
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
		if(quantityField.getText().trim().isEmpty()){
			doPublishError("SMS Quantity cannot be empty!");
			return;
		}
		
		if(!Utils.isNumbers(quantityField.getText().trim())){
			doPublishError("Invalid Quantity input!");
			return;
		}
		

		doPrepareSMSPriceModel();
		next();
	}

	private void doPrepareSMSPriceModel(){
		if(model == null){
			model = new SMSPurchaseModel();
		}

		model.setAmount(Math.round((Double.parseDouble(quantityField.getText().trim())*smsRate)*100d)/100d);
		model.setSmsQuantity(Double.parseDouble(quantityField.getText().trim()));
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
		model.setMemberId(GlobalResources.getInstance().getModel().getId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<SMSPurchaseModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareSMSPriceModel();
		back();
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);		
	}

	@Override
	public void back() {
	}

}
