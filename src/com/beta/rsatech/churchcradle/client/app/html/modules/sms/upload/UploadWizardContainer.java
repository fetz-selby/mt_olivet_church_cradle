package com.beta.rsatech.churchcradle.client.app.html.modules.sms.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class UploadWizardContainer extends UIObject {

	
	private UploadWizardStepOne stepOne;
	private UploadWizardStepTwo stepTwo;
	private UploadWizardStepThree stepThree;
	
	private static UploadWizardContainerUiBinder uiBinder = GWT
			.create(UploadWizardContainerUiBinder.class);

	interface UploadWizardContainerUiBinder extends
			UiBinder<Element, UploadWizardContainer> {
	}

	
	@UiField DivElement step1, step2, step3;
	public UploadWizardContainer() {
		setElement(uiBinder.createAndBindUi(this));
		initElement();
		initComponents();
	}
	
	private void initElement(){
		step1.setId("step1");
		step2.setId("step2");
		step3.setId("step3");
	}

	private void initComponents(){
		stepOne = new UploadWizardStepOne();
		stepTwo = new UploadWizardStepTwo();
		stepThree = new UploadWizardStepThree();
		
		step1.appendChild(stepOne.getElement());
		step2.appendChild(stepTwo.getElement());
		step3.appendChild(stepThree.getElement());

	}
	
}
