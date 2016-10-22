package com.beta.rsatech.churchcradle.client.app.html.modules.dashboard;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class DashboadHTMLComposite extends UIObject {

	private final static int SUNDAY = 1;
	private static DashboadHTMLCompositeUiBinder uiBinder = GWT
			.create(DashboadHTMLCompositeUiBinder.class);

	interface DashboadHTMLCompositeUiBinder extends
	UiBinder<Element, DashboadHTMLComposite> {
	}

	@UiField SpanElement smsSpan, membersSpan, requestSpan, birthdaySpan, groupSpan;
	@UiField DivElement groupDiv, smsDiv;
	
	public DashboadHTMLComposite() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initComponents(){
		
		GlobalResources.getInstance().getListRPC().getDayOfWeek(new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				if(result == SUNDAY){
					showSMSData();
				}else{
					showGroupData();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});

		GlobalResources.getInstance().getListRPC().getPendingRequestCount(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				requestSpan.setInnerText(""+result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		GlobalResources.getInstance().getListRPC().getBirthdayCelebCounts(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				birthdaySpan.setInnerText(""+result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
		
		GlobalResources.getInstance().getListRPC().getMembersCount(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				membersSpan.setInnerText(""+result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void showSMSData(){
		smsDiv.removeClassName("hide");
		GlobalResources.getInstance().getListRPC().getCurrentMonthSMS(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				smsSpan.setInnerText(""+result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void showGroupData(){
		groupDiv.removeClassName("hide");
		GlobalResources.getInstance().getListRPC().getOrganisationsCount(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				groupSpan.setInnerText(""+result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public void load(){
		initComponents();
	}

}
