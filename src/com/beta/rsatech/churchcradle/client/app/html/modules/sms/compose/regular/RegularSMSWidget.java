package com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.regular;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.events.MembersEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.SMSTextArea;
import com.beta.rsatech.churchcradle.client.widgets.SMSTextArea.SMSTextAreaEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegularSMSWidget extends Composite {

	private static RegularSMSWidgetUiBinder uiBinder = GWT
			.create(RegularSMSWidgetUiBinder.class);

	interface RegularSMSWidgetUiBinder extends
			UiBinder<Widget, RegularSMSWidget> {
	}

	@UiField DivElement smsCounter, charCounter;
	@UiField SMSTextArea smsTextArea;
	@UiField LabelElement smsTo, smsFrom;
	@UiField TextBox toBox, fromBox;
	@UiField AnchorElement sendBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	
	public RegularSMSWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
		initEvents();
	}
	
	private void initComponents(){
		smsTextArea.setCharacterLimit(AppConstants.SMS_THRESHOLD);
		smsTextArea.setCustomStyle("textarea-style2", AppConstants.NATIVE);
		smsTextArea.getElement().setAttribute("placeholder", "Enter message ...");
		
		toBox.getElement().setAttribute("placeholder", AppConstants.SMS_NUMBER_PLACEHOLDER);
		
		fromBox.setText(GlobalResources.getInstance().getChurchModel().getDisplay());
		fromBox.setEnabled(false);
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
		titleErrorMessage.setInnerText("SMS sent successfully");
	}
	
	private void showPendingSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("SMS saved successfully for approval");
	}
	
	private void initEvents(){
		smsTextArea.setSMSTextAreaEventHandler(new SMSTextAreaEventHandler() {
			
			@Override
			public void onCharacterChange(int characterCount, int smsCounter) {
				charCounter.setInnerText(AppConstants.SMS_CHARACTER_LABEL+" "+characterCount);
				RegularSMSWidget.this.smsCounter.setInnerText(AppConstants.SMS_LABEL+" "+smsCounter);
			}
		});
		
		Element sendElement = sendBtn.cast();
		DOM.sinkEvents(sendElement, Event.ONCLICK);
		DOM.setEventListener(sendElement, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				doChecks();
			}
		});
	}
	
	private void startResetTimer(){
		Timer timer = new Timer() {
			
			@Override
			public void run() {
				reset();
			}
		};
		
		//Two seconds
		timer.schedule(2*1000);
	}
	
	private void reset(){
		toBox.setText("");
		smsTextArea.setText("");
	}
	
	private void doChecks(){
		if(toBox.getText().trim().isEmpty()){
			showError(true, "Sorry, recipient can not be empty.");
			return;
		}
		
		if(!Utils.isMsisdnValid(toBox.getText().trim())){
			showError(true, "Invalid mobile number(s)");
			return;
		}
		
		if(smsTextArea.getText().trim().isEmpty()){
			showError(true, "Sorry, message can not be empty.");
			return;
		}
		
		String recipient = toBox.getText().trim();
		String message = smsTextArea.getText().trim();
		String display = GlobalResources.getInstance().getChurchModel().getDisplay();
		
		SMSModel model = new SMSModel();
		model.setMessage(message);
		model.setDisplay(display);
		model.setCreatedBy(GlobalResources.getInstance().getModel().getId());
		model.setChurchId(GlobalResources.getInstance().getChurchModel().getId());
		model.setModifiedBy(GlobalResources.getInstance().getModel().getId());
		model.setRecipient(recipient);
		model.setStatus(AppConstants.PENDING);
		model.setType(AppConstants.MSISDN);
		model.setMsisdn(toBox.getText().trim());
		model.setBulk(toBox.getText().contains(",")?true:false);
		
		GlobalResources.getInstance().getAddRPC().addSMS(model, GlobalResources.getInstance().getModel().getId(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				if(result > 0){
					showPendingSuccess();
					startResetTimer();
				}else{
					showError(true, "Sorry, there was a problem with your request. Please try again later");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				showError(true, "Oop, bad network connectivity");
			}
		});
	}

}
