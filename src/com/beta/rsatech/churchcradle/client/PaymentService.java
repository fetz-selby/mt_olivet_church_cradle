package com.beta.rsatech.churchcradle.client;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.shared.AppRenewModel;
import com.beta.rsatech.churchcradle.shared.OnlineDonationPaymentModel;
import com.beta.rsatech.churchcradle.shared.OnlineOfferingPaymentModel;
import com.beta.rsatech.churchcradle.shared.OnlineTithePaymentModel;
import com.beta.rsatech.churchcradle.shared.SMSPurchaseModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("pay")

public interface PaymentService extends RemoteService{
	String offeringPay(int churchId, int memberId, int amount, String month, String hostPageUrl);
	String tithePay(int churchId, int memberId, int amount, String month, String hostPageUrl);
	String donationPay(int churchId, int memberId, int amount, String message);
	String specialOffering(int churchId, int memberId, int amount, String message);
	String annoymousPay(int churchId, int paymentId, int amount, String month, String hostPageUrl);
	boolean addTitheBySystem(int paymentId, String token, String externalToken);
	boolean addMyOfferingBySystem(int paymentId, String token, String externalToken);
	boolean addDonationBySystem(int paymentId, String token, String externalToken);
	
	ArrayList<OnlineTithePaymentModel> getOnlineTithePayments(int churchId, String date);
	ArrayList<OnlineOfferingPaymentModel> getOnlineOfferingPayments(int churchId, String date);
	ArrayList<OnlineDonationPaymentModel> getOnlineDonationPayments(int churchId, String date);

	String buySMS(SMSPurchaseModel model, String hostPageUrl);
	boolean updateSMSPayment(int paymentId, String token, String externalToken);
	
	String renewApp(AppRenewModel model, String hostPageUrl);
	boolean updateAppRenewPayment(int paymentId, String token, String externalToken);
	
}
