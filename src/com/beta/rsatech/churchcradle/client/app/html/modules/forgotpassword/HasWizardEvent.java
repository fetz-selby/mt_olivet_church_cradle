package com.beta.rsatech.churchcradle.client.app.html.modules.forgotpassword;


public interface HasWizardEvent<T> {
	void onValidateComplete(WizardStage stage, T model);
	void onError(String message);
}
