package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ClassesEvent extends GwtEvent<ClassesEventHandler>{

	public static Type<ClassesEventHandler> TYPE = new Type<ClassesEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClassesEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClassesEventHandler handler) {
		handler.onClassesEventInvoked(this);
	}

}
