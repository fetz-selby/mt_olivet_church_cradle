package com.beta.rsatech.churchcradle.client.app.html.modules.sms;

import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.UIObject;

public class GroupSMSLevel extends UIObject {

	private GroupSMSEventHandler handler;
	private static GroupSMSLevelUiBinder uiBinder = GWT
			.create(GroupSMSLevelUiBinder.class);

	interface GroupSMSLevelUiBinder extends UiBinder<Element, GroupSMSLevel> {
	}

	public interface GroupSMSEventHandler{
		void onModuleClicked(int module);
	}

	@UiField AnchorElement composeAnchor, sentAnchor, pendingAnchor;
	@UiField LIElement pendingLi;
	
	public GroupSMSLevel() {
		setElement(uiBinder.createAndBindUi(this));
		initEvents();
	}

	public void initEvents(){
		Element composeAnchorElement = composeAnchor.cast();
		Element sentAnchoElement = sentAnchor.cast();

		DOM.sinkEvents(composeAnchorElement, Event.ONCLICK);
		DOM.setEventListener(composeAnchorElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onModuleClicked(SMSModuleConstants.COMPOSE);
				}
			}
		});

		if(Utils.isSMSApproveEnabled()){
			pendingLi.removeClassName("hide");
			Element pendingAnchorElement = pendingAnchor.cast();
			DOM.sinkEvents(pendingAnchorElement, Event.ONCLICK);
			DOM.setEventListener(pendingAnchorElement, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					if(handler != null){
						handler.onModuleClicked(SMSModuleConstants.PENDING);
					}
				}
			});
		}

		DOM.sinkEvents(sentAnchoElement, Event.ONCLICK);
		DOM.setEventListener(sentAnchoElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onModuleClicked(SMSModuleConstants.SENT);
				}
			}
		});
	}

	public void setGroupSMSEventHandler(GroupSMSEventHandler handler){
		this.handler = handler;
	}

}
