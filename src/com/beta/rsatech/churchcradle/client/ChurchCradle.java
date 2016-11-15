package com.beta.rsatech.churchcradle.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.controller.AppController;
import com.beta.rsatech.churchcradle.client.controller.LoginController;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.CookieVerifier;
import com.beta.rsatech.churchcradle.shared.ChurchModel;
import com.beta.rsatech.churchcradle.shared.DateModel;
import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChurchCradle implements EntryPoint{

	public void onModuleLoad() {
		ListServiceAsync listRPC = GWT.create(ListService.class);
		AddServiceAsync addRPC = GWT.create(AddService.class);
		UpdateServiceAsync updateRPC = GWT.create(UpdateService.class);
		PaymentServiceAsync paymentRPC = GWT.create(PaymentService.class);
		
		HandlerManager eventBus = new HandlerManager(null);

		GlobalResources.getInstance().setListRPC(listRPC);
		GlobalResources.getInstance().setAddRPC(addRPC);
		GlobalResources.getInstance().setUpdateRPC(updateRPC);
		GlobalResources.getInstance().setPaymentRPC(paymentRPC);
		
		GlobalResources.getInstance().setEventBus(eventBus);

		setModuleBaseUrl(eventBus);
		
	}
	
	private void setModuleBaseUrl(final HandlerManager eventBus){
		GlobalResources.getInstance().getUpdateRPC().setModuleBaseUrl(com.google.gwt.core.client.GWT.getModuleBaseURL(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(CookieVerifier.isAppCookieExist() && Window.Location.getPath().contains("app.html")){
					loadAllResources();
				}else{
					LoginController app = new LoginController(RootPanel.get(), eventBus);
					app.go();
				}				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadAllPowerLeaders(final int churchId){
		GlobalResources.getInstance().getListRPC().getAllPowerLeadersList(churchId, new AsyncCallback<ArrayList<Integer>>() {
			
			@Override
			public void onSuccess(ArrayList<Integer> result) {
				GlobalResources.getInstance().setPowerLeadersList(result);
				loadDates();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadAllResources(){
		GlobalResources.getInstance().getListRPC().getFullUser(CookieVerifier.getInstance().getId(), new AsyncCallback<UserModel>() {

			@Override
			public void onSuccess(UserModel result) {
				GlobalResources.getInstance().setModel(result);
				int churchId = result.getChurchId();
				loadAllApproveModules(churchId);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void loadAllApproveModules(final int churchId){
		GlobalResources.getInstance().getListRPC().getAllApproveModulesMap(churchId, new AsyncCallback<HashMap<Integer,String>>() {
			
			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				GlobalResources.getInstance().setApproveModuleMap(result);
				loadAllOrganisations(churchId);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadAllClassLeaders(final int churchId){
		GlobalResources.getInstance().getListRPC().getAllClassLeadersMap(churchId, new AsyncCallback<HashMap<Integer,String>>() {
			
			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				GlobalResources.getInstance().setClassesLeadersMap(result);
				loadAllMembers(churchId);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadAllOrganisations(final int churchId){
		GlobalResources.getInstance().getListRPC().getAllOrganisationMap(churchId, new AsyncCallback<HashMap<Integer,String>>() {
			
			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				GlobalResources.getInstance().setOrganisationMap(result);
				loadAllClassLeaders(churchId);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadAllMembers(final int churchId){
		GlobalResources.getInstance().getListRPC().getAllMembersMap(churchId, new AsyncCallback<HashMap<Integer,String>>() {
			
			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				GlobalResources.getInstance().setMembersMap(result);
				loadChurchModel(churchId);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadChurchModel(final int churchId){
		GlobalResources.getInstance().getListRPC().getChurchModel(churchId, new AsyncCallback<ChurchModel>() {
			
			@Override
			public void onSuccess(ChurchModel result) {
				GlobalResources.getInstance().setChurchModel(result);
				loadAllEntryModules(churchId);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadAllEntryModules(final int churchId){
		GlobalResources.getInstance().getListRPC().getAllEntryModulesMap(churchId, new AsyncCallback<HashMap<Integer,String>>() {
			
			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				GlobalResources.getInstance().setEntryModuleMap(result);
				loadAllPowerLeaders(churchId);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadDates(){
		GlobalResources.getInstance().getListRPC().getDateList(new AsyncCallback<ArrayList<DateModel>>() {
			
			@Override
			public void onSuccess(ArrayList<DateModel> result) {
				GlobalResources.getInstance().setDatesList(result);
				loadEducationalLevel();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadEducationalLevel(){
		GlobalResources.getInstance().getListRPC().getEducationalList(new AsyncCallback<HashMap<String,String>>() {
			
			@Override
			public void onSuccess(HashMap<String, String> result) {
				GlobalResources.getInstance().setEducationalLevelMap(result);
				loadRegions();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadRegions(){
		GlobalResources.getInstance().getListRPC().getRegionsList(new AsyncCallback<HashMap<String,String>>() {
			
			@Override
			public void onSuccess(HashMap<String, String> result) {
				GlobalResources.getInstance().setRegionsMap(result);
				AppController mainApp = new AppController(RootPanel.get("headerContainer"), RootPanel.get("nav"), RootPanel.get("content"), RootPanel.get("odd"), GlobalResources.getInstance().getEventBus());
				mainApp.go();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
