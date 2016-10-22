package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageSix extends Composite implements IsWizardable<MemberModel>{

	private MemberModel model;
	private HasWizardEvent<MemberModel> wizardHandler;
	private static StageSixUiBinder uiBinder = GWT
			.create(StageSixUiBinder.class);

	interface StageSixUiBinder extends UiBinder<Widget, StageSix> {
	}

	@UiField TextBox dobField;

	@UiField(provided = true) ReviewPopupTextArea otherInfo;


	public StageSix(MemberModel model) {
		otherInfo = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		this.model = model;
		initComponents();
	}
	private void initComponents(){
		dobField.getElement().setAttribute("placeholder", "Year of Commencement  (yyyy)");

		if(model.getOtherInfo() != null){
			otherInfo.setText(model.getOtherInfo());
		}else{
			otherInfo.getElement().setAttribute("placeholder", "Specify other info ...");
		}
		
		if(model.getDateOfCommencement() != null){
			dobField.setText(model.getDateOfCommencement() != null ? model.getDateOfCommencement() : "");
		}else{
			dobField.getElement().setAttribute("placeholder", "Year of Commencement  (yyyy)");
		}
	}
	
	private void prepareMemberModel(){
		model.setOtherInfo(otherInfo.getText().trim());
		model.setDateOfCommencement(dobField.getText().trim());
	}

	@Override
	public void validateAndProceed() {
		if(dobField.getText().trim().isEmpty()){
			doPublishError("Enter Year of Commencement");
			return;
		}
		
		if(!Utils.isYearValid(dobField.getText().trim())){
			doPublishError("Wrong Year");
			return;
		}
		
		prepareMemberModel();
		next();
	}
	
	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}
	
	@Override
	public void validateAndReturn() {
		prepareMemberModel();
		back();
	}
	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}
	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);
	}
	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.FIVE, model);
	}


}
