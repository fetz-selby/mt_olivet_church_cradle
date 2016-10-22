package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SMSModel;
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

public class SMSApprovePage extends UIObject {

	private SMSApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static SMSApprovePageUiBinder uiBinder = GWT
			.create(SMSApprovePageUiBinder.class);

	interface SMSApprovePageUiBinder extends UiBinder<Element, SMSApprovePage> {
	}
	
	public interface SMSApprovePageEventHandler{
		void onSMSEditInvoked(SMSModel model);
	}

	@UiField UListElement ulListContainer;
	public SMSApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private Element getLi(final SMSModel model){
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
					handler.onSMSEditInvoked(model);
				}
			}
		});
		
		Element memberDiv = DOM.createElement("div");
		memberDiv.setClassName("col-sm-2 clear text-white");
		memberDiv.setInnerText(Utils.getMember(model.getCreatedBy()));
		
		Element messageDiv = DOM.createElement("div");
		messageDiv.setClassName("col-sm-5 clear text-white");
		messageDiv.setInnerText(Utils.getTruncatedText(model.getMessage(), AppConstants.APPROVE_SMS_MESSAGE_LIMIT));
		
		Element displayDiv = DOM.createElement("div");
		displayDiv.setClassName("col-sm-2 clear text-white");
		displayDiv.setInnerText(model.getDisplay());
		
		Element date = DOM.createElement("div");
		date.setClassName("col-sm-2 clear text-white");
		date.setInnerText(model.getCreatedTs());
		
		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);
		
		li.appendChild(spanRight);
		
		li.appendChild(memberDiv);
		li.appendChild(messageDiv);
		li.appendChild(displayDiv);
		li.appendChild(date);
		
		return li;
	}

	private void doSMSLoad(){
		GlobalResources.getInstance().getListRPC().getSMSListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<SMSModel>>() {
			
			@Override
			public void onSuccess(ArrayList<SMSModel> result) {
				ulListContainer.setInnerText("");
				ulListContainer.removeAllChildren();
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(SMSModel model : result){
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
		doSMSLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}
	
	public void setSMSApprovePageEventHandler(SMSApprovePageEventHandler handler){
		this.handler = handler;
	}
}
