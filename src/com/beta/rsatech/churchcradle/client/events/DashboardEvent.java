package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class DashboardEvent extends GwtEvent<DashboardEventHandler>{
	public static Type<DashboardEventHandler> TYPE = new Type<DashboardEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DashboardEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DashboardEventHandler handler) {
		handler.onDashboardInvoked(this);
	}
}
