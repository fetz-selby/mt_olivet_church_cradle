package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinedonations;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinedonations.GroupOnlineDonationLevel.GroupOnlineDonationLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.OnlineDonationPaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineDonationElement extends UIObject {

	private GroupOnlineDonationLevel groupLevel;
	private OnlineDonationList listElement;
	private static BibleReadingsElementUiBinder uiBinder = GWT
			.create(BibleReadingsElementUiBinder.class);

	interface BibleReadingsElementUiBinder extends
			UiBinder<Element, OnlineDonationElement> {
	}

	@UiField SectionElement bibleReadingContainer;
	@UiField DivElement modulesContainer, childContainer;

	public OnlineDonationElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new OnlineDonationList();
		groupLevel = new GroupOnlineDonationLevel();
		groupLevel.setGroupOnlineDonationLevelEventHandler(new GroupOnlineDonationLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<OnlineDonationPaymentModel> modelList) {
				OnlineDonationListPage page = new OnlineDonationListPage(modelList);
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
