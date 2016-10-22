package com.beta.rsatech.churchcradle.client.app.html.modules.members;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class MembersLevel extends UIObject {

	private MembersLevelEventHandler handler;
	private static MembersLevelUiBinder uiBinder = GWT
			.create(MembersLevelUiBinder.class);

	interface MembersLevelUiBinder extends UiBinder<Element, MembersLevel> {
	}

	public interface MembersLevelEventHandler{
		void onMemberInvoked(MemberModel model, Element element);
	}
	
	@UiField DivElement listContainer;
	public MembersLevel(int groupId) {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	public MembersLevel() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private Element getAnchorListElement(String listName){
		//<a href="#" class="list-group-item active"> All Contacts </a>

		Element a = DOM.createElement("a");
		a.setAttribute("href", "javascript:void(0)");
		a.setClassName("list-group-item");
		a.setInnerText(listName);
		
		return a;
	}
	
	private void initContactsList(ArrayList<MemberModel> contactsList){

		listContainer.setInnerText("");
		
		for(final MemberModel model : contactsList){
			final Element a = getAnchorListElement(model.getFirstName()+" "+model.getLastName());
			DOM.sinkEvents(a, Event.ONCLICK);
			DOM.setEventListener(a, new EventListener() {
				
				@Override
				public void onBrowserEvent(Event event) {
					if(handler != null){
						handler.onMemberInvoked(model, a);
					}
				}
			});
			
			listContainer.appendChild(a);
		}
	}
	
	public void loadByOrganisation(int groupId){
		GWT.log("Loading groups ....");
		doGetAllGroupMembers(GlobalResources.getInstance().getModel().getChurchId(), groupId);
	}
	
	public void loadByClasses(int leaderId){
		doGetAllGroupMembersByClasses(GlobalResources.getInstance().getModel().getChurchId(), leaderId);
	}
	
	private void doGetAllGroupMembersByClasses(int churchId, int leaderId){
		GlobalResources.getInstance().getListRPC().getAllMembersListByLeader(churchId, leaderId, new AsyncCallback<ArrayList<MemberModel>>() {
			
			@Override
			public void onSuccess(ArrayList<MemberModel> result) {
				if(result != null){
					initContactsList(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void doGetAllGroupMembers(int churchId, int groupId){
		GlobalResources.getInstance().getListRPC().getAllMembersList(churchId, groupId, new AsyncCallback<ArrayList<MemberModel>>() {
			
			@Override
			public void onSuccess(ArrayList<MemberModel> result) {
				if(result != null){
					initContactsList(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setMembersLevelEventHandler(MembersLevelEventHandler handler){
		this.handler = handler;
	}
}
