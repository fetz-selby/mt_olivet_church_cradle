package com.beta.rsatech.churchcradle.client.utils;

import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

public interface FormSaveEventHandler {
	void onSuccessfulSave(SubmitCompleteEvent event);
	void onFailSave();
}
