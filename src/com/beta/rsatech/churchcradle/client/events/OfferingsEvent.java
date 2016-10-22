package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class OfferingsEvent extends GwtEvent<OfferingsEventHandler>{

	public static Type<OfferingsEventHandler> TYPE = new Type<OfferingsEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<OfferingsEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OfferingsEventHandler handler) {
		handler.onOfferingsEventInvoked(this);
	}

}
