package com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders;

import com.beta.rsatech.churchcradle.client.elements.AsideElement;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class PowerLeadersListLevel extends UIObject {

	private static ApproveListLevelUiBinder uiBinder = GWT
			.create(ApproveListLevelUiBinder.class);

	interface ApproveListLevelUiBinder extends
			UiBinder<Element, PowerLeadersListLevel> {
	}

	@UiField AsideElement listContainer;
	public PowerLeadersListLevel() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	public void setListPage(Element element){
		listContainer.removeAllChildren();
		listContainer.appendChild(element);
	}
}
