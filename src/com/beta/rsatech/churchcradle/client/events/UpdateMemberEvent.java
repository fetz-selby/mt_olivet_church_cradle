package com.beta.rsatech.churchcradle.client.events;

import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.event.shared.GwtEvent;

public class UpdateMemberEvent extends GwtEvent<UpdateMemberEventHandler>{
	public static Type<UpdateMemberEventHandler> TYPE = new Type<UpdateMemberEventHandler>();
	private MemberModel model;
	
	public UpdateMemberEvent(MemberModel model){
		this.model = model;
	}
	
	public MemberModel getModel() {
		return model;
	}

	public void setModel(MemberModel model) {
		this.model = model;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UpdateMemberEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateMemberEventHandler handler) {
		handler.onMemberUpdateInvoked(this);
	}
	
}
