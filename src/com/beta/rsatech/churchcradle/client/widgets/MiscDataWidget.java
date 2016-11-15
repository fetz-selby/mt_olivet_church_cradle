package com.beta.rsatech.churchcradle.client.widgets;

import com.beta.rsatech.churchcradle.shared.PositionHistoryModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MiscDataWidget extends Composite {
	private int id, memberId;
	private static MiscDataWidgetUiBinder uiBinder = GWT
			.create(MiscDataWidgetUiBinder.class);

	interface MiscDataWidgetUiBinder extends UiBinder<Widget, MiscDataWidget> {
	}

	@UiField TextBox infobox, positionbox, frombox, endbox;
	
	public MiscDataWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		initElement(null);
	}
	
	public MiscDataWidget(PositionHistoryModel miscObj) {
		initWidget(uiBinder.createAndBindUi(this));
		initElement(miscObj);
	}

	
	private void initElement(PositionHistoryModel miscObj){
		infobox.getElement().setAttribute("placeholder", "Name / Description of Org");
		positionbox.getElement().setAttribute("placeholder", "position");
		frombox.getElement().setAttribute("placeholder", "from (YYYY-MM-DD)");
		endbox.getElement().setAttribute("placeholder", "end (YYYY-MM-DD)");
		
		if(miscObj != null){
			infobox.setText(miscObj.getName());
			positionbox.setText(miscObj.getPosition());
			frombox.setText(miscObj.getBeginDate());
			endbox.setText(miscObj.getEndDate());
			id = miscObj.getId();
			memberId = miscObj.getMemberId();
		}
	}
	
	public PositionHistoryModel getMiscData(){
		PositionHistoryModel obj = new PositionHistoryModel();
		obj.setName(infobox.getText().trim());
		obj.setPosition(positionbox.getText().trim());
		obj.setBeginDate(frombox.getText().trim());
		obj.setEndDate(endbox.getText().trim());
		obj.setId(id);
		obj.setMemberId(memberId);
		
		return obj;
	}
	
}
