package com.beta.rsatech.churchcradle.server.utils.sms;

import com.beta.rsatech.churchcradle.shared.ChurchModel;

public class MemberModel {
	private int id, churchId;
	private String name, msisdn;
	private int classLeaderId;
	private int years;
	private ChurchModel church;
	
	public MemberModel(int id,String name, String msisdn, int churchId, int classLeaderId){
		this.id = id;
		this.name = name;
		this.msisdn = msisdn;
		this.churchId = churchId;
		this.classLeaderId = classLeaderId;
	}
	
	public MemberModel(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	public int getClassLeaderId() {
		return classLeaderId;
	}

	public void setClassLeaderId(int classLeaderId) {
		this.classLeaderId = classLeaderId;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public ChurchModel getChurch() {
		return church;
	}

	public void setChurch(ChurchModel church) {
		this.church = church;
	}
	
}
