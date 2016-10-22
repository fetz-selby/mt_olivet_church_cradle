package com.beta.rsatech.churchcradle.server.utils.currency.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beta.rsatech.churchcradle.server.utils.DBConnection;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.google.gson.Gson;

public class CurrencyKeeper {
	private static Connection con = DBConnection.getConnection();

	private double dollarRate;
	
	public CurrencyKeeper(){
		init();
	}
	
	private boolean isCurrencyUpdated(){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("select id,cedis from dollar_rates where date(created_ts) = date(curdate()) and status = ? order by id desc limit 1");
			prstmt.setString(1, "A");

			ResultSet results = prstmt.executeQuery();
			if(results != null){
				while(results.next()){
					dollarRate = results.getDouble("cedis");
					return true;
				}
			}
			//con.close();
			return false;
		}catch(SQLException sql){
			sql.printStackTrace();
		}

		return false;
	}
	
	private void init(){
		if(!isCurrencyUpdated() && dollarRate == 0){
			System.out.println("[CurrencyKeeper] Got Inside!");
			try{
				URL url = new URL(AppConstants.CURRENCY_REQUEST_URL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				//connection.setRequestProperty("X-Custom-Header", "xxx");
				connection.setRequestProperty("Content-Type", "application/json");

				if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
					StringBuffer res = new StringBuffer(); 
					String line;
					
					while((line = reader.readLine()) != null) {
						res.append(line);
					}
					
					reader.close();

					CurrencyConverter currency = new Gson().fromJson(res.toString(), CurrencyConverter.class);
					System.out.println("dollar rate => "+currency.getRates().getGHS());
					dollarRate = currency.getRates().getGHS();
					updateCurrency(dollarRate);
					
				} else {
					// Server returned HTTP error code.
					System.out.println("Error response");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateCurrency(double dollarRate){
		PreparedStatement prstmt = null;
		con = DBConnection.getConnection();

		try{
			prstmt = (PreparedStatement) con.prepareStatement("insert into dollar_rates (cedis) values (?) ");
			prstmt.setDouble(1, dollarRate);
			
			int success = prstmt.executeUpdate();
			if(success > 0){
				//con.close();
			}

		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	public double getDollarRate() {
		return dollarRate;
	}	
	
}
