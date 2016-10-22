package com.beta.rsatech.churchcradle.client.app.html.admin.popup.biblereading;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<BibleReadingModel>{

	private HasWizardEvent<BibleReadingModel> wizardHandler;
	private BibleReadingModel model;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox preacherField, verseField, dateField;
	@UiField (provided = true) ReviewPopupTextArea descField;

	public StageOne(BibleReadingModel model) {
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

	private void initComponent(BibleReadingModel model){
		preacherField.getElement().setAttribute("placeholder", "Preacher Name");
		verseField.getElement().setAttribute("placeholder", "Verse");
		dateField.getElement().setAttribute("placeholder", "YYYY-MM-DD");


		descField.getElement().setAttribute("placeholder", "Message/Theme ...");

		if(model != null){
			preacherField.setText(model.getName());
			verseField.setText(model.getDescription());
			descField.setText(model.getTheme());
			dateField.setText(model.getDate());
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
		if(preacherField.getText().trim().isEmpty()){
			doPublishError("Preacher Name cannot be empty!");
			return;
		}
		
		if(verseField.getText().trim().isEmpty()){
			doPublishError("Verses cannot be empty!");
			return;
		}
		
		if(descField.getText().trim().isEmpty()){
			doPublishError("Theme/Message cannot be empty!");
			return;
		}
		
		if(dateField.getText().trim().isEmpty()){
			doPublishError("Date cannot be empty!");
			return;
		}
		
		if(!Utils.isDateValid(dateField.getText().trim(), "-")){
			doPublishError("Wrong date");
			return;
		}

		doPrepareSpecialOfferingModel();
		next();
	}

	private void doPrepareSpecialOfferingModel(){
		if(model == null){
			model = new BibleReadingModel();
		}

		model.setName(Utils.getCapitalizedWord(preacherField.getText().trim()));
		model.setDescription(verseField.getText().trim());
		model.setTheme(descField.getText().trim());
		model.setDate(dateField.getText().trim());
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<BibleReadingModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareSpecialOfferingModel();
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
