package com.beta.rsatech.churchcradle.client.widgets;

import java.util.ArrayList;
import java.util.Date;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.DateModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class YearWidget extends Composite {

	private YearWidgetEventHandler handler;
	private ArrayList<Integer> yearList;
	private int cursor = 0, currentYear = 2015;
	private static YearWidgetUiBinder uiBinder = GWT
			.create(YearWidgetUiBinder.class);

	interface YearWidgetUiBinder extends UiBinder<Widget, YearWidget> {
	}
	
	public interface YearWidgetEventHandler{
		void onDateClicked(int year);
	}

	@UiField UListElement dateList;
	@UiField AnchorElement chevronAnchorLeft, chevronAnchorRight;
	@UiField SpanElement yearSpan;
	public YearWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
		initEvents();
	}
	
	private void initComponents(){
		Date date = new Date();
		if(date.getYear() < 1900){
			currentYear = date.getYear() + AppConstants.EPOC;
		}
		
		ArrayList<DateModel> dateList = GlobalResources.getInstance().getDatesList();
		yearList = new ArrayList<Integer>();
		for(DateModel model : dateList){
			yearList.add(model.getDate());
		}
		
		if(yearList.contains(currentYear)){
			cursor = yearList.indexOf(currentYear);
		}
		
		//Init year display
		yearSpan.setInnerText(yearList.get(cursor)+"");
	}
	
	public void addDateStyle(String style){
		dateList.addClassName(style);
	}
	
	private void initEvents(){
		DOM.sinkEvents(chevronAnchorLeft, Event.ONCLICK);
		DOM.setEventListener(chevronAnchorLeft, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				decrementDate();
			}
		});
		
		DOM.sinkEvents(chevronAnchorRight, Event.ONCLICK);
		DOM.setEventListener(chevronAnchorRight, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				incrementDate();
			}
		});
	}
	
	private void incrementDate(){
		if((cursor+1) <= (yearList.size()-1)){
			cursor ++;
			yearSpan.setInnerText(yearList.get(cursor)+"");
			
			if(handler != null){
				handler.onDateClicked(yearList.get(cursor));
			}
		}
	}
	
	private void decrementDate(){
		if((cursor - 1) >= 0){
			cursor --;
			yearSpan.setInnerText(yearList.get(cursor)+"");
			
			if(handler != null){
				handler.onDateClicked(yearList.get(cursor));
			}
		}
	}
	
	public void setYearWidgetEventHandler(YearWidgetEventHandler handler){
		this.handler = handler;
	}

}
