package com.beta.rsatech.churchcradle.server.utils.sms.parser;


import java.util.HashMap;
import java.util.Map;

public class Contact {

	private String id;
	private String name;
	private String number;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Contact() {
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param number
	 */
	public Contact(String id, String name, String number) {
		this.id = id;
		this.name = name;
		this.number = number;
	}

	/**
	 * 
	 * @return
	 * The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 * The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public Contact withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * 
	 * @return
	 * The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 * The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Contact withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 
	 * @return
	 * The number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * 
	 * @param number
	 * The number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	public Contact withNumber(String number) {
		this.number = number;
		return this;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Contact withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
