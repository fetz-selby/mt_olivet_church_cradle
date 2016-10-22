package com.beta.rsatech.churchcradle.client.app.html.modules.tithes;

import com.beta.rsatech.churchcradle.client.elements.AsideElement;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class TitheList extends UIObject {

	private static OfferingListUiBinder uiBinder = GWT
			.create(OfferingListUiBinder.class);

	interface OfferingListUiBinder extends UiBinder<Element, TitheList> {
	}

	@UiField AsideElement listContainer;
	public TitheList() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	public void setListPage(Element element){
		listContainer.setInnerText("");
		listContainer.removeAllChildren();
		listContainer.appendChild(element);
	}

}
