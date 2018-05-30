package com.beta.rsatech.churchcradle.client.app.html.modules.members;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.app.html.modules.members.SearchMemberPopup.SearchMemberPopupEventHandler;
import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.events.SendMessageEvent;
import com.beta.rsatech.churchcradle.client.events.UpdateMemberEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.UIObject;

public class ProfileLevel extends UIObject {

	private MemberModel model;
	private static ProfileLevelUiBinder uiBinder = GWT
			.create(ProfileLevelUiBinder.class);

	interface ProfileLevelUiBinder extends UiBinder<Element, ProfileLevel> {
	}

	@UiField DivElement msisdnDiv, employerDiv, addressDiv, classLeaderDiv, orgDiv, yoc, extraInfo, searchDiv;
	@UiField HeadingElement h3name, idfield;
	@UiField ParagraphElement emailParagraph;
	@UiField StrongElement occupationStrong;
	@UiField AnchorElement sendSMSAnchor, profileEdit, searchBtn;
	@UiField UListElement showMessageUL;
	@UiField ImageElement avatar;
	@UiField InputElement searchField;

	public ProfileLevel() {
		setElement(uiBinder.createAndBindUi(this));
	}

	private void initComponents(MemberModel model){
//		DOM.sinkEvents(searchBtn, Event.ONCLICK);
//		DOM.setEventListener(searchBtn, new EventListener() {
//
//			@Override
//			public void onBrowserEvent(Event event) {
//				//Grab email or mobile from fields
//				doSearchExec();
//			}
//		});
		
		DOM.sinkEvents(searchDiv, Event.ONCLICK);
		DOM.setEventListener(searchDiv, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				//Grab email or mobile from fields
				doSearchExec();
			}
		});

		if(GlobalResources.getInstance().getModel().isSMSEnabled()){
			showMessageUL.removeClassName("hide");
		}

		if(GlobalResources.getInstance().getModel().isAdmin()){
			profileEdit.removeClassName("hide");
			DOM.sinkEvents(profileEdit, Event.ONCLICK);
			DOM.setEventListener(profileEdit, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					GlobalResources.getInstance().getEventBus().fireEvent(new UpdateMemberEvent(ProfileLevel.this.model));
				}
			});
		}

		msisdnDiv.setInnerText(model.getMsisdn());
		employerDiv.setInnerText(model.getEmployer().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getEmployer());
		addressDiv.setInnerText(model.getAddress());
		classLeaderDiv.setInnerText(Utils.getClassLeader(model.getClassId()));
		orgDiv.setInnerText(Utils.getOrganisations(",", model.getOrganisations()).trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:Utils.getOrganisations(",", model.getOrganisations()));
		h3name.setInnerText(model.getFirstName()+" "+model.getLastName());
		idfield.setInnerText(model.getId()+"");
		emailParagraph.setInnerText(model.getEmail().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getEmail());
		occupationStrong.setInnerText(model.getOccupation().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getOccupation());
		yoc.setInnerText(model.getDateOfCommencement());
		extraInfo.setInnerText(model.getOtherInfo().trim().isEmpty()?AppConstants.PROFILE_EMPTY_PLACEHOLDER:model.getOtherInfo());
		searchField.setPropertyString("value", model.getId()+"");

		if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
			avatar.setSrc(AppConstants.PHOTO_URL+model.getAvatar());
		}else{
			avatar.setSrc(AppConstants.NO_IMAGE);
		}
	}

	private void doSearchExec(){
		String searchValue = searchField.getPropertyString("value");
		if(Utils.isEmailValidFormat(searchValue)){
			GlobalResources.getInstance().getListRPC().getMemberWithEmail(searchValue, GlobalResources.getInstance().getModel().getChurchId(),  new AsyncCallback<MemberModel>() {

				@Override
				public void onSuccess(MemberModel result) {
					if(result != null){
						initComponents(result);
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		}else if(Utils.isMsisdnValidFormat(searchValue)){
			GlobalResources.getInstance().getListRPC().getMemberWithMsisdn(searchValue, GlobalResources.getInstance().getModel().getChurchId(),  new AsyncCallback<MemberModel>() {

				@Override
				public void onSuccess(MemberModel result) {
					if(result != null){
						initComponents(result);
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		}else if(Utils.isNumber(searchValue)){
				GlobalResources.getInstance().getListRPC().getMemberWithId(Integer.parseInt(searchValue), GlobalResources.getInstance().getModel().getChurchId(),  new AsyncCallback<MemberModel>() {

					@Override
					public void onSuccess(MemberModel result) {
						if(result != null){
							initComponents(result);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}
				});
		}else if(Utils.isName(searchValue)){
			GlobalResources.getInstance().getListRPC().getMemberWithName(searchValue, GlobalResources.getInstance().getModel().getChurchId(),  new AsyncCallback<ArrayList<MemberModel>>() {

				@Override
				public void onSuccess(ArrayList<MemberModel> result) {
					if(result != null){
						if(result.size() > 1){
							//Show popup with names
							final SearchMemberPopup searchPopup = new SearchMemberPopup(result);
							searchPopup.setSearchMemberPopupEventHandler(new SearchMemberPopupEventHandler() {
								
								@Override
								public void onMemberSelect(MemberModel model) {
									initComponents(model);
									searchPopup.hide();
								}
							});
							searchPopup.load();
							
						}else{
							initComponents(result.get(0));
						}
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		}
	}
	
	private void initEvents(){
		Element a = sendSMSAnchor.cast();
		DOM.sinkEvents(a, Event.ONCLICK);
		DOM.setEventListener(a, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				GlobalResources.getInstance().getEventBus().fireEvent(new SendMessageEvent(model.getMsisdn()));
			}
		});
	}

	public void load(MemberModel model){
		this.model = model;
		initComponents(model);
		initEvents();
	}

	public MemberModel getModel(){
		return model;
	}

}
