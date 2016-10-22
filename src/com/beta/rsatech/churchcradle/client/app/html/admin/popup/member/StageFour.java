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

public class StageFour extends Composite implements IsWizardable<MemberModel>{

	private MemberModel model;
	private boolean isSMSEnabled, isPaymentEnabled;
	private HasWizardEvent<MemberModel> wizardHandler;
	private ArrayList<CustomCheckBox> checkBoxList;
	private ArrayList<String> selectedApproveList;
	private static StageFourUiBinder uiBinder = GWT
			.create(StageFourUiBinder.class);

	interface StageFourUiBinder extends UiBinder<Widget, StageFour> {
	}

	@UiField(provided = true) CustomCheckBox smsBox;
	@UiField(provided = true) CustomCheckBox payments;

	@UiField FlowPanel approveModulesContainer;
	
	public StageFour(MemberModel model) {
		smsBox = new CustomCheckBox("Enable SMS", "1");
		payments = new CustomCheckBox("Enable Payments View", "1");

		initWidget(uiBinder.createAndBindUi(this));
		this.model = model;
		initSMSEnableEvent();
		initComponents();
	}

	private void initComponents(){
		checkBoxList = new ArrayList<CustomCheckBox>();
		selectedApproveList = new ArrayList<String>();
		loadCheckBoxes(GlobalResources.getInstance().getApproveModuleMap());
	}
	
	private void initSMSEnableEvent(){
		smsBox.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {
			
			@Override
			public void onCheck(String id, String name, boolean isChecked) {
				isSMSEnabled = isChecked;
			}
		});
		
		payments.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {
			
			@Override
			public void onCheck(String id, String name, boolean isChecked) {
				isPaymentEnabled = isChecked;
			}
		});
	}

	private void loadCheckBoxes(HashMap<Integer, String> orgMap){

		for(Integer id : orgMap.keySet()){
			String approveName = orgMap.get(id);
			CustomCheckBox checkBox = new CustomCheckBox(Utils.getCapitalizedWord(approveName), ""+id);
			checkBox.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {

				@Override
				public void onCheck(String id, String labelName, boolean isChecked) {
					if(isChecked){
						if(!selectedApproveList.contains(id)){
							selectedApproveList.add(id);
						}
					}else{
						selectedApproveList.remove(id);
					}
				}
			});
			approveModulesContainer.add(checkBox);
			checkBoxList.add(checkBox);

			autoSelectCheckBoxes();
		}
	}
	
	private void autoSelectCheckBoxes(){
		if(model.getApproveModules() != null && !model.getApproveModules().trim().isEmpty()){
			String[] idTokens = model.getApproveModules().split(",");
			ArrayList<String> idList = new ArrayList<String>();
			for(String id : idTokens){
				idList.add(id);
			}
			
			for(String id : idList){
				for(CustomCheckBox checkBox : checkBoxList){
					String tmpId = checkBox.getId();
					if(tmpId.trim().equals(id)){
						checkBox.setChecked(true);
						if(!selectedApproveList.contains(id)){
							selectedApproveList.add(id);
						}
					}
				}
			}
		}
		
		if(model.isCanSMS()){
			smsBox.setChecked(true);
			isSMSEnabled = true;
		}
		
		if(model.isCanViewPayments()){
			payments.setChecked(true);
			isPaymentEnabled = true;
		}
	}
	
	private void prepareMemberModel(){
		model.setApproveModules(getIds());
		model.setCanSMS(isSMSEnabled);
		model.setCanViewPayments(isPaymentEnabled);

	}
	
	private String getIds(){
		String keys = "";
		for(String key : selectedApproveList){
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
		wizardHandler.onValidateComplete(WizardStage.FIVE, model);
	}
	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.THREE, model);
	}

}
