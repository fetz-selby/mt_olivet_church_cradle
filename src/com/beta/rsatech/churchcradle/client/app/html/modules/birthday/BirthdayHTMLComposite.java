package com.beta.rsatech.churchcradle.client.app.html.modules.birthday;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BirthdayModel;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class BirthdayHTMLComposite extends UIObject {

	private BirthdayModel model;
	private static FuneralHTMLCompositeUiBinder uiBinder = GWT
			.create(FuneralHTMLCompositeUiBinder.class);

	interface FuneralHTMLCompositeUiBinder extends
			UiBinder<Element, BirthdayHTMLComposite> {
	}

	@UiField SmallElement age, smallDate;
	@UiField DivElement celebrantNameDiv;
	@UiField ImageElement avatar;
	public BirthdayHTMLComposite(BirthdayModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initComponents(){
		age.setInnerText(model.getAge()+"");
		celebrantNameDiv.setInnerText(Utils.getTruncatedText(model.getName(), AppConstants.ANNOUNCE_FUNERAL_TITLE));
		smallDate.setInnerText(model.getDate());
		//avatar.setSrc(model.getAvatar() == null || model.getAvatar().trim().isEmpty() ? AppConstants.NO_IMAGE : model.getAvatar());
		
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

	public BirthdayModel getModel() {
		return model;
	}

	public void setModel(BirthdayModel model) {
		this.model = model;
	}

	public void load(){
		initComponents();
	}
	
}
