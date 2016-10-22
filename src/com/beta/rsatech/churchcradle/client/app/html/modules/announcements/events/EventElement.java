package com.beta.rsatech.churchcradle.client.app.html.modules.announcements.events;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class EventElement extends UIObject {

	private static EventElementUiBinder uiBinder = GWT
			.create(EventElementUiBinder.class);

	interface EventElementUiBinder extends UiBinder<Element, EventElement> {
	}

	@UiField DivElement divContainer;
	public EventElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doEventLoad(){
		GlobalResources.getInstance().getListRPC().getEAnnouncesListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.ACTIVE, new AsyncCallback<ArrayList<EAnnounceModel>>() {
			
			@Override
			public void onSuccess(ArrayList<EAnnounceModel> result) {
				for(EAnnounceModel model : result){
					EventHTMLComposite composite = new EventHTMLComposite(model);
					divContainer.appendChild(composite.getElement());
					composite.load();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void load(){
		doEventLoad();
	}

}
