package com.beta.rsatech.churchcradle.client.app.html.modules.appstats;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.appstats.GroupAppStatsLevel.AppStatsLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class AppStatsElement extends UIObject {

	private GroupAppStatsLevel groupApprove;
	private AppStatsListLevel listLevel;
	
	private static ApproveElementUiBinder uiBinder = GWT
			.create(ApproveElementUiBinder.class);

	interface ApproveElementUiBinder extends UiBinder<Element, AppStatsElement> {
	}
	
	@UiField SectionElement approveContainer;
	public AppStatsElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initElement(){
		groupApprove = new GroupAppStatsLevel();
		listLevel = new AppStatsListLevel();
		
		approveContainer.appendChild(groupApprove.getElement());
		approveContainer.appendChild(listLevel.getElement());
		approveContainer.appendChild(new ContentHTMLCompositeEnder().getElement());
	}
	
	private void initGroupEvent(){
		groupApprove.setAppStatsLevelEventHandler(new AppStatsLevelEventHandler() {
			
			@Override
			public void onItemClicked(String module) {
				loadPage(module);
			}
		});
	}
	
	private void loadPage(String item){
		if(item.equalsIgnoreCase(AppConstants.APP_HEALTH)){
			renderAppStatsPage();
		}
	}
	
	private void renderAppStatsPage(){
		AppStatsWidget widget = new AppStatsWidget();
		listLevel.setListPage(widget.getElement());

	}
	
	public void load(){
		initElement();
		initGroupEvent();
	}

}
