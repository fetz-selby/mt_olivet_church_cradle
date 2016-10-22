package com.beta.rsatech.churchcradle.client.widgets;

import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class SMSTextArea extends TextArea{

	private int characterCount, characterLimit, smsCounter;
	private SMSTextAreaEventHandler handler;
	
	public interface SMSTextAreaEventHandler{
		void onCharacterChange(int characterCount, int smsCounter);
	}
	
	public SMSTextArea(){
		//initComponent();
	}
	
	public void setCustomStyle(String styleName, int baseType){
		this.setStyleName(styleName);
		if(baseType == AppConstants.NATIVE){
			initNative();
		}else{
			initEvents();
		}
	}
	
	private void initEvents(){
		this.addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				updateCharacterCounter();
				
				if(isThresholdReached()){
					Widget sender = (Widget) event.getSource();
				    int keyCode = event.getNativeEvent().getKeyCode();
				    if (!(keyCode == KeyCodes.KEY_BACKSPACE || keyCode == KeyCodes.KEY_DELETE || keyCode == KeyCodes.KEY_UP || keyCode == KeyCodes.KEY_RIGHT || keyCode == KeyCodes.KEY_DOWN || keyCode == KeyCodes.KEY_LEFT)) {
				      ((TextArea) sender).cancelKey();
				    }
				}
			}
		});
		
		this.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				updateCharacterCounter();
			}
		});
	}
	
	private void initNative(){
		DOM.sinkEvents(this.getElement(), Event.KEYPRESS);
		DOM.setEventListener(this.getElement(), new EventListener() {
			
			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				updateCharacterCounter();
				
				if(isThresholdReached()){
				    int keyCode = event.getKeyCode();
				    if (!(keyCode == KeyCodes.KEY_BACKSPACE || keyCode == KeyCodes.KEY_DELETE || keyCode == KeyCodes.KEY_UP || keyCode == KeyCodes.KEY_RIGHT || keyCode == KeyCodes.KEY_DOWN || keyCode == KeyCodes.KEY_LEFT)) {
				    	((TextArea) SMSTextArea.this).cancelKey();
				    }
				}				
			}
		});
		
		DOM.sinkEvents(this.getElement(), Event.KEYUP);
		DOM.setEventListener(this.getElement(), new EventListener() {
			
			@Override
			public void onBrowserEvent(com.google.gwt.user.client.Event event) {
				updateCharacterCounter();
			}
		});
	}
	
	private boolean isThresholdReached(){
		if(this.getText().length() >= characterLimit){
			return true;
		}				
		
		return false;
	}
	
	private void updateCharacterCounter(){
		characterCount = characterLimit - this.getText().length();
		
		if(this.getText().length() <= 160){
			smsCounter = 1;
		}else if(this.getText().length() > 160 && this.getText().length() < 306){
			smsCounter = 2;
		}else if(this.getText().length() > 306 && this.getText().length() < 459){
			smsCounter = 3;
		}else if(this.getText().length() > 459 && this.getText().length() < 621){
			smsCounter = 4;
		}
		
		if(handler != null){
			handler.onCharacterChange(characterCount, smsCounter);
		}
	}
	
	public void setCharacterLimit(int characterLimit){
		this.characterLimit = characterLimit;
	}
	
	public void setSMSTextAreaEventHandler(SMSTextAreaEventHandler handler){
		this.handler = handler;
	}
}
