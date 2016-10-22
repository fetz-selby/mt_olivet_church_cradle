package com.beta.rsatech.churchcradle.server.utils.sms.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beta.rsatech.churchcradle.server.utils.sms.parser.Result;

public class SMSGatewayObject {

	private boolean success;
	private List<Result> result = new ArrayList<Result>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public SMSGatewayObject() {
	}

	/**
	 * 
	 * @param result
	 * @param success
	 */
	public SMSGatewayObject(boolean success, List<Result> result) {
		this.success = success;
		this.result = result;
	}

	/**
	 * 
	 * @return
	 * The success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 
	 * @param success
	 * The success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public SMSGatewayObject withSuccess(boolean success) {
		this.success = success;
		return this;
	}

	/**
	 * 
	 * @return
	 * The result
	 */
	public List<Result> getResult() {
		return result;
	}

	/**
	 * 
	 * @param result
	 * The result
	 */
	public void setResult(List<Result> result) {
		this.result = result;
	}

	public SMSGatewayObject withResult(List<Result> result) {
		this.result = result;
		return this;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public SMSGatewayObject withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
