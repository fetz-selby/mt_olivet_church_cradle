package com.beta.rsatech.churchcradle.client.app.html.modules.sms.pending;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class SMSPendingPage extends Composite {

	private static SMSPendingPageUiBinder uiBinder = GWT
			.create(SMSPendingPageUiBinder.class);

	interface SMSPendingPageUiBinder extends UiBinder<Widget, SMSPendingPage> {
	}

	@UiField HTMLPanel container;
	public SMSPendingPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private void initPage(){
		GlobalResources.getInstance().getListRPC().getSMSListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<SMSModel>>() {
			
			@Override
			public void onSuccess(ArrayList<SMSModel> result) {
				if(result != null){
					for(SMSModel model : result){
						SMSPendingPageWidget widget = new SMSPendingPageWidget(model);
						container.add(widget);
					}
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void load(){
		initPage();
	}


}
