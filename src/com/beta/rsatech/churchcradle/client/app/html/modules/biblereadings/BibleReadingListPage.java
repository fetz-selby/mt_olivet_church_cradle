package com.beta.rsatech.churchcradle.client.app.html.modules.biblereadings;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class BibleReadingListPage extends UIObject {

	private ArrayList<BibleReadingModel> modelList;
	private static BibleReadingListPageUiBinder uiBinder = GWT
			.create(BibleReadingListPageUiBinder.class);

	interface BibleReadingListPageUiBinder extends
			UiBinder<Element, BibleReadingListPage> {
	}

	@UiField DivElement divContainer;
	public BibleReadingListPage(ArrayList<BibleReadingModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		for(BibleReadingModel model : modelList){
			BibleReadingHTMLComposite composite = new BibleReadingHTMLComposite(model);
			divContainer.appendChild(composite.getElement());
		}
	}

}
