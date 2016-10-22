package com.beta.rsatech.churchcradle.client.app.html.forms.popup;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.events.MembersEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.client.widgets.SMSTextArea;
import com.beta.rsatech.churchcradle.client.widgets.SMSTextArea.SMSTextAreaEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SMSPopup extends CustomDraggablePopupPanel {

	private String msisdn;
	private SMSPopupEventHandler handler;
	private static SMSPopupUiBinder uiBinder = GWT
			.create(SMSPopupUiBinder.class);

	interface SMSPopupUiBinder extends UiBinder<Widget, SMSPopup> {
	}

	@UiField TextBox displayName, receiverName;
	@UiField SMSTextArea textArea;
	@UiField Button saveBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	@UiField LabelElement smsCounter, characterCounter;
	
	public interface SMSPopupEventHandler{
		void onSMSSent();
	}
	
	public SMSPopup(String msisdn) {
		this.msisdn = msisdn;
		add(uiBinder.createAndBindUi(this));
		initComponents();
		initEvents();
		initPopup();
	}
	
	private void initComponents(){
		textArea.setCustomStyle("textarea-style", AppConstants.REGULAR);
		textArea.setCharacterLimit(AppConstants.SMS_THRESHOLD);
		displayName.setText(GlobalResources.getInstance().getChurchModel().getDisplay());
		receiverName.setText(msisdn);
		
		displayName.setEnabled(false);
		receiverName.setEnabled(false);
	}
	
	private void initEvents(){
		textArea.setSMSTextAreaEventHandler(new SMSTextAreaEventHandler() {
			
			@Override
			public void onCharacterChange(int characterCount, int smsCounter) {
				SMSPopup.this.smsCounter.setInnerText(AppConstants.SMS_LABEL+" "+smsCounter);
				SMSPopup.this.characterCounter.setInnerText(characterCount+" "+AppConstants.SMS_CHARACTER_LABEL);
			}
		});
		
		saveBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				enableButton(false);
				addSMS();
			}
		});
	}
	
	private void initPopup(){
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		setGlassStyleName("glassPanel");
		setDraggable(true);
		center();
	}
	
	private void showError(boolean isShow, String message){
		if(isShow){
			errorDiv.removeClassName("hide");
			titleErrorMessage.setInnerText(message);
		}else{
			errorDiv.addClassName("hide");
		}
		
	}
	
	private void showSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Message sent successfully");
	}
	
	private void showPendingSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Message saved successfully");
	}
	
	private void showSuccessAndHide(){
		if(Utils.isSMSApproveEnabled()){
			showPendingSuccess();
		}else{
			showSuccess();
		}
		Timer timer = new Timer() {
			
			@Override
			public void run() {
				GlobalResources.getInstance().getEventBus().fireEvent(new MembersEvent());
				if(handler != null){
					handler.onSMSSent();
				}
				hide();
			}
		};
		
		//Two seconds
		timer.schedule(2*1000);
	}
	
	private void showLoading(boolean isShow){
		if(isShow){
			errorDiv.setClassName("alert alert-success");
			titleErrorMessage.setInnerText("Loading ...");
			saveBtn.setEnabled(false);
		}else{
			errorDiv.setClassName("hide");
			titleErrorMessage.setInnerText("Loading ...");
			saveBtn.setEnabled(true);
		}
	}
	
	private void showAndHide(String message){
		showError(true, message);
		Timer timer = new Timer() {

			@Override
			public void run() {
				hide();
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}
	
	private void enableButton(boolean isEnable){
		if(isEnable){
			saveBtn.setEnabled(true);
		}else{
			saveBtn.setEnabled(false);
		}
	}
	
	private void addSMS(){
		if(textArea.getText().trim().isEmpty()){
			//Display no group name entered message
			showError(true, "Sorry, message can not be empty.");
			enableButton(true);
			return;
		}
		
		showError(false, "");
		
		SMSModel model = new SMSModel();
		model.setMessage(textArea.getText().trim());
		model.setDisplay(GlobalResources.getInstance().getChurchModel().getDisplay());
		model.setCreatedBy(GlobalResources.getInstance().getModel().getId());
		model.setChurchId(GlobalResources.getInstance().getChurchModel().getId());
		model.setModifiedBy(GlobalResources.getInstance().getModel().getId());
		model.setType(AppConstants.MSISDN);
		model.setBulk(false);
		model.setMsisdn(msisdn);
		model.setStatus(AppConstants.PENDING);
		
		showLoading(true);
		GlobalResources.getInstance().getAddRPC().addSMS(model, GlobalResources.getInstance().getModel().getId(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				if(result > 0){
					showSuccessAndHide();
				}else{
					//Display error message
					if(Utils.isSMSApproveEnabled()){
						showError(true, "Sorry, message could not be saved. Try again later.");
					}else{
						showError(true, "Sorry, message could not be sent. Try again later.");
					}
					enableButton(true);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
//				showError(true, "Network is Unavailable. Please try again later.");
//				enableButton(true);
				showAndHide("Bad network connection. Please try again later.");

			}
		});
	}
	
	public void setSMSPopupEventHandler(SMSPopupEventHandler handler){
		this.handler = handler;
	}

}
