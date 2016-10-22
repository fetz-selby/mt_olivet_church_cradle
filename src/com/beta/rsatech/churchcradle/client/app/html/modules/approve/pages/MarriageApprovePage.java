package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
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

public class MarriageApprovePage extends UIObject {

	private MarriageApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static MarriageApprovePageUiBinder uiBinder = GWT
			.create(MarriageApprovePageUiBinder.class);

	interface MarriageApprovePageUiBinder extends
			UiBinder<Element, MarriageApprovePage> {
	}

	public interface MarriageApprovePageEventHandler{
		void onMarriageEditInvoked(MarriageModel model);
	}

	@UiField UListElement ulListContainer;
	
	public MarriageApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doMarriageLoad(){
		GlobalResources.getInstance().getListRPC().getMarriagesListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<MarriageModel>>() {
			
			@Override
			public void onSuccess(ArrayList<MarriageModel> result) {
				if(result != null){
					ulListContainer.setInnerText("");
					ulListContainer.removeAllChildren();
					liMap = new HashMap<Integer, Element>();
					for(MarriageModel model : result){
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
	
	private Element getLi(final MarriageModel model){
		Element li = DOM.createElement("li");
		li.setClassName("list-group-item bg-primary list-height");
		
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
					handler.onMarriageEditInvoked(model);
				}
			}
		});
		
		Element maleDiv = DOM.createElement("div");
		maleDiv.setClassName("col-sm-2 clear text-white");
		maleDiv.setInnerText(model.getManName());
		
		Element femaleDiv = DOM.createElement("div");
		femaleDiv.setClassName("col-sm-2 clear text-white");
		femaleDiv.setInnerText(model.getFemaleName());
		
		Element descriptionDiv = DOM.createElement("div");
		descriptionDiv.setClassName("col-sm-3 clear text-white");
		descriptionDiv.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.APPROVE_MA_DESCRIPTION_LIMIT));
		
		Element venue = DOM.createElement("div");
		venue.setClassName("col-sm-2 clear text-white");
		venue.setInnerText(Utils.getTruncatedText(model.getVenue(), AppConstants.APPROVE_MA_VENUE_LIMIT));
		
		Element dateDiv = DOM.createElement("div");
		dateDiv.setClassName("col-sm-2 clear text-white");
		dateDiv.setInnerText(model.getDate());
		
		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);
		
		li.appendChild(spanRight);
		
		li.appendChild(maleDiv);
		li.appendChild(femaleDiv);
		li.appendChild(descriptionDiv);
		li.appendChild(venue);
		li.appendChild(dateDiv);
		
		return li;
	}
	
	public void load(){
		doMarriageLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}
	
	public void setMarriageApprovePageEventHandler(MarriageApprovePageEventHandler handler){
		this.handler = handler;
	}

}
