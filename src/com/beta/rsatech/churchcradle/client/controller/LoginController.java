package com.beta.rsatech.churchcradle.client.controller;

import com.beta.rsatech.churchcradle.client.Controller;
import com.beta.rsatech.churchcradle.client.app.html.modules.forgotpassword.ForgotPasswordPopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.forgotpassword.ForgotPasswordPopup.ForgotMemberPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.payment.annoymousdonation.AnnoymousDonation;
import com.beta.rsatech.churchcradle.client.app.payment.annoymousdonation.AnnoymousDonation.AnnoymousDonationPaymentPopupEventHandler;
import com.beta.rsatech.churchcradle.client.events.AnnoymousDonateEvent;
import com.beta.rsatech.churchcradle.client.events.AnnoymousDonateEventHandler;
import com.beta.rsatech.churchcradle.client.events.ForgotPasswordEvent;
import com.beta.rsatech.churchcradle.client.events.ForgotPasswordEventHandler;
import com.beta.rsatech.churchcradle.client.events.LoginSuccessEvent;
import com.beta.rsatech.churchcradle.client.events.LoginSuccessEventHandler;
import com.beta.rsatech.churchcradle.client.events.LogoutEvent;
import com.beta.rsatech.churchcradle.client.events.LogoutEventHandler;
import com.beta.rsatech.churchcradle.client.login.presenter.LoginPresenter;
import com.beta.rsatech.churchcradle.client.login.view.LoginView;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.CookieVerifier;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.HashURL;
import com.beta.rsatech.churchcradle.client.utils.PageDirector;
import com.beta.rsatech.churchcradle.client.utils.URLConstants;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginController implements Controller, ValueChangeHandler<String> {
	private HandlerManager eventBus;
	private RootPanel mainContainer;

	public LoginController(RootPanel mainContainer, HandlerManager eventBus){
		this.eventBus = eventBus;
		this.mainContainer = mainContainer;
	}

	private void init(){
		History.addValueChangeHandler(this);

		if(History.getToken().trim().isEmpty()){
			History.newItem(URLConstants.LOGOUT);
		}else{
			History.fireCurrentHistoryState();
		}
	}

	private void bind(){
		eventBus.addHandler(LoginSuccessEvent.TYPE, new LoginSuccessEventHandler() {

			@Override
			public void onLoginSuccessEventInvoked(LoginSuccessEvent event) {
				CookieVerifier.addCookie(event.getModel());
				PageDirector.getInstance().directTo("app.html", "dashboard");
			}
		});

		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {

			@Override
			public void onLogoutInvoked(LogoutEvent event) {
				if(History.getToken().equals(URLConstants.LOGOUT)){
					History.fireCurrentHistoryState();
				}else{
					History.newItem(URLConstants.LOGOUT);
				}

			}
		});

		eventBus.addHandler(ForgotPasswordEvent.TYPE, new ForgotPasswordEventHandler() {

			@Override
			public void onForgotPasswordEventInvoked(ForgotPasswordEvent event) {
				if(History.getToken().equals(URLConstants.FORGOT)){
					History.fireCurrentHistoryState();
				}else{
					History.newItem(URLConstants.FORGOT);
				}
			}
		});

		eventBus.addHandler(AnnoymousDonateEvent.TYPE, new AnnoymousDonateEventHandler() {

			@Override
			public void onAnnoymousDonateEventInvoked(AnnoymousDonateEvent event) {
				if(History.getToken().equals(URLConstants.ANNOYMOUS_DONATE)){
					History.fireCurrentHistoryState();
				}else{
					History.newItem(URLConstants.ANNOYMOUS_DONATE);
				}			}
		});
	}

	private void loadConfirmation(){
		int paymentId = Integer.parseInt(HashURL.getValue("id"));
		String token = HashURL.getValue("g");
		String type = HashURL.getValue("t");
		String externalToken = HashURL.getAfterSymbolToken();
		if(externalToken == null){
			externalToken = "failed";
		}

		if(type.equalsIgnoreCase("D")){
			GlobalResources.getInstance().getPaymentRPC().addDonationBySystem(paymentId, token, externalToken, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						eventBus.fireEvent(new LogoutEvent());
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		}
	}

	private void showForgotPasswordScreen(){
		final PopupPanel popup = getPopup();

		ForgotPasswordPopup forgotSettings = new ForgotPasswordPopup();
		forgotSettings.setForgotMemberPopupEventHandler(new ForgotMemberPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
				eventBus.fireEvent(new LogoutEvent());
			}
		});
		popup.add(forgotSettings);
		popup.center();
	}

	private void showAnnoymousDonatePopupScreen(){
		final PopupPanel popup = getPopup();
		Utils.retrieveServerMonth(new GeneralEventHandler<String>() {

			@Override
			public void onSuccess(String t) {

				AnnoymousDonation paymentPopup = new AnnoymousDonation(t);
				paymentPopup.setAnnoymousDonationPaymentPopupEventHandler(new AnnoymousDonationPaymentPopupEventHandler() {

					@Override
					public void onClose() {
						popup.hide();
					}
				});

				popup.add(paymentPopup);
				popup.center();					
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});
	}

	private PopupPanel getPopup(){
		PopupPanel popup = new PopupPanel();
		popup.setAutoHideEnabled(false);
		popup.setGlassEnabled(true);
		popup.setGlassStyleName("glassPanel");
		popup.addStyleName("popup-style");

		return popup;
	}

	private void pushSMSNotificationToServer(){
		GlobalResources.getInstance().getListRPC().notifySMS(new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void go(){
		bind();
		init();
	}

	private void clearContainer(){
		mainContainer.clear();
	}

	private void loadLoginPage(){
		LoginPresenter loginPage = new LoginPresenter(new LoginView());
		loginPage.go(mainContainer);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		HashURL.load();

		String token = event.getValue().trim();
		if(token.equals(URLConstants.LOGOUT)){
			clearContainer();
			loadLoginPage();
		}else if(token.equals(URLConstants.FORGOT)){
			showForgotPasswordScreen();
		}else if(token.equals(URLConstants.ANNOYMOUS_DONATE)){
			showAnnoymousDonatePopupScreen();
		}else if(token.contains(URLConstants.SMS_NOTIFICATION)){
			pushSMSNotificationToServer();
		}else if(token.contains(URLConstants.CONFIRM) && HashURL.getValue() != null){
			loadConfirmation();
		}else{
			CookieVerifier.clearCookie();
			History.newItem(URLConstants.LOGOUT);
		}



		//		if(HashURL.getToken().equals(URLConstants.VERIFY) && HashURL.getValue() != null){
		//
		//		}
	}
}
