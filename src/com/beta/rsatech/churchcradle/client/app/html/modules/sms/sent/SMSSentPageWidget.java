package com.beta.rsatech.churchcradle.client.app.html.modules.sms.sent;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SMSSentPageWidget extends Composite {

	private SMSModel model;
	private static SMSSentPageUiBinder uiBinder = GWT
			.create(SMSSentPageUiBinder.class);

	interface SMSSentPageUiBinder extends UiBinder<Widget, SMSSentPageWidget> {
	}

	@UiField SmallElement message, smallLength, smallDate, smallApprovedBy;
	@UiField AnchorElement approvedAnchor;
	
	public SMSSentPageWidget(SMSModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}
	
	private void initComponents(){
		if(Utils.isSMSApproveEnabled()){
			approvedAnchor.removeClassName("hide");
			smallApprovedBy.setInnerText(Utils.getMember(model.getApprovedBy()));
		}
		
		message.setInnerText(model.getMessage());
		smallLength.setInnerText(model.getSmsCounts()+"");
		smallDate.setInnerText(model.getCreatedTs());
	}

}
