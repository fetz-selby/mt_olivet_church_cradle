package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class SendMessageEvent extends GwtEvent<SendMessageEventHandler>{

	private String msisdn;
	public static Type<SendMessageEventHandler> TYPE = new Type<SendMessageEventHandler>();
	
	public SendMessageEvent(String msisdn){
		this.msisdn = msisdn;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SendMessageEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SendMessageEventHandler handler) {
		handler.onSendMessageInvoked(this);
	}

}
