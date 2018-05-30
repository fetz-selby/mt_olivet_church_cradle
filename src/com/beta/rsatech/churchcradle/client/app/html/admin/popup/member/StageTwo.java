package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageTwo extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;
	private boolean isMale, isFemale;
	private HashMap<String, Integer> eduTrackMap;
	private HashMap<String, Integer> regionsMap;
	private static StageTwoUiBinder uiBinder = GWT
			.create(StageTwoUiBinder.class);

	interface StageTwoUiBinder extends UiBinder<Widget, StageTwo> {
	}

	@UiField InputElement maleInput, femaleInput;
	@UiField TextBox occupationField, dobField, employerField, nationalityField, hometownField;
	@UiField ListBox eLevelField, regionsField;
	@UiField TextArea addressField;
	
	public StageTwo(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	
	private void initComponent(MemberModel model){
		addressField.getElement().setAttribute("placeholder", "Residence Address");
		dobField.getElement().setAttribute("placeholder", "Date of Birth  (yyyy-mm-dd)");
		occupationField.getElement().setAttribute("placeholder", "Occupation/Profession");
		employerField.getElement().setAttribute("placeholder", "Company / Institution");
		hometownField.getElement().setAttribute("placeholder", "Hometown");
		nationalityField.getElement().setAttribute("placeholder", AppConstants.DEFAULT_NATIONALITY);
		
		initEducationList();
		initRegions();
		
		if(model != null){
			addressField.setText(model.getAddress() != null ? model.getAddress() : "");
			dobField.setText(model.getDateOfBirth() != null ? model.getDateOfBirth() : "");
			occupationField.setText(model.getOccupation() != null ? model.getOccupation() : "");
			employerField.setText(model.getEmployer() != null ? model.getEmployer() : "");
			nationalityField.setText(model.getNationality() != null ? model.getNationality() : "");
			hometownField.setText(model.getHometown() != null ? model.getHometown() : "");
			
			if(model.getGender() != null && model.getGender().equalsIgnoreCase(AppConstants.MALE)){
				enableMaleCheck();
			}else if(model.getGender() != null && model.getGender().equalsIgnoreCase(AppConstants.FEMALE)){
				enableFemaleCheck();
			}
			
			if(model.geteLevel() != null && !model.geteLevel().isEmpty()){
				eLevelField.setItemSelected(eduTrackMap.get(model.geteLevel()), true);
			}
			
			if(model.getRegion() != null && !model.getRegion().isEmpty()){
				regionsField.setItemSelected(regionsMap.get(model.getRegion()), true);
			}
		}
	}
	
	private void initEducationList(){
		HashMap<String, String> eduMap = GlobalResources.getInstance().getEducationalLevelMap();
		eduTrackMap = new HashMap<String, Integer>();
		
		int index = 0;
		for(String key : eduMap.keySet()){
			eLevelField.addItem(key, eduMap.get(key));
			eduTrackMap.put(eduMap.get(key), index);
			
			index ++;
		}
	}
	
	private void initRegions(){
		HashMap<String, String> regMap = GlobalResources.getInstance().getRegionsMap();
		regionsMap = new HashMap<String, Integer>();
		
		int index = 1;
		//Init region
		regionsField.addItem("-- Select Region --", "0");
		for(String key : regMap.keySet()){
			regionsField.addItem(key, regMap.get(key));
			regionsMap.put(regMap.get(key), index);
			
			index ++;
		}
	}
	
	private void enableMaleCheck(){
		maleInput.setAttribute("checked", "");
		isMale = true;
	}
	
	private void enableFemaleCheck(){
		femaleInput.setAttribute("checked", "");
		isFemale = true;
	}
	
	private void initEvent(){
		DOM.sinkEvents(maleInput, Event.ONCLICK);
		DOM.setEventListener(maleInput, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isMale = true;
				isFemale = false;
			}
		});
		
		DOM.sinkEvents(femaleInput, Event.ONCLICK);
		DOM.setEventListener(femaleInput, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isFemale = true;
				isMale = false;
			}
		});
	}
	
	@Override
	public void validateAndProceed() {
		if(isMale == false && isFemale == false){
			doPublishError("Please choose a gender");
			return;
		}
		
		if(dobField.getText().trim().isEmpty()){
			doPublishError("Enter date of birth");
			return;
		}
		
		if(!Utils.isDateValid(dobField.getText().trim(), "-")){
			doPublishError("Wrong date");
			return;
		}
		
		if(addressField.getText().trim().isEmpty()){
			doPublishError("Please specify address");
			return;
		}
		
		if(addressField.getText().trim().length() < 5){
			doPublishError("Address is too short");
			return;
		}
		
		if(regionsField.getSelectedIndex() == 0){
			doPublishError("Please select a region");
			return;
		}
		
		if(hometownField.getText().trim().length() < 3){
			doPublishError("Please specify hometown");
			return;
		}
			
		doPrepareMemberModel();
		next();		
	}
	
	public void next(){
		wizardHandler.onValidateComplete(WizardStage.TWO_SUB, model);
	}
	
	public void back(){
		wizardHandler.onValidateComplete(WizardStage.ONE, model);
	}
	
	private void doPrepareMemberModel(){
		model.setDateOfBirth(dobField.getText().trim());
		model.setGender(isMale?AppConstants.MALE:AppConstants.FEMALE);
		model.setAddress(addressField.getText().trim());
		model.setOccupation(occupationField.getText().trim());
		model.setEmployer(employerField.getText().trim());
		model.seteLevel(eLevelField.getValue(eLevelField.getSelectedIndex()));
		model.setRegion(regionsField.getValue(regionsField.getSelectedIndex()));
		model.setNationality(nationalityField.getText().trim().isEmpty()?AppConstants.DEFAULT_NATIONALITY:nationalityField.getText().trim());
		model.setHometown(hometownField.getText().trim());
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
