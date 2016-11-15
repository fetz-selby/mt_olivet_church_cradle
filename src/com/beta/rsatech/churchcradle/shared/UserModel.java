package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserModel implements IsSerializable{
	
	private int id, churchId, billingId, classId, modifiedBy;
	private String firstName, lastName, avatar, items, tithes, email, msisdn, modifiedTs, prefs, address, createdTs, approveModules, entryModules, groups, occupation, dateOfBirth, gender, employer;
	private boolean isAdmin, isSMSEnabled, canViewPayments;
	private String placeOfBaptism = "", dateOfBaptism = "", previousChurch = "", nameOfMinister = "", nationality = "", hometown = "", region = "", spouseName = "", kinName = "", kinMsisdn = "", maritalStatus = "", level = "";
	
	public UserModel(){}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isSMSEnabled() {
		return isSMSEnabled;
	}

	public void setSMSEnabled(boolean isSMSEnabled) {
		this.isSMSEnabled = isSMSEnabled;
	}

	public int getChurchId() {
		return churchId;
	}

	public void setChurchId(int churchId) {
		this.churchId = churchId;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getTithes() {
		return tithes;
	}

	public void setTithes(String tithes) {
		this.tithes = tithes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getPrefs() {
		return prefs;
	}

	public void setPrefs(String prefs) {
		this.prefs = prefs;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getApproveModules() {
		return approveModules;
	}

	public void setApproveModules(String approveModules) {
		this.approveModules = approveModules;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEntryModules() {
		return entryModules;
	}

	public void setEntryModules(String entryModules) {
		this.entryModules = entryModules;
	}

	public boolean isCanViewPayments() {
		return canViewPayments;
	}

	public void setCanViewPayments(boolean canViewPayments) {
		this.canViewPayments = canViewPayments;
	}

	public String getPlaceOfBaptism() {
		return placeOfBaptism;
	}

	public void setPlaceOfBaptism(String placeOfBaptism) {
		this.placeOfBaptism = placeOfBaptism;
	}

	public String getDateOfBaptism() {
		return dateOfBaptism;
	}

	public void setDateOfBaptism(String dateOfBaptism) {
		this.dateOfBaptism = dateOfBaptism;
	}

	public String getPreviousChurch() {
		return previousChurch;
	}

	public void setPreviousChurch(String previousChurch) {
		this.previousChurch = previousChurch;
	}

	public String getNameOfMinister() {
		return nameOfMinister;
	}

	public void setNameOfMinister(String nameOfMinister) {
		this.nameOfMinister = nameOfMinister;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getKinName() {
		return kinName;
	}

	public void setKinName(String kinName) {
		this.kinName = kinName;
	}

	public String getKinMsisdn() {
		return kinMsisdn;
	}

	public void setKinMsisdn(String kinMsisdn) {
		this.kinMsisdn = kinMsisdn;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
