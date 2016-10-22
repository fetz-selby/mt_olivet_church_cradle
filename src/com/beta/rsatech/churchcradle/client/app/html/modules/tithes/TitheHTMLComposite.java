package com.beta.rsatech.churchcradle.client.app.html.modules.tithes;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.elements.HeaderElement;
import com.beta.rsatech.churchcradle.client.elements.IElement;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.events.TithePaymentEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.UIObject;

public class TitheHTMLComposite extends UIObject {

	private ArrayList<TitheModel> modelList;
	private String style, month;
	private double amount;
	private boolean showDetails;
	private static TitheHTMLCompositeUiBinder uiBinder = GWT
			.create(TitheHTMLCompositeUiBinder.class);

	interface TitheHTMLCompositeUiBinder extends
	UiBinder<Element, TitheHTMLComposite> {
	}

	@UiField SmallElement desc, smallAmount;
	@UiField IElement iElement;
	@UiField HeaderElement header;
	@UiField SectionElement mainPanel;
	@UiField AnchorElement detailsAnchor;
	@UiField DivElement detailsDiv;

	public TitheHTMLComposite(ArrayList<TitheModel> modelList, String month, double amount, String style) {
		this.modelList = modelList;
		this.month = month;
		this.amount = amount;
		this.style = style;
		
		if(modelList.size() > 1){
			showDetails = true;
		}
		setElement(uiBinder.createAndBindUi(this));
		initElement();
		initFakePaymentEvent();
	}

	private void initElement(){
		desc.setInnerText(month);
		smallAmount.setInnerHTML("&#8373; "+amount+" GHS");
		if(showDetails){
			detailsDiv.removeClassName("hide");
			initDetailEvent();
		}
		
		if(style.equalsIgnoreCase(AppConstants.BG_SUCCESS)){
			iElement.addClassName("text-success");
			smallAmount.setClassName("text-success");
			header.addClassName("bg-success");
		}else if(style.equalsIgnoreCase(AppConstants.BG_DANGER)){
			iElement.addClassName("text-danger");
			smallAmount.setClassName("text-danger");
			header.addClassName("bg-danger");
		}
	}
	
	private void initDetailEvent(){
		DOM.sinkEvents(detailsAnchor, Event.ONCLICK);
		DOM.setEventListener(detailsAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				TitheDetailsPopup popup = new TitheDetailsPopup(modelList, month);
			}
		});
	}
	
	private void initFakePaymentEvent(){
//		DOM.sinkEvents(mainPanel, Event.ONCLICK);
//		DOM.setEventListener(mainPanel, new EventListener() {
//			
//			@Override
//			public void onBrowserEvent(Event event) {
//				GlobalResources.getInstance().getEventBus().fireEvent(new TithePaymentEvent(month));
//			}
//		});
	}

}
