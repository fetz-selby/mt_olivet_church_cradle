package com.beta.rsatech.churchcradle.client.widgets;

import com.google.gwt.user.client.ui.TextArea;

public class ReviewPopupTextArea extends TextArea{
	
	public ReviewPopupTextArea(){
		initStyle(false);
	}
	
	public ReviewPopupTextArea(boolean isEnabled){
		initStyle(isEnabled);
	}
	
	private void initStyle(boolean isEnable){
		this.setStyleName("textarea-style form-control");
		this.setEnabled(isEnable);
	}
	
}
