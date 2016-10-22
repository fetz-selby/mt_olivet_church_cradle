package com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.GroupPowerLeadersLevel.GroupPowerLeadersLevelEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.pages.PowerLeadersPage;
import com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.pages.PowerLeadersPage.PowerLeadersPageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.popup.PowerLeadersPopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.popup.PowerLeadersPopup.PowerLeadersPopupEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.PowerLeaderModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class PowerLeadersElement extends UIObject {

	private GroupPowerLeadersLevel groupApprove;
	private PowerLeadersListLevel listLevel;
	
	private static ApproveElementUiBinder uiBinder = GWT
			.create(ApproveElementUiBinder.class);

	interface ApproveElementUiBinder extends UiBinder<Element, PowerLeadersElement> {
	}
	
	@UiField SectionElement approveContainer;
	public PowerLeadersElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initElement(){
		groupApprove = new GroupPowerLeadersLevel(GlobalResources.getInstance().getModel().getApproveModules());
		listLevel = new PowerLeadersListLevel();
		
		approveContainer.appendChild(groupApprove.getElement());
		approveContainer.appendChild(listLevel.getElement());
		approveContainer.appendChild(new ContentHTMLCompositeEnder().getElement());
	}
	
	private void initGroupEvent(){
		groupApprove.setGroupPowerLeadersLevelEventHandler(new GroupPowerLeadersLevelEventHandler() {
			
			@Override
			public void onItemClicked(String module) {
				loadPage(module);
			}
		});
	}
	
	private void loadPage(String item){
		
		if(item.equalsIgnoreCase(AppConstants.POWER_LEADERS)){
			renderPowerLeadersPage();
		}
	}
	
	private void renderPowerLeadersPage(){
		final PowerLeadersPage page = new PowerLeadersPage();
		page.setPowerLeadersPageEventHandler(new PowerLeadersPageEventHandler() {
			
			@Override
			public void onPowerLeaderEditInvoked(PowerLeaderModel model) {
				PowerLeadersPopup popup = new PowerLeadersPopup(model);
				popup.setPowerLeadersPopupEventHandler(new PowerLeadersPopupEventHandler() {
					
					@Override
					public void onPowerLeaderRemoved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	public void load(){
		initElement();
		initGroupEvent();
	}

}
