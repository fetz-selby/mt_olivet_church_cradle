package com.beta.rsatech.churchcradle.client.app.html.composites.header;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class LogoutHTMLComposite extends UIObject {

	private static LogoutHTMLCompositeUiBinder uiBinder = GWT
			.create(LogoutHTMLCompositeUiBinder.class);

	interface LogoutHTMLCompositeUiBinder extends
			UiBinder<Element, LogoutHTMLComposite> {
	}

	@UiField LIElement userOptionsList;
	@UiField SpanElement userNameSpan;
	@UiField ImageElement avatar;
	public LogoutHTMLComposite() {
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}
	
	private void initElement(){
		
//		if(GlobalResources.getInstance().getModel() != null && GlobalResources.getInstance().getModel().isAdmin()){
//			userOptionsList.appendChild(getSwitchToAdminList());
//		}
		
		userNameSpan.setInnerText(GlobalResources.getInstance().getModel().getFirstName());
		//avatar.setSrc(GlobalResources.getInstance().getModel().getAvatar());
		
		if(GlobalResources.getInstance().getModel().getAvatar() != null && !GlobalResources.getInstance().getModel().getAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(GlobalResources.getInstance().getModel().getAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					avatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
	}

//	private Element getSwitchToAdminList(){
//		
//		Element anchor = DOM.createElement("a");
//		anchor.setAttribute("href", "#switch");
//		anchor.setInnerText("Switch");
//		
//		return anchor;
//	}
	
}
