package com.beta.rsatech.churchcradle.client.app.html.modules.sms.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class UploadWizardStepOne extends UIObject {

	private static UploadWizardStepOneUiBinder uiBinder = GWT
			.create(UploadWizardStepOneUiBinder.class);

	interface UploadWizardStepOneUiBinder extends
			UiBinder<Element, UploadWizardStepOne> {
	}

	public UploadWizardStepOne() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
