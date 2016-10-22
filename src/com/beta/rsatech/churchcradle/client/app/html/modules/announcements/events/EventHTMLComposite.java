package com.beta.rsatech.churchcradle.client.app.html.modules.announcements.events;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class EventHTMLComposite extends UIObject {

	private EAnnounceModel model;
	private static EventHTMLCompositeUiBinder uiBinder = GWT
			.create(EventHTMLCompositeUiBinder.class);

	interface EventHTMLCompositeUiBinder extends
			UiBinder<Element, EventHTMLComposite> {
	}

	@UiField SmallElement desc, smallVenue, smallTime, smallDate;
	public EventHTMLComposite(EAnnounceModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initComponents(){
		desc.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.ANNOUNCE_EVENT_TITLE));
		smallVenue.setInnerText(Utils.getTruncatedText(model.getVenue(), AppConstants.ANNOUNCE_VENUE));
		smallTime.setInnerText(model.getTime());
		smallDate.setInnerText(model.getDate());
	}
	
	public EAnnounceModel getModel() {
		return model;
	}

	public void setModel(EAnnounceModel model) {
		this.model = model;
	}

	public void load(){
		initComponents();
	}

}
