package com.beta.rsatech.churchcradle.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {
	private static Connection con;
	private static final String MYSQL_DRIVER = "jdbc:mysql://";
	private static DBConnection dbc = new DBConnection();
	private static int counter;

	private DBConnection(){
	}

	public static Connection getConnection(){
		if(counter == 0){
			dbc.establishConnection();
			counter ++;
		}
		
		try {
			if(con.isClosed()){
				dbc.establishConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	private void establishConnection(){
		
		
		String url = "";

		try {
			if (SystemProperty.environment.value() ==
					SystemProperty.Environment.Value.Production) {
				// Load the class that provides the new "jdbc:google:mysql://" prefix.
				Class.forName("com.mysql.jdbc.GoogleDriver");
				url = "jdbc:google:mysql://cradleapps:alpha-db/olivet_church_cradle_gl?user=root&autoReconnect=true";
//				Class.forName("com.mysql.jdbc.Driver");
			} else {
				// Local MySQL instance to use during development.
				Class.forName("com.mysql.jdbc.Driver");
				//url = "jdbc:mysql://173.194.250.73:3306/olivet_church_cradle_gl?user=root&autoReconnect=true";
				url = "jdbc:mysql://127.0.0.1:3306/church_cradle_web_google?user=root&autoReconnect=true";

			}
			con = (Connection)DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}