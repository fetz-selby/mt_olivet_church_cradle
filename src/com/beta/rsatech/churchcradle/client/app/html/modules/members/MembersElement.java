package com.beta.rsatech.churchcradle.client.app.html.modules.members;

import com.beta.rsatech.churchcradle.client.app.html.modules.members.GroupMemberLevel.GroupMemberLevelEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.members.MembersLevel.MembersLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MembersElement extends UIObject {

	private GroupMemberLevel groupLevel;
	private MembersLevel memberLevel;
	private ProfileLevel profileLevel;
	
	private static MembersElementUiBinder uiBinder = GWT
			.create(MembersElementUiBinder.class);

	interface MembersElementUiBinder extends UiBinder<Element, MembersElement> {
	}

	@UiField SectionElement membersContainer;
	public MembersElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initElement(){
		groupLevel = new GroupMemberLevel();
		memberLevel = new MembersLevel();

		groupLevel.setGroupMemberLevelEventHandler(new GroupMemberLevelEventHandler() {
			
			@Override
			public void onListInvoked(String text, int groupId, Element element) {
				memberLevel.setMembersLevelEventHandler(new MembersLevelEventHandler() {
					
					@Override
					public void onMemberInvoked(MemberModel model, Element element) {
						if(profileLevel == null){
							profileLevel = new ProfileLevel();
							membersContainer.appendChild(profileLevel.getElement());
							profileLevel.load(model);
						}else{
							profileLevel.load(model);
						}
					}
				});
				memberLevel.loadByOrganisation(groupId);

			}
		});
		
		
		groupLevel.load();
		membersContainer.appendChild(groupLevel.getElement());
		membersContainer.appendChild(memberLevel.getElement());

		//membersContainer.appendChild(new ContentHTMLCompositeEnder().getElement());


	}
	
	public void load(){
		initElement();
	}

}
