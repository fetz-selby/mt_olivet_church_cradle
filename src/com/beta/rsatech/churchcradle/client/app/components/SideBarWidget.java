package com.beta.rsatech.churchcradle.client.app.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SideBarWidget extends Composite {

	private static SideBarWidgetUiBinder uiBinder = GWT
			.create(SideBarWidgetUiBinder.class);

	interface SideBarWidgetUiBinder extends UiBinder<Widget, SideBarWidget> {
	}

	public SideBarWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
