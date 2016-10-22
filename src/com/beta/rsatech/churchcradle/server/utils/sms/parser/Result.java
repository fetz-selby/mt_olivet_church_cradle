package com.beta.rsatech.churchcradle.server.utils.sms.parser;

import java.util.HashMap;
import java.util.Map;

public class Result {

	private String id;
	private String deviceId;
	private String message;
	private String status;
	private int sendAt;
	private int queuedAt;
	private int sentAt;
	private int deliveredAt;
	private int expiresAt;
	private int canceledAt;
	private int failedAt;
	private long receivedAt;
	private String error;
	private int createdAt;
	private Contact contact;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Result() {
	}

	/**
	 * 
	 * @param sentAt
	 * @param receivedAt
	 * @param error
	 * @param expiresAt
	 * @param status
	 * @param failedAt
	 * @param contact
	 * @param queuedAt
	 * @param id
	 * @param message
	 * @param canceledAt
	 * @param createdAt
	 * @param deviceId
	 * @param sendAt
	 * @param deliveredAt
	 */
	public Result(String id, String deviceId, String message, String status, int sendAt, int queuedAt, int sentAt, int deliveredAt, int expiresAt, int canceledAt, int failedAt, long receivedAt, String error, int createdAt, Contact contact) {
		this.id = id;
		this.deviceId = deviceId;
		this.message = message;
		this.status = status;
		this.sendAt = sendAt;
		this.queuedAt = queuedAt;
		this.sentAt = sentAt;
		this.deliveredAt = deliveredAt;
		this.expiresAt = expiresAt;
		this.canceledAt = canceledAt;
		this.failedAt = failedAt;
		this.receivedAt = receivedAt;
		this.error = error;
		this.createdAt = createdAt;
		this.contact = contact;
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

	public Result withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * 
	 * @return
	 * The deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 
	 * @param deviceId
	 * The device_id
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Result withDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
	}

	/**
	 * 
	 * @return
	 * The message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 * The message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Result withMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * 
	 * @return
	 * The status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 * The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public Result withStatus(String status) {
		this.status = status;
		return this;
	}

	/**
	 * 
	 * @return
	 * The sendAt
	 */
	public int getSendAt() {
		return sendAt;
	}

	/**
	 * 
	 * @param sendAt
	 * The send_at
	 */
	public void setSendAt(int sendAt) {
		this.sendAt = sendAt;
	}

	public Result withSendAt(int sendAt) {
		this.sendAt = sendAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The queuedAt
	 */
	public int getQueuedAt() {
		return queuedAt;
	}

	/**
	 * 
	 * @param queuedAt
	 * The queued_at
	 */
	public void setQueuedAt(int queuedAt) {
		this.queuedAt = queuedAt;
	}

	public Result withQueuedAt(int queuedAt) {
		this.queuedAt = queuedAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The sentAt
	 */
	public int getSentAt() {
		return sentAt;
	}

	/**
	 * 
	 * @param sentAt
	 * The sent_at
	 */
	public void setSentAt(int sentAt) {
		this.sentAt = sentAt;
	}

	public Result withSentAt(int sentAt) {
		this.sentAt = sentAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The deliveredAt
	 */
	public int getDeliveredAt() {
		return deliveredAt;
	}

	/**
	 * 
	 * @param deliveredAt
	 * The delivered_at
	 */
	public void setDeliveredAt(int deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public Result withDeliveredAt(int deliveredAt) {
		this.deliveredAt = deliveredAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The expiresAt
	 */
	public int getExpiresAt() {
		return expiresAt;
	}

	/**
	 * 
	 * @param expiresAt
	 * The expires_at
	 */
	public void setExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Result withExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The canceledAt
	 */
	public int getCanceledAt() {
		return canceledAt;
	}

	/**
	 * 
	 * @param canceledAt
	 * The canceled_at
	 */
	public void setCanceledAt(int canceledAt) {
		this.canceledAt = canceledAt;
	}

	public Result withCanceledAt(int canceledAt) {
		this.canceledAt = canceledAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The failedAt
	 */
	public int getFailedAt() {
		return failedAt;
	}

	/**
	 * 
	 * @param failedAt
	 * The failed_at
	 */
	public void setFailedAt(int failedAt) {
		this.failedAt = failedAt;
	}

	public Result withFailedAt(int failedAt) {
		this.failedAt = failedAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The receivedAt
	 */
	public long getReceivedAt() {
		return receivedAt;
	}

	/**
	 * 
	 * @param receivedAt
	 * The received_at
	 */
	public void setReceivedAt(long receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Result withReceivedAt(long receivedAt) {
		this.receivedAt = receivedAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The error
	 */
	public String getError() {
		return error;
	}

	/**
	 * 
	 * @param error
	 * The error
	 */
	public void setError(String error) {
		this.error = error;
	}

	public Result withError(String error) {
		this.error = error;
		return this;
	}

	/**
	 * 
	 * @return
	 * The createdAt
	 */
	public int getCreatedAt() {
		return createdAt;
	}

	/**
	 * 
	 * @param createdAt
	 * The created_at
	 */
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	public Result withCreatedAt(int createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * 
	 * @return
	 * The contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * 
	 * @param contact
	 * The contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Result withContact(Contact contact) {
		this.contact = contact;
		return this;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Result withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
