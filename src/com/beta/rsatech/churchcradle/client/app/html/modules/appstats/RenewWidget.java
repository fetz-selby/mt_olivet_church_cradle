package com.beta.rsatech.churchcradle.client.app.html.modules.appstats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RenewWidget extends Composite {

	private static RenewWidgetUiBinder uiBinder = GWT
			.create(RenewWidgetUiBinder.class);

	interface RenewWidgetUiBinder extends UiBinder<Widget, RenewWidget> {
	}

	public RenewWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
