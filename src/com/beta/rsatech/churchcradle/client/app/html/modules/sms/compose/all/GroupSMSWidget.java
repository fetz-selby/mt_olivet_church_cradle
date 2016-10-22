package com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.all;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.all.SMSGroupSelectPopup.SMSGroupSelectPopupEventHandler;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.SMSTextArea;
import com.beta.rsatech.churchcradle.client.widgets.SMSTextArea.SMSTextAreaEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.GroupInfoModel;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GroupSMSWidget extends Composite {

	private String groupIds;
	int smsCounts;
	private boolean isAllMembers;
	private static GroupSMSWidgetUiBinder uiBinder = GWT
			.create(GroupSMSWidgetUiBinder.class);

	interface GroupSMSWidgetUiBinder extends UiBinder<Widget, GroupSMSWidget> {
	}

	@UiField DivElement smsCounter, charCounter, totalGroups, totalMembers;
	@UiField SMSTextArea smsTextArea;
	@UiField AnchorElement sendBtn, groupBtn;
	@UiField TextBox fromBox;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;

	public GroupSMSWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
		initEvents();
	}

	private void initComponents(){
		smsTextArea.setCharacterLimit(AppConstants.SMS_THRESHOLD);
		smsTextArea.setCustomStyle("textarea-style2", AppConstants.NATIVE);
		smsTextArea.getElement().setAttribute("placeholder", "Enter message ...");

		fromBox.setText(GlobalResources.getInstance().getChurchModel().getDisplay());
		fromBox.setEnabled(false);
	}

	private void initEvents(){
		smsTextArea.setSMSTextAreaEventHandler(new SMSTextAreaEventHandler() {

			@Override
			public void onCharacterChange(int characterCount, int smsCounter) {
				charCounter.setInnerText(AppConstants.SMS_CHARACTER_LABEL+" "+characterCount);
				GroupSMSWidget.this.smsCounter.setInnerText(AppConstants.SMS_LABEL+" "+smsCounter);
				GroupSMSWidget.this.smsCounts = smsCounter;
			}
		});

		Element groupBtnElement = groupBtn.cast();
		DOM.sinkEvents(groupBtnElement, Event.ONCLICK);
		DOM.setEventListener(groupBtnElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				showSelectionPopup();
			}
		});

		Element sendBtnElement = sendBtn.cast();
		DOM.sinkEvents(sendBtnElement, Event.ONCLICK);
		DOM.setEventListener(sendBtnElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				validateAndSave();
			}
		});
	}

	private void validateAndSave(){
		if(smsTextArea.getText().trim().isEmpty()){
			showError(true, "Message cannot be empty.");
			return;
		}

		if(groupIds == null || groupIds.trim().isEmpty()){
			showError(true, "Groups cannot be empty");
			return;
		}

		saveSMS();
	}

	private void saveSMS(){
		SMSModel model = new SMSModel();
		model.setBulk(true);
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
		model.setCreatedBy(GlobalResources.getInstance().getModel().getId());
		model.setDisplay(GlobalResources.getInstance().getChurchModel().getDisplay());
		model.setEditable(true);
		if(isAllMembers){
			model.setGroups("0");
		}else{
			model.setGroups(groupIds);
		}
		model.setType(AppConstants.GROUPS);
		model.setMessage(smsTextArea.getText().trim());
		model.setSmsCounts(smsCounts);
		model.setStatus(AppConstants.PENDING);
		model.setRecipient(groupIds);
		model.setBulk(true);

		GlobalResources.getInstance().getAddRPC().addSMS(model, GlobalResources.getInstance().getModel().getId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				if(result > 0){
					showPendingSuccess();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				showError(true, "Oop, bad network connectivity");
			}
		});
	}

	private void showError(boolean isShow, String message){
		if(isShow){
			errorDiv.removeClassName("hide");
			titleErrorMessage.setInnerText(message);
		}else{
			errorDiv.addClassName("hide");
		}
	}

	private void showPendingSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("SMS saved successfully for approval");
	}

	private void showSelectionPopup(){
		SMSGroupSelectPopup popup = new SMSGroupSelectPopup();
		popup.setSMSGroupSelectPopupEventHandler(new SMSGroupSelectPopupEventHandler() {

			@Override
			public void onGroupSelect(ArrayList<GroupInfoModel> groupInfoList, int totalGroups, boolean isAllMembers) {
				GroupSMSWidget.this.isAllMembers = isAllMembers;
				setGroupDetails(groupInfoList.size(), totalGroups);
				setMembersDetails(groupInfoList);
			}
		});
	}

	private void setGroupDetails(int current, int totalSize){
		if(isAllMembers){
			totalGroups.setInnerText(totalSize+"/"+totalSize);
		}else{
			totalGroups.setInnerText(current+"/"+totalSize);
		}
	}

	private void setMembersDetails(ArrayList<GroupInfoModel> modelList){
		int sum = 0;
		groupIds = "";

		if(isAllMembers){
			groupIds = "0";
			totalMembers.setInnerText(GlobalResources.getInstance().getMembersMap().size()+"");
		}else{

			for(GroupInfoModel model : modelList){
				sum += model.getTotalMembers();
				groupIds += model.getId()+",";
			}

			totalMembers.setInnerText(sum+"");
			if(groupIds.contains(",")){
				groupIds = groupIds.substring(0, groupIds.length()-1);
			}
		}
	}

}
