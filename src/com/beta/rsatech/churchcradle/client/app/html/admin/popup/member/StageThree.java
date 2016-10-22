package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox.CustomCheckBoxEventHandler;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;

public class StageThree extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;
	private String leader;
	private int leaderId;
	SuggestBox leaderSearch;
	private ArrayList<String> selectedOrgList;
	private ArrayList<CustomCheckBox> checkBoxList;
	
	private static StepThreeUiBinder uiBinder = GWT
			.create(StepThreeUiBinder.class);

	interface StepThreeUiBinder extends UiBinder<Widget, StageThree> {
	}

	@UiField SimplePanel searchContainer;
	@UiField FlowPanel orgContainer;
	public StageThree(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		loadLeadersSearch();
		loadAllOrganisation();
		
		if(model.getClassId() > 0){
			leaderId = model.getClassId();
			leader = GlobalResources.getInstance().getClassesLeadersMap().get(model.getClassId());
			leaderSearch.getElement().setAttribute("placeholder", leader);
		}
		
	}
	
	private void autoSelectCheckBoxes(){
		if(model.getOrganisations() != null && !model.getOrganisations().trim().isEmpty()){
			String[] idTokens = model.getOrganisations().split(",");
			ArrayList<String> idList = new ArrayList<String>();
			for(String id : idTokens){
				idList.add(id);
			}
			
			for(String id : idList){
				for(CustomCheckBox checkBox : checkBoxList){
					String tmpId = checkBox.getId();
					if(tmpId.trim().equals(id)){
						checkBox.setChecked(true);
						if(!selectedOrgList.contains(id)){
							selectedOrgList.add(id);
						}
					}
				}
			}
		}
	}
	
	private void loadLeadersSearch(){
		final HashMap<Integer, String> membersMap = GlobalResources.getInstance().getClassesLeadersMap();
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for(Integer key : membersMap.keySet()){
			oracle.add(membersMap.get(key));
		}
		
		leaderSearch = new SuggestBox(oracle);
		leaderSearch.setStyleName("form-control");
		leaderSearch.getElement().setAttribute("placeholder", "Enter Leader Name");
		leaderSearch.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
			
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				leader = event.getSelectedItem().getReplacementString();
				leaderId = fetchId(leader, membersMap);
			}
		});
		
		searchContainer.setWidget(leaderSearch);
	}
	
	private int fetchId(String leader, HashMap<Integer, String> membersMap){
		if(membersMap != null){
			for(Integer id : membersMap.keySet()){
				String tmpLeader = membersMap.get(id);
				if(tmpLeader.equals(leader)){
					return id;
				}
			}
		}
		return 0;
	}
	
	private void loadAllOrganisation(){
		GlobalResources.getInstance().getListRPC().getAllOrganisationMapWithLeaders(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<HashMap<Integer,String>>() {
			
			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				checkBoxList = new ArrayList<CustomCheckBox>();
				selectedOrgList = new ArrayList<String>();
				loadCheckBoxes(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadCheckBoxes(HashMap<Integer, String> orgMap){
		
		for(Integer id : orgMap.keySet()){
			String orgName = orgMap.get(id);
			CustomCheckBox checkBox = new CustomCheckBox(orgName, ""+id);
			checkBox.setCustomCheckBoxEventHandler(new CustomCheckBoxEventHandler() {
				
				@Override
				public void onCheck(String id, String labelName, boolean isChecked) {
					if(isChecked){
						if(!selectedOrgList.contains(id)){
							selectedOrgList.add(id);
						}
					}else{
						selectedOrgList.remove(id);
					}
				}
			});
			orgContainer.add(checkBox);
			checkBoxList.add(checkBox);
			
			autoSelectCheckBoxes();
		}
	}
	
	private void prepareMemberModel(){
		model.setOrganisations(getIds());
		model.setClassId(leaderId);
	}
	
	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}
	
	private String getIds(){
		String keys = "";
		for(String key : selectedOrgList){
			keys+=key+",";
		}
		return keys.substring(0,keys.length()-1);
	}
	
	@Override
	public void validateAndProceed() {
		if(leaderId <= 0){
			doPublishError("Please select a class leader");
			return;
		}
		
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
		wizardHandler.onValidateComplete(WizardStage.FOUR, model);
	}
	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.TWO_SUB, model);
	}
	
}
