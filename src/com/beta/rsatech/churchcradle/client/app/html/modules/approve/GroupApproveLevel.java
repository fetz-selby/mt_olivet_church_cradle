package com.beta.rsatech.churchcradle.client.app.html.modules.approve;

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

public class GroupApproveLevel extends UIObject {

	private String groupValue;
	private GroupApprovalLevelEventHandler handler;
	private static GroupApproveLevelUiBinder uiBinder = GWT
			.create(GroupApproveLevelUiBinder.class);

	interface GroupApproveLevelUiBinder extends
			UiBinder<Element, GroupApproveLevel> {
	}

	public interface GroupApprovalLevelEventHandler{
		void onItemClicked(String module);
	}
	
	@UiField UListElement approveList;
	public GroupApproveLevel(String groupValue) {
		this.groupValue = groupValue;
		setElement(uiBinder.createAndBindUi(this));
		doGroupLevelRendering();
	}
	
	private void doGroupLevelRendering(){
//		String OFFERING = "OFFERING";
//		String MEMBER = "MEMBER";
//		String DONATION = "DONATION";
//		String MARRIAGE = "MARRIAGE";
//		String FUNERAL = "FUNERAL";
//		String EVENT = "EVENT";
//		String B_READING = "B_READING";
//		String LIBRARY = "LIBRARY";
//		String TITHES = "TITHES";
//		String S_OFFERING = "S_OFFERING";
		
		ArrayList<String> moduleList = Utils.getModulesList(",", groupValue);
		for(String module : moduleList){
			if(module.equals("SMS")){
				approveList.appendChild(getLi(module));
			}else{
				approveList.appendChild(getLi(Utils.getCapitalizedWord(module)));
			}
		}
		
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
	
	public void setGroupApprovalLevelEventHandler(GroupApprovalLevelEventHandler handler){
		this.handler = handler;
	}
	
//	<li class="b-b ">
//	<a href="#">
//		<i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
//		New Member
//	</a>
//</li>

}
