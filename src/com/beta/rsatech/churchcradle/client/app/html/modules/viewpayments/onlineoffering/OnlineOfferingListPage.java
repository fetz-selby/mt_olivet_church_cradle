package com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlineoffering;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.OnlineOfferingPaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OnlineOfferingListPage extends UIObject {

	private ArrayList<OnlineOfferingPaymentModel> modelList;
	private static BibleReadingListPageUiBinder uiBinder = GWT
			.create(BibleReadingListPageUiBinder.class);

	interface BibleReadingListPageUiBinder extends
			UiBinder<Element, OnlineOfferingListPage> {
	}

	@UiField DivElement divContainer;
	public OnlineOfferingListPage(ArrayList<OnlineOfferingPaymentModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		for(OnlineOfferingPaymentModel model : modelList){
			OnlineOfferingHTMLComposite composite = new OnlineOfferingHTMLComposite(model);
			divContainer.appendChild(composite.getElement());
		}
	}

}
