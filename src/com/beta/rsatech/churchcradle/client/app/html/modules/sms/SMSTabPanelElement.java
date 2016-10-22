package com.beta.rsatech.churchcradle.client.app.html.modules.sms;

import com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.all.GroupSMSWidget;
import com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.regular.RegularSMSWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class SMSTabPanelElement extends UIObject {

	private GroupSMSWidget smsAll;
	private RegularSMSWidget regularSMS;
	private static SMSTabPanelUiBinder uiBinder = GWT
			.create(SMSTabPanelUiBinder.class);

	interface SMSTabPanelUiBinder extends UiBinder<Element, SMSTabPanelElement> {
	}

	@UiField DivElement smsRegularContainer, smsAllContainer;
	public SMSTabPanelElement() {
		setElement(uiBinder.createAndBindUi(this));
		initElments();
	}
	
	private void initElments(){
		smsAll = new GroupSMSWidget();
		regularSMS = new RegularSMSWidget();
		
		smsAllContainer.appendChild(smsAll.getElement());
		smsRegularContainer.appendChild(regularSMS.getElement());
	}

}
