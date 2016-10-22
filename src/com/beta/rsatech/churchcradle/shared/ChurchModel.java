package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChurchModel implements IsSerializable{
	private int id, paymentId, defaultGroupId;
	private double pricePerSMS, annualFee, smsLeft, memberLimit,appFee;
	private String name, modules, display, expireDate, status, packageType;
	private boolean isValid;
	
	public ChurchModel(){}

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

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getPricePerSMS() {
		return pricePerSMS;
	}

	public void setPricePerSMS(double pricePerSMS) {
		this.pricePerSMS = pricePerSMS;
	}

	public double getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(double annualFee) {
		this.annualFee = annualFee;
	}

	public double getSmsLeft() {
		return smsLeft;
	}

	public void setSmsLeft(double smsLeft) {
		this.smsLeft = smsLeft;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public double getMemberLimit() {
		return memberLimit;
	}

	public void setMemberLimit(double memberLimit) {
		this.memberLimit = memberLimit;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public double getAppFee() {
		return appFee;
	}

	public void setAppFee(double appFee) {
		this.appFee = appFee;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getDefaultGroupId() {
		return defaultGroupId;
	}

	public void setDefaultGroupId(int defaultGroupId) {
		this.defaultGroupId = defaultGroupId;
	}
	
}
