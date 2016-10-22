package com.beta.rsatech.churchcradle.client.login.presenter;

import com.beta.rsatech.churchcradle.client.Presenter;
import com.beta.rsatech.churchcradle.client.events.AnnoymousDonateEvent;
import com.beta.rsatech.churchcradle.client.events.LoginSuccessEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class LoginPresenter implements Presenter{

	private Display view;

	public interface Display extends IsWidget{
		Widget asWidget();
		String getUserName();
		String getPassword();
		HasClickHandlers getSignInEvent();
		HasClickHandlers getDonateEvent();
		Element getUsernameElement();
		Element getPasswordElement();
		void showError();
		void changeSigninBtn(String word);
		void disableSignInButton(boolean isDisable);
	}

	public LoginPresenter(Display view){
		this.view = view;
	}

	private void bind(){
		view.getSignInEvent().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSigningIn();
			}
		});
		
		view.getDonateEvent().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GlobalResources.getInstance().getEventBus().fireEvent(new AnnoymousDonateEvent());
			}
		});
		
		DOM.sinkEvents(view.getUsernameElement(), Event.ONKEYPRESS);
		DOM.setEventListener(view.getUsernameElement(), new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				if(event.getCharCode() == KeyCodes.KEY_ENTER){
					doSigningIn();
				}
			}
		});
		
		DOM.sinkEvents(view.getPasswordElement(), Event.ONKEYPRESS);
		DOM.setEventListener(view.getPasswordElement(), new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				if(event.getCharCode() == KeyCodes.KEY_ENTER){
					doSigningIn();
				}
			}
		});
	}
	
	private void doSigningIn(){
		view.disableSignInButton(true);
		view.changeSigninBtn("Signing in ...");
		doValidation(view.getUserName(), view.getPassword());
	}

	private void doValidation(String username, String password){
		if((Utils.isEmailValidFormat(username) || Utils.isMsisdnValidFormat(username)) && Utils.isValidPassword(password)){
			GlobalResources.getInstance().getListRPC().getUser(username, password, false, new AsyncCallback<UserModel>() {

				@Override
				public void onSuccess(UserModel result) {
					if(result != null){
						GlobalResources.getInstance().getEventBus().fireEvent(new LoginSuccessEvent(result));
					}else{
						view.showError();
						view.changeSigninBtn("Sign In");
						view.disableSignInButton(false);
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					view.showError();
					view.changeSigninBtn("Sign In");
					view.disableSignInButton(false);
				}
			});
		}
	}

	private UserModel getFakeUser(){
		UserModel model = new UserModel();
		model.setId(1);

		return model;
	}

	public Widget getWidget(){
		return view.asWidget();
	}

	@Override
	public void go(HasWidgets screen) {
		screen.clear();
		screen.add(getWidget());
		bind();
	}

}
