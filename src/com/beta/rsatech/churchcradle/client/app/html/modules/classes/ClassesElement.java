package com.beta.rsatech.churchcradle.client.app.html.modules.classes;

import com.beta.rsatech.churchcradle.client.app.html.modules.classes.GroupClassesLevel.GroupClassesLevelEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.members.MembersLevel;
import com.beta.rsatech.churchcradle.client.app.html.modules.members.MembersLevel.MembersLevelEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class ClassesElement extends UIObject {

	private GroupClassesLevel groupLevel;
	private MembersLevel memberLevel;
	private ProfileLevel profileLevel;
	
	private static MembersElementUiBinder uiBinder = GWT
			.create(MembersElementUiBinder.class);

	interface MembersElementUiBinder extends UiBinder<Element, ClassesElement> {
	}

	@UiField SectionElement membersContainer;
	public ClassesElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initElement(){
		groupLevel = new GroupClassesLevel();
		memberLevel = new MembersLevel();

		groupLevel.setGroupClassesLevelEventHandler(new GroupClassesLevelEventHandler() {
			
			@Override
			public void onListInvoked(String text, int leaderId, Element element) {
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
				memberLevel.loadByClasses(leaderId);
			}
		});
		
		
		groupLevel.load();
		membersContainer.appendChild(groupLevel.getElement());
		membersContainer.appendChild(memberLevel.getElement());

	}
	
	public void load(){
		initElement();
	}

}
