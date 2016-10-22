package com.beta.rsatech.churchcradle.client.app.html.modules.classes;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.events.SendMessageEvent;
import com.beta.rsatech.churchcradle.client.events.UpdateMemberEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.UIObject;

public class ProfileLevel extends UIObject {

	private MemberModel model;
	private static ProfileLevelUiBinder uiBinder = GWT
			.create(ProfileLevelUiBinder.class);

	interface ProfileLevelUiBinder extends UiBinder<Element, ProfileLevel> {
	}
	
	
	@UiField DivElement msisdnDiv, employerDiv, addressDiv, classLeaderDiv, orgDiv;
	@UiField HeadingElement h3name;
	@UiField ParagraphElement emailParagraph;
	@UiField StrongElement occupationStrong;
	@UiField AnchorElement sendSMSAnchor, profileEdit;
	@UiField UListElement showMessageUL;
	@UiField ImageElement avatar;
	
	public ProfileLevel() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initComponents(MemberModel model){
		if(GlobalResources.getInstance().getModel().isSMSEnabled()){
			showMessageUL.removeClassName("hide");
		}
		
		if(GlobalResources.getInstance().getModel().isAdmin()){
			profileEdit.removeClassName("hide");
			DOM.sinkEvents(profileEdit, Event.ONCLICK);
			DOM.setEventListener(profileEdit, new EventListener() {
				
				@Override
				public void onBrowserEvent(Event event) {
					GlobalResources.getInstance().getEventBus().fireEvent(new UpdateMemberEvent(ProfileLevel.this.model));
				}
			});
		}
		
		msisdnDiv.setInnerText(model.getMsisdn());
		employerDiv.setInnerText(model.getEmployer().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getEmployer());
		addressDiv.setInnerText(model.getAddress());
		classLeaderDiv.setInnerText(Utils.getClassLeader(model.getClassId()));
		orgDiv.setInnerText(Utils.getOrganisations(",", model.getOrganisations()).trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:Utils.getOrganisations(",", model.getOrganisations()));
		h3name.setInnerText(model.getFirstName()+" "+model.getLastName());
		emailParagraph.setInnerText(model.getEmail().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getEmail());
		occupationStrong.setInnerText(model.getOccupation().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getOccupation());
		//avatar.setSrc(model.getAvatar());
		
		if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(model.getAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					avatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
	private void initEvents(){
		Element a = sendSMSAnchor.cast();
		DOM.sinkEvents(a, Event.ONCLICK);
		DOM.setEventListener(a, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				GlobalResources.getInstance().getEventBus().fireEvent(new SendMessageEvent(model.getMsisdn()));
			}
		});
	}
	
	public void load(MemberModel model){
		this.model = model;
		initComponents(model);
		initEvents();
	}
	
	public MemberModel getModel(){
		return model;
	}

}
