package com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.renew;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.shared.AppRenewModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<AppRenewModel>{

	private AppRenewModel model;
	private HasWizardEvent<AppRenewModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement amountField;
	@UiField SmallElement desc;
	
	public ReviewStage(AppRenewModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		amountField.setInnerHTML("&#8373; "+model.getAmount()+" GHS");
		desc.setInnerText(model.getMonths()+" Months");
	}
	
	private void preparAppRenewPurchaseModel(){
		model.setAmount(Math.round(model.getAmount()));
	}
	
	private void revertValues(){
		model.setAmount(0);
		model.setMonths(0);
		model.setPricePerMonth(0);
	}
	
	@Override
	public void validateAndProceed() {
		preparAppRenewPurchaseModel();
		next();
	}

	@Override
	public void validateAndReturn() {
		revertValues();
		back();
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<AppRenewModel> event) {
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
