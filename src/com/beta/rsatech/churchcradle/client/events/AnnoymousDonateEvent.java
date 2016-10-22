package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class AnnoymousDonateEvent extends GwtEvent<AnnoymousDonateEventHandler>{

	public static Type<AnnoymousDonateEventHandler> TYPE = new Type<AnnoymousDonateEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AnnoymousDonateEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AnnoymousDonateEventHandler handler) {
		handler.onAnnoymousDonateEventInvoked(this);
	}

}
