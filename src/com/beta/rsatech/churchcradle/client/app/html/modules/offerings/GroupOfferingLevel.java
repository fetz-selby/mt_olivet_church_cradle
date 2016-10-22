package com.beta.rsatech.churchcradle.client.app.html.modules.offerings;

import java.util.ArrayList;
import java.util.Date;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.YearWidget;
import com.beta.rsatech.churchcradle.client.widgets.YearWidget.YearWidgetEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GroupOfferingLevel extends Composite {

	private int yearFilter = new Date().getYear() < 1900?new Date().getYear()+1900:new Date().getYear();
	private GroupOfferingLevelEventHandler handler;
	private static GroupOfferingUiBinder uiBinder = GWT
			.create(GroupOfferingUiBinder.class);

	interface GroupOfferingUiBinder extends
			UiBinder<Widget, GroupOfferingLevel> {
	}
	
	public interface GroupOfferingLevelEventHandler{
		void onMonthClicked(ArrayList<OfferingModel> modelList);
	}

	@UiField UListElement offeringList;
	@UiField YearWidget yearWidget;
	@UiField AnchorElement janAnchor, febAnchor, marAnchor, aprAnchor, mayAnchor, junAnchor, julAnchor, augAnchor, sepAnchor, octAnchor, novAnchor, decAnchor;
	
	public GroupOfferingLevel() {
		initWidget(uiBinder.createAndBindUi(this));
		doDateStyling();
		initEvents();
	}
	
	private void doDateStyling(){
		yearWidget.addDateStyle(AppConstants.BG_SUCCESS);
	}
	
	private void initEvents(){
		yearWidget.setYearWidgetEventHandler(new YearWidgetEventHandler() {
			
			@Override
			public void onDateClicked(int year) {
				yearFilter = year;
			}
		});
		
		DOM.sinkEvents(janAnchor, Event.ONCLICK);
		DOM.setEventListener(janAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.JAN, yearFilter);
			}
		});
		
		DOM.sinkEvents(febAnchor, Event.ONCLICK);
		DOM.setEventListener(febAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.FEB, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(marAnchor, Event.ONCLICK);
		DOM.setEventListener(marAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.MAR, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(aprAnchor, Event.ONCLICK);
		DOM.setEventListener(aprAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.APR, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(mayAnchor, Event.ONCLICK);
		DOM.setEventListener(mayAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.MAY, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(junAnchor, Event.ONCLICK);
		DOM.setEventListener(junAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.JUN, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(julAnchor, Event.ONCLICK);
		DOM.setEventListener(julAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.JUL, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(augAnchor, Event.ONCLICK);
		DOM.setEventListener(augAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.AUG, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(sepAnchor, Event.ONCLICK);
		DOM.setEventListener(sepAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.SEP, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(octAnchor, Event.ONCLICK);
		DOM.setEventListener(octAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.OCT, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(novAnchor, Event.ONCLICK);
		DOM.setEventListener(novAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.NOV, yearFilter);
				
			}
		});
		
		DOM.sinkEvents(decAnchor, Event.ONCLICK);
		DOM.setEventListener(decAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doListing(AppConstants.DEC, yearFilter);
				
			}
		});
	}
	
	private void doListing(String month, int year){
		GlobalResources.getInstance().getListRPC().getOffering(GlobalResources.getInstance().getModel().getChurchId(), month+","+year, new AsyncCallback<ArrayList<OfferingModel>>() {
			
			@Override
			public void onSuccess(ArrayList<OfferingModel> result) {
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
	
	public void setGroupOfferingLevelEventHandler(GroupOfferingLevelEventHandler handler){
		this.handler = handler;
	}

}
