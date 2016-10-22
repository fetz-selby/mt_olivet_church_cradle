package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
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

public class FuneralApprovePage extends UIObject {

	private FuneralApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static FuneralApprovePageUiBinder uiBinder = GWT
			.create(FuneralApprovePageUiBinder.class);

	interface FuneralApprovePageUiBinder extends
			UiBinder<Element, FuneralApprovePage> {
	}

	public interface FuneralApprovePageEventHandler{
		void onFuneralEditInvoked(FAnnounceModel model);
	}
	
	@UiField UListElement ulListContainer;

	public FuneralApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doFAnnounceLoad(){
		GlobalResources.getInstance().getListRPC().getFAnnouncesListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<FAnnounceModel>>() {
			
			@Override
			public void onSuccess(ArrayList<FAnnounceModel> result) {
				ulListContainer.setInnerText("");
				ulListContainer.removeAllChildren();
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(FAnnounceModel model : result){
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
	
	private Element getLi(final FAnnounceModel model){
		Element li = DOM.createElement("li");
		li.setClassName("list-group-item bg-danger list-height");
		
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
					handler.onFuneralEditInvoked(model);
				}
			}
		});
		
		Element nameDiv = DOM.createElement("div");
		nameDiv.setClassName("col-sm-2 clear text-white");
		nameDiv.setInnerText(model.getName());
		
		Element ageDiv = DOM.createElement("div");
		ageDiv.setClassName("col-sm-1 clear text-white");
		ageDiv.setInnerText(model.getAge()+"");
		
		Element descriptionDiv = DOM.createElement("div");
		descriptionDiv.setClassName("col-sm-4 clear text-white");
		descriptionDiv.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.APPROVE_FA_NOTICE_LIMIT));
		
		Element date = DOM.createElement("div");
		date.setClassName("col-sm-2 clear text-white");
		date.setInnerText(model.getDate());
		
		Element createdDiv = DOM.createElement("div");
		createdDiv.setClassName("col-sm-2 clear text-white");
		createdDiv.setInnerText(model.getCreatedTs());
		
		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);
		
		li.appendChild(spanRight);
		
		li.appendChild(nameDiv);
		li.appendChild(ageDiv);
		li.appendChild(descriptionDiv);
		li.appendChild(date);
		li.appendChild(createdDiv);
		
		return li;
	}

	public void load(){
		doFAnnounceLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}
	
	public void setFuneralApprovePageEventHandler(FuneralApprovePageEventHandler handler){
		this.handler = handler;
	}
}
