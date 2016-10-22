package com.beta.rsatech.churchcradle.client.app.html.composites.sidebar;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.BlobstoreModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.UIObject;

public class SideBarHTMLComposite extends UIObject {

	private static SideBarHTMLUiBinder uiBinder = GWT
			.create(SideBarHTMLUiBinder.class);

	interface SideBarHTMLUiBinder extends UiBinder<Element, SideBarHTMLComposite> {
	}

	@UiField LIElement smsLi, approveLi, userOptions, membersLi, organisationLi, classesLi, paymentsLi, myOfferingLi, titheLi, donationLi, pLeadersLi, appStats;
	@UiField StrongElement userNameStrong;
	@UiField ImageElement avatar;

	public SideBarHTMLComposite() {
		setElement(uiBinder.createAndBindUi(this));
		initElement();
	}

	private void initElement(){
		if(GlobalResources.getInstance().getModel() != null && GlobalResources.getInstance().getModel().isSMSEnabled()){
			loadSMS();
		}

		if(GlobalResources.getInstance().getModel() != null && GlobalResources.getInstance().getModel().isAdmin()){
			loadMembers();
			loadPowerLeaders();
			loadAppStats();
		}
		
		if(GlobalResources.getInstance().getModel() != null && GlobalResources.getInstance().getModel().isCanViewPayments()){
			loadPayments();
		}

		if(GlobalResources.getInstance().getModel() != null && !GlobalResources.getInstance().getModel().getApproveModules().trim().isEmpty()){
			loadApprove();
		}

		
		if(GlobalResources.getInstance().getModel().getAvatar() != null && !GlobalResources.getInstance().getModel().getAvatar().trim().isEmpty()){
			Utils.retrieveFromBlobstore(GlobalResources.getInstance().getModel().getAvatar(), new GeneralEventHandler<BlobstoreModel>() {
				
				@Override
				public void onSuccess(BlobstoreModel t) {
					avatar.setSrc(t.getUrl());
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		
		userNameStrong.setInnerText(GlobalResources.getInstance().getModel().getFirstName());
	}

	private Element getSMSElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#sms");

		Element i = DOM.createElement("i");
		i.setClassName("i i-mail icon");

		Element span = DOM.createElement("span");
		span.setClassName("font-bold");
		span.setInnerText("SMS");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}
	
	private Element getAppStatsElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#app_stats");

		Element i = DOM.createElement("i");
		i.setClassName("i i-statistics icon");

		Element span = DOM.createElement("span");
		span.setClassName("font-bold");
		span.setInnerText("App Statistics");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}

	private Element getApproveElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#approve");

		Element i = DOM.createElement("i");
		i.setClassName("fa fa-check");

		Element span = DOM.createElement("span");
		span.setClassName("font-bold");
		span.setInnerText("Approve");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}

	private Element getOrganisationElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#members");

		Element i = DOM.createElement("i");
		i.setClassName("i i-dot");

		Element span = DOM.createElement("span");
		span.setInnerText("Organisations");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}

	private Element getClassesElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#classes");

		Element i = DOM.createElement("i");
		i.setClassName("i i-dot");

		Element span = DOM.createElement("span");
		span.setInnerText("Classes");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}
	
	private Element getTitheElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#p_tithes");

		Element i = DOM.createElement("i");
		i.setClassName("i i-dot");

		Element span = DOM.createElement("span");
		span.setInnerText("Tithes");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}
	
	private Element getMyOfferingElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#p_offerings");

		Element i = DOM.createElement("i");
		i.setClassName("i i-dot");

		Element span = DOM.createElement("span");
		span.setInnerText("Offerings");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}
	
	private Element getDonationElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#p_donations");

		Element i = DOM.createElement("i");
		i.setClassName("i i-dot");

		Element span = DOM.createElement("span");
		span.setInnerText("Donations");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}
	
	private Element getPowerLeadersElement(){

		Element a = DOM.createElement("a");
		a.setClassName("auto");
		a.setAttribute("href", "#p_leaders");

		Element i = DOM.createElement("i");
		i.setClassName("fa fa-gavel");

		Element span = DOM.createElement("span");
		span.setClassName("font-bold");
		span.setInnerText("Power Leaders");

		a.appendChild(i);
		a.appendChild(span);

		return a;
	}
	
	private void loadPowerLeaders(){
		pLeadersLi.appendChild(getPowerLeadersElement());
	}
	
	private void loadSMS(){
		if(GlobalResources.getInstance().getChurchModel().isValid()){
			smsLi.appendChild(getSMSElement());
		}
	}

	private void loadApprove(){
		approveLi.appendChild(getApproveElement());
	}
	
	private void loadPayments(){
		paymentsLi.removeClassName("hide");

		titheLi.appendChild(getTitheElement());
		myOfferingLi.appendChild(getMyOfferingElement());
		donationLi.appendChild(getDonationElement());
	}

	private void loadMembers(){
		membersLi.removeClassName("hide");

		organisationLi.appendChild(getOrganisationElement());
		classesLi.appendChild(getClassesElement());
	}
	
	private void loadAppStats(){
		appStats.removeClassName("hide");
		appStats.appendChild(getAppStatsElement());
	}

}
