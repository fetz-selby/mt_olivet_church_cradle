package com.beta.rsatech.churchcradle.server.utils.sms.parser;


public class SMSGatewayModel {
	private int messageId;
	private String message, from;
	private String postedTs;
	
	public SMSGatewayModel(int messageId, String message, String from, String postedTs){
		this.messageId = messageId;
		this.message = message;
		this.from = from;
		this.postedTs = postedTs;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPostedTs() {
		return postedTs;
	}

	public void setPostedTs(String postedTs) {
		this.postedTs = postedTs;
	}
	
	
}
