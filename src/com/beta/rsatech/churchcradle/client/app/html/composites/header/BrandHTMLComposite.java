package com.beta.rsatech.churchcradle.client.app.html.composites.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class BrandHTMLComposite extends UIObject {

	private String orgNameString;
	private static BrandHTMLCompositeUiBinder uiBinder = GWT
			.create(BrandHTMLCompositeUiBinder.class);

	interface BrandHTMLCompositeUiBinder extends
			UiBinder<Element, BrandHTMLComposite> {
	}

	@UiField SpanElement orgName;
	public BrandHTMLComposite(String orgNameString) {
		this.orgNameString = orgNameString;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		orgName.setInnerText(orgNameString);
	}

}
