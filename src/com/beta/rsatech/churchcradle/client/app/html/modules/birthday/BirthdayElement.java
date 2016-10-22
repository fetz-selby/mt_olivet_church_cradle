package com.beta.rsatech.churchcradle.client.app.html.modules.birthday;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.BirthdayModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class BirthdayElement extends UIObject {

	private static FuneralElementUiBinder uiBinder = GWT
			.create(FuneralElementUiBinder.class);

	interface FuneralElementUiBinder extends UiBinder<Element, BirthdayElement> {
	}

	@UiField DivElement divContainer;
	public BirthdayElement() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void doBirthdayLoad(){
		GlobalResources.getInstance().getListRPC().getBirthdays(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<ArrayList<BirthdayModel>>() {
			
			@Override
			public void onSuccess(ArrayList<BirthdayModel> result) {
				for(BirthdayModel model : result){
					BirthdayHTMLComposite composite = new BirthdayHTMLComposite(model);
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
		doBirthdayLoad();
	}

	
}
