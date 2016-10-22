package com.beta.rsatech.churchcradle.client.app.html.modules.sms.compose.all;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.CheckTokenWidget;
import com.beta.rsatech.churchcradle.client.widgets.CheckTokenWidget.CheckTokenWidgetEventHandler;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.client.widgets.SmallerCheckTokenWidget;
import com.beta.rsatech.churchcradle.client.widgets.SmallerCheckTokenWidget.SmallerCheckTokenWidgetEventHandler;
import com.beta.rsatech.churchcradle.shared.GroupInfoModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SMSGroupSelectPopup extends CustomDraggablePopupPanel {

	private SmallerCheckTokenWidget selectAllCheckBox;
	private int counter, totalGroups;
	private boolean isAllMembers;
	private SMSGroupSelectPopupEventHandler handler;
	private ArrayList<GroupInfoModel> groupInfoList;
	private ArrayList<CheckTokenWidget> checkTokenList;
	private static SMSGroupSelectPopupUiBinder uiBinder = GWT
			.create(SMSGroupSelectPopupUiBinder.class);

	interface SMSGroupSelectPopupUiBinder extends
			UiBinder<Widget, SMSGroupSelectPopup> {
	}

	public interface SMSGroupSelectPopupEventHandler{
		void onGroupSelect(ArrayList<GroupInfoModel> groupInfoList, int totalGroups, boolean isAllMembers);
	}
	
	@UiField FlowPanel widgetContainer;
	@UiField Button submitBtn;
	@UiField LabelElement membersCounter;
	@UiField SimplePanel selectAllPanel;
	
	public SMSGroupSelectPopup() {
		add(uiBinder.createAndBindUi(this));
		initComponents();
		initEvents();
	}
	
	private void initComponents(){
		GlobalResources.getInstance().getListRPC().getGroupInfoList(GlobalResources.getInstance().getModel().getChurchId(), new AsyncCallback<ArrayList<GroupInfoModel>>() {
			
			@Override
			public void onSuccess(ArrayList<GroupInfoModel> result) {
				if(result != null){
					widgetContainer.clear();
					groupInfoList = new ArrayList<GroupInfoModel>();
					checkTokenList = new ArrayList<CheckTokenWidget>();
					for(GroupInfoModel model : result){
						totalGroups = result.size();
						CheckTokenWidget checkToken = new CheckTokenWidget(model);
						checkToken.setCheckTokenWidgetEventHandler(new CheckTokenWidgetEventHandler() {
							
							@Override
							public void onCheckClicked(boolean isChecked, GroupInfoModel model) {
								if(isChecked){
									if(selectAllCheckBox != null && isAllMembers){
										selectAllCheckBox.unCheck();
										counter = 0;
										isAllMembers = false;
									}
									
									counter += model.getTotalMembers();
									groupInfoList.add(model);
									
								}else{
									counter -= model.getTotalMembers();
									groupInfoList.remove(model);
								}
								updateView();
							}
						});
						
						checkTokenList.add(checkToken);
						widgetContainer.add(checkToken);
					}
					initPopup();
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		selectAllCheckBox = new SmallerCheckTokenWidget(getSelectAllGroupInfo());
		selectAllCheckBox.setSmallerCheckTokenWidgetEventHandler(new SmallerCheckTokenWidgetEventHandler() {
			
			@Override
			public void onCheckClicked(boolean isChecked, GroupInfoModel model) {
				if(isChecked){
					unCheckAllGroups();
					counter = GlobalResources.getInstance().getMembersMap().size();
					membersCounter.setInnerText(counter+"");
					isAllMembers = true;
				}else{
					isAllMembers = false;
					counter = 0;
					membersCounter.setInnerText(counter+"");
				}
			}
		});
		selectAllPanel.setWidget(selectAllCheckBox);
		
	}
	
	private void unCheckAllGroups(){
		for(CheckTokenWidget widget : checkTokenList){
			widget.unCheck();
		}
	}
	
	private void updateView(){
		membersCounter.setInnerText(counter+"");
	}
	
	private void initEvents(){
		submitBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
				if(handler != null){
					handler.onGroupSelect(groupInfoList, totalGroups, isAllMembers);
				}
			}
		});
	}
	
	private GroupInfoModel getSelectAllGroupInfo(){
		GroupInfoModel model = new GroupInfoModel();
		model.setGroupName("All Members");
		model.setTotalMembers(GlobalResources.getInstance().getMembersMap().size());
		model.setId(0);
		
		return model;
	}
	
	private void initPopup(){
		setAutoHideEnabled(false);
		setGlassEnabled(true);
		setGlassStyleName("glassPanel");
		setDraggable(true);
		center();
	} 
	
	public void setSMSGroupSelectPopupEventHandler(SMSGroupSelectPopupEventHandler handler){
		this.handler = handler;
	}

}
