package com.beta.rsatech.churchcradle.client.app.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ContainerWidget extends Composite {

	private static ContainerWidgetUiBinder uiBinder = GWT
			.create(ContainerWidgetUiBinder.class);

	interface ContainerWidgetUiBinder extends UiBinder<Widget, ContainerWidget> {
	}

	public ContainerWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
