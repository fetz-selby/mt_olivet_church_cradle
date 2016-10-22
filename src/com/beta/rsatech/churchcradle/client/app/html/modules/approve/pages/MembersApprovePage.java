package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MemberModel;
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

public class MembersApprovePage extends UIObject {

	private MembersApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static MembersApprovePageUiBinder uiBinder = GWT
			.create(MembersApprovePageUiBinder.class);

	interface MembersApprovePageUiBinder extends
			UiBinder<Element, MembersApprovePage> {
	}
	
	public interface MembersApprovePageEventHandler{
		void onMemberEditInvoked(MemberModel model);
	}

	@UiField UListElement ulListContainer;
	
	public MembersApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void doMembersLoad(){
		GlobalResources.getInstance().getListRPC().getMembersListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<MemberModel>>() {
			
			@Override
			public void onSuccess(ArrayList<MemberModel> result) {
				if(result != null){
					ulListContainer.setInnerText("");
					ulListContainer.removeAllChildren();
					liMap = new HashMap<Integer, Element>();
					for(MemberModel model : result){
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
	
	private Element getLi(final MemberModel model){
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
					handler.onMemberEditInvoked(model);
				}
			}
		});
		
		Element nameDiv = DOM.createElement("div");
		nameDiv.setClassName("col-sm-3 clear text-white");
		nameDiv.setInnerText(model.getFirstName()+" "+model.getLastName());
		
		Element occupationDiv = DOM.createElement("div");
		occupationDiv.setClassName("col-sm-3 clear text-white");
		occupationDiv.setInnerText(Utils.getTruncatedText(model.getOccupation(), AppConstants.APPROVE_MEM_OCCUPATION_LIMIT));
		
		Element classDiv = DOM.createElement("div");
		classDiv.setClassName("col-sm-3 clear text-white");
		classDiv.setInnerText(Utils.getClassLeader(model.getClassId()));
		
		Element createdTsDiv = DOM.createElement("div");
		createdTsDiv.setClassName("col-sm-2 clear text-white");
		createdTsDiv.setInnerText(model.getCreatedTs());
		
		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);
		
		li.appendChild(spanRight);
		li.appendChild(nameDiv);
		li.appendChild(occupationDiv);
		li.appendChild(classDiv);
		li.appendChild(createdTsDiv);
		
		return li;
	}
	
	public void load(){
		doMembersLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}
	
	public void setMembersApprovePageEventHandler(MembersApprovePageEventHandler handler){
		this.handler = handler;
	}
}
