package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinetithes;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.OnlineTithePaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineTitheHTMLComposite extends UIObject {

	private OnlineTithePaymentModel model;
	private static BibleReadingHTMLCompositeUiBinder uiBinder = GWT
			.create(BibleReadingHTMLCompositeUiBinder.class);

	interface BibleReadingHTMLCompositeUiBinder extends
			UiBinder<Element, OnlineTitheHTMLComposite> {
	}

	@UiField SmallElement desc, smallName, smallDate;
	public OnlineTitheHTMLComposite(OnlineTithePaymentModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		desc.setInnerHTML("&#8373; "+model.getAmount()+" GHS");
		smallName.setInnerText(Utils.getMember(model.getMemberId()));
		smallDate.setInnerText(model.getCreatedTs());
	}

}
