package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

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

public class AddMemberPopup extends Composite {

	private int stageIndex = 0;
	//private MemberModel model;
	AddMemberPopupEventHandler handler;
	private IsWizardable<MemberModel> wizard;
	private static AddMemberPopupUiBinder uiBinder = GWT
			.create(AddMemberPopupUiBinder.class);

	public interface AddMemberPopupEventHandler{
		void onClose();
	}

	interface AddMemberPopupUiBinder extends UiBinder<Widget, AddMemberPopup> {
	}

	@UiField SimplePanel wizardPanel;
	@UiField Button previousBtn, nextBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	@UiField AnchorElement closeAnchor;
	@UiField SpanElement titleSpan;

	public AddMemberPopup() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponent();
		initEvent();
	}

	public AddMemberPopup(MemberModel model){
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(MemberModel model){
		titleSpan.setInnerText(AppConstants.UPDATE_MEMBER_TITLE);
		model.setUpdate(true);
		wizard = new StageOne(model);
		initWizardableEvent(wizard);

		wizardPanel.setWidget(wizard);
	}

	private void initComponent(){
		titleSpan.setInnerText(AppConstants.ADD_MEMBER_TITLE);
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
				case TWO_SUB:
					wizard = new StageTwoSub1(model);
					stageIndex = 2;
					break;
				case TWO_SUB2:
					wizard = new StageTwoSub2(model);
					stageIndex = 3;
					break;
				case THREE:
					wizard = new StageThree(model);
					stageIndex = 4;
					break;
				case FOUR:
					wizard = new StageFour(model);
					stageIndex = 5;
					break;
				case FIVE:
					wizard = new StageFive(model);
					stageIndex = 6;
					break;
				case SIX:
					wizard = new StageSix(model);
					stageIndex = 7;
					break;
				case SIX_SUB1:
					wizard = new StageSixSub1(model);
					stageIndex = 8;
					break;
				case REVIEW:
					wizard = new ReviewStage(model);
					stageIndex = 9;
					break;
				case DONE:
					if(!model.isUpdate()){
						doSaveMember(model);
					}else{
						doMemberUpdate(model);
					}
					break;
				default:
					break;

				}

				if(stageIndex > 0){
					showPrevious(true);
				}else{
					showPrevious(false);
				}

				if(stageIndex == 9){
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

	private void showAddSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Member Added Successfully");
	}

	private void showUpdateSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Member Updated Successfully");
	}

	private void showAddSuccessAndHide(){
		showAddSuccess();
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

	private void doSaveMember(MemberModel model){
		disableButton(true);
		GlobalResources.getInstance().getAddRPC().addMember(model, GlobalResources.getInstance().getModel().getId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				if(result > 0){
					showAddSuccessAndHide();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				showAndHide("Bad network connection. Please try again later.");
			}
		});
	}

	private void doMemberUpdate(MemberModel model){
		disableButton(true);
		if(model.isDeleteMember()){
			GlobalResources.getInstance().getUpdateRPC().removeMember(GlobalResources.getInstance().getModel().getId(), model.getChurchId(), model.getId(), new AsyncCallback<Boolean>() {

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
		}else{
			GlobalResources.getInstance().getUpdateRPC().updateMember(GlobalResources.getInstance().getModel().getId(), model, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						showUpdateSuccessAndHide();
					}				
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	public void setAddMemberPopupEventHandler(AddMemberPopupEventHandler handler){
		this.handler = handler;
	}

}
