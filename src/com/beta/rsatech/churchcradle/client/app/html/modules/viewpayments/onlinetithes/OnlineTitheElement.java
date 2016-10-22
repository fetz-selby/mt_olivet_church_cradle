package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinetithes;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinetithes.GroupOnlineTitheLevel.GroupOnlineTitheLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.OnlineTithePaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineTitheElement extends UIObject {

	private GroupOnlineTitheLevel groupLevel;
	private OnlineTitheList listElement;
	private static BibleReadingsElementUiBinder uiBinder = GWT
			.create(BibleReadingsElementUiBinder.class);

	interface BibleReadingsElementUiBinder extends
			UiBinder<Element, OnlineTitheElement> {
	}

	@UiField SectionElement bibleReadingContainer;
	@UiField DivElement modulesContainer, childContainer;

	public OnlineTitheElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new OnlineTitheList();
		groupLevel = new GroupOnlineTitheLevel();
		groupLevel.setGroupOnlineTitheLevelEventHandler(new GroupOnlineTitheLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<OnlineTithePaymentModel> modelList) {
				OnlineTitheListPage page = new OnlineTitheListPage(modelList);
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
