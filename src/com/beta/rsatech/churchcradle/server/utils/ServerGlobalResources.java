package com.beta.rsatech.churchcradle.server.utils;

import com.beta.rsatech.churchcradle.shared.ChurchModel;
import com.beta.rsatech.churchcradle.shared.ThirdPartyPaymentModel;

public class ServerGlobalResources {
	private String dbName, dbPassword, dbUserName, port, ip, smsUserName, smsPassword, smsPort, smsUrl;
	private ChurchModel churchModel;
	private ThirdPartyPaymentModel paymentModel,ownPaymentModel;
	
	private static ServerGlobalResources instance = new ServerGlobalResources();
	
	public static ServerGlobalResources getInstance(){
		return instance;
	}

	public String getSmsUserName() {
		return smsUserName;
	}

	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getSmsPort() {
		return smsPort;
	}

	public void setSmsPort(String smsPort) {
		this.smsPort = smsPort;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getPort() {
		return port;
	}

	public void setDbPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setDbIp(String ip) {
		this.ip = ip;
	}

	public ChurchModel getChurchModel() {
		return churchModel;
	}

	public void setChurchModel(ChurchModel churchModel) {
		this.churchModel = churchModel;
	}

	public ThirdPartyPaymentModel getOwnPaymentModel() {
		return ownPaymentModel;
	}

	public void setOwnPaymentModel(ThirdPartyPaymentModel ownPaymentModel) {
		this.ownPaymentModel = ownPaymentModel;
	}

	public ThirdPartyPaymentModel getPaymentModel() {
		if(paymentModel == null){
			paymentModel = new ThirdPartyPaymentModel();
			paymentModel.setMasterKey("f0ee61c1-1639-4431-bc57-4b8c4a682291");
			paymentModel.setPrivateKey("live_private_gAZ2En7uvRB4kK9dDtbPoS1XVOI");
			paymentModel.setPublicKey("live_public_NfiJBT9bl9yP8EGbZKlfuMGjb6g");
			paymentModel.setToken("7937460f2e9443422337"); 
		}
		return paymentModel;
	}

	public void setPaymentModel(ThirdPartyPaymentModel paymentModel) {
		this.paymentModel = paymentModel;
	}
	
}
