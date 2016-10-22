package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DateModel implements IsSerializable{
	private int id, date;
	private String alias, status;
	
	public DateModel(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
