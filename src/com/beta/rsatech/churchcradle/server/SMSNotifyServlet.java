package com.beta.rsatech.churchcradle.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beta.rsatech.churchcradle.server.utils.sms.SMSGetter;

@SuppressWarnings("serial")
public class SMSNotifyServlet extends HttpServlet{
	private SMSGetter readSMS;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doGet(req, resp);

		//		SMSDispatchObject sendSMS = new SMSDispatchObject("233244960321", "[Get] Worked!");
		//		sendSMS.send();

		if(readSMS == null){
			readSMS = new SMSGetter();
		}
		readSMS.readSMS();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//			SMSDispatchObject sendSMS = new SMSDispatchObject("233244960321", "[Post] Worked!");
		//			sendSMS.send();
		if(readSMS == null){
			readSMS = new SMSGetter();
		}
		readSMS.readSMS();
	}



}
