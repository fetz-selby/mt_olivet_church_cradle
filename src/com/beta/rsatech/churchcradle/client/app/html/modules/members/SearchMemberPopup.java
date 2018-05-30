package com.beta.rsatech.churchcradle.client.app.html.modules.members;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchMemberPopup extends CustomDraggablePopupPanel {

	private SearchMemberPopupEventHandler handler;
	private ArrayList<MemberModel> modelList;
	private static SearchMemberPopupUiBinder uiBinder = GWT
			.create(SearchMemberPopupUiBinder.class);

	public interface SearchMemberPopupEventHandler{
		void onMemberSelect(MemberModel model);
	}

	interface SearchMemberPopupUiBinder extends UiBinder<Widget, SearchMemberPopup> {
	}

	@UiField SimplePanel wizardPanel;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	@UiField AnchorElement closeAnchor;
	@UiField SpanElement titleSpan;
	@UiField ButtonElement closeBtn;

	public SearchMemberPopup(ArrayList<MemberModel> modelList){
		add(uiBinder.createAndBindUi(this));
		this.modelList = modelList;
		initEvent();
	}

	private void initComponent(){
		if(modelList != null){
			for(MemberModel model : modelList){
				wizardPanel.getElement().appendChild(getListItem(model));
				wizardPanel.getElement().appendChild(getClearfix());
			}
		}
		
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		setGlassStyleName("glassPanel");
		setDraggable(true);
		center();
	}

	private void initEvent(){
		DOM.sinkEvents(closeAnchor, Event.ONCLICK);
		DOM.setEventListener(closeAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				hide();
			}
		});
	}
	
	private Element getClearfix(){
		Element divAnchor = DOM.createElement("div");
		divAnchor.setAttribute("style", "height:0px;clear:both;");
		
		return divAnchor;
	}
	
	private Element getListItem(final MemberModel model){
		Element divAnchor = DOM.createElement("div");
		divAnchor.setClassName("col-md-12 search-style");
		
		divAnchor.setInnerText(model.getId()+" - "+model.getFirstName()+"  "+model.getLastName()+" - "+model.getMsisdn());
		DOM.sinkEvents(divAnchor, Event.ONCLICK);
		DOM.setEventListener(divAnchor, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				if(handler != null){
					handler.onMemberSelect(model);
				}
			}
		});
		
		
		return divAnchor;
	}
	
	public void setSearchMemberPopupEventHandler(SearchMemberPopupEventHandler handler){
		this.handler = handler;
	}
	
	public void load(){
		initComponent();
	}

}
