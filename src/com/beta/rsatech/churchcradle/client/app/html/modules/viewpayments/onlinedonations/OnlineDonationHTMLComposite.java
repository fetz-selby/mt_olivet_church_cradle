package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinedonations;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.OnlineDonationPaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineDonationHTMLComposite extends UIObject {

	private OnlineDonationPaymentModel model;
	private static BibleReadingHTMLCompositeUiBinder uiBinder = GWT
			.create(BibleReadingHTMLCompositeUiBinder.class);

	interface BibleReadingHTMLCompositeUiBinder extends
			UiBinder<Element, OnlineDonationHTMLComposite> {
	}

	@UiField SmallElement desc, smallDate;
	public OnlineDonationHTMLComposite(OnlineDonationPaymentModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		desc.setInnerHTML("&#8373; "+model.getAmount()+" GHS");
		smallDate.setInnerText(model.getCreatedTs());
	}

}
