package com.beta.rsatech.churchcradle.client.app.html.modules.myoffering;

import com.beta.rsatech.churchcradle.client.elements.AsideElement;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MyOfferingList extends UIObject {

	private static BibleReadListUiBinder uiBinder = GWT
			.create(BibleReadListUiBinder.class);

	interface BibleReadListUiBinder extends UiBinder<Element, MyOfferingList> {
	}

	@UiField AsideElement listContainer;
	public MyOfferingList() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	public void setListPage(Element element){
		listContainer.removeAllChildren();
		listContainer.appendChild(element);
	}

}
