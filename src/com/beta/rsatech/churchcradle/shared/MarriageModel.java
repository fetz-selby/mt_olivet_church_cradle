package com.beta.rsatech.churchcradle.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MarriageModel implements IsSerializable{
	private int id, approvedBy, createdBy, churchId, modifiedBy;
	private String manName, femaleName, venue, description, createdTs, date, mAvatar, fAvatar, modifiedTs, mOccupation, fOccupation, time;
	private boolean isEditable;
	
	public MarriageModel(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public String getFemaleName() {
		return femaleName;
	}

	public void setFemaleName(String femaleName) {
		this.femaleName = femaleName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getmAvatar() {
		return mAvatar;
	}

	public void setmAvatar(String mAvatar) {
		this.mAvatar = mAvatar;
	}

	public String getfAvatar() {
		return fAvatar;
	}

	public void setfAvatar(String fAvatar) {
		this.fAvatar = fAvatar;
	}

	public String getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(String modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public String getmOccupation() {
		return mOccupation;
	}

	public void setmOccupation(String mOccupation) {
		this.mOccupation = mOccupation;
	}

	public String getfOccupation() {
		return fOccupation;
	}

	public void setfOccupation(String fOccupation) {
		this.fOccupation = fOccupation;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
