package com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.renew;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppRenewModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<AppRenewModel>{

	private HasWizardEvent<AppRenewModel> wizardHandler;
	private AppRenewModel model;
	private double dollarRate, appFee;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField SmallElement desc;
	@UiField SelectElement chosenElement;

	public StageOne(AppRenewModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	public StageOne(double dollarRate) {
		this.dollarRate = dollarRate;
		model = new AppRenewModel();
		model.setDollarRate(dollarRate);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(AppRenewModel model){

		appFee = GlobalResources.getInstance().getChurchModel().getAppFee();

		if(model != null){
			dollarRate = model.getDollarRate();
		}
	}

	private void initEvent(){

		DOM.sinkEvents(chosenElement, Event.ONCHANGE);
		DOM.setEventListener(chosenElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				doCalculation(chosenElement.getPropertyString("value"));
			}
		});
	}

	private void doCalculation(String monthsStr){
		int months = Integer.parseInt(monthsStr);

		double pricePerMonthInDollars = appFee / 12;
		double pricePerMonthInCedis = pricePerMonthInDollars * dollarRate;

		if(model == null){
			model = new AppRenewModel();
		}
		
		model.setAmount(Math.round(((double)months*pricePerMonthInCedis)*100d)/100d);
		model.setMonths(months);
		model.setPricePerMonth(pricePerMonthInCedis);

		desc.setInnerHTML("&#8373; "+model.getAmount()+" GHS");

	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void validateAndProceed() {
		//		if(quantityField.getText().trim().isEmpty()){
		//			doPublishError("SMS Quantity cannot be empty!");
		//			return;
		//		}

		//		if(!Utils.isNumbers(quantityField.getText().trim())){
		//			doPublishError("Invalid Quantity input!");
		//			return;
		//		}


		doPrepareAppRenewModel();
		next();
	}

	private void doPrepareAppRenewModel(){
		if(model == null){
			model = new AppRenewModel();
		}

		//model.setAmount((Double.parseDouble(quantityField.getText().trim())*smsRate));
		//model.setSmsQuantity(Double.parseDouble(quantityField.getText().trim()));
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
		model.setMemberId(GlobalResources.getInstance().getModel().getId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<AppRenewModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareAppRenewModel();
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
