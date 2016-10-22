package com.beta.rsatech.churchcradle.client.app.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class SideBarHTML extends UIObject {

	private static SideBarHTMLUiBinder uiBinder = GWT
			.create(SideBarHTMLUiBinder.class);

	interface SideBarHTMLUiBinder extends UiBinder<Element, SideBarHTML> {
	}

	public SideBarHTML() {
		setElement(uiBinder.createAndBindUi(this));
	}

}
