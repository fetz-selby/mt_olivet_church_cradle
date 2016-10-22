package com.beta.rsatech.churchcradle.client.app.html.forms.popup;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.shared.PowerLeaderModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;

public class AddPowerLeaderFormPopup extends CustomDraggablePopupPanel {

	private String leader;
	private int leaderId;

	private AddGroupFormPopupEventHandler handler;
	private static AddGroupFormPopupUiBinder uiBinder = GWT
			.create(AddGroupFormPopupUiBinder.class);

	interface AddGroupFormPopupUiBinder extends
	UiBinder<Widget, AddPowerLeaderFormPopup> {
	}

	public interface AddGroupFormPopupEventHandler{
		void onSave();
	}

	@UiField SimplePanel searchContainer;
	@UiField Button saveBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;

	public AddPowerLeaderFormPopup() {
		add(uiBinder.createAndBindUi(this));
		initComponents();
		initSearchBox();
		initEvents();
		initPopup();
	}

	private void initComponents(){
	}

	private void initEvents(){
		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addGroup();
			}
		});
	}

	private void initPopup(){
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		setGlassStyleName("glassPanel");
		setDraggable(true);
		center();
	}

	private void showError(boolean isShow, String message){
		if(isShow){
			errorDiv.removeClassName("hide");
			titleErrorMessage.setInnerText(message);
		}else{
			errorDiv.addClassName("hide");
		}

	}

	private void showSuccess(){
		errorDiv.setClassName("alert alert-success");
		titleErrorMessage.setInnerText("Power Leader added successfully");
	}
	
	private void showLoading(boolean isShow){
		if(isShow){
			errorDiv.setClassName("alert alert-success");
			titleErrorMessage.setInnerText("Loading ...");
			saveBtn.setEnabled(false);
		}else{
			errorDiv.setClassName("hide");
			titleErrorMessage.setInnerText("Loading ...");
			saveBtn.setEnabled(true);
		}
	}
	

	private void showSuccessAndHide(){
		showSuccess();
		Timer timer = new Timer() {

			@Override
			public void run() {
				//GlobalResources.getInstance().getEventBus().fireEvent(new ClassesEvent());
				if(handler != null){
					handler.onSave();
				}
				hide();
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}
	
	private void showAndHide(String message){
		showError(true, message);
		Timer timer = new Timer() {

			@Override
			public void run() {
				hide();
			}
		};

		//Two seconds
		timer.schedule(2*1000);
	}

	private void addGroup(){
		if(leaderId == 0){
			//Display leader does not exist / can't be empty message
			showError(true, "Member does not exist or can not be empty.");
			return;
		}

		if(isLeaderExist(leaderId)){
			showError(true, "Power Leader already exist.");
			return;
		}

		showError(false, "");

		PowerLeaderModel model = new PowerLeaderModel();
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
		model.setMemberId(leaderId);

		showLoading(true);
		
		GlobalResources.getInstance().getAddRPC().addPowerLeader(model, GlobalResources.getInstance().getModel().getId(), new AsyncCallback<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				if(result > 0){
					showSuccessAndHide();
				}else{
					//Display error message
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				showAndHide("Bad network connection. Please try again later.");
			}
		});
	}

	private boolean isLeaderExist(int leaderId){
		ArrayList<Integer> powerLeaderList = GlobalResources.getInstance().getPowerLeadersList();
		if(powerLeaderList.contains(leaderId)){
			return true;
		}else{
			return false;
		}
	}

	private void initSearchBox(){
		final HashMap<Integer, String> membersMap = GlobalResources.getInstance().getMembersMap();
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for(Integer key : membersMap.keySet()){
			oracle.add(membersMap.get(key));
		}

		SuggestBox leaderSearch = new SuggestBox(oracle);
		leaderSearch.setStyleName("form-control");
		leaderSearch.getElement().setAttribute("placeholder", "Enter Leader Name");
		leaderSearch.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				leader = event.getSelectedItem().getReplacementString();
				leaderId = fetchId(leader, membersMap);
			}
		});

		searchContainer.setWidget(leaderSearch);
	}

	private int fetchId(String leader, HashMap<Integer, String> membersMap){
		if(membersMap != null){
			for(Integer id : membersMap.keySet()){
				String tmpLeader = membersMap.get(id);
				if(tmpLeader.equals(leader)){
					return id;
				}
			}
		}
		return 0;
	}

	public void setAddGroupFormPopupEventHandler(AddGroupFormPopupEventHandler handler){
		this.handler = handler;
	}
}
