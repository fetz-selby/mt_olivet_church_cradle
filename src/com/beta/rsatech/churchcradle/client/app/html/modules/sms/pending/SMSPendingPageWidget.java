package com.beta.rsatech.churchcradle.client.app.html.modules.sms.pending;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SMSPendingPageWidget extends Composite {

	private SMSModel model;
	private static SMSPendingPageWidgetUiBinder uiBinder = GWT
			.create(SMSPendingPageWidgetUiBinder.class);

	interface SMSPendingPageWidgetUiBinder extends
			UiBinder<Widget, SMSPendingPageWidget> {
	}

	@UiField SmallElement message, smallLength, smallDate;
	public SMSPendingPageWidget(SMSModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		message.setInnerText(model.getMessage());
		smallLength.setInnerText(model.getSmsCounts()+"");
		smallDate.setInnerText(model.getCreatedTs());
	}


}
