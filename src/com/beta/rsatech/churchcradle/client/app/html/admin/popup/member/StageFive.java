package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox.CustomCheckBoxEventHandler;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class StageFive extends Composite implements IsWizardable<MemberModel>{

	private MemberModel model;
	private boolean isAdminEnabled;
	private HasWizardEvent<MemberModel> wizardHandler;
	private ArrayList<CustomCheckBox> checkBoxList;
	private ArrayList<String> selectedEntryList;
	private static StageSixUiBinder uiBinder = GWT
			.create(StageSixUiBinder.class);

	interface StageSixUiBinder extends UiBinder<Widget, StageFive> {
	}

	@UiField(provided = true) CustomCheckBox adminBox;
	@UiField FlowPanel entryModulesContainer;
	
	public StageFive(MemberModel model) {
		adminBox = new CustomCheckBox("Administrator", "1");
		initWidget(uiBinder.createAndBindUi(this));
		this.model = model;
		initSMSEnableEvent();
		initComponents();
	}
	private void initComponents(){
		checkBoxList = new ArrayList<CustomCheckBox>();
		selectedEntryList = new ArrayList<String>();
		loadCheckBoxes(GlobalResources.getInstance().getEntryModuleMap());
	}
	
	private void initSMSEnableEvent(){
		adminBox.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {
			
			@Override
			public void onCheck(String id, String name, boolean isChecked) {
				isAdminEnabled = isChecked;
			}
		});
	}

	private void loadCheckBoxes(HashMap<Integer, String> orgMap){

		for(Integer id : orgMap.keySet()){
			String entryName = orgMap.get(id);
			CustomCheckBox checkBox = new CustomCheckBox(Utils.getCapitalizedWord(entryName), ""+id);
			checkBox.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {

				@Override
				public void onCheck(String id, String labelName, boolean isChecked) {
					if(isChecked){
						if(!selectedEntryList.contains(id)){
							selectedEntryList.add(id);
						}
					}else{
						selectedEntryList.remove(id);
					}
				}
			});
			entryModulesContainer.add(checkBox);
			checkBoxList.add(checkBox);

			autoSelectCheckBoxes();
		}
	}
	
	private void autoSelectCheckBoxes(){
		if(model.getEntryModules() != null && !model.getEntryModules().trim().isEmpty()){
			String[] idTokens = model.getEntryModules().split(",");
			ArrayList<String> idList = new ArrayList<String>();
			for(String id : idTokens){
				idList.add(id);
			}
			
			for(String id : idList){
				for(CustomCheckBox checkBox : checkBoxList){
					String tmpId = checkBox.getId();
					if(tmpId.trim().equals(id)){
						checkBox.setChecked(true);
						if(!selectedEntryList.contains(id)){
							selectedEntryList.add(id);
						}
					}
				}
			}
		}
		
		if(model.isAdmin()){
			adminBox.setChecked(true);
			isAdminEnabled = true;
		}
	}
	
	private void prepareMemberModel(){
		model.setEntryModules(getIds());
		model.setAdmin(isAdminEnabled);
	}
	
	private String getIds(){
		String keys = "";
		for(String key : selectedEntryList){
			keys+=key+",";
		}
		return keys.substring(0,keys.length()-1);
	}

	@Override
	public void validateAndProceed() {
		prepareMemberModel();
		next();
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
		wizardHandler.onValidateComplete(WizardStage.SIX, model);
	}
	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.FOUR, model);
	}


}
