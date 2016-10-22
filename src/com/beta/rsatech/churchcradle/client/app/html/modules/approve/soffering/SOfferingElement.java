package com.beta.rsatech.churchcradle.client.app.html.modules.approve.soffering;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class SOfferingElement extends UIObject {

	private static EventElementUiBinder uiBinder = GWT
			.create(EventElementUiBinder.class);

	interface EventElementUiBinder extends UiBinder<Element, SOfferingElement> {
	}

	@UiField DivElement divContainer;
	public SOfferingElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doSpecialOfferingLoad(){
		GlobalResources.getInstance().getListRPC().getSpecialOfferingsListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.ACTIVE, new AsyncCallback<ArrayList<SpecialOfferingModel>>() {
			
			@Override
			public void onSuccess(ArrayList<SpecialOfferingModel> result) {
				for(SpecialOfferingModel model : result){
					SOfferingHTMLComposite composite = new SOfferingHTMLComposite(model);
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
		doSpecialOfferingLoad();
	}

}
