package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinetithes;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.beta.rsatech.churchcradle.shared.OnlineTithePaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineTitheListPage extends UIObject {

	private ArrayList<OnlineTithePaymentModel> modelList;
	private static BibleReadingListPageUiBinder uiBinder = GWT
			.create(BibleReadingListPageUiBinder.class);

	interface BibleReadingListPageUiBinder extends
			UiBinder<Element, OnlineTitheListPage> {
	}

	@UiField DivElement divContainer;
	public OnlineTitheListPage(ArrayList<OnlineTithePaymentModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		for(OnlineTithePaymentModel model : modelList){
			OnlineTitheHTMLComposite composite = new OnlineTitheHTMLComposite(model);
			divContainer.appendChild(composite.getElement());
		}
	}

}
