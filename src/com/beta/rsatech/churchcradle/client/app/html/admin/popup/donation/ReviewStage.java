package com.beta.rsatech.churchcradle.client.app.html.admin.popup.donation;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.SmallElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.DonationModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReviewStage extends Composite implements IsWizardable<DonationModel>{

	private DonationModel model;
	private HasWizardEvent<DonationModel> wizardHandler;
	private static StageFiveUiBinder uiBinder = GWT
			.create(StageFiveUiBinder.class);

	interface StageFiveUiBinder extends UiBinder<Widget, ReviewStage> {
	}

	@UiField StrongElement amountField;
	@UiField SmallElement desc;
	
	public ReviewStage(DonationModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
	}

	private void initComponents(){
		amountField.setInnerText(""+model.getAmount());
		desc.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.EVENT_REVIEW_DESCRIPTION));
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
	public void setHasWizardEvent(HasWizardEvent<DonationModel> event) {
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
