package com.beta.rsatech.churchcradle.client.app.html.modules.appstats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SMSBuyWidget extends Composite {

	private static SMSBuyWidgetUiBinder uiBinder = GWT
			.create(SMSBuyWidgetUiBinder.class);

	interface SMSBuyWidgetUiBinder extends UiBinder<Widget, SMSBuyWidget> {
	}

	public SMSBuyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
