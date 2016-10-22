package com.beta.rsatech.churchcradle.client.app.html.modules.myoffering;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.myoffering.GroupMyOfferingLevel.GroupMyOfferingLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.MyOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MyOfferingsElement extends UIObject {

	private GroupMyOfferingLevel groupLevel;
	private MyOfferingList listElement;
	private static BibleReadingsElementUiBinder uiBinder = GWT
			.create(BibleReadingsElementUiBinder.class);

	interface BibleReadingsElementUiBinder extends
			UiBinder<Element, MyOfferingsElement> {
	}

	@UiField SectionElement bibleReadingContainer;
	@UiField DivElement modulesContainer, childContainer;

	public MyOfferingsElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new MyOfferingList();
		groupLevel = new GroupMyOfferingLevel();
		groupLevel.setGroupMyOfferingLevelEventHandler(new GroupMyOfferingLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<MyOfferingModel> modelList) {
				MyOfferingListPage page = new MyOfferingListPage(modelList);
				listElement.setListPage(page.getElement());
			}
		});
		
		modulesContainer.appendChild(groupLevel.getElement());
		childContainer.appendChild(listElement.getElement());
		bibleReadingContainer.appendChild(new ContentHTMLCompositeEnder().getElement());
	}
	
	public void load(){
		initElement();
	}
}
