package com.beta.rsatech.churchcradle.client.app.html.forms.popup;

import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.elements.StrongElement;
import com.beta.rsatech.churchcradle.client.events.MembersEvent;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.shared.GroupModel;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddGroupFormPopup extends CustomDraggablePopupPanel {

	private String leader;
	private int leaderId;
	
	private AddGroupFormPopupEventHandler handler;
	private static AddGroupFormPopupUiBinder uiBinder = GWT
			.create(AddGroupFormPopupUiBinder.class);

	interface AddGroupFormPopupUiBinder extends
			UiBinder<Widget, AddGroupFormPopup> {
	}
	
	public interface AddGroupFormPopupEventHandler{
		void onSave();
	}

	@UiField TextBox groupName;
	@UiField SimplePanel searchContainer;
	@UiField Button saveBtn;
	@UiField StrongElement titleErrorMessage;
	@UiField DivElement errorDiv;
	
	public AddGroupFormPopup() {
		add(uiBinder.createAndBindUi(this));
		initComponents();
		initSearchBox();
		initEvents();
		initPopup();
	}
	
	private void initComponents(){
		groupName.getElement().setAttribute("placeholder", "Enter Group Name");
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
		titleErrorMessage.setInnerText("Group added successfully");
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
				GlobalResources.getInstance().getEventBus().fireEvent(new MembersEvent());
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
		if(groupName.getText().trim().isEmpty()){
			//Display no group name entered message
			showError(true, "Sorry, Group name can not be empty.");
			return;
		}else if(groupName.getText().length() <= 2){
			//Display group name too short message
			showError(true, "Group name is too short, should be three(3) or more characters long.");
			return;
		}else if(isGroupExist(groupName.getText().trim())){
			//Display group name already exist message
			showError(true, "Group name already exist. Please try a different name.");
			return;
		}else if(leaderId == 0){
			//Display leader does not exist / can't be empty message
			showError(true, "Leader does not exist or can not be empty.");
			return;
		}
		
		showError(false, "");
		
		GroupModel model = new GroupModel();
		model.setName(groupName.getText().trim());
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
		model.setLeaderId(leaderId);
		model.setDescription("");
		
		showLoading(true);
		
		GlobalResources.getInstance().getAddRPC().addGroup(model, GlobalResources.getInstance().getModel().getId(), new AsyncCallback<Integer>() {
			
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
	
	private boolean isGroupExist(String groupName){
		
		HashMap<Integer, String> allGroups = GlobalResources.getInstance().getOrganisationMap();
		if(allGroups != null){
			for(Integer id : allGroups.keySet()){
				String tmpGroup = allGroups.get(id);
				if(tmpGroup.equalsIgnoreCase(groupName)){
					return true;
				}
			}
		}
		return false;
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
