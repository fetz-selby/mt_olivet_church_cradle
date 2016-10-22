package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class BibleReadingEvent extends GwtEvent<BibleReadingEventHandler>{
	public static Type<BibleReadingEventHandler> TYPE = new Type<BibleReadingEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<BibleReadingEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(BibleReadingEventHandler handler) {
		handler.onBibleReadingInvoked(this);
	}

}
