package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class TithePaymentEvent extends GwtEvent<TithePaymentEventHandler>{

	private String month;
	public static Type<TithePaymentEventHandler> TYPE = new Type<TithePaymentEventHandler>();
	
	public TithePaymentEvent(String month){
		this.month = month;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TithePaymentEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TithePaymentEventHandler handler) {
		handler.onTithePaymentEvent(this);
	}

}
