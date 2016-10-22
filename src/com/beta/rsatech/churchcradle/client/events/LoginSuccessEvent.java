package com.beta.rsatech.churchcradle.client.events;

import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.event.shared.GwtEvent;

public class LoginSuccessEvent extends GwtEvent<LoginSuccessEventHandler>{
	private UserModel model;
	public static Type<LoginSuccessEventHandler> TYPE = new Type<LoginSuccessEventHandler>();

	public LoginSuccessEvent(UserModel model){
		this.model = model;
	}
	
	public UserModel getModel() {
		return model;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginSuccessEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoginSuccessEventHandler handler) {
		handler.onLoginSuccessEventInvoked(this);
	}
	
	
}
