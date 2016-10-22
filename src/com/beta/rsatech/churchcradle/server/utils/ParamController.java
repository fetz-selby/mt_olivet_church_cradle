package com.beta.rsatech.churchcradle.server.utils;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@SuppressWarnings("serial")
public class ParamController extends GenericServlet{
	private String smsUserName, smsPassword, smsPort, smsUrl, dbName, dbUser, dbPassword, dbPort, dbIP;
	
	public void init(ServletConfig servletConfig) throws ServletException{
	    this.dbName = servletConfig.getInitParameter("dbname");
	    this.dbPassword = servletConfig.getInitParameter("dbpassword");
	    this.dbUser = servletConfig.getInitParameter("dbuser");
	    this.dbPort = servletConfig.getInitParameter("dbport");
	    this.dbIP = servletConfig.getInitParameter("dbip");
	    this.smsUserName = servletConfig.getInitParameter("sms_username");
	    this.smsPassword = servletConfig.getInitParameter("sms_password");
	    this.smsPort = servletConfig.getInitParameter("sms_port");
	    this.smsUrl = servletConfig.getInitParameter("sms_url");

	    System.out.println("DBName => "+dbName);
	    System.out.println("DBPassword => "+dbPassword);
	    System.out.println("DBUser => "+dbUser);
	    System.out.println("DBPort => "+dbPort);
	    System.out.println("DBIP => "+dbIP);
	    System.out.println("SMSUsername => "+smsUserName);
	    System.out.println("SMSPassword => "+smsPassword);
	    System.out.println("SMSPort => "+smsPort);
	    System.out.println("SMSUrl => "+smsUrl);

	    if(dbUser == null){
	    	dbUser = "";
	    } 
	    
	    if(dbPassword == null){
	    	dbPassword = "";
	    }
	    
	    if(dbPort == null){
	    	dbPort = "3306";
	    }
	    
	    if(dbIP == null){
	    	dbIP = "127.0.0.1";
	    }

	    if(smsPort == null){
	    	smsPort = "8080";
	    }
	  
	    ServerGlobalResources.getInstance().setDbName(dbName);
	    ServerGlobalResources.getInstance().setDbPassword(dbPassword);
	    ServerGlobalResources.getInstance().setDbUserName(dbUser);
	    ServerGlobalResources.getInstance().setDbPort(dbPort);
	    ServerGlobalResources.getInstance().setDbIp(dbIP);
	    ServerGlobalResources.getInstance().setSmsPassword(smsPassword);
	    ServerGlobalResources.getInstance().setSmsPort(smsPort);
	    ServerGlobalResources.getInstance().setSmsUrl(smsUrl);
	    ServerGlobalResources.getInstance().setSmsUserName(smsUserName);

	  }
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
