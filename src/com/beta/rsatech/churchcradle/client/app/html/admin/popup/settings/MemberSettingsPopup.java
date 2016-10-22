package com.beta.rsatech.churchcradle.client.app.html.admin.popup.settings;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MemberModel;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MemberSettingsPopup extends Composite {

	private int stageIndex = 0;
	//private MemberModel model;
	MemberSettingsPopupEventHandler handler;
	private IsWizardable<MemberModel> wizard;
	private static AddMemberPopupUiBinder uiBinder = GWT
			.create(AddMemberPopupUiBinder.class);

	public interface MemberSettingsPopupEventHandler{
		void onClose();
	}

	interface AddMemberPopupUiBinder extends UiBinder<Widget, MemberSettingsPopup> {
	}

	@UiField SimplePanel wizardPanel;
	@UiField Button previousBtn, nextBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	@UiField AnchorElement closeAnchor;
	@UiField SpanElement titleSpan;

	public MemberSettingsPopup(MemberModel model){
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(MemberModel model){
		titleSpan.setInnerText(AppConstants.UPDATE_PASSWORD);
		wizard = new StageOne(model);
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

	private void initWizardableEvent(final IsWizardable<MemberModel> tmpWizard){
		tmpWizard.setHasWizardEvent(new HasWizardEvent<MemberModel>() {

			@Override
			public void onValidateComplete(WizardStage stage, MemberModel model) {

				showError(false, "");

				switch(stage){
				case ONE:
					wizard = new StageOne(model);
					stageIndex = 0;
					break;
				case TWO:
					wizard = new StageTwo(model);
					stageIndex = 1;
					break;
				case REVIEW:
					wizard = new ReviewStage(model);
					stageIndex = 2;
					break;
				case DONE:
					doMemberUpdate(model);
					break;
				default:
					break;

				}

				if(stageIndex > 0){
					showPrevious(true);
				}else{
					showPrevious(false);
				}

				if(stageIndex == 2){
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

	private void showUpdateSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Password Updated Successfully");
	}

	private void showUpdateSuccessAndHide(){
		showUpdateSuccess();
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

	private void doMemberUpdate(MemberModel model){
		disableButton(true);
		GlobalResources.getInstance().getUpdateRPC().updateMemberPassword(GlobalResources.getInstance().getModel().getId(), GlobalResources.getInstance().getModel().getChurchId(), model.getPassword(), new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if(result){
					showUpdateSuccessAndHide();
				}				
			}

			@Override
			public void onFailure(Throwable caught) {
				showAndHide("Bad network connection. Please try again later.");
			}
		});
	}

	public void setMemberSettingsPopupEventHandler(MemberSettingsPopupEventHandler handler){
		this.handler = handler;
	}

}
