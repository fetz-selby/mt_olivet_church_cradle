package com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.sms;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SMSPurchaseModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SMSPurchasePopup extends Composite {

	private int stageIndex = 0;
	SMSPurchasePopupEventHandler handler;
	private IsWizardable<SMSPurchaseModel> wizard;
	private static AddMemberPopupUiBinder uiBinder = GWT
			.create(AddMemberPopupUiBinder.class);

	public interface SMSPurchasePopupEventHandler{
		void onClose();
	}

	interface AddMemberPopupUiBinder extends UiBinder<Widget, SMSPurchasePopup> {
	}

	@UiField SimplePanel wizardPanel;
	@UiField Button previousBtn, nextBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	@UiField AnchorElement closeAnchor;
	@UiField SpanElement titleSpan;

	public SMSPurchasePopup() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponent();
		initEvent();
	}

	private void initComponent(){
		titleSpan.setInnerText(AppConstants.SMS_PURCHASE_TITLE);
		wizard = new StageOne();
		initWizardableEvent(wizard);

		wizardPanel.setWidget(wizard);
	}

	private void showError(boolean isShow, String message){
		if(isShow){
			errorDiv.removeClassName("hide");
			titleErrorMessage.setInnerText(message);
		}else{
			errorDiv.addClassName("hide");
		}

	}

	private void showPrevious(boolean isShow){
		if(isShow){
			previousBtn.removeStyleName("hide");
		}else{
			previousBtn.addStyleName("hide");
		}
	}

	private void initWizardableEvent(final IsWizardable<SMSPurchaseModel> tmpWizard){
		tmpWizard.setHasWizardEvent(new HasWizardEvent<SMSPurchaseModel>() {

			@Override
			public void onValidateComplete(WizardStage stage, SMSPurchaseModel model) {

				showError(false, "");

				switch(stage){
				case ONE:
					wizard = new StageOne(model);
					stageIndex = 0;
					break;
				
				case REVIEW:
					wizard = new ReviewStage(model);
					stageIndex = 1;
					break;
				case DONE:
					doSMSPurchase(model);
					break;
				default:
					break;

				}

				if(stageIndex > 0){
					showPrevious(true);
				}else{
					showPrevious(false);
				}

				if(stageIndex == 1){
					nextBtn.setText(AppConstants.BUTTON_DONE);
				}else{
					nextBtn.setText(AppConstants.BUTTON_NEXT);
				}

				wizardPanel.setWidget(wizard);

				initWizardableEvent(wizard);
			}

			@Override
			public void onError(String message) {
				showError(true, message);
			}
		});
	}

	private void initEvent(){
		previousBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				stageIndex --;

				if(stageIndex == 0){
					showPrevious(false);
				}

				wizard.validateAndReturn();
			}
		});

		nextBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				wizard.validateAndProceed();
			}
		});

		DOM.sinkEvents(closeAnchor, Event.ONCLICK);
		DOM.setEventListener(closeAnchor, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onClose();
				}
			}
		});
	}
	
	private void showAndHide(String message){
		showError(true, message);
		Timer timer = new Timer() {

			@Override
			public void run() {
				if(handler != null){
					handler.onClose();
				}
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}
	
	private void showLoading(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Loading, please wait ...");
	}
	
	private void hideMessage(){
		errorDiv.setClassName("hide");
	}
	
	private void disableButton(boolean isDisabled){
		if(isDisabled){
			showLoading();
			nextBtn.setEnabled(false);
		}else{
			hideMessage();
			nextBtn.setEnabled(true);
		}
	}
	
	private void doSMSPurchase(SMSPurchaseModel model){
		disableButton(true);
		GlobalResources.getInstance().getPaymentRPC().buySMS(model, Utils.getLocationPath("app.html"),new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				if(result != null){
					disableButton(false);
					Window.Location.assign(result);						
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				showAndHide("Bad network connection. Please try again later.");
			}
		});
	}
	

	public void setSMSPurchasePopupEventHandler(SMSPurchasePopupEventHandler handler){
		this.handler = handler;
	}

}
