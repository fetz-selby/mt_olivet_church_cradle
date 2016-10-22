package com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.sms;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.shared.SMSPurchaseModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<SMSPurchaseModel>{

	private SMSPurchaseModel model;
	private HasWizardEvent<SMSPurchaseModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement amountField;
	@UiField SmallElement desc;
	
	public ReviewStage(SMSPurchaseModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		amountField.setInnerText(model.getAmount()+" GHS");
		desc.setInnerText(model.getSmsQuantity()+" SMS");
	}
	
	private void preparSMSPurchaseModel(){
	}
	
	@Override
	public void validateAndProceed() {
		preparSMSPurchaseModel();
		next();
	}

	@Override
	public void validateAndReturn() {
		preparSMSPurchaseModel();
		back();
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<SMSPurchaseModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.DONE, model);
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.ONE, model);
	}

}
