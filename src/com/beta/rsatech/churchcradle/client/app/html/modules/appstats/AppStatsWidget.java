package com.beta.rsatech.churchcradle.client.app.html.modules.appstats;

import com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.renew.AppRenewPurchasePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.renew.AppRenewPurchasePopup.AppRenewPurchasePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.sms.SMSPurchasePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.appstats.purchases.sms.SMSPurchasePopup.SMSPurchasePopupEventHandler;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.AppStatsModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AppStatsWidget extends Composite {

	private AppStatsModel model;
	private boolean isBirthdayPush, isTithePush;
	private static AppStatsWidgetUiBinder uiBinder = GWT
			.create(AppStatsWidgetUiBinder.class);

	interface AppStatsWidgetUiBinder extends UiBinder<Widget, AppStatsWidget> {
	}

	@UiField DivElement smsLeft, daysLeft, membersRatio, approveStatus, entryStatus, notiStatus, sPushStatus, confirmTitle, loadingTitle, errorTitle;
	@UiField AnchorElement smsBuy, renewBtn, upgradeBtn;
	@UiField InputElement bPushSwitch, tPushSwitch;
	@UiField Button saveBtn;
	
	public AppStatsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		initAppStatsCall();
	}
	
	private CustomDraggablePopupPanel getPopup(){
		CustomDraggablePopupPanel popup = new CustomDraggablePopupPanel();
		popup.setAutoHideEnabled(false);
		popup.setGlassEnabled(true);
		popup.setGlassStyleName("glassPanel");
		popup.setDraggable(true);

		return popup;
	}
	
	private void initEvents(){
		DOM.sinkEvents(smsBuy, Event.ONCLICK);
		DOM.setEventListener(smsBuy, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				final CustomDraggablePopupPanel popup = getPopup();
				SMSPurchasePopup smsPopup = new SMSPurchasePopup();
				smsPopup.setSMSPurchasePopupEventHandler(new SMSPurchasePopupEventHandler() {
					
					@Override
					public void onClose() {
						popup.hide();
					}
				});
				
				popup.add(smsPopup);
				popup.center();				
			}
		});
		
		DOM.sinkEvents(renewBtn, Event.ONCLICK);
		DOM.setEventListener(renewBtn, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				final CustomDraggablePopupPanel popup = getPopup();
				AppRenewPurchasePopup renewPopup = new AppRenewPurchasePopup();
				renewPopup.setAppRenewPurchasePopupEventHandler(new AppRenewPurchasePopupEventHandler() {
					
					@Override
					public void onClose() {
						popup.hide();
					}
				});
				
				popup.add(renewPopup);
				popup.center();					
			}
		});
		
		DOM.sinkEvents(upgradeBtn, Event.ONCLICK);
		DOM.setEventListener(upgradeBtn, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		DOM.sinkEvents(bPushSwitch, Event.ONCLICK);
		DOM.setEventListener(bPushSwitch, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isBirthdayPush = !isBirthdayPush;
			}
		});
		
		DOM.sinkEvents(tPushSwitch, Event.ONCLICK);
		DOM.setEventListener(tPushSwitch, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				isTithePush = !isTithePush;
			}
		});
		
		DOM.sinkEvents(saveBtn.getElement(), Event.ONCLICK);
		DOM.setEventListener(saveBtn.getElement(), new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				saveChanges();
			}
		});
	}
	
	private void initComponents(){
//		smsLeft.setInnerText(model.getSmsLeft()+"");
//		daysLeft.setInnerText(model.getDaysLeft()+" days");
		
		doSMSLeftInit();
		doDayLeftInit();
		
		membersRatio.setInnerText(model.getTotalMembers()+"/"+model.getMembersLimit());
		
		if(model.isBirthdayPush()){
			enableBirthday(true);
		}
		
		if(model.isTithePush()){
			enableTithe(true);
		}
		
		if(model.getPackageType().equalsIgnoreCase(AppConstants.LITE)){
			doLiteComponentInit();
		}else if(model.getPackageType().equalsIgnoreCase(AppConstants.BASIC)){
			doBasicComponentInit();
		}else if(model.getPackageType().equalsIgnoreCase(AppConstants.FULL)){
			doFullComponentInit();
		}
	}
	
	private void doSMSLeftInit(){
		if((model.getSmsLeft() < model.getTotalMembers()) && model.getSmsLeft() >= (model.getTotalMembers()/2)){
			smsLeft.addClassName("btn-warning");
			smsLeft.setInnerText(model.getSmsLeft()+"");
		}else if(model.getSmsLeft() < (model.getTotalMembers()/2)){
			smsLeft.addClassName("btn-danger");
			smsLeft.setInnerText(model.getSmsLeft()+"");
		}else if(model.getSmsLeft() >= model.getTotalMembers()){
			smsLeft.addClassName("btn-success");
			smsLeft.setInnerText(model.getSmsLeft()+"");
		}
	}
	
	private void doDayLeftInit(){
		final int THREE_MONTHS = 90;
		final int ONE_MONTH = 30;
		
		if(model.getDaysLeft() >= THREE_MONTHS){
			daysLeft.addClassName("btn-success");
			daysLeft.setInnerText(model.getDaysLeft()+" days");
		}else if(model.getDaysLeft() < THREE_MONTHS && model.getDaysLeft() >= ONE_MONTH){
			daysLeft.addClassName("btn-warning");
			daysLeft.setInnerText(model.getDaysLeft()+" days");
		}else if(model.getDaysLeft() < ONE_MONTH && model.getDaysLeft() > 0){
			daysLeft.addClassName("btn-danger");
			daysLeft.setInnerText(model.getDaysLeft()+" days");
		}else if(model.getDaysLeft() < ONE_MONTH && model.getDaysLeft() <= 0){
			daysLeft.addClassName("btn-danger");
			daysLeft.setInnerText("expired");
		}
	}
	
	private void enableBirthday(boolean isEnabled){
		if(isEnabled){
			bPushSwitch.setAttribute("checked", "");
			this.isBirthdayPush = true;
		}else{
			bPushSwitch.removeAttribute("checked");
			this.isBirthdayPush = false;
		}
	}
	
	private void enableTithe(boolean isEnabled){
		if(isEnabled){
			tPushSwitch.setAttribute("checked", "");
			this.isTithePush = true;
		}else{
			tPushSwitch.removeAttribute("checked");
			this.isTithePush = false;
		}
	}
	
	private void enableElement(Element element){
		element.removeClassName("btn-danger");
		element.addClassName("btn-success");
		element.setInnerText("Enabled");
	}
	
	private void doLiteComponentInit(){
		enableElement((Element)entryStatus.cast());
		enableElement((Element)notiStatus.cast());
		enableElement((Element)sPushStatus.cast());
	}
	
	private void doBasicComponentInit(){
		enableElement((Element)notiStatus.cast());
		enableElement((Element)sPushStatus.cast());
	}
	private void doFullComponentInit(){
		enableElement((Element)approveStatus.cast());
		enableElement((Element)entryStatus.cast());
		enableElement((Element)notiStatus.cast());
		enableElement((Element)sPushStatus.cast());
	}
	
	private void showSuccess(){
		confirmTitle.removeClassName("hide");
		
		Timer timer = new Timer() {
			
			@Override
			public void run() {
				confirmTitle.addClassName("hide");
			}
		};
		
		timer.schedule(2*1000);
	}
	
	private void showBadConnectionError(){
		errorTitle.removeClassName("hide");

		Timer timer = new Timer() {

			@Override
			public void run() {
				errorTitle.addClassName("hide");
			}
		};

		timer.schedule(2*1000);
	}
	
	private void showLoading(boolean isShow){
		if(isShow){
			loadingTitle.removeClassName("hide");
			saveBtn.setEnabled(false);
		}else{
			loadingTitle.addClassName("hide");
			saveBtn.setEnabled(true);
		}
	}
	
	private void saveChanges(){
		if(isBirthdayPush != model.isBirthdayPush() || isTithePush != model.isTithePush()){
			model.setBirthdayPush(isBirthdayPush);
			model.setTithePush(isTithePush);
			
			showLoading(true);

			
			GlobalResources.getInstance().getUpdateRPC().updateAppStats(GlobalResources.getInstance().getModel().getId(), model, new AsyncCallback<Boolean>() {
				
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						showLoading(false);
						showSuccess();
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					showBadConnectionError();					
				}
			});
			
		}
	}
	
	private void initAppStatsCall(){
		GlobalResources.getInstance().getListRPC().getAppModel(GlobalResources.getInstance().getChurchModel().getId(), new AsyncCallback<AppStatsModel>() {
			
			@Override
			public void onSuccess(AppStatsModel result) {
				if(result != null){
					model = result;
					initComponents();
					initEvents();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				showBadConnectionError();				
			}
		});
	}

}
