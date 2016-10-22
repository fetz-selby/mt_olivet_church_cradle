package com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.regular;

import com.beta.rsatech.churchcradle.shared.GroupModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.UIObject;

public class SelectableHTMLButton extends UIObject {

	private static int counter = -1;
	private GroupModel groupModel;
	private static SelectableHTMLButtonUiBinder uiBinder = GWT
			.create(SelectableHTMLButtonUiBinder.class);

	interface SelectableHTMLButtonUiBinder extends
	UiBinder<Element, SelectableHTMLButton> {
	}

	@UiField DivElement groupName, elementContainer;
	@UiField InputElement check;

	public SelectableHTMLButton(GroupModel groupModel) {
		counter ++;
		this.groupModel = groupModel;
		setElement(uiBinder.createAndBindUi(this));
		initElement();
		initEvent();
	}
	
	private void initElement(){
		switch(counter % 4){
		case 0 : 
			elementContainer.setClassName("col-xs-3 bg-primary dk lter r-l m-r-sm m-b-sm");
			break;
		case 1 : 
			elementContainer.setClassName("col-xs-3 bg-success dk lter r-l m-r-sm m-b-sm");
			break;
		case 2 :
			elementContainer.setClassName("col-xs-3 bg-info dk lter r-l m-r-sm m-b-sm");
			break;
		case 3 :
			elementContainer.setClassName("col-xs-3 bg-danger dk lter r-l m-r-sm m-b-sm");
			break;
		}
		
		groupName.setInnerText(groupModel.getDescription());
		
	}

	private void initEvent(){
		DOM.sinkEvents(check, Event.ONCLICK);
		DOM.setEventListener(check, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				// TODO Auto-generated method stub

			}
		});
	}

	public boolean isChecked(){
		if(check.getPropertyBoolean("checked")){
			return true;
		}

		return false;
	}
	
	public int getGroupId(){
		return groupModel.getId();
	}
}
