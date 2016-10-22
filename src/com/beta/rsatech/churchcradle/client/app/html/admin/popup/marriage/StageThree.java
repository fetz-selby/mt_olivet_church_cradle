package com.beta.rsatech.churchcradle.client.app.html.admin.popup.marriage;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageThree extends Composite implements IsWizardable<MarriageModel>{

	private HasWizardEvent<MarriageModel> wizardHandler;
	private MarriageModel model;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageThree> {
	}

	@UiField TextBox venueField, dateField;
	@UiField(provided = true) ReviewPopupTextArea descField;

	public StageThree(MarriageModel model) {
		this.model = model;
		descField = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(MarriageModel model){

		venueField.getElement().setAttribute("placeholder", "Venue");
		dateField.getElement().setAttribute("placeholder", "yyyy-mm-dd");
		descField.getElement().setAttribute("placeholder", "Description ...");

		if(model != null){
			venueField.setText(model.getVenue());
			dateField.setText(model.getDate());
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
		if(venueField.getText().trim().isEmpty()){
			doPublishError("Venue cannot be empty!");
			return;
		}
		if(dateField.getText().trim().isEmpty()){
			doPublishError("Date cannot be empty!");
			return;
		}

		doPrepareMarriageModel();
		next();
	}

	private void doPrepareMarriageModel(){

		model.setVenue(venueField.getText().trim());
		model.setDate(dateField.getText().trim());
		model.setDescription(descField.getText());
		//model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<MarriageModel> event) {
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
		wizardHandler.onValidateComplete(WizardStage.TWO, model);		
	}

}
