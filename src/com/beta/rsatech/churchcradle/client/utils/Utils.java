package com.beta.rsatech.churchcradle.client.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.google.apphosting.utils.glob.Glob;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Widget;

public class Utils {
	private static FormPanel userGlobalForm;

	public static void setGlobalForm(FormPanel formPanel){
		userGlobalForm = formPanel;
	}

	public static void setFeed(Widget widget, String url, boolean isPost, final FormSaveEventHandler handler){
		if(userGlobalForm == null){
			return;
		}

		userGlobalForm.setAction(GWT.getModuleBaseURL()+url);
		if(isPost){
			userGlobalForm.setMethod(FormPanel.METHOD_POST);
			userGlobalForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		}else{
			userGlobalForm.setMethod(FormPanel.METHOD_GET);
		}

		userGlobalForm.clear();
		userGlobalForm.setWidget(widget);
		userGlobalForm.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				if(handler != null){
					handler.onSuccessfulSave(event);
				}
			}
		});
		userGlobalForm.submit();
	}


//	public static void reInitUserModel(final OperationCompleteHandler<UserModel> model){
//		GlobalResources.getInstance().getSmsListRPC().getUser(GlobalResources.getInstance().getUserModel().getId(), new AsyncCallback<UserModel>() {
//			
//			@Override
//			public void onSuccess(UserModel result) {
//				if(model != null){
//					model.onOperationComplete(result);
//				}
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
	
	public static boolean isDateValid(String date, String delimiter){
		String[] dateTokens = date.split(delimiter);
		if(dateTokens.length == 3){
			Date now = new Date();
			
			int year = Integer.parseInt(dateTokens[0]);
			int month = Integer.parseInt(dateTokens[1]);
			int day = Integer.parseInt(dateTokens[2]);
			
			if(day > 31){
				return false;
			}
			
			if(month > 12){
				return false;
			}
			
			if(year < 1900){
				return false;
			}
			
			
			if(now.getYear() < 1900){
				int thisYear = now.getYear() + AppConstants.EPOC;
				if(year > thisYear){
					return false;
				}
			}
			
			return true;
			
		}else{
			return false;
		}
	}
	
	public static boolean isYearValid(String year){
		//String[] dateTokens = date.split(delimiter);
		if(year != null && !year.isEmpty()){
			Date now = new Date();
			
			int tmpYear = Integer.parseInt(year);
			
			if(tmpYear < 1900){
				return false;
			}
			
			if(now.getYear() < 1900){
				int thisYear = now.getYear() + AppConstants.EPOC;
				if(tmpYear > thisYear){
					return false;
				}
			}
			
			return true;
			
		}else{
			return false;
		}
	}
	
	public static boolean isEmailValidFormat(String email){
		if(email.trim().matches("^([a-z0-9_\\.-]+)@([\\d\\p{L}\\a-z\\.-]+)\\.([a-z\\.]{2,6})$")){
			return true;
		}
		return false;
	}
	
	public static boolean isMsisdnValidFormat(String username){
		if(username.length() > 10 && username.length() < 16 && !username.contains("@")){
			for(int i = 0; i < username.length(); i++){
				if(!Character.isDigit(username.charAt(i))){
					if(!(username.charAt(i) == '+')){
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public static void validateEmail(String email, int churchId, final GeneralEventHandler<Boolean> handler){
		GlobalResources.getInstance().getListRPC().isEmailExist(email, churchId, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				handler.onSuccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				handler.onError();
			}
		});
	}
	
	public static void validatePassword(String password, int churchId, int memberId, final GeneralEventHandler<Boolean> handler){
		GlobalResources.getInstance().getListRPC().isPasswordValid(memberId, churchId, password, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				handler.onSuccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public static void validateMsisdn(String msisdn, int churchId, final GeneralEventHandler<Boolean> handler){
		GlobalResources.getInstance().getListRPC().isMsisdnExist(msisdn, churchId, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				handler.onSuccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				handler.onError();
			}
		});
	}
	
	public static String getTruncatedText(String text, int limit){
		if(text == null || text.trim().isEmpty()){
			return AppConstants.PROFILE_EMPTY_PLACEHOLDER;
		}else if(text.length() > limit){
			return text.substring(0, limit - 3)+" ...";
		}

		return text;
	}

	public static String getCompoundedName(String name){
		if(name != null){
			if(name.trim().contains(" ")){
				return name.replaceAll(" ", "_");
			}else{
				return name;
			}
		}

		return name;
	}

	public static String getDecodedCompoundedName(String name){
		if(name != null){
			if(name.trim().contains("_")){
				return name.replaceAll("_", " ");
			}else{
				return name;
			}
		}

		return name;
	}

	public static String getTodayDate(){
		DateTimeFormat dateFormatter = DateTimeFormat.getFormat("yyyy-MM-dd");
		return dateFormatter.format(new Date());
	}

	public static String getDateString(Date date){
		DateTimeFormat dateFormatter = DateTimeFormat.getFormat("yyyy-MM-dd");
		return dateFormatter.format(date);
	}

	public static boolean isValidPassword(String password) {
		if(password.trim().length() >= 5){
			return true;
		}
		return false;
	}
	
	public static ArrayList<Integer> getTokenList(String delimiter, String value){
		if(value != null && !value.trim().isEmpty()){
			ArrayList<Integer> valueList = new ArrayList<Integer>();
			String[] valueTokens = value.split(delimiter);
			for(String token : valueTokens){
				valueList.add(Integer.parseInt(token));
			}
			return valueList;
		}
		return null;
	}
	
	public static String getOrganisations(String delimiter, String values){
		if(values.trim().isEmpty()){
			return "";
		}
		String organisations = "";
		String[] valueTokens = values.split(delimiter);
		
		for(String token : valueTokens){
			String org = GlobalResources.getInstance().getOrganisationMap().get(Integer.parseInt(token));
			if(org.trim().equalsIgnoreCase(AppConstants.DEFAULT_GROUP)){
				continue;
			}
			organisations += org+", ";
		}
		
		return organisations.substring(0, organisations.lastIndexOf(delimiter));
	}
	
	public static String getApproveModules(String delimiter, String values){
		if(values.trim().isEmpty()){
			return "";
		}
		String approves = "";
		String[] valueTokens = values.split(delimiter);
		
		for(String token : valueTokens){
			approves += GlobalResources.getInstance().getApproveModuleMap().get(Integer.parseInt(token))+", ";
		}
		
		return approves.substring(0, approves.lastIndexOf(delimiter));
	}
	
	public static String getEntryModules(String delimiter, String values){
		if(values.trim().isEmpty()){
			return "";
		}
		String entries = "";
		String[] valueTokens = values.split(delimiter);
		
		for(String token : valueTokens){
			entries += GlobalResources.getInstance().getEntryModuleMap().get(Integer.parseInt(token))+", ";
		}
		
		return entries.substring(0, entries.lastIndexOf(delimiter));
	}
	
	public static String getClassLeader(int classId){
		return GlobalResources.getInstance().getClassesLeadersMap().get((classId));
	}
	
	public static ArrayList<String> getModulesList(String delimiter, String modules){
		String[] moduleToken = modules.split(delimiter);
		TreeSet<String> orderedModuleList = new TreeSet<String>();
		
		for(String module : moduleToken){
			if(GlobalResources.getInstance().getApproveModuleMap().get(Integer.parseInt(module)) == null) continue;
			orderedModuleList.add(GlobalResources.getInstance().getApproveModuleMap().get(Integer.parseInt(module)));
		}
		
		ArrayList<String> moduleList = new ArrayList<String>();
		for(String module : orderedModuleList){
			moduleList.add(module);
		}
		
		return moduleList;
	}
	
	public static String getCapitalizedWord(String word){
		String[] wordTokens = word.split("[\\s]+");
		String tmpWord = "";
		
		for(String wordToken : wordTokens){
			tmpWord += wordToken.substring(0,1).toUpperCase()+wordToken.substring(1).toLowerCase()+" ";
		}
		
		return tmpWord.trim();
	}
	
	public static String getMember(int memberId){
		if(GlobalResources.getInstance().getMembersMap().containsKey(memberId)){
			return GlobalResources.getInstance().getMembersMap().get(memberId);
		}
		return "Anonymous";
	}
	
	public static boolean isSMSApproveEnabled(){
		for(Integer key : GlobalResources.getInstance().getApproveModuleMap().keySet()){
			String module = GlobalResources.getInstance().getApproveModuleMap().get(key);
			if(module.equalsIgnoreCase(AppConstants.SMS)){
				return true;
			}
		}
		return false;
	}
	
	public static String getMonth(String month){
		if(month.trim().equals(AppConstants.JAN) || month.trim().equals("1")){
			return AppConstants.JANUARY;
		}else if(month.trim().equals(AppConstants.FEB) || month.trim().equals("2")){
			return AppConstants.FEBUARY;
		}else if(month.trim().equals(AppConstants.MAR) || month.trim().equals("3")){
			return AppConstants.MARCH;
		}else if(month.trim().equals(AppConstants.APR) || month.trim().equals("4")){
			return AppConstants.APRIL;
		}else if(month.trim().equals(AppConstants.MAY) || month.trim().equals("5")){
			return AppConstants.MAYW;
		}else if(month.trim().equals(AppConstants.JUN) || month.trim().equals("6")){
			return AppConstants.JUNE;
		}else if(month.trim().equals(AppConstants.JUL) || month.trim().equals("7")){
			return AppConstants.JULY;
		}else if(month.trim().equals(AppConstants.AUG) || month.trim().equals("8")){
			return AppConstants.AUGUST;
		}else if(month.trim().equals(AppConstants.SEP) || month.trim().equals("9")){
			return AppConstants.SEPTEMBER;
		}else if(month.trim().equals(AppConstants.OCT)){
			return AppConstants.OCTOBER;
		}else if(month.trim().equals(AppConstants.NOV)){
			return AppConstants.NOVEMBER;
		}else if(month.trim().equals(AppConstants.DEC)){
			return AppConstants.DECEMBER;
		}
		
		return "";
	}
	
	public static void retrieveServerMonth(final GeneralEventHandler<String> month){
			GlobalResources.getInstance().getListRPC().getServerDate(new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
					String tmpMonth = result.split("-")[1];
					month.onSuccess(Utils.getMonth(tmpMonth));
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
	}
	
	public static String getPartialPassword(String password){
		return password.substring(0,3)+"*****";
	}
	
	public static String getLocationPath(String htmlFile){
		return GWT.getHostPageBaseURL()+htmlFile;
	}
	
	public static boolean isNumber(String msisdn){
		if(msisdn != null){
			for(int i = 0; i < msisdn.length(); i++){
				char c = msisdn.charAt(i);
				if(c == '+') continue;
				if(!Character.isDigit(c)){
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public static boolean isMsisdnValid(String msisdn){
		if(msisdn.contains(",")){
			String[] msisdnToken = msisdn.split(",");
			for(String tmpMsisdn : msisdnToken){
				if(tmpMsisdn.length() < 12){
					return false;
				}
				
				if(!isNumber(tmpMsisdn)){
					return false;
				}
			}
			
			return true;
		}else if(msisdn.length() > 11 && isNumber(msisdn)){
			return true;
		}
		
		return false;
	}
	
	public static void retrieveFromBlobstore(String blobKey, final GeneralEventHandler<BlobstoreModel> handler){
		GlobalResources.getInstance().getListRPC().retrieveFromBlobstore(blobKey, new AsyncCallback<BlobstoreModel>() {
			
			@Override
			public void onSuccess(BlobstoreModel result) {
				handler.onSuccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public static boolean isNumbers(String msisdn){
		if(msisdn != null){
			for(int i = 0; i < msisdn.length(); i++){
				char c = msisdn.charAt(i);
				if(!Character.isDigit(c)){
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}

}
