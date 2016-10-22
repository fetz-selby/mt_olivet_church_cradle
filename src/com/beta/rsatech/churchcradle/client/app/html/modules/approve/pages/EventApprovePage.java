package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class EventApprovePage extends UIObject {

	private EventApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static EventApprovePageUiBinder uiBinder = GWT
			.create(EventApprovePageUiBinder.class);

	interface EventApprovePageUiBinder extends
			UiBinder<Element, EventApprovePage> {
	}
	
	public interface EventApprovePageEventHandler{
		void onEventEditInvoked(EAnnounceModel model);
	}

	@UiField UListElement ulListContainer;
	public EventApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private Element getLi(final EAnnounceModel model){
		Element li = DOM.createElement("li");
		li.setClassName("list-group-item bg-info list-height");

		Element spanRight = DOM.createElement("span");
		spanRight.setClassName("pull-right");

		Element editIconAnchor = DOM.createElement("a");
		editIconAnchor.setAttribute("href", "javascript:void(0)");

		Element editIcon = DOM.createElement("i");
		editIcon.setClassName("fa fa-pencil fa-fw m-r-xs");

		DOM.sinkEvents(editIconAnchor, Event.ONCLICK);
		DOM.setEventListener(editIconAnchor, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onEventEditInvoked(model);
				}
			}
		});

		Element descDiv = DOM.createElement("div");
		descDiv.setClassName("col-sm-3 clear text-white");
		descDiv.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.APPROVE_EA_DESCRIPTION_LIMIT));

		Element venueDiv = DOM.createElement("div");
		venueDiv.setClassName("col-sm-2 clear text-white");
		venueDiv.setInnerText(Utils.getTruncatedText(model.getVenue(), AppConstants.APPROVE_EA_VENUE_LIMIT));

		Element date = DOM.createElement("div");
		date.setClassName("col-sm-2 clear text-white");
		date.setInnerText(model.getDate());
		
		Element createdByDiv = DOM.createElement("div");
		createdByDiv.setClassName("col-sm-2 clear text-white");
		createdByDiv.setInnerText(Utils.getMember(model.getCreatedBy()));
		
		Element createdTs = DOM.createElement("div");
		createdTs.setClassName("col-sm-2 clear text-white");
		createdTs.setInnerText(model.getCreatedTs());

		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);

		li.appendChild(spanRight);

		li.appendChild(descDiv);
		li.appendChild(venueDiv);
		li.appendChild(date);
		li.appendChild(createdByDiv);
		li.appendChild(createdTs);

		return li;
	}
	
	public void load(){
		doEAnnounceLoad();
	}
	
	private void doEAnnounceLoad(){
		GlobalResources.getInstance().getListRPC().getEAnnouncesListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<EAnnounceModel>>() {

			@Override
			public void onSuccess(ArrayList<EAnnounceModel> result) {
				ulListContainer.setInnerText("");
				ulListContainer.removeAllChildren();
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(EAnnounceModel model : result){
						Element li = getLi(model);
						ulListContainer.appendChild(li);
						liMap.put(model.getId(), li);
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}
	
	public void setEventApprovePageEventHandler(EventApprovePageEventHandler handler){
		this.handler = handler;
	}

}
