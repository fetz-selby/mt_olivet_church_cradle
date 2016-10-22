package com.beta.rsatech.churchcradle.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LogoutEventHandler extends EventHandler{
	void onLogoutInvoked(LogoutEvent event);
}
