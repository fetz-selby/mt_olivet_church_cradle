package com.beta.rsatech.churchcradle.client.app.html.modules.offerings;

import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.UIObject;

public class OfferingHTMLComposite extends UIObject {

	private OfferingModel model;
	private static OfferingHTMLCompositeUiBinder uiBinder = GWT
			.create(OfferingHTMLCompositeUiBinder.class);

	interface OfferingHTMLCompositeUiBinder extends
			UiBinder<Element, OfferingHTMLComposite> {
	}

	@UiField SmallElement desc, smallAmount, smallDate;
	@UiField SectionElement mainPanel;
	
	public OfferingHTMLComposite(OfferingModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
		initEvent();
	}
	
	private void initElement(){
		desc.setInnerText(model.getDescription());
		smallAmount.setInnerText(model.getAmount()+"");
		smallDate.setInnerText(model.getCreatedTs());
	}
	
	private void initEvent(){
		DOM.sinkEvents(mainPanel, Event.ONCLICK);
		DOM.setEventListener(mainPanel, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
