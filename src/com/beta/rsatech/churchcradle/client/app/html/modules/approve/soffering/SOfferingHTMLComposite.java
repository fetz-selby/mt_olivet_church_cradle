package com.beta.rsatech.churchcradle.client.app.html.modules.approve.soffering;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class SOfferingHTMLComposite extends UIObject {

	private SpecialOfferingModel model;
	private static EventHTMLCompositeUiBinder uiBinder = GWT
			.create(EventHTMLCompositeUiBinder.class);

	interface EventHTMLCompositeUiBinder extends
			UiBinder<Element, SOfferingHTMLComposite> {
	}

	@UiField SmallElement desc, smallVenue, smallTime, smallDate;
	public SOfferingHTMLComposite(SpecialOfferingModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initComponents(){
		desc.setInnerText(Utils.getTruncatedText(model.getMessage(), AppConstants.SPECIAL_OFFERING_TITLE));
		smallVenue.setInnerText(Utils.getTruncatedText(GlobalResources.getInstance().getMembersMap().get(model.getMemberId()), AppConstants.SPECIAL_OFFERING_NAME));
		smallTime.setInnerText(model.getAmount()+"");
		smallDate.setInnerText(model.getCreatedDate());
	}
	
	public SpecialOfferingModel getModel() {
		return model;
	}

	public void setModel(SpecialOfferingModel model) {
		this.model = model;
	}

	public void load(){
		initComponents();
	}

}
