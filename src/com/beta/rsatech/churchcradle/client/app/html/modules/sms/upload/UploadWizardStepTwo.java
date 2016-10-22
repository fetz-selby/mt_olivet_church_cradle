package com.beta.rsatech.churchcradle.client.app.html.modules.sms.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class UploadWizardStepTwo extends UIObject {

	private static UploadWizardStepTwoUiBinder uiBinder = GWT
			.create(UploadWizardStepTwoUiBinder.class);

	interface UploadWizardStepTwoUiBinder extends
			UiBinder<Element, UploadWizardStepTwo> {
	}

	public UploadWizardStepTwo() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
