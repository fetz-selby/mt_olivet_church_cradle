package com.beta.rsatech.churchcradle.server.utils;

import com.beta.rsatech.churchcradle.shared.ThirdPartyPaymentModel;
import com.mpowerpayments.mpower.MPowerCheckoutInvoice;
import com.mpowerpayments.mpower.MPowerCheckoutStore;
import com.mpowerpayments.mpower.MPowerSetup;

public class PaymentFactory {
	private static PaymentFactory factory = new PaymentFactory();

	private PaymentFactory(){}

	public static PaymentFactory getInstance(){
		return factory;
	}

	private MPowerSetup getInit(ThirdPartyPaymentModel model, boolean isTest){
		MPowerSetup setup = new MPowerSetup();

		if(!isTest){
			setup.setMasterKey(model.getMasterKey());
			setup.setPrivateKey(model.getPrivateKey());
			setup.setPublicKey(model.getPublicKey());
			setup.setToken(model.getToken()); 
			setup.setMode("live");
		}else{
			setup.setMasterKey("f0ee61c1-1639-4431-bc57-4b8c4a682291");
			setup.setPrivateKey("test_private_C5W_RqNpNFTq-_rKIICvKbTcJGI");
			setup.setPublicKey("test_public_SeIG2w79-I23d03ya7yJMtLbar8");
			setup.setToken("835788a3d53394b94426"); 
			setup.setMode("test");
		}

		return setup;
	}

	private MPowerCheckoutStore getCheckoutStore(ThirdPartyPaymentModel model, String returnUrl, String cancelUrl){
		MPowerCheckoutStore store = new MPowerCheckoutStore();
		store.setName(model.getName());
		store.setTagline(model.getTagLine());
		store.setPhoneNumber(model.getMsisdn());
		store.setPostalAddress(model.getAddress());
		store.setWebsiteUrl(model.getWebsite());

		if(returnUrl != null){
			store.setReturnUrl(returnUrl);
		}

		if(cancelUrl != null){
			store.setCancelUrl(cancelUrl);
		}

		return store;
	}

	public MPowerCheckoutInvoice getCheckoutInvoice(ThirdPartyPaymentModel model, String returnUrl, String cancelUrl, boolean isTest){
		MPowerCheckoutInvoice invoice = new MPowerCheckoutInvoice (getInit(model, isTest), getCheckoutStore(model, returnUrl, cancelUrl));

		return invoice;
	}

}
