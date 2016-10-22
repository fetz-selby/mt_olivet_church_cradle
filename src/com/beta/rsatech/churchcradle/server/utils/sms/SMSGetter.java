package com.beta.rsatech.churchcradle.server.utils.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.beta.rsatech.churchcradle.server.utils.sms.parser.Result;
import com.beta.rsatech.churchcradle.server.utils.sms.parser.SMSGatewayModel;
import com.beta.rsatech.churchcradle.server.utils.sms.parser.SMSGatewayObject;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.google.gson.Gson;


public class SMSGetter {
	private ArrayList<Integer> pastMessageIds = null;
	private DBInboundUtils dbService = null;

	public void readSMS(){
		
		if(dbService == null || pastMessageIds == null){
			dbService = DBInboundUtils.getInstance();
			//Grab past messages ids
			pastMessageIds = dbService.getMessageIdList();
		}
		
		try{
			URL url = new URL(AppConstants.GET_URL+"password="+AppConstants.PASSWORD+"&email="+AppConstants.EMAIL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("X-Custom-Header", "xxx");
			connection.setRequestProperty("Content-Type", "application/json");

			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// OK
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				StringBuffer res = new StringBuffer(); 
				String line;
				
				while((line = reader.readLine()) != null) {
					res.append(line);
				}
				
				reader.close();
				//System.out.println("[json] => "+res.toString());

				SMSGatewayObject sms = new Gson().fromJson(res.toString(), SMSGatewayObject.class);

				doMessageProcessing(sms, pastMessageIds, dbService);


			} else {
				// Server returned HTTP error code.
				System.out.println("Error response");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFormattedDate(long dateInt, String format){
		Date dNow = new Date(dateInt);

		SimpleDateFormat ft = new SimpleDateFormat(format);

		return ft.format(dNow);
	}

	private SMSGatewayModel getPreparedModel(Result result){
		int messageId = Integer.parseInt(result.getId());
		String message = result.getMessage();
		String from = result.getContact().getNumber();
		String date = getFormattedDate(result.getReceivedAt(), "yyyy-MM-dd hh:mm:ss");

		SMSGatewayModel model = new SMSGatewayModel(messageId, message, from, date);
		return model;
	}

	private void doMessageProcessing(SMSGatewayObject smsGateway, ArrayList<Integer> pastMessageIds, DBInboundUtils dbService){

		for(Result result : smsGateway.getResult()){
			if(result.getStatus().equalsIgnoreCase(AppConstants.RECEIVED) || result.getStatus().equalsIgnoreCase(AppConstants.QUEUED)){
				int messageId = Integer.parseInt(result.getId());
				if(!pastMessageIds.contains(messageId)){
					dbService.saveGatewaySMS(getPreparedModel(result));
					pastMessageIds.add(messageId);
				}
			}
		}
	}

}
