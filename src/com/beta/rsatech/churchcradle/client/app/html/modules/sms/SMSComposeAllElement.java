package com.beta.rsatech.churchcradle.client.app.html.modules.sms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class SMSComposeAllElement extends UIObject {

	private static SMSComposeAllElementUiBinder uiBinder = GWT
			.create(SMSComposeAllElementUiBinder.class);

	interface SMSComposeAllElementUiBinder extends
			UiBinder<Element, SMSComposeAllElement> {
	}
	
	@UiField DivElement memberCount;

	public SMSComposeAllElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
