package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;


public interface HasWizardEvent<T> {
	void onValidateComplete(WizardStage stage, T model);
	void onError(String message);
}
