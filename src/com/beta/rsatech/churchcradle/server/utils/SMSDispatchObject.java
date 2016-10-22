package com.beta.rsatech.churchcradle.server.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beta.rsatech.churchcradle.shared.ChurchModel;

public class SMSDispatchObject {
	private static Connection con = DBConnection.getConnection();
	private String message, msisdn;
	private ChurchModel churchModel;

	public SMSDispatchObject(String msisdn, String message){
		this.message = message;
		this.msisdn = msisdn;
		churchModel = getChurchModel(ServerGlobalResources.getInstance().getChurchModel().getId());
	}
	
	public void send(){
		if(isAppClean(churchModel, msisdn)){
			doDeduction(churchModel, msisdn);
		}else{
			System.out.println("App Has Expire or has Elapse SMS");
		}
	}
	
	private void doDeduction(ChurchModel model, String msisdn){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("update churches set sms_left = ? where id = ? and status = 'A'");
			prstmt.setDouble(1, model.getSmsLeft() - Utils.getSMSCount(msisdn));
			prstmt.setInt(2, model.getId());

			int success = prstmt.executeUpdate();
			System.out.println("[sms limit valid] success is "+success);
			if(success >= 0){
				//con.close();
				submitMessage();
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}	
	}
	
	private boolean isAppClean(ChurchModel model, String msisdn){
		double smsCount = Utils.getSMSCount(msisdn);
		if(model.getSmsLeft() >= smsCount && Utils.isAppValid(false, model.getExpireDate())){
			return true;
		}
		
		return false;
	}
	
	private void submitMessage() {

		if(msisdn == null || msisdn.trim().isEmpty()){
			return;
		}

		try {
			// Url that will be called to submit the message
			URL sendUrl = new URL(ServerGlobalResources.getInstance().getSmsUrl()+":" + ServerGlobalResources.getInstance().getSmsPort()+"/sendsms?");
			HttpURLConnection httpConnection = (HttpURLConnection) sendUrl
					.openConnection();
			// This method sets the method type to POST so that
			// will be send as a POST request
			httpConnection.setRequestMethod("POST");
			// This method is set as true wince we intend to send
			// input to the server
			httpConnection.setDoInput(true);
			// This method implies that we intend to receive data from server.
			httpConnection.setDoOutput(true);
			// Implies do not use cached data
			httpConnection.setUseCaches(false);
			// Data that will be sent over the stream to the server.
			DataOutputStream dataStreamToServer = new DataOutputStream(
					httpConnection.getOutputStream());
			dataStreamToServer.writeBytes("username="
					+ URLEncoder.encode(ServerGlobalResources.getInstance().getSmsUserName(), "UTF-8") + "&password="
					+ URLEncoder.encode(ServerGlobalResources.getInstance().getSmsPassword(), "UTF-8") + "&type="
					+ URLEncoder.encode("0", "UTF-8") + "&dlr="
					+ URLEncoder.encode("1", "UTF-8") + "&destination="
					+ URLEncoder.encode(msisdn, "UTF-8") + "&source="
					+ URLEncoder.encode(ServerGlobalResources.getInstance().getChurchModel() != null?ServerGlobalResources.getInstance().getChurchModel().getDisplay():"C Cradle", "UTF-8") + "&message="
					+ URLEncoder.encode(message, "UTF-8"));
			dataStreamToServer.flush();
			dataStreamToServer.close();
			// Here take the output value of the server.
			BufferedReader dataStreamFromUrl = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream()));
			String dataFromUrl = "", dataBuffer = "";
			// Writing information from the stream to the buffer
			while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
				dataFromUrl += dataBuffer;
			}
			/**
			 * Now dataFromUrl variable contains the Response received from the
			 * server so we can parse the response and process it accordingly.
			 */
			dataStreamFromUrl.close();
			System.out.println("Response: " + dataFromUrl);
			
			saveToSentSMS(ServerGlobalResources.getInstance().getChurchModel() != null?ServerGlobalResources.getInstance().getChurchModel().getId():0, getSuccessfulCount(dataFromUrl));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//1701|233504298056|1c19b7dd-eae9-4cd7-8c1d-8290eee495bb,1701|233244960321|4ea17266-6697-4a20-8d71-fc9e77437ff4

	private double getSuccessfulCount(String smsToken){
		final String SUCCESS = "1701";
		
		if(smsToken != null && smsToken.contains("|")){
			double counter = 0;
			//Split
			String[] tmpSMSTokens = smsToken.split(",");
			for(String tmpSMSToken : tmpSMSTokens){
				String[] smsSingles = tmpSMSToken.split("[|]");
				if(smsSingles != null && smsSingles.length == 3){

					String status = smsSingles[0];
					//String msisdn = smsSingles[1];
					//String token = smsSingles[2];
					
					if(status.equals(SUCCESS)){
						counter ++;
					}
				}
			}
			
			return counter;
		}

		return 0;
	}

	private void saveToSentSMS(int churchId, double smsCount){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			//con.setCachePreparedStatements(false);
			prstmt = (PreparedStatement) con.prepareStatement("insert into sent_sms (church_id,sms_count) values (?, ?)");
			prstmt.setInt(1, churchId);
			prstmt.setDouble(2, smsCount);

			int success = prstmt.executeUpdate();
			if(success >= 0){
				System.out.println("Logged SMS status successfully!");
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	
	private ChurchModel getChurchModel(int churchId){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,name,display,member_limit,sms_left,expire_date from churches where id = ? and status = 'A'");
			prstmt.setInt(1, churchId);

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					int id = results.getInt("id");
					String name = results.getString("name").trim();
					String display = results.getString("display").trim();
					String expireDate = results.getString("expire_date").trim();
					double smsLeft = results.getDouble("sms_left");
					double memberLimit = results.getDouble("member_limit");
					
					ChurchModel model = new ChurchModel();
					model.setId(id);
					model.setName(name);
					model.setDisplay(display);
					model.setSmsLeft(smsLeft);
					model.setMemberLimit(memberLimit);
					model.setExpireDate(expireDate);
					
					return model;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return null;
	}

}
