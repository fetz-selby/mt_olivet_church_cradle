package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageTwoSub2 extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;
	private boolean isBaptise;

	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageTwoSub2> {
	}

	@UiField InputElement baptiseYesChk, baptiseNoChk;
	@UiField TextBox ministerBox, dateOfBaptiseBox, placeOfBaptiseBox, previousChurch;
	public StageTwoSub2(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	public StageTwoSub2() {
		model = null;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(null);
		initEvent();
	}

	private void initComponent(MemberModel model){
		ministerBox.getElement().setAttribute("placeholder", "Name of Minister");
		dateOfBaptiseBox.getElement().setAttribute("placeholder", "Date of Baptism");
		placeOfBaptiseBox.getElement().setAttribute("placeholder", "Place of Baptism");
		previousChurch.getElement().setAttribute("placeholder", "Previous Church");

		if(model != null){
			ministerBox.setText(model.getNameOfMinister());
			dateOfBaptiseBox.setText(model.getDateOfBaptism());
			placeOfBaptiseBox.setText(model.getPlaceOfBaptism());
			previousChurch.setText(model.getPreviousChurch());
			
			if(!model.getNameOfMinister().trim().isEmpty() || !model.getDateOfBaptism().trim().isEmpty() || !model.getPlaceOfBaptism().trim().isEmpty()){
				setBaptismCheck(true);
			}
		}
	}
	
	private void setBaptismCheck(boolean isChecked){
		if(isChecked){
			baptiseYesChk.setAttribute("checked", "");
			showBaptismDetails(true);
		}else{
			baptiseNoChk.setAttribute("checked", "");
			showBaptismDetails(false);
		}
	}

	private void initEvent(){
		DOM.sinkEvents(baptiseYesChk, Event.ONCLICK);
		DOM.setEventListener(baptiseYesChk, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isBaptise = true;
				showBaptismDetails(true);
			}
		});
		
		DOM.sinkEvents(baptiseNoChk, Event.ONCLICK);
		DOM.setEventListener(baptiseNoChk, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isBaptise = false;
				showBaptismDetails(false);
			}
		});
	}
	
	private void showBaptismDetails(boolean isShow){
		if(isShow){
			ministerBox.getElement().removeClassName("hide");
			dateOfBaptiseBox.getElement().removeClassName("hide");
			placeOfBaptiseBox.getElement().removeClassName("hide");
		}else{
			ministerBox.getElement().addClassName("hide");
			dateOfBaptiseBox.getElement().addClassName("hide");
			placeOfBaptiseBox.getElement().addClassName("hide");
		}
	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void validateAndProceed() {
		
		if(isBaptise && ministerBox.getText().trim().isEmpty()){
			doPublishError("Minister Name cannot be empty!");
			return;
		}
		if(isBaptise && dateOfBaptiseBox.getText().trim().isEmpty()){
			doPublishError("Date of Baptism cannot be empty!");
			return;
		}
		if(isBaptise && placeOfBaptiseBox.getText().trim().isEmpty()){
			doPublishError("Place of Baptism cannot be empty!");
			return;
		}
		
		doPrepareMemberModel();
		next();
	}

	private void doPrepareMemberModel(){
		if(model == null){
			model = new MemberModel();
		}

		model.setNameOfMinister(ministerBox.getText().trim());
		model.setDateOfBaptism(dateOfBaptiseBox.getText().trim());
		model.setPlaceOfBaptism(placeOfBaptiseBox.getText().trim());
		model.setPreviousChurch(previousChurch.getText().trim());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareMemberModel();
		back();	
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.THREE, model);		
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.TWO_SUB, model);
	}
}
