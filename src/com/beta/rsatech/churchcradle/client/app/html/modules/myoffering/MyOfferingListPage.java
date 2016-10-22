package com.beta.rsatech.churchcradle.client.app.html.modules.myoffering;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.MyOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MyOfferingListPage extends UIObject {

	private ArrayList<MyOfferingModel> modelList;
	private static BibleReadingListPageUiBinder uiBinder = GWT
			.create(BibleReadingListPageUiBinder.class);

	interface BibleReadingListPageUiBinder extends
			UiBinder<Element, MyOfferingListPage> {
	}

	@UiField DivElement divContainer;
	public MyOfferingListPage(ArrayList<MyOfferingModel> modelList) {
		this.modelList = modelList;
		setElement(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		for(MyOfferingModel model : modelList){
			MyOfferingHTMLComposite composite = new MyOfferingHTMLComposite(model);
			divContainer.appendChild(composite.getElement());
		}
	}

}
