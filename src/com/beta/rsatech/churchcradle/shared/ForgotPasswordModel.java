package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ForgotPasswordModel implements IsSerializable{
	private int memberId;
	private String firstname, lastname, avatar, msisdn, code, password;
	private boolean isStageOneValidated, isStageTwoValidated;
	
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isStageOneValidated() {
		return isStageOneValidated;
	}

	public void setStageOneValidated(boolean isStageOneValidated) {
		this.isStageOneValidated = isStageOneValidated;
	}

	public boolean isStageTwoValidated() {
		return isStageTwoValidated;
	}

	public void setStageTwoValidated(boolean isStageTwoValidated) {
		this.isStageTwoValidated = isStageTwoValidated;
	}
}
