package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SMSPurchaseModel implements IsSerializable{
	private int memberId, churchId;
	private double amount;
	private double smsQuantity;
	public int getChurchId() {
		return churchId;
	}
	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getSmsQuantity() {
		return smsQuantity;
	}
	public void setSmsQuantity(double smsQuantity) {
		this.smsQuantity = smsQuantity;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
}
