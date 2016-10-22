package com.beta.rsatech.churchcradle.client.app.html.modules.announcements.marriage;

import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MarriageHTMLComposite extends UIObject {

	private MarriageModel model;
	private static MarriageHTMLCompositeUiBinder uiBinder = GWT
			.create(MarriageHTMLCompositeUiBinder.class);

	interface MarriageHTMLCompositeUiBinder extends
			UiBinder<Element, MarriageHTMLComposite> {
	}

	@UiField SmallElement maleOccupation, femaleOccupation, smallVenue, smallTime, smallDate;
	@UiField DivElement maleNameDiv, femaleNameDiv;
	@UiField ImageElement mAvatar, fAvatar;
	
	public MarriageHTMLComposite(MarriageModel model) {
		this.model = model;
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initComponents(){
		maleOccupation.setInnerText(Utils.getTruncatedText(model.getmOccupation(), AppConstants.ANNOUNCE_MARRIAGE_OCCUPATION));
		femaleOccupation.setInnerText(Utils.getTruncatedText(model.getfOccupation(), AppConstants.ANNOUNCE_MARRIAGE_OCCUPATION));
		maleNameDiv.setInnerText(Utils.getTruncatedText(model.getManName(), AppConstants.ANNOUNCE_MARRIAGE_NAME));
		femaleNameDiv.setInnerText(Utils.getTruncatedText(model.getFemaleName(), AppConstants.ANNOUNCE_MARRIAGE_NAME));
		smallVenue.setInnerText(Utils.getTruncatedText(model.getVenue(), AppConstants.ANNOUNCE_VENUE));
		smallDate.setInnerText(model.getDate());
		smallTime.setInnerText(model.getTime());
		
		if(model.getmAvatar() != null && !model.getmAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(model.getmAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					mAvatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		if(model.getfAvatar() != null && !model.getfAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(model.getfAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					fAvatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		
		
		//mAvatar.setSrc(model.getmAvatar() == null || model.getmAvatar().trim().isEmpty() ? AppConstants.NO_IMAGE : model.getmAvatar());
		//fAvatar.setSrc(model.getfAvatar() == null || model.getfAvatar().trim().isEmpty() ? AppConstants.NO_IMAGE : model.getfAvatar());
	}
	
	public MarriageModel getModel() {
		return model;
	}

	public void setModel(MarriageModel model) {
		this.model = model;
	}

	public void load(){
		initComponents();
	}

}
