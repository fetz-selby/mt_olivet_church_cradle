package com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.PowerLeaderModel;
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

public class PowerLeadersPage extends UIObject {

	private PowerLeadersPageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static OfferingApprovePageUiBinder uiBinder = GWT
			.create(OfferingApprovePageUiBinder.class);

	interface OfferingApprovePageUiBinder extends
			UiBinder<Element, PowerLeadersPage> {
	}

	public interface PowerLeadersPageEventHandler{
		void onPowerLeaderEditInvoked(PowerLeaderModel model);
	}
	
	
	@UiField UListElement ulListContainer;

	public PowerLeadersPage() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private Element getLi(final PowerLeaderModel model){
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
					handler.onPowerLeaderEditInvoked(model);
				}
			}
		});
		
		Element nameDiv = DOM.createElement("div");
		nameDiv.setClassName("col-sm-3 clear text-white");
		nameDiv.setInnerText(model.getName());
		
		Element msisdnDiv = DOM.createElement("div");
		msisdnDiv.setClassName("col-sm-2 clear text-white");
		msisdnDiv.setInnerText(model.getMsisdn()+"");
		
		Element createdByDiv = DOM.createElement("div");
		createdByDiv.setClassName("col-sm-3 clear text-white");
		createdByDiv.setInnerText(Utils.getMember(model.getCreatedBy()));
		
		Element createdTsDiv = DOM.createElement("div");
		createdTsDiv.setClassName("col-sm-3 clear text-white");
		createdTsDiv.setInnerText(model.getCreatedTs());
		
		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);
		
		li.appendChild(spanRight);
		
		li.appendChild(nameDiv);
		li.appendChild(msisdnDiv);
		li.appendChild(createdByDiv);
		li.appendChild(createdTsDiv);
		
		return li;
	}
	
	private void doOfferingLoad(){
		GlobalResources.getInstance().getListRPC().getPowerLeaders(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<ArrayList<PowerLeaderModel>>() {
			
			@Override
			public void onSuccess(ArrayList<PowerLeaderModel> result) {
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(PowerLeaderModel model : result){
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
	
	public void setPowerLeadersPageEventHandler(PowerLeadersPageEventHandler handler){
		this.handler = handler;
	}

}
