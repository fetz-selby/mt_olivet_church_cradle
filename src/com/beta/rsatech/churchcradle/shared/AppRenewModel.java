package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AppRenewModel implements IsSerializable{
	private double months, amount, dollarRate, pricePerMonth;
	private int churchId, memberId;
	
	public double getMonths() {
		return months;
	}
	public void setMonths(double months) {
		this.months = months;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getChurchId() {
		return churchId;
	}
	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public double getDollarRate() {
		return dollarRate;
	}
	public void setDollarRate(double dollarRate) {
		this.dollarRate = dollarRate;
	}
	public double getPricePerMonth() {
		return pricePerMonth;
	}
	public void setPricePerMonth(double pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}
}
