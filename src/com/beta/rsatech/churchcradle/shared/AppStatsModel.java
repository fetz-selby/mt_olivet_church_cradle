package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AppStatsModel implements IsSerializable{
	private int churchId;
	private double smsLeft, daysLeft, totalMembers, membersLimit, smsCost, renewCost;
	private String expireDate, lastRenewedDate, packageType;
	private boolean isBirthdayPush, isTithePush;
	
	public int getChurchId() {
		return churchId;
	}
	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}
	public double getSmsLeft() {
		return smsLeft;
	}
	public void setSmsLeft(double smsLeft) {
		this.smsLeft = smsLeft;
	}
	public double getDaysLeft() {
		return daysLeft;
	}
	public void setDaysLeft(double daysLeft) {
		this.daysLeft = daysLeft;
	}
	public double getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(double totalMembers) {
		this.totalMembers = totalMembers;
	}
	public double getMembersLimit() {
		return membersLimit;
	}
	public void setMembersLimit(double membersLimit) {
		this.membersLimit = membersLimit;
	}
	public double getSmsCost() {
		return smsCost;
	}
	public void setSmsCost(double smsCost) {
		this.smsCost = smsCost;
	}
	public double getRenewCost() {
		return renewCost;
	}
	public void setRenewCost(double renewCost) {
		this.renewCost = renewCost;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getLastRenewedDate() {
		return lastRenewedDate;
	}
	public void setLastRenewedDate(String lastRenewedDate) {
		this.lastRenewedDate = lastRenewedDate;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public boolean isBirthdayPush() {
		return isBirthdayPush;
	}
	public void setBirthdayPush(boolean isBirthdayPush) {
		this.isBirthdayPush = isBirthdayPush;
	}
	public boolean isTithePush() {
		return isTithePush;
	}
	public void setTithePush(boolean isTithePush) {
		this.isTithePush = isTithePush;
	}
	
}
