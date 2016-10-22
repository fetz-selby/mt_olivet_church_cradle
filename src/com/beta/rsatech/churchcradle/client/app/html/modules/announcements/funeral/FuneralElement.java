package com.beta.rsatech.churchcradle.client.app.html.modules.announcements.funeral;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class FuneralElement extends UIObject {

	private static FuneralElementUiBinder uiBinder = GWT
			.create(FuneralElementUiBinder.class);

	interface FuneralElementUiBinder extends UiBinder<Element, FuneralElement> {
	}

	@UiField DivElement divContainer;
	public FuneralElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void doFuneralLoad(){
		GlobalResources.getInstance().getListRPC().getFAnnouncesListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.ACTIVE, new AsyncCallback<ArrayList<FAnnounceModel>>() {
			
			@Override
			public void onSuccess(ArrayList<FAnnounceModel> result) {
				for(FAnnounceModel model : result){
					FuneralHTMLComposite composite = new FuneralHTMLComposite(model);
					divContainer.appendChild(composite.getElement());
					composite.load();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void load(){
		doFuneralLoad();
	}

	
}
