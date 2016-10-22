package com.beta.rsatech.churchcradle.client.utils;

import java.util.Date;

import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.user.client.Cookies;

public class CookieVerifier {
	private static CookieVerifier cookie = new CookieVerifier();
	
	private CookieVerifier(){}
	
	public static CookieVerifier getInstance(){
		return cookie;
	}
	
	public static void addCookie(UserModel model){
		Date date = new Date();
		
		//Expires in 1 hour from login
		date.setHours(date.getHours() + 1);
		//date.setMinutes(date.getMinutes() + 1);
		
		Cookies.setCookie("user_id", ""+model.getId());
		Cookies.setCookie("email", model.getEmail());
		Cookies.setCookie("msisdn", model.getMsisdn());
	}
	
	public static void addForgetCookie(){
	}
	
	public static void clearCookie(){
		Cookies.removeCookie("user_id");
	}
	
	public static boolean isAppCookieExist(){
		if(Cookies.getCookie("user_id") != null && !(Cookies.getCookie("user_id").isEmpty())){
			return true;
		}
		
		return false;
	}
	
	public static boolean isForgetCookieExist(){
//		if(Cookies.getCookie("apptype") != null && !(Cookies.getCookie("apptype").isEmpty())){
//			return true;
//		}
		
		return false;
	}
	
	public int getId(){
		return Integer.parseInt(Cookies.getCookie("user_id"));
	}

}
