package com.beta.rsatech.churchcradle.server.utils.currency.parser;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
	private String disclaimer;
	private String license;
	private Integer timestamp;
	private String base;
	private Rates rates;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	* 
	* @return
	* The disclaimer
	*/
	public String getDisclaimer() {
	return disclaimer;
	}

	/**
	* 
	* @param disclaimer
	* The disclaimer
	*/
	public void setDisclaimer(String disclaimer) {
	this.disclaimer = disclaimer;
	}

	/**
	* 
	* @return
	* The license
	*/
	public String getLicense() {
	return license;
	}

	/**
	* 
	* @param license
	* The license
	*/
	public void setLicense(String license) {
	this.license = license;
	}

	/**
	* 
	* @return
	* The timestamp
	*/
	public Integer getTimestamp() {
	return timestamp;
	}

	/**
	* 
	* @param timestamp
	* The timestamp
	*/
	public void setTimestamp(Integer timestamp) {
	this.timestamp = timestamp;
	}

	/**
	* 
	* @return
	* The base
	*/
	public String getBase() {
	return base;
	}

	/**
	* 
	* @param base
	* The base
	*/
	public void setBase(String base) {
	this.base = base;
	}

	/**
	* 
	* @return
	* The rates
	*/
	public Rates getRates() {
	return rates;
	}

	/**
	* 
	* @param rates
	* The rates
	*/
	public void setRates(Rates rates) {
	this.rates = rates;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

}
