package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AccountModel implements IsSerializable{
	private int id, churchId, modifiedBy;
	private double balance, arreas;
	private String modifiedTs, status;
	
	public AccountModel(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getArreas() {
		return arreas;
	}

	public void setArreas(double arreas) {
		this.arreas = arreas;
	}

	public String getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
