package com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.all;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class SMSAllElement extends UIObject {

	private static SMSAllElementUiBinder uiBinder = GWT
			.create(SMSAllElementUiBinder.class);

	interface SMSAllElementUiBinder extends UiBinder<Element, SMSAllElement> {
	}

	public SMSAllElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
