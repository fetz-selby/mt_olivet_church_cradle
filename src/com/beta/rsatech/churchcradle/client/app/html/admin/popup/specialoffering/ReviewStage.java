package com.beta.rsatech.churchcradle.client.app.html.admin.popup.specialoffering;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<SpecialOfferingModel>{

	private SpecialOfferingModel model;
	private HasWizardEvent<SpecialOfferingModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement amountField, dateField, nameField;
	@UiField SmallElement desc;
	
	public ReviewStage(SpecialOfferingModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		amountField.setInnerText(""+model.getAmount());
		nameField.setInnerText(GlobalResources.getInstance().getMembersMap().get(model.getMemberId()));
		desc.setInnerText(model.getMessage());
		showDateServerDate();
	}
	
	private void showDateServerDate(){
		GlobalResources.getInstance().getListRPC().getServerDate(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				String month = result.split("-")[1];
				dateField.setInnerText(Utils.getMonth(month));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void prepareMemberModel(){
	}
	
	@Override
	public void validateAndProceed() {
		prepareMemberModel();
		next();
	}

	@Override
	public void validateAndReturn() {
		prepareMemberModel();
		back();
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<SpecialOfferingModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.DONE, model);
	}

	@Override
	public void back() {
		wizardHandler.onValidateComplete(WizardStage.ONE, model);
	}

}
