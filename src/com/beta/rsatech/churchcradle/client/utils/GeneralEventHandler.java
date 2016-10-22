package com.beta.rsatech.churchcradle.client.utils;

public interface GeneralEventHandler<T> {
	void onSuccess(T t);
	void onError();
}
