package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinedonations;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.OnlineDonationPaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineDonationListPage extends UIObject {

	private ArrayList<OnlineDonationPaymentModel> modelList;
	private static BibleReadingListPageUiBinder uiBinder = GWT
			.create(BibleReadingListPageUiBinder.class);

	interface BibleReadingListPageUiBinder extends
			UiBinder<Element, OnlineDonationListPage> {
	}

	@UiField DivElement divContainer;
	public OnlineDonationListPage(ArrayList<OnlineDonationPaymentModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		for(OnlineDonationPaymentModel model : modelList){
			OnlineDonationHTMLComposite composite = new OnlineDonationHTMLComposite(model);
			divContainer.appendChild(composite.getElement());
		}
	}

}
