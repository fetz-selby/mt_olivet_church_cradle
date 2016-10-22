package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlineoffering;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlineoffering.GroupOnlineOfferingLevel.GroupOnlineOfferingLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.OnlineOfferingPaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineOfferingElement extends UIObject {

	private GroupOnlineOfferingLevel groupLevel;
	private OnlineOfferingList listElement;
	private static BibleReadingsElementUiBinder uiBinder = GWT
			.create(BibleReadingsElementUiBinder.class);

	interface BibleReadingsElementUiBinder extends
			UiBinder<Element, OnlineOfferingElement> {
	}

	@UiField SectionElement bibleReadingContainer;
	@UiField DivElement modulesContainer, childContainer;

	public OnlineOfferingElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new OnlineOfferingList();
		groupLevel = new GroupOnlineOfferingLevel();
		groupLevel.setGroupOnlineOfferingLevelEventHandler(new GroupOnlineOfferingLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<OnlineOfferingPaymentModel> modelList) {
				OnlineOfferingListPage page = new OnlineOfferingListPage(modelList);
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
