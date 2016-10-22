package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class MembersEvent extends GwtEvent<MembersEventHandler>{

	public static Type<MembersEventHandler> TYPE = new Type<MembersEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MembersEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MembersEventHandler handler) {
		handler.onMembersInvoked(this);
	}

}
