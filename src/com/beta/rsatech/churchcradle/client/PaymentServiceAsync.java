package com.beta.rsatech.churchcradle.client;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.AppRenewModel;
import com.beta.rsatech.churchcradle.shared.OnlineDonationPaymentModel;
import com.beta.rsatech.churchcradle.shared.OnlineOfferingPaymentModel;
import com.beta.rsatech.churchcradle.shared.OnlineTithePaymentModel;
import com.beta.rsatech.churchcradle.shared.SMSPurchaseModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PaymentServiceAsync {

	void offeringPay(int churchId, int memberId, int amount, String month,
			String hostPageUrl, AsyncCallback<String> callback);

	void tithePay(int churchId, int memberId, int amount, String month,
			String hostPageUrl, AsyncCallback<String> callback);

	void donationPay(int churchId, int memberId, int amount, String message,
			AsyncCallback<String> callback);

	void specialOffering(int churchId, int memberId, int amount,
			String message, AsyncCallback<String> callback);

	void addTitheBySystem(int paymentId, String token, String externalToken,
			AsyncCallback<Boolean> callback);

	void addMyOfferingBySystem(int paymentId, String token,
			String externalToken, AsyncCallback<Boolean> callback);

	void getOnlineTithePayments(int churchId, String date,
			AsyncCallback<ArrayList<OnlineTithePaymentModel>> callback);

	void getOnlineOfferingPayments(int churchId, String date,
			AsyncCallback<ArrayList<OnlineOfferingPaymentModel>> callback);

	void annoymousPay(int churchId, int paymentId, int amount, String month,
			String hostPageUrl, AsyncCallback<String> callback);

	void addDonationBySystem(int paymentId, String token, String externalToken,
			AsyncCallback<Boolean> callback);

	void getOnlineDonationPayments(int churchId, String date,
			AsyncCallback<ArrayList<OnlineDonationPaymentModel>> callback);

	void buySMS(SMSPurchaseModel model, String hostPageUrl,
			AsyncCallback<String> callback);

	void updateSMSPayment(int paymentId, String token, String externalToken,
			AsyncCallback<Boolean> callback);

	void renewApp(AppRenewModel model, String hostPageUrl,
			AsyncCallback<String> callback);

	void updateAppRenewPayment(int paymentId, String token,
			String externalToken, AsyncCallback<Boolean> callback);

}
