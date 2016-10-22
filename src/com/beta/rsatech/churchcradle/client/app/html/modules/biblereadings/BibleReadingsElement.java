package com.beta.rsatech.churchcradle.client.app.html.modules.biblereadings;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.biblereadings.GroupBibleReadLevel.GroupBibleReadLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class BibleReadingsElement extends UIObject {

	private GroupBibleReadLevel groupLevel;
	private BibleReadList listElement;
	private static BibleReadingsElementUiBinder uiBinder = GWT
			.create(BibleReadingsElementUiBinder.class);

	interface BibleReadingsElementUiBinder extends
			UiBinder<Element, BibleReadingsElement> {
	}

	@UiField SectionElement bibleReadingContainer;
	@UiField DivElement modulesContainer, childContainer;

	public BibleReadingsElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new BibleReadList();
		groupLevel = new GroupBibleReadLevel();
		groupLevel.setGroupBibleReadLevelEventHandler(new GroupBibleReadLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<BibleReadingModel> modelList) {
				BibleReadingListPage page = new BibleReadingListPage(modelList);
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
