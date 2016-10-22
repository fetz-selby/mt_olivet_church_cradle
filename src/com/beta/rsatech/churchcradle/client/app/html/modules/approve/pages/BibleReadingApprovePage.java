package com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
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

public class BibleReadingApprovePage extends UIObject {

	private BibleReadingApprovePageEventHandler handler;
	private HashMap<Integer, Element> liMap;
	private static BibleReadingApprovePageUiBinder uiBinder = GWT
			.create(BibleReadingApprovePageUiBinder.class);

	interface BibleReadingApprovePageUiBinder extends
			UiBinder<Element, BibleReadingApprovePage> {
	}

	public interface BibleReadingApprovePageEventHandler{
		void onBibleReadingEditInvoked(BibleReadingModel model);
	}
	
	@UiField UListElement ulListContainer;

	public BibleReadingApprovePage() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void doBibleReadingLoad(){
		GlobalResources.getInstance().getListRPC().getBibleReadingsListWithStatusFilter(GlobalResources.getInstance().getModel().getChurchId(), AppConstants.PENDING, new AsyncCallback<ArrayList<BibleReadingModel>>() {

			@Override
			public void onSuccess(ArrayList<BibleReadingModel> result) {
				ulListContainer.setInnerText("");
				ulListContainer.removeAllChildren();
				if(result != null){
					liMap = new HashMap<Integer, Element>();
					for(BibleReadingModel model : result){
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
	
	private Element getLi(final BibleReadingModel model){
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
					handler.onBibleReadingEditInvoked(model);
				}
			}
		});
		
		Element readerDiv = DOM.createElement("div");
		readerDiv.setClassName("col-sm-3 clear text-white");
		readerDiv.setInnerText(model.getName());
		
		Element descDiv = DOM.createElement("div");
		descDiv.setClassName("col-sm-2 clear text-white");
		descDiv.setInnerText(Utils.getTruncatedText(model.getDescription(), AppConstants.APPROVE_BR_VERSE_LIMIT));
		
		Element themeDiv = DOM.createElement("div");
		themeDiv.setClassName("col-sm-2 clear text-white");
		themeDiv.setInnerText(Utils.getTruncatedText(model.getTheme(), AppConstants.APPROVE_BR_THEME_LIMIT));

		Element createdByDiv = DOM.createElement("div");
		createdByDiv.setClassName("col-sm-2 clear text-white");
		createdByDiv.setInnerText(Utils.getMember(model.getCreatedBy()));

		Element date = DOM.createElement("div");
		date.setClassName("col-sm-2 clear text-white");
		date.setInnerText(model.getCreatedTs());

		editIconAnchor.appendChild(editIcon);
		spanRight.appendChild(editIconAnchor);

		li.appendChild(spanRight);
		
		li.appendChild(readerDiv);
		li.appendChild(descDiv);
		li.appendChild(themeDiv);
		li.appendChild(createdByDiv);
		li.appendChild(date);

		return li;
	}
	
	public void load(){
		doBibleReadingLoad();
	}
	
	public void removeList(int listId){
		if(liMap.containsKey(listId)){
			liMap.get(listId).removeFromParent();
		}
	}

	public void setBibleReadingApprovePageEventHandler(BibleReadingApprovePageEventHandler handler){
		this.handler = handler;
	}

}
