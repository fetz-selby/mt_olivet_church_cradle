package com.beta.rsatech.churchcradle.client.app.html.admin.popup.funeral;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageTwo extends Composite implements IsWizardable<FAnnounceModel>{

	private HasWizardEvent<FAnnounceModel> wizardHandler;
	private FAnnounceModel model;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageTwo> {
	}

	@UiField TextBox venueField, dateField;
	@UiField(provided = true) ReviewPopupTextArea descField;

	public StageTwo(FAnnounceModel model) {
		this.model = model;
		descField = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(FAnnounceModel model){

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
	public void setHasWizardEvent(HasWizardEvent<FAnnounceModel> event) {
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
