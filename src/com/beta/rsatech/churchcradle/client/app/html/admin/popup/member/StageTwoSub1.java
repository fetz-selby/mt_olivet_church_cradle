package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
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

public class StageTwoSub1 extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;
	private boolean isMarried, isSingle, isWidow, isDivorced;
	private String maritalStatus = "";
	private static StageTwoSub1UiBinder uiBinder = GWT
			.create(StageTwoSub1UiBinder.class);

	interface StageTwoSub1UiBinder extends UiBinder<Widget, StageTwoSub1> {
	}

	@UiField InputElement mInput, wInput,dInput,sInput;
	@UiField TextBox spouseField, kinField, kinMsisdnField;
	
	
	public StageTwoSub1(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	private void initComponent(MemberModel model){
		spouseField.getElement().setAttribute("placeholder", "Spouse Name");
		kinField.getElement().setAttribute("placeholder", "Next of Kin");
		kinMsisdnField.getElement().setAttribute("placeholder", "Kin mobile number");
		
		if(model != null){
			spouseField.setText(model.getSpouseName() != null ? model.getSpouseName() : "");
			kinField.setText(model.getKinName() != null ? model.getKinName() : "");
			kinMsisdnField.setText(model.getKinMsisdn() != null ? model.getKinMsisdn() : "");
			
			if(model.getMaritalStatus() != null && model.getMaritalStatus().equalsIgnoreCase(AppConstants.MARRIED)){
				enableMarriedCheck();
			}else if(model.getMaritalStatus() != null && model.getMaritalStatus().equalsIgnoreCase(AppConstants.SINGLE)){
				enableSingleCheck();
			}else if(model.getMaritalStatus() != null && model.getMaritalStatus().equalsIgnoreCase(AppConstants.DIVORCED)){
				enableDivorcedCheck();
			}else if(model.getMaritalStatus() != null && model.getMaritalStatus().equalsIgnoreCase(AppConstants.WIDOW)){
				enableWidowCheck();
			}
		}
	}
	
	private void enableMarriedCheck(){
		mInput.setAttribute("checked", "");
		isMarried = true;
		maritalStatus = AppConstants.MARRIED;
	}
	
	private void enableWidowCheck(){
		wInput.setAttribute("checked", "");
		isWidow = true;
		maritalStatus = AppConstants.WIDOW;
	}
	
	private void enableDivorcedCheck(){
		dInput.setAttribute("checked", "");
		isDivorced = true;
		maritalStatus = AppConstants.DIVORCED;
	}
	
	private void enableSingleCheck(){
		sInput.setAttribute("checked", "");
		isSingle = true;
		maritalStatus = AppConstants.SINGLE;
	}
	
	private void showSpouse(boolean isShow){
		if(isShow){
			spouseField.getElement().removeClassName("hide");
		}else{
			spouseField.getElement().addClassName("hide");
		}
	}
	
	private void initEvent(){
		DOM.sinkEvents(mInput, Event.ONCLICK);
		DOM.setEventListener(mInput, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isMarried = true;
				isWidow = false;
				isSingle = false;
				isDivorced = false;
				
				showSpouse(true);
				maritalStatus = AppConstants.MARRIED;
			}
		});
		
		DOM.sinkEvents(wInput, Event.ONCLICK);
		DOM.setEventListener(wInput, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isMarried = false;
				isWidow = true;
				isSingle = false;
				isDivorced = false;
				
				showSpouse(false);
				maritalStatus = AppConstants.WIDOW;
			}
		});
		
		DOM.sinkEvents(sInput, Event.ONCLICK);
		DOM.setEventListener(sInput, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isMarried = false;
				isWidow = false;
				isSingle = true;
				isDivorced = false;
				
				showSpouse(false);
				maritalStatus = AppConstants.SINGLE;
			}
		});
		
		DOM.sinkEvents(dInput, Event.ONCLICK);
		DOM.setEventListener(dInput, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isMarried = false;
				isWidow = false;
				isSingle = false;
				isDivorced = true;
				
				showSpouse(false);
				maritalStatus = AppConstants.DIVORCED;
			}
		});
	}
	
	@Override
	public void validateAndProceed() {
		if(isMarried == false && isSingle == false && isDivorced == false && isWidow == false){
			doPublishError("Please choose a marital status");
			return;
		}
		
		if(kinField.getText().trim().isEmpty()){
			doPublishError("Enter next of kin");
			return;
		}
		
		if(!Utils.isMsisdnValid(kinMsisdnField.getText().trim())){
			doPublishError("Wrong mobile number");
			return;
		}
		
		doPrepareMemberModel();
		next();
		
	}
	
	public void next(){
		wizardHandler.onValidateComplete(WizardStage.THREE, model);
	}
	
	public void back(){
		wizardHandler.onValidateComplete(WizardStage.TWO, model);
	}
	
	private void doPrepareMemberModel(){
		model.setSpouseName(spouseField.getText().trim());
		model.setMaritalStatus(maritalStatus);
		model.setKinName(kinField.getText().trim());
		model.setKinMsisdn(kinMsisdnField.getText().trim());
	}
	
	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
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

}
