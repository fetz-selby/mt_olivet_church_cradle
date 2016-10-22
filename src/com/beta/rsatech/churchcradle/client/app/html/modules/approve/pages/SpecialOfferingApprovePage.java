package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
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

public class SpecialOfferingApprovePage extends UIObject {

	private SpecialOfferingApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static OfferingApprovePageUiBinder uiBinder = GWT
			.create(OfferingApprovePageUiBinder.class);

	interface OfferingApprovePageUiBinder extends
			UiBinder<Element, SpecialOfferingApprovePage> {
	}

	public interface SpecialOfferingApprovePageEventHandler{
		void onSpecialOfferingEditInvoked(SpecialOfferingModel model);
	}
	
	
	@UiField UListElement ulListContainer;

	public SpecialOfferingApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private Element getLi(final SpecialOfferingModel model){
		Element li = DOM.createElement("li");
		li.setClassName("list-group-item bg-dark list-height");
		
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
					handler.onSpecialOfferingEditInvoked(model);
				}
			}
		});
		
		Element descDiv = DOM.createElement("div");
		descDiv.setClassName("col-sm-4 clear text-white");
		descDiv.setInnerText(Utils.getTruncatedText(model.getMessage(), AppConstants.APPROVE_OFFERING_DESCRIPTION_LIMIT));
		
		Element amountDiv = DOM.createElement("div");
		amountDiv.setClassName("col-sm-2 clear text-white");
		amountDiv.setInnerText(model.getAmount()+"");
		
		Element memberDiv = DOM.createElement("div");
		memberDiv.setClassName("col-sm-3 clear text-white");
		memberDiv.setInnerText(Utils.getMember(model.getMemberId()));
		
		Element date = DOM.createElement("div");
		date.setClassName("col-sm-2 clear text-white");
		date.setInnerText(model.getCreatedDate());
		
		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);
		
		li.appendChild(spanRight);
		
		li.appendChild(descDiv);
		li.appendChild(amountDiv);
		li.appendChild(memberDiv);
		li.appendChild(date);
		
		return li;
	}
	
	private void doOfferingLoad(){
		GlobalResources.getInstance().getListRPC().getSpecialOfferingsListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<SpecialOfferingModel>>() {
			
			@Override
			public void onSuccess(ArrayList<SpecialOfferingModel> result) {
				ulListContainer.setInnerText("");
				ulListContainer.removeAllChildren();
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(SpecialOfferingModel model : result){
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
	
	public void load(){
		doOfferingLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}
	
	public void setSpecialOfferingApprovePageEventHandler(SpecialOfferingApprovePageEventHandler handler){
		this.handler = handler;
	}

}
