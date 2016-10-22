package com.beta.rsatech.churchcradle.client.app.html.modules.sms.sent;

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

public class SMSSentPage extends Composite {

	private static SMSSentPageUiBinder uiBinder = GWT
			.create(SMSSentPageUiBinder.class);

	interface SMSSentPageUiBinder extends UiBinder<Widget, SMSSentPage> {
	}

	@UiField HTMLPanel container;
	public SMSSentPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private void initPage(){
		GlobalResources.getInstance().getListRPC().getSMSListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.ACTIVE, new AsyncCallback<ArrayList<SMSModel>>() {
			
			@Override
			public void onSuccess(ArrayList<SMSModel> result) {
				if(result != null){
					for(SMSModel model : result){
						SMSSentPageWidget widget = new SMSSentPageWidget(model);
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
