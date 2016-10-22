package com.beta.rsatech.churchcradle.client.app.html.modules.announcements.marriage;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class MarriageElement extends UIObject {

	private static MarriageElementUiBinder uiBinder = GWT
			.create(MarriageElementUiBinder.class);

	interface MarriageElementUiBinder extends
			UiBinder<Element, MarriageElement> {
	}

	@UiField DivElement divContainer;
	public MarriageElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doMarriageLoad(){
		GlobalResources.getInstance().getListRPC().getMarriagesListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.ACTIVE, new AsyncCallback<ArrayList<MarriageModel>>() {
			
			@Override
			public void onSuccess(ArrayList<MarriageModel> result) {
				for(MarriageModel model : result){
					MarriageHTMLComposite composite = new MarriageHTMLComposite(model);
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
		doMarriageLoad();
	}

}
