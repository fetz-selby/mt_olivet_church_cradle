package com.beta.rsatech.churchcradle.client.app.html.modules.tithes;

import java.util.ArrayList;
import java.util.Date;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.DateModel;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GroupTitheLevel extends Composite {

	private int yearFilter = new Date().getYear() < 1900?new Date().getYear()+1900:new Date().getYear();
	private GroupTitheLevelEventHandler handler;
	private static GroupTitheUiBinder uiBinder = GWT
			.create(GroupTitheUiBinder.class);

	interface GroupTitheUiBinder extends
			UiBinder<Widget, GroupTitheLevel> {
	}
	
	public interface GroupTitheLevelEventHandler{
		void onMonthClicked(ArrayList<TitheModel> modelList);
	}

	@UiField UListElement titheList;
	
	public GroupTitheLevel() {
		initWidget(uiBinder.createAndBindUi(this));
		doYearInit();
	}
	
	private void doYearInit(){
		GlobalResources.getInstance().getListRPC().getDateList(new AsyncCallback<ArrayList<DateModel>>() {
			
			@Override
			public void onSuccess(ArrayList<DateModel> result) {
				if(result != null){
					titheList.setInnerText("");
					titheList.removeAllChildren();
					for(DateModel model : result){
						Element li = getLi(model.getDate());
						titheList.appendChild(li);
					}
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private Element getLi(final int date){
		Element li = DOM.createElement("li");
		li.setClassName("b-b");
		
		Element a = DOM.createElement("a");
		a.setAttribute("href", "javascript:void(0)");
		
		Element i = DOM.createElement("i");
		i.setClassName("fa fa-chevron-right pull-right m-t-xs text-xs icon-muted");
		
		Element span = DOM.createElement("span");
		span.setInnerText(date+"");
		
		a.appendChild(i);
		a.appendChild(span);
		
		DOM.sinkEvents(a, Event.ONCLICK);
		DOM.setEventListener(a, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing("", date);
			}
		});
		
		li.appendChild(a);
		
		return li;
	}
	
	private void doListing(String month, int year){
		GlobalResources.getInstance().getListRPC().getTithe(GlobalResources.getInstance().getModel().getId(), GlobalResources.getInstance().getModel().getChurchId(), ""+year, new AsyncCallback<ArrayList<TitheModel>>() {
			
			@Override
			public void onSuccess(ArrayList<TitheModel> result) {
				if(result != null){
					if(handler != null){
						handler.onMonthClicked(result);
					}
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void setGroupTitheLevelEventHandler(GroupTitheLevelEventHandler handler){
		this.handler = handler;
	}

}
