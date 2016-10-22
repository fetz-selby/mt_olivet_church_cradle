package com.beta.rsatech.churchcradle.client.app.html.modules.members;

import java.util.HashMap;
import java.util.TreeSet;

import com.beta.rsatech.churchcradle.client.events.AddGroupEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class GroupMemberLevel extends UIObject {

	private GroupMemberLevelEventHandler handler;
	private static GroupMemberLevelUiBinder uiBinder = GWT
			.create(GroupMemberLevelUiBinder.class);

	interface GroupMemberLevelUiBinder extends
	UiBinder<Element, GroupMemberLevel> {
	}

	public interface GroupMemberLevelEventHandler{
		void onListInvoked(String text, int groupId, Element element);
	}

	@UiField DivElement listContainer;
	@UiField AnchorElement addGroupAnchor;

	public GroupMemberLevel() {
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}

	private void initElement(){
		if(GlobalResources.getInstance().getModel().isAdmin()){
			addGroupAnchor.setAttribute("href", "javascript:void(0)");
			Element aElement = addGroupAnchor.cast();
			DOM.sinkEvents(aElement, Event.ONCLICK);
			DOM.setEventListener(aElement, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					GlobalResources.getInstance().getEventBus().fireEvent(new AddGroupEvent());
				}
			});
			addGroupAnchor.removeClassName("hide");
		}
	}

	private void doLoadAllOrganisations(){
		GlobalResources.getInstance().getListRPC().getAllOrganisationMap(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<HashMap<Integer,String>>() {

			@Override
			public void onSuccess(HashMap<Integer, String> result) {
				if(result != null){
					initList(result);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void initList(final HashMap<Integer, String> listMap){
		listContainer.setInnerText("");
		
		TreeSet<String> tree = new TreeSet<String>();
		
		for(Integer key : listMap.keySet()){
			tree.add(listMap.get(key));
		}

		for(final String key : tree){
			
			if(listMap.containsValue(key)){
				for(final Integer intKey : listMap.keySet()){
					
					if(listMap.get(intKey).equals(key)){
						
						final Element a = getAnchorListElement(listMap.get(intKey));
						DOM.sinkEvents(a, Event.ONCLICK);
						DOM.setEventListener(a, new EventListener() {

							@Override
							public void onBrowserEvent(Event event) {
								if(handler != null){
									handler.onListInvoked(key, intKey, a);
								}
							}
						});

						listContainer.appendChild(a);
						
					}
				}
			}
			
		}
	}

	public void load(){
		doLoadAllOrganisations();
	}

	private Element getAnchorListElement(String listName){
		//<a href="#" class="list-group-item active"> All Contacts </a>

		Element a = DOM.createElement("a");
		a.setAttribute("href", "javascript:void(0)");
		a.setClassName("list-group-item");
		a.setInnerText(listName);

		return a;
	}

	public void setGroupMemberLevelEventHandler(GroupMemberLevelEventHandler handler){
		this.handler = handler;
	}
}
