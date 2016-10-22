package com.beta.rsatech.churchcradle.client.app.components;

import com.beta.rsatech.churchcradle.client.HasPlugins;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DashboardWidgetContent extends Composite implements HasPlugins{

	private static ContentWidgetUiBinder uiBinder = GWT
			.create(ContentWidgetUiBinder.class);

	interface ContentWidgetUiBinder extends UiBinder<Widget, DashboardWidgetContent> {
	}

	public DashboardWidgetContent() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void doPluginsLoad() {
		// TODO Auto-generated method stub
		
	}

}
