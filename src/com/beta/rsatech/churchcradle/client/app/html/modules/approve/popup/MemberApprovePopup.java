package com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup;

import com.beta.rsatech.churchcradle.client.elements.IElement;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomCheckBox;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
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

public class MemberApprovePopup extends CustomDraggablePopupPanel {

	private MemberApprovePopupEventHandler handler;
	private MemberModel model;
	private boolean isChecked = false;
	private static MemberApprovePopupUiBinder uiBinder = GWT
			.create(MemberApprovePopupUiBinder.class);

	interface MemberApprovePopupUiBinder extends
			UiBinder<Widget, MemberApprovePopup> {
	}
	
	public interface MemberApprovePopupEventHandler{
		void onMemberApproved(int id);
	}
	
	@UiField TextBox nameField, occupationField, classLeaderField, msisdnField, createdByField, dateField;
	@UiField Button submitBtn, rejectBtn;
	@UiField InputElement checkbox;
	@UiField IElement iCheck;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv, loadingDiv, orgDiv, aprDiv, entryDiv;
	@UiField AnchorElement closeAnchor;
	@UiField TextBox orgField, entryField, approveField;
	@UiField LabelElement isAdminLabel, isSMSLabel, isPaymentLabel;

	public MemberApprovePopup(MemberModel model) {
		this.model = model;
		add(uiBinder.createAndBindUi(this));
		initComponent();
		initEvents();
		initPopup();
	}
	private void initComponent(){
		nameField.setText(model.getFirstName()+" "+model.getLastName());
		occupationField.setText(model.getOccupation());
		msisdnField.setText(model.getMsisdn());
		createdByField.setText(Utils.getMember(model.getCreatedBy()));
		dateField.setText(model.getCreatedTs());
		classLeaderField.setText(Utils.getMember(model.getClassId()));
		
		nameField.setEnabled(false);
		occupationField.setEnabled(false);
		msisdnField.setEnabled(false);
		createdByField.setEnabled(false);
		classLeaderField.setEnabled(false);
		dateField.setEnabled(false);
		
		if(model.getOrganisations() != null && !model.getOrganisations().trim().isEmpty()){
			orgDiv.removeClassName("hide");
			orgField.setText(Utils.getOrganisations(",", model.getOrganisations()));
			orgField.setEnabled(false);
		}
		
		if(model.getApproveModules() != null && !model.getApproveModules().trim().isEmpty()){
			aprDiv.removeClassName("hide");
			approveField.setText(Utils.getApproveModules(",", model.getApproveModules()));
			approveField.setEnabled(false);
		}
		
		if(model.getEntryModules() != null && !model.getEntryModules().trim().isEmpty()){
			entryDiv.removeClassName("hide");
			entryField.setText(Utils.getEntryModules(",", model.getEntryModules()));
			entryField.setEnabled(false);
		}

		if(model.isAdmin()){
			isAdminLabel.setInnerHTML("&nbsp;"+"Enabled");
		}else{
			isAdminLabel.setInnerHTML("&nbsp;"+"Disabled");
		}
		
		if(model.isCanSMS()){
			isSMSLabel.setInnerHTML("&nbsp;"+"Enabled");
		}else{
			isSMSLabel.setInnerHTML("&nbsp;"+"Disabled");
		}
		
		if(model.isCanViewPayments()){
			isPaymentLabel.setInnerHTML("&nbsp;"+"Enabled");
		}else{
			isPaymentLabel.setInnerHTML("&nbsp;"+"Disabled");
		}

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
		titleErrorMessage.setInnerText("Member Approved Successfully");
	}
	
	private void showRejectSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Member Rejected Successfully");
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
					handler.onMemberApproved(model.getId());
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
					handler.onMemberApproved(model.getId());
				}
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}

	private void doUpdate(){
		if(isChecked){
			showLoading(true);
			GlobalResources.getInstance().getUpdateRPC().approveMember(GlobalResources.getInstance().getModel().getId(), model, new AsyncCallback<Boolean>() {

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
		GlobalResources.getInstance().getUpdateRPC().rejectMember(GlobalResources.getInstance().getModel().getId(), model, new AsyncCallback<Boolean>() {

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
	
	public void setMemberApprovePopupEventHandler(MemberApprovePopupEventHandler handler){
		this.handler = handler;
	}


}
