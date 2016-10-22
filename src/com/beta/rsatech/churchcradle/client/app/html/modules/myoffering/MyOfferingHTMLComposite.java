package com.beta.rsatech.churchcradle.client.app.html.modules.myoffering;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.MyOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MyOfferingHTMLComposite extends UIObject {

	private MyOfferingModel model;
	private static BibleReadingHTMLCompositeUiBinder uiBinder = GWT
			.create(BibleReadingHTMLCompositeUiBinder.class);

	interface BibleReadingHTMLCompositeUiBinder extends
			UiBinder<Element, MyOfferingHTMLComposite> {
	}

	@UiField SmallElement desc, smallDate;
	public MyOfferingHTMLComposite(MyOfferingModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		desc.setInnerHTML("&#8373; "+model.getAmount()+" GHS");
		smallDate.setInnerText(model.getDate());
	}

}
