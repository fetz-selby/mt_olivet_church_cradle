package com.beta.rsatech.churchcradle.client.app.html.modules.offerings;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.offerings.GroupOfferingLevel.GroupOfferingLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OfferingsElement extends UIObject {

	private GroupOfferingLevel groupLevel;
	private OfferingList listElement;
	private static OfferingElementUiBinder uiBinder = GWT
			.create(OfferingElementUiBinder.class);

	interface OfferingElementUiBinder extends
			UiBinder<Element, OfferingsElement> {
	}

	@UiField SectionElement offeringContainer;
	@UiField DivElement modulesContainer, childContainer;

	public OfferingsElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new OfferingList();
		groupLevel = new GroupOfferingLevel();
		groupLevel.setGroupOfferingLevelEventHandler(new GroupOfferingLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<OfferingModel> modelList) {
				OfferingListPage page = new OfferingListPage(modelList);
				listElement.setListPage(page.getElement());
			}
		});
		
		modulesContainer.appendChild(groupLevel.getElement());
		childContainer.appendChild(listElement.getElement());
		offeringContainer.appendChild(new ContentHTMLCompositeEnder().getElement());
	}
	
	public void load(){
		initElement();
	}
}
