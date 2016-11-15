package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.widgets.MiscDataWidget;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.PositionHistoryModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class StageSixSub1 extends Composite implements IsWizardable<MemberModel>{

	private MemberModel model;
	private ArrayList<MiscDataWidget> miscWidgetList;
	
	private HasWizardEvent<MemberModel> wizardHandler;
	private static StageSixUiBinder uiBinder = GWT
			.create(StageSixUiBinder.class);

	interface StageSixUiBinder extends UiBinder<Widget, StageSixSub1> {
	}

	@UiField ButtonElement addButton, clearButton;
	@UiField FlowPanel widgetContainer;

	public StageSixSub1(MemberModel model) {
		initWidget(uiBinder.createAndBindUi(this));
		this.model = model;
		initComponents();
		initEvents();
	}
	private void initComponents(){
		miscWidgetList = new ArrayList<MiscDataWidget>();
		
		if(model.getHistoryList() != null && model.getHistoryList().size() > 0){
			for(PositionHistoryModel positionModel : model.getHistoryList()){
				addPositionRow(positionModel);
			}
		}
	}
	
	private void initEvents(){
		DOM.sinkEvents(addButton, Event.ONCLICK);
		DOM.setEventListener(addButton, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				MiscDataWidget widget = new MiscDataWidget();
				widgetContainer.add(widget);
				miscWidgetList.add(widget);
			}
		});
		
		DOM.sinkEvents(clearButton, Event.ONCLICK);
		DOM.setEventListener(clearButton, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				widgetContainer.clear();
				miscWidgetList.clear();
			}
		});
	}
	
	private void addPositionRow(PositionHistoryModel model){
		MiscDataWidget widget = new MiscDataWidget(model);
		miscWidgetList.add(widget);
		widgetContainer.add(widget);
	}
	
	private void prepareMemberModel(){
		//Clear history held list
		model.getHistoryList().clear();
		
		//Add the new ones
		for(MiscDataWidget widget : miscWidgetList){
			model.getHistoryList().add(widget.getMiscData());
		}
	}

	@Override
	public void validateAndProceed() {
		prepareMemberModel();
		next();
	}
	
	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}
	
	@Override
	public void validateAndReturn() {
		prepareMemberModel();
		back();
	}
	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}
	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);
	}
	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.SIX, model);
	}


}
