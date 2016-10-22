package com.beta.rsatech.churchcradle.client.login.view;

import com.beta.rsatech.churchcradle.client.HasPlugins;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.client.login.presenter.LoginPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite implements LoginPresenter.Display, HasPlugins{

	private static LoginViewUiBinder uiBinder = GWT
			.create(LoginViewUiBinder.class);

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}

	@UiField SectionElement sectionElement;
	@UiField InputElement usernameElement, passwordElement;
	@UiField Button signInBtn, donateBtn;
	
	public LoginView() {
		initWidget(uiBinder.createAndBindUi(this));
		initComponents();
		doPluginsLoad();
	}
	
	private void initComponents(){
		sectionElement.setId("sels_login");
	}

	@Override
	public String getUserName() {
		return usernameElement.getPropertyString("value").trim();
	}

	@Override
	public String getPassword() {
		return passwordElement.getPropertyString("value").trim();
	}

	@Override
	public void doPluginsLoad() {
		Scheduler.get().scheduleDeferred(new Command() {
			@Override public void execute() {
				doDOMPluginsLoad();
			}
		});		
	}
	
	private native void doDOMPluginsLoad()/*-{
		var app = this;
		var plugins = "<script src='js/custom.js'></script>";
		$wnd.$('#sels_login').append(plugins);
	}-*/;

	@Override
	public HasClickHandlers getSignInEvent() {
		return signInBtn;
	}

	@Override
	public void showError() {
		usernameElement.addClassName("red-border-style");
		passwordElement.addClassName("red-border-style");
	}

	@Override
	public HasClickHandlers getDonateEvent() {
		return donateBtn;
	}

	@Override
	public void changeSigninBtn(String word) {
		signInBtn.setText(word);		
	}

	@Override
	public Element getUsernameElement() {
		return usernameElement.cast();
	}

	@Override
	public Element getPasswordElement() {
		return passwordElement.cast();
	}

	@Override
	public void disableSignInButton(boolean isDisable) {
		if(isDisable){
			signInBtn.setEnabled(false);
		}else{
			signInBtn.setEnabled(true);
		}
	}

	
}
