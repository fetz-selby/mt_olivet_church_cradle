package com.beta.rsatech.churchcradle.client.app.html.modules.offerings;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class OfferingListPage extends UIObject {

	private ArrayList<OfferingModel> modelList;
	private static OfferingListPageUiBinder uiBinder = GWT
			.create(OfferingListPageUiBinder.class);

	interface OfferingListPageUiBinder extends
			UiBinder<Element, OfferingListPage> {
	}

	@UiField DivElement divContainer;
	public OfferingListPage(ArrayList<OfferingModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		for(OfferingModel model : modelList){
			OfferingHTMLComposite composite = new OfferingHTMLComposite(model);
			divContainer.appendChild(composite.getElement());
		}
	}

}
