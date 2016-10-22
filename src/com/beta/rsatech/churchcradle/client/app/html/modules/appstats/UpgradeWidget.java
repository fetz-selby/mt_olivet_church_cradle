package com.beta.rsatech.churchcradle.client.app.html.modules.appstats;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UpgradeWidget extends Composite {

	private static UpgradeWidgetUiBinder uiBinder = GWT
			.create(UpgradeWidgetUiBinder.class);

	interface UpgradeWidgetUiBinder extends UiBinder<Widget, UpgradeWidget> {
	}

	public UpgradeWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
