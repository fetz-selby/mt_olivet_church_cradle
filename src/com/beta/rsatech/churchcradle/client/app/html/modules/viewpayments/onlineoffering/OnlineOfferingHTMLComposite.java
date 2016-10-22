package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlineoffering;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.OnlineOfferingPaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineOfferingHTMLComposite extends UIObject {

	private OnlineOfferingPaymentModel model;
	private static BibleReadingHTMLCompositeUiBinder uiBinder = GWT
			.create(BibleReadingHTMLCompositeUiBinder.class);

	interface BibleReadingHTMLCompositeUiBinder extends
			UiBinder<Element, OnlineOfferingHTMLComposite> {
	}

	@UiField SmallElement desc, smallDate;
	public OnlineOfferingHTMLComposite(OnlineOfferingPaymentModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		desc.setInnerHTML("&#8373; "+model.getAmount()+" GHS");
		smallDate.setInnerText(model.getCreatedTs());
	}

}
