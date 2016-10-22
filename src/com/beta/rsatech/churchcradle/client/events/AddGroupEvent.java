package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AddGroupEvent extends GwtEvent<AddGroupEventHandler>{
	public static Type<AddGroupEventHandler> TYPE = new Type<AddGroupEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddGroupEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddGroupEventHandler handler) {
		handler.onAddGroupEventInvoked(this);
	}
	

}
