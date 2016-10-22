package com.beta.rsatech.churchcradle.client.app.html.modules.sms.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class UploadWizardStepThree extends UIObject {

	private static UploadWizardStepThreeUiBinder uiBinder = GWT
			.create(UploadWizardStepThreeUiBinder.class);

	interface UploadWizardStepThreeUiBinder extends
			UiBinder<Element, UploadWizardStepThree> {
	}

	public UploadWizardStepThree() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
