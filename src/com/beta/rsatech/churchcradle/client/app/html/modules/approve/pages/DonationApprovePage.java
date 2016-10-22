package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.DonationModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class DonationApprovePage extends UIObject {

	private DonationApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static DonationApprovePageUiBinder uiBinder = GWT
			.create(DonationApprovePageUiBinder.class);

	interface DonationApprovePageUiBinder extends
			UiBinder<Element, DonationApprovePage> {
	}

	public interface DonationApprovePageEventHandler{
		void onDonationEditInvoked(DonationModel model);
	}
	
	@UiField UListElement ulListContainer;

	public DonationApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doDonationLoad(){
		GlobalResources.getInstance().getListRPC().getDonationsListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<DonationModel>>() {

			@Override
			public void onSuccess(ArrayList<DonationModel> result) {
				ulListContainer.setInnerText("");
				ulListContainer.removeAllChildren();
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(DonationModel model : result){
						Element li = getLi(model);
						ulListContainer.appendChild(li);
						liMap.put(model.getId(), li);
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private Element getLi(final DonationModel model){
		Element li = DOM.createElement("li");
		li.setClassName("list-group-item bg-primary list-height");

		Element spanRight = DOM.createElement("span");
		spanRight.setClassName("pull-right");

		Element editIconAnchor = DOM.createElement("a");
		editIconAnchor.setAttribute("href", "javascript:void(0)");

		Element editIcon = DOM.createElement("i");
		editIcon.setClassName("fa fa-pencil fa-fw m-r-xs");

		DOM.sinkEvents(editIconAnchor, Event.ONCLICK);
		DOM.setEventListener(editIconAnchor, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onDonationEditInvoked(model);
				}
			}
		});

		Element memberDiv = DOM.createElement("div");
		memberDiv.setClassName("col-sm-2 clear text-white");
		memberDiv.setInnerText(Utils.getMember(model.getMemberId()));
		
		Element descDiv = DOM.createElement("div");
		descDiv.setClassName("col-sm-4 clear text-white");
		descDiv.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.APPROVE_DONATION_DESCRIPTION_LIMIT));

		Element amountDiv = DOM.createElement("div");
		amountDiv.setClassName("col-sm-1 clear text-white");
		amountDiv.setInnerText(model.getAmount()+"");
		
		Element createdByDiv = DOM.createElement("div");
		createdByDiv.setClassName("col-sm-2 clear text-white");
		createdByDiv.setInnerText(Utils.getMember(model.getCreatedBy()));

		Element date = DOM.createElement("div");
		date.setClassName("col-sm-2 clear text-white");
		date.setInnerText(model.getCreatedTs());

		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);

		li.appendChild(spanRight);
		li.appendChild(memberDiv);
		li.appendChild(descDiv);
		li.appendChild(amountDiv);
		li.appendChild(createdByDiv);
		li.appendChild(date);

		return li;
	}
	
	public void load(){
		doDonationLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}

	public void setDonationApprovePageEventHandler(DonationApprovePageEventHandler handler){
		this.handler = handler;
	}

}
