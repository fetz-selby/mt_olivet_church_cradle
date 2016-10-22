package com.beta.rsatech.churchcradle.client.test;

import java.util.HashMap;

import com.beta.rsatech.churchcradle.shared.UserModel;

public class DummyFactory {
	private static DummyFactory instance = new DummyFactory();
	
	private DummyFactory(){}
	
	public static DummyFactory getInstance(){
		return instance;
	}
	
	public UserModel getUser(){
		
		String[] groupIds = {"1","2","3"};
		String[] groupNames = {"Finance", "Mrs Pulley's", "Mr Essel"};
		
		String[] approveIds = {"1,2"};
		String[] approveNames = {"Finance, Members"};
		
		HashMap<String, String> groupMap = new HashMap<String, String>();
		HashMap<String, String> approveMap = new HashMap<String, String>();
		
		for(int i = 0; i < groupIds.length; i++){
			groupMap.put(groupIds[i], groupNames[i]);
		}
		
		for(int i = 0; i < approveIds.length; i++){
			approveMap.put(approveIds[i], approveNames[i]);
		}
		
		UserModel user = new UserModel();
		user.setAdmin(true);
		user.setId(0);
		user.setSMSEnabled(true);
		user.setBillingId(0);
		user.setChurchId(0);
		//user.setGroupMap(groupMap);
		user.setChurchId(0);
		user.setTithes("1,2,3,4,5");
		
		return user;
	}
	
	
}
