package com.beta.rsatech.churchcradle.client.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class UIObjectTest extends UIObject {

	private static UIObjectTestUiBinder uiBinder = GWT
			.create(UIObjectTestUiBinder.class);

	interface UIObjectTestUiBinder extends UiBinder<Element, UIObjectTest> {
	}

	public UIObjectTest() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
