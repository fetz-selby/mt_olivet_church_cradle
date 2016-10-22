package com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.UIObject;

public class GroupPowerLeadersLevel extends UIObject {

	private GroupPowerLeadersLevelEventHandler handler;
	private static GroupApproveLevelUiBinder uiBinder = GWT
			.create(GroupApproveLevelUiBinder.class);

	interface GroupApproveLevelUiBinder extends
			UiBinder<Element, GroupPowerLeadersLevel> {
	}

	public interface GroupPowerLeadersLevelEventHandler{
		void onItemClicked(String module);
	}
	
	@UiField UListElement modulesList;
	public GroupPowerLeadersLevel(String groupValue) {
		setElement(uiBinder.createAndBindUi(this));
		doGroupLevelRendering();
	}
	
	private void doGroupLevelRendering(){
		
		ArrayList<String> moduleList = getModuleList();
		for(String module : moduleList){
			if(module.equals("SMS")){
				modulesList.appendChild(getLi(module));
			}else{
				modulesList.appendChild(getLi(Utils.getCapitalizedWord(module)));
			}
		}
		
	}
	
	private ArrayList<String> getModuleList(){
		String[] modules = {"Power Leaders"};
		
		ArrayList<String> modulesList = new ArrayList<String>();
		for(String module : modules){
			modulesList.add(module);
		}
		
		return modulesList;
	}
	
	private Element getLi(final String item){
		Element li = DOM.createElement("li");
		li.setClassName("b-b");
		
		Element a = DOM.createElement("a");
		a.setAttribute("href", "javascript:void(0)");
		
		Element i = DOM.createElement("i");
		i.setClassName("fa fa-chevron-right pull-right m-t-xs text-xs icon-muted");
		
		Element span = DOM.createElement("span");
		span.setInnerText(item);
		
		a.appendChild(i);
		a.appendChild(span);
		
		DOM.sinkEvents(a, Event.ONCLICK);
		DOM.setEventListener(a, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onItemClicked(item);
				}
			}
		});
		
		li.appendChild(a);
		
		return li;
	}
	
	public void setGroupPowerLeadersLevelEventHandler(GroupPowerLeadersLevelEventHandler handler){
		this.handler = handler;
	}
	
//	<li class="b-b ">
//	<a href="#">
//		<i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
//		New Member
//	</a>
//</li>

}
