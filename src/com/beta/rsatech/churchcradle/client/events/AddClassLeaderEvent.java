package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AddClassLeaderEvent extends GwtEvent<AddClassLeaderEventHandler>{

	public static Type<AddClassLeaderEventHandler> TYPE = new Type<AddClassLeaderEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddClassLeaderEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddClassLeaderEventHandler handler) {
		handler.onAddClassLeaderEventInvoked(this);
	}

}
