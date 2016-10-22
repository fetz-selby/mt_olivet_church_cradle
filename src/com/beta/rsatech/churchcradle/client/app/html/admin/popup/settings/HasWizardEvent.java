package com.beta.rsatech.churchcradle.client.app.html.admin.popup.settings;


public interface HasWizardEvent<T> {
	void onValidateComplete(WizardStage stage, T model);
	void onError(String message);
}
