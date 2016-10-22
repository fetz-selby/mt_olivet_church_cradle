package com.beta.rsatech.churchcradle.client.widgets;

import com.beta.rsatech.churchcradle.client.elements.IElement;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.GroupInfoModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class SmallerCheckTokenWidget extends Composite {
	private boolean isChecked;
	private GroupInfoModel model;
	private SmallerCheckTokenWidgetEventHandler handler;
	private static CheckTokenWidgetUiBinder uiBinder = GWT
			.create(CheckTokenWidgetUiBinder.class);

	interface CheckTokenWidgetUiBinder extends
	UiBinder<Widget, SmallerCheckTokenWidget> {
	}

	/**
	 * <i class="pull-right fa fa-check" ui:field="checker"></i>
		<div class="padder-v">
			<span class="m-b-xs h3 block text-white" ui:field="memberCountSpan">245</span>
			<small class="text-muted" ui:field="organisationNameSmall">Followers</small>
		</div>
	 */

	public interface SmallerCheckTokenWidgetEventHandler{
		void onCheckClicked(boolean isChecked, GroupInfoModel model);
	}

	@UiField HTMLPanel mainPanel;
	@UiField SpanElement memberCountSpan;
	@UiField SmallElement organisationNameSmall;
	@UiField IElement checker;

	public SmallerCheckTokenWidget(GroupInfoModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
		initEvents();
	}

	private void initComponents(){
		organisationNameSmall.setInnerText(model.getGroupName());
		memberCountSpan.setInnerText(model.getTotalMembers()+"");
	}

	private void initEvents(){

		mainPanel.sinkEvents(Event.ONCLICK);
		mainPanel.addHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				isChecked = !isChecked;
				showCheck(isChecked);
				if(handler != null){
					handler.onCheckClicked(isChecked, model);
				}
			}
		}, ClickEvent.getType());

	}

	private void showCheck(boolean isShow){
		if(isShow){
			checker.removeClassName("hide");
		}else{
			checker.addClassName("hide");
		}
	}

	public void unCheck(){
		isChecked = false;
		showCheck(isChecked);
		if(handler != null){
			handler.onCheckClicked(isChecked, model);
		}
	}

	public boolean isChecked() {
		return isChecked;
	}

	public GroupInfoModel getModel() {
		return model;
	}

	public void setModel(GroupInfoModel model) {
		this.model = model;
	}

	public void setSmallerCheckTokenWidgetEventHandler(SmallerCheckTokenWidgetEventHandler handler){
		this.handler = handler;
	}
}
