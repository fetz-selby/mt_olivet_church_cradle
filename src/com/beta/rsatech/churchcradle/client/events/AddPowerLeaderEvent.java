package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AddPowerLeaderEvent extends GwtEvent<AddPowerLeaderEventHandler>{

	public static Type<AddPowerLeaderEventHandler> TYPE = new Type<AddPowerLeaderEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddPowerLeaderEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddPowerLeaderEventHandler handler) {
		handler.onAddPowerLeaderEventInvoked(this);
	}

}
