package com.beta.rsatech.churchcradle.client.app.html.composites.contents;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.UIObject;

public class ContentHTMLCompositeEnder extends UIObject {

	private static ContentHTMLCompositeEnderUiBinder uiBinder = GWT
			.create(ContentHTMLCompositeEnderUiBinder.class);

	interface ContentHTMLCompositeEnderUiBinder extends
			UiBinder<Element, ContentHTMLCompositeEnder> {
	}

	public ContentHTMLCompositeEnder() {
		setElement(uiBinder.createAndBindUi(this));
		//addPlugins();
	}
	
	private void addPlugins(){
		Scheduler.get().scheduleDeferred(new Command() {
			@Override public void execute() {
				doPluginLoad();
			}
		});
	}
	
	public void loadPlugins(){
		//addPlugins();
	}
	
	private native void doPluginLoad()/*-{
		var app = this;
		var plugin = "<script src='js/app.plugin.js'></script>";
		$wnd.$('#ender').append(plugin);
	}-*/;

}
