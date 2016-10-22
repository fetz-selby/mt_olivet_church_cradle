package com.beta.rsatech.churchcradle.client.app.html.modules.sms;

import com.beta.rsatech.churchcradle.client.app.html.modules.sms.GroupSMSLevel.GroupSMSEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.sms.pending.SMSPendingPage;
import com.beta.rsatech.churchcradle.client.app.html.modules.sms.sent.SMSSentPage;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class SMSElement extends UIObject {

	private GroupSMSLevel groupSMSLevel;
	private SMSTabPanelElement smsComposeTabPanel;
	
	private static SMSElementUiBinder uiBinder = GWT
			.create(SMSElementUiBinder.class);

	interface SMSElementUiBinder extends UiBinder<Element, SMSElement> {
	}

	@UiField SectionElement smsContainer;
	@UiField DivElement modulesContainer, childContainer;
	public SMSElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initElement(){
		groupSMSLevel = new GroupSMSLevel();
		groupSMSLevel.setGroupSMSEventHandler(new GroupSMSEventHandler() {
			
			@Override
			public void onModuleClicked(int module) {
				loadModule(module);
			}
		});
		smsComposeTabPanel = new SMSTabPanelElement();
		
		modulesContainer.appendChild(groupSMSLevel.getElement());
		childContainer.appendChild(smsComposeTabPanel.getElement());
	}
	
	private void loadModule(int module){
		clearContainer();
		
		if(module == SMSModuleConstants.COMPOSE){
			loadCompose();
		}else if(module == SMSModuleConstants.PENDING){
			loadPending();
		}else if(module == SMSModuleConstants.USAGE){
			loadUsage();
		}else if(module == SMSModuleConstants.SENT){
			loadSent();
		}
	}
	
	private void loadCompose(){
		SMSTabPanelElement compose = new SMSTabPanelElement();
		childContainer.appendChild(compose.getElement());
	}
	
	private void loadPending(){
		SMSPendingPage page = new SMSPendingPage();
		childContainer.appendChild(page.getElement());
		page.load();
	}
	
	private void loadSent(){
		SMSSentPage page = new SMSSentPage();
		childContainer.appendChild(page.getElement());
		page.load();
	}
	
	private void loadUsage(){
		
	}
	
	private void clearContainer(){
		childContainer.removeAllChildren();
	}
	
	public void load(){
		initElement();
	}
	
}
