package com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup;

import com.beta.rsatech.churchcradle.client.elements.IElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EventApprovePopup extends CustomDraggablePopupPanel {

	private EventApprovePopupEventHandler handler;
	private EAnnounceModel model;
	private boolean isChecked = false;
	private static EventApprovePopupUiBinder uiBinder = GWT
			.create(EventApprovePopupUiBinder.class);

	interface EventApprovePopupUiBinder extends
			UiBinder<Widget, EventApprovePopup> {
	}

	public interface EventApprovePopupEventHandler{
		void onEventApproved(int id);
	}
	
	@UiField ReviewPopupTextArea descField;
	@UiField TextBox venueField, createdByField, dateField;
	@UiField Button submitBtn, rejectBtn;
	@UiField InputElement checkbox;
	@UiField IElement iCheck;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv, loadingDiv;
	@UiField AnchorElement closeAnchor;
	
	public EventApprovePopup(EAnnounceModel model) {
		this.model = model;
		add(uiBinder.createAndBindUi(this));
		initComponent();
		initEvents();
		initPopup();
	}
	private void initComponent(){
		descField.setText(model.getDescription());
		venueField.setText(model.getVenue());
		createdByField.setText(Utils.getMember(model.getCreatedBy()));
		dateField.setText(model.getCreatedTs());
		
		descField.setEnabled(false);
		venueField.setEnabled(false);
		createdByField.setEnabled(false);
		dateField.setEnabled(false);
	}

	private void showError(boolean isShow, String message){
		if(isShow){
			errorDiv.removeClassName("hide");
			titleErrorMessage.setInnerText(message);
		}else{
			errorDiv.addClassName("hide");
		}

	}

	private void showLoading(boolean isShow){
		if(isShow){
			loadingDiv.removeClassName("hide");
			submitBtn.setEnabled(false);
		}else{
			loadingDiv.addClassName("hide");
			submitBtn.setEnabled(true);
		}
	}

	private void showSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Event Approved Successfully");
	}
	
	private void showRejectSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Event Rejected Successfully");
	}

	private void disableSubmit(){
		submitBtn.setEnabled(false);
	}
	
	private void disableReject(){
		rejectBtn.setEnabled(false);
	}

	private void initEvents(){
		submitBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				disableSubmit();
				doUpdate();
			}
		});
		
		rejectBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				disableReject();
				doReject();
			}
		});

		Element checkElement = checkbox.cast();
		DOM.sinkEvents(checkElement, Event.ONCLICK);
		DOM.setEventListener(checkElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				isChecked = !isChecked;
			}
		});
		
		DOM.sinkEvents(closeAnchor, Event.ONCLICK);
		DOM.setEventListener(closeAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				hide();
			}
		});
	}

	private void showSuccessAndHide(){
		showSuccess();
		Timer timer = new Timer() {

			@Override
			public void run() {
				hide();
				if(handler != null){
					handler.onEventApproved(model.getId());
				}
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}
	
	private void showRejectSuccessAndHide(){
		showRejectSuccess();
		Timer timer = new Timer() {

			@Override
			public void run() {
				hide();
				if(handler != null){
					handler.onEventApproved(model.getId());
				}
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}

	private void doUpdate(){
		if(isChecked){
			showLoading(true);
			GlobalResources.getInstance().getUpdateRPC().approveEvent(GlobalResources.getInstance().getModel().getId(), model, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					showLoading(false);
					if(result){
						showSuccessAndHide();
					}else{
						showError(true, "Sorry, information couldn't be approved");
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					showError(true, "Bad network connection. Please try again later.");
					hide();
				}
			});
		}else{
			hide();
		}
	}
	
	private void doReject(){
		showLoading(true);
		GlobalResources.getInstance().getUpdateRPC().rejectEvent(GlobalResources.getInstance().getModel().getId(), model, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				showLoading(false);
				if(result){
					showRejectSuccessAndHide();
				}else{
					showError(true, "Sorry, information couldn't be rejected");
					rejectBtn.setEnabled(true);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				showError(true, "Bad network connection. Please try again later.");
				hide();
			}
		});
	}

	private void initPopup(){
		setAutoHideEnabled(false);
		setGlassEnabled(true);
		setGlassStyleName("glassPanel");
		setDraggable(true);
		center();
	}
	
	public void setEventApprovePopupEventHandler(EventApprovePopupEventHandler handler){
		this.handler = handler;
	}


}
