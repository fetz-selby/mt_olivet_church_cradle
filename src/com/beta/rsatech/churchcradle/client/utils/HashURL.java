package com.beta.rsatech.churchcradle.client.utils;

import java.util.HashMap;

import com.google.gwt.user.client.Window;

public class HashURL {
	private static final HashURL instance = new HashURL();
	private static String token, value, symbolToken;
	private static HashMap<String, String> paramMap;
	
	private HashURL(){}
	
	public static HashURL getInstance(){
		return instance;
	}
	
	public static void load(){
		token = null;
		value = null;
		
		String url = Window.Location.getHash();
		if(!url.trim().isEmpty()){
			if(url.indexOf('#') == 0 && url.contains("/")){
				String tokenAndValue = url.substring(1);
				token = tokenAndValue.split("/")[0];
				value = tokenAndValue.split("/")[1];
				doParamExtraction(value);
			}else if(url.indexOf('#') == 0 && url.trim().length() > 1){
				token = url.substring(1);
			}
		}
	}
	
	private static void doParamExtraction(String urlParam){
		paramMap = new HashMap<String, String>();

		if(urlParam != null){
			String[] pairArray = urlParam.split(";");
			for(String pair : pairArray){
				String key = pair.split("=")[0];
				String value = pair.split("=")[1];
				
				paramMap.put(key, value);
			}
		}
		
		if(urlParam.contains("?")){
			String externalToken = urlParam.split("[?]")[1];
			
			if(externalToken.contains("=")){
				symbolToken = externalToken.split("=")[1];
			}
		}
	}
	
	public static String getValue(String key){
		if(paramMap != null){
			if(paramMap.containsKey(key)){
				return paramMap.get(key);
			}
		}
		
		return null;
	}
	
	public static String getAfterSymbolToken(){
		return symbolToken;
	}
	
	public static String getToken(){
		return token;
	}
	
	public static String getValue(){
		return value;
	}
}
