package com.beta.rsatech.churchcradle.client.app.html.admin.popup.event;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<EAnnounceModel>{

	private HasWizardEvent<EAnnounceModel> wizardHandler;
	private EAnnounceModel model;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox venueField, dateField, timeField;
	@UiField(provided = true) ReviewPopupTextArea descField;

	public StageOne(EAnnounceModel model) {
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

	private void initComponent(EAnnounceModel model){

		venueField.getElement().setAttribute("placeholder", "Venue");
		dateField.getElement().setAttribute("placeholder", "yyyy-mm-dd");
		timeField.getElement().setAttribute("placeholder", "12 PM");
		descField.getElement().setAttribute("placeholder", "Description ...");

		if(model != null){
			venueField.setText(model.getVenue());
			dateField.setText(model.getDate());
			timeField.setText(model.getTime() == null || model.getTime().trim().isEmpty() ? "" : model.getTime());
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
		if(model == null){
			model = new EAnnounceModel();
		}

		model.setVenue(venueField.getText().trim());
		model.setDate(dateField.getText().trim());
		model.setDescription(descField.getText());
		model.setTime(timeField.getText().trim().isEmpty()?"12 pm":timeField.getText().trim());
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<EAnnounceModel> event) {
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
