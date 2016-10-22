package com.beta.rsatech.churchcradle.client.app.html.modules.biblereadings;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class BibleReadingHTMLComposite extends UIObject {

	private BibleReadingModel model;
	private static BibleReadingHTMLCompositeUiBinder uiBinder = GWT
			.create(BibleReadingHTMLCompositeUiBinder.class);

	interface BibleReadingHTMLCompositeUiBinder extends
			UiBinder<Element, BibleReadingHTMLComposite> {
	}

	@UiField SmallElement desc, smallName, smalltheme, smallDate;
	public BibleReadingHTMLComposite(BibleReadingModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		desc.setInnerText(model.getDescription());
		smallName.setInnerText(model.getName());
		smalltheme.setInnerText(model.getTheme());
		smallDate.setInnerText(model.getDate());
	}

}
