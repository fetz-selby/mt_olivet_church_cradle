package com.beta.rsatech.churchcradle.client.app.html.modules.tithes;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.tithes.GroupTitheLevel.GroupTitheLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class TitheElement extends UIObject {

	private GroupTitheLevel groupLevel;
	private TitheList listElement;
	private static OfferingElementUiBinder uiBinder = GWT
			.create(OfferingElementUiBinder.class);

	interface OfferingElementUiBinder extends
			UiBinder<Element, TitheElement> {
	}

	@UiField SectionElement offeringContainer;
	@UiField DivElement modulesContainer, childContainer;

	public TitheElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		listElement = new TitheList();
		groupLevel = new GroupTitheLevel();
		groupLevel.setGroupTitheLevelEventHandler(new GroupTitheLevelEventHandler() {
			
			@Override
			public void onMonthClicked(ArrayList<TitheModel> modelList) {
				TitheListPage page = new TitheListPage(modelList);
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
