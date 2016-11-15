package com.beta.rsatech.churchcradle.client.resources;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.AddServiceAsync;
import com.beta.rsatech.churchcradle.client.ListServiceAsync;
import com.beta.rsatech.churchcradle.client.PaymentServiceAsync;
import com.beta.rsatech.churchcradle.client.UpdateServiceAsync;
import com.beta.rsatech.churchcradle.shared.ChurchModel;
import com.beta.rsatech.churchcradle.shared.DateModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.event.shared.HandlerManager;

public class GlobalResources {
	private static GlobalResources instance = new GlobalResources();
	private ListServiceAsync listRPC;
	private AddServiceAsync addRPC;
	private UpdateServiceAsync updateRPC;
	private PaymentServiceAsync paymentRPC;
	private HandlerManager eventBus;
	private HashMap<Integer, String> organisationMap, classesLeadersMap, approveModuleMap, membersMap, entryModuleMap;
	private HashMap<String, String> educationalLevelMap, regionsMap;
	private UserModel model;
	private ChurchModel churchModel;
	private ArrayList<DateModel> datesList;
	private ArrayList<Integer> powerLeadersList;
	private String module;
	
	private GlobalResources(){}
	
	public static GlobalResources getInstance(){
		return instance;
	}

	public ListServiceAsync getListRPC() {
		return listRPC;
	}

	public void setListRPC(ListServiceAsync listRPC) {
		this.listRPC = listRPC;
	}

	public HandlerManager getEventBus() {
		return eventBus;
	}

	public void setEventBus(HandlerManager eventBus) {
		this.eventBus = eventBus;
	}

	public UserModel getModel() {
		return model;
	}
	
	public MemberModel getMemberModel(){
		MemberModel memberModel = new MemberModel();
		
		if(model != null){
			memberModel.setId(model.getId());
			memberModel.setAddress(model.getAddress());
			memberModel.setAdmin(model.isAdmin());
			memberModel.setApproveModules(model.getApproveModules());
			memberModel.setAvatar(model.getAvatar());
			memberModel.setCanSMS(model.isSMSEnabled());
			memberModel.setFirstName(model.getFirstName());
			memberModel.setLastName(model.getLastName());
			memberModel.setMsisdn(model.getMsisdn());
			memberModel.setEmail(model.getEmail());
			memberModel.setChurchId(model.getChurchId());
			memberModel.setClassId(model.getClassId());
			
			return memberModel;
		}
		
		return null;
	}

	public void setModel(UserModel model) {
		this.model = model;
	}

	public HashMap<Integer, String> getOrganisationMap() {
		return organisationMap;
	}

	public void setOrganisationMap(HashMap<Integer, String> organisationMap) {
		this.organisationMap = organisationMap;
	}

	public HashMap<Integer, String> getClassesLeadersMap() {
		return classesLeadersMap;
	}

	public void setClassesLeadersMap(HashMap<Integer, String> classesLeadersMap) {
		this.classesLeadersMap = classesLeadersMap;
	}

	public HashMap<Integer, String> getApproveModuleMap() {
		return approveModuleMap;
	}

	public void setApproveModuleMap(HashMap<Integer, String> approveModuleMap) {
		this.approveModuleMap = approveModuleMap;
	}

	public HashMap<Integer, String> getMembersMap() {
		return membersMap;
	}

	public void setMembersMap(HashMap<Integer, String> membersMap) {
		this.membersMap = membersMap;
	}

	public AddServiceAsync getAddRPC() {
		return addRPC;
	}

	public void setAddRPC(AddServiceAsync addRPC) {
		this.addRPC = addRPC;
	}

	public UpdateServiceAsync getUpdateRPC() {
		return updateRPC;
	}

	public void setUpdateRPC(UpdateServiceAsync updateRPC) {
		this.updateRPC = updateRPC;
	}

	public ChurchModel getChurchModel() {
		return churchModel;
	}

	public void setChurchModel(ChurchModel churchModel) {
		this.churchModel = churchModel;
	}

	public ArrayList<DateModel> getDatesList() {
		return datesList;
	}

	public void setDatesList(ArrayList<DateModel> datesList) {
		this.datesList = datesList;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public HashMap<Integer, String> getEntryModuleMap() {
		return entryModuleMap;
	}

	public void setEntryModuleMap(HashMap<Integer, String> entryModuleMap) {
		this.entryModuleMap = entryModuleMap;
	}

	public PaymentServiceAsync getPaymentRPC() {
		return paymentRPC;
	}

	public void setPaymentRPC(PaymentServiceAsync paymentRPC) {
		this.paymentRPC = paymentRPC;
	}

	public ArrayList<Integer> getPowerLeadersList() {
		return powerLeadersList;
	}

	public void setPowerLeadersList(ArrayList<Integer> powerLeadersList) {
		this.powerLeadersList = powerLeadersList;
	}

	public HashMap<String, String> getEducationalLevelMap() {
		return educationalLevelMap;
	}

	public void setEducationalLevelMap(HashMap<String, String> educationalLevelMap) {
		this.educationalLevelMap = educationalLevelMap;
	}

	public HashMap<String, String> getRegionsMap() {
		return regionsMap;
	}

	public void setRegionsMap(HashMap<String, String> regionsMap) {
		this.regionsMap = regionsMap;
	}
	
}
