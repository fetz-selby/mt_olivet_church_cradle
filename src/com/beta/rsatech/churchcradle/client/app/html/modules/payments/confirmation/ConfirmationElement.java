package com.beta.rsatech.churchcradle.client.app.html.modules.payments.confirmation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class ConfirmationElement extends UIObject {

	private static FuneralElementUiBinder uiBinder = GWT
			.create(FuneralElementUiBinder.class);

	interface FuneralElementUiBinder extends UiBinder<Element, ConfirmationElement> {
	}

	@UiField DivElement divContainer;
	public ConfirmationElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void doFuneralLoad(){
		
	}
	
	public void load(){
		doFuneralLoad();
	}

	
}
