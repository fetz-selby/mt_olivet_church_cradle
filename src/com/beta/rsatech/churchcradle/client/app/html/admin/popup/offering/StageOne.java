package com.beta.rsatech.churchcradle.client.app.html.admin.popup.offering;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<OfferingModel>{

	private HasWizardEvent<OfferingModel> wizardHandler;
	private OfferingModel model;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox amountField;
	@UiField(provided = true) ReviewPopupTextArea descField;

	public StageOne(OfferingModel model) {
		this.model = model;
		descField = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	
	public StageOne() {
		descField = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(OfferingModel model){

		amountField.getElement().setAttribute("placeholder", "GHS");
		descField.getElement().setAttribute("placeholder", "Description ...");

		if(model != null){
			amountField.setText(""+model.getAmount());
			descField.setText(model.getDescription() == null ? "" : model.getDescription());
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
			doPublishError("Amount cannot be empty!");
			return;
		}
		if(descField.getText().trim().isEmpty()){
			doPublishError("Description cannot be empty!");
			return;
		}

		doPrepareMarriageModel();
		next();
	}

	private void doPrepareMarriageModel(){
		if(model == null){
			model = new OfferingModel();
		}

		model.setAmount(Double.parseDouble(amountField.getText().trim()));
		model.setDescription(descField.getText());
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<OfferingModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareMarriageModel();
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
