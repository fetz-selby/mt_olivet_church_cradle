package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ForgotPasswordEvent extends GwtEvent<ForgotPasswordEventHandler>{

	public static Type<ForgotPasswordEventHandler> TYPE = new Type<ForgotPasswordEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ForgotPasswordEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ForgotPasswordEventHandler handler) {
		handler.onForgotPasswordEventInvoked(this);
	}

}
