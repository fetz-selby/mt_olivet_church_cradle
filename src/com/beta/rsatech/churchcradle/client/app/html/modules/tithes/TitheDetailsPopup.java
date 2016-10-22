package com.beta.rsatech.churchcradle.client.app.html.modules.tithes;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class TitheDetailsPopup extends CustomDraggablePopupPanel {

	private ArrayList<TitheModel> modelList;
	private String month;
	private boolean isChecked = false;
	private static BibleReadingApprovePopupUiBinder uiBinder = GWT
			.create(BibleReadingApprovePopupUiBinder.class);

	interface BibleReadingApprovePopupUiBinder extends
	UiBinder<Widget, TitheDetailsPopup> {
	}

	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv, loadingDiv;
	@UiField AnchorElement closeAnchor;
	@UiField DivElement rowContainer, monthTitle;
	@UiField Button submitBtn;

	public TitheDetailsPopup(ArrayList<TitheModel> model, String month) {
		this.modelList = model;
		this.month = month;
		add(uiBinder.createAndBindUi(this));
		initPopup();
		initComponent();
		initEvents();
	}

	private void initPopup(){
		setAutoHideEnabled(false);
		setGlassEnabled(true);
		setGlassStyleName("glassPanel");
		setDraggable(true);
		center();
	}

	private void initComponent(){
		monthTitle.setInnerText(month);
		for(TitheModel model : modelList){
			rowContainer.appendChild(getRow(model.getDate(), model.getAmount()));
		}
	}

	private Element getRow(String date, double amount){
		Element divElement = DOM.createElement("div");
		divElement.setClassName("form-group");

		Element dateElement = DOM.createElement("div");
		dateElement.setClassName("list-group-item pull-left");
		dateElement.setInnerText(date);

		Element amountElement = DOM.createElement("div");
		amountElement.setClassName("bg-success list-group-item pull-right");
		amountElement.setInnerHTML("&#8373; "+amount+" GHS");

		divElement.appendChild(dateElement);
		divElement.appendChild(amountElement);

		return divElement;
	}

	private void initEvents(){
		DOM.sinkEvents(closeAnchor, Event.ONCLICK);
		DOM.setEventListener(closeAnchor, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				hide();
			}
		});

		submitBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}
}
