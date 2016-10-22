package com.beta.rsatech.churchcradle.server.utils;

public class ServerGlobals {
	private static ServerGlobals instance = new ServerGlobals();
	private String moduleBaseUrl;
	
	private ServerGlobals(){}
	
	public static ServerGlobals getInstance(){
		return instance;
	}

	public String getModuleBaseUrl() {
		return moduleBaseUrl;
	}

	public void setModuleBaseUrl(String moduleBaseUrl) {
		this.moduleBaseUrl = moduleBaseUrl;
	}
	
	
}
