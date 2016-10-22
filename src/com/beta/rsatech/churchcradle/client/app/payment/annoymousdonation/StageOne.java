package com.beta.rsatech.churchcradle.client.app.payment.annoymousdonation;

import java.util.ArrayList;
import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.ChurchModel;
import com.beta.rsatech.churchcradle.shared.PaymentModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<PaymentModel>{

	private HasWizardEvent<PaymentModel> wizardHandler;
	private PaymentModel model;
	private HashMap<String, ChurchModel> churchesHash;
	private HashMap<String, Integer> itemsMap;
	private String month;
	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField TextBox fullNameField, monthField, amountField;
	@UiField ListBox churchField;
	public StageOne(PaymentModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initChurches();
		initEvent();
	}

	public StageOne(String month) {
		model = null;
		this.month = month;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(null);
		initChurches();
		initEvent();
	}

	private void initComponent(PaymentModel model){
		fullNameField.setText("Annoymous");
		amountField.getElement().setAttribute("placeholder", "Amount");
		amountField.setEnabled(false);
		
		fullNameField.setEnabled(false);
		monthField.setEnabled(false);
		
		
		if(model != null){
			amountField.setText(model.getAmount()+"");
			monthField.setText(model.getMonth());
			amountField.setEnabled(true);
			month = model.getMonth();
		}else{
			monthField.setText(month);
		}
	}
	
	private void initChurches(){
		churchField.addItem("Loading ...");
		GlobalResources.getInstance().getListRPC().getAllChurches(new AsyncCallback<ArrayList<ChurchModel>>() {
			
			@Override
			public void onSuccess(ArrayList<ChurchModel> result) {
				churchField.clear();
				if(result != null){
					churchesHash = new HashMap<String, ChurchModel>();
					itemsMap = new HashMap<String, Integer>();
					
					//Init with a value
					churchField.addItem("Please select a church", "0");
					
					int index = 1;
					for(ChurchModel church : result){
						churchField.addItem(church.getName(), church.getId()+"");
						churchesHash.put(church.getId()+"", church);
						itemsMap.put(church.getId()+"", index);
						index ++;
					}
					
					if(model != null){
						setSelectedChurch(model.getChurchId());
					}
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void setSelectedChurch(int churchId){
		String stringifiedChurchId = churchId+"";
		
		if(churchesHash != null && churchesHash.containsKey(stringifiedChurchId) && itemsMap.containsKey(stringifiedChurchId)){
			int index = itemsMap.get(stringifiedChurchId);
			
			churchField.setSelectedIndex(index);
		}
	}

	private void initEvent(){
		churchField.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				amountField.setEnabled(true);
			}
		});
	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}

	@Override
	public void validateAndProceed() {
		if(amountField.getText().trim().isEmpty()){
			doPublishError("Amount field cannot be empty.");
			return;
		}
		
		if(Integer.parseInt(amountField.getText().trim()) < 1){
			doPublishError("Amount must be greater than 1.");
			return;
		}
		
		if(wizardHandler != null){
			next();
		}
	}

	private void doPreparePaymentModel(){
		if(model == null){
			model = new PaymentModel();
		}
		
		model.setAmount(Integer.parseInt(amountField.getText().trim()));
		model.setMonth(month);
		model.setChurchId(Integer.parseInt(churchField.getValue(churchField.getSelectedIndex())));
		model.setPaymentId(churchesHash.get(churchField.getValue(churchField.getSelectedIndex())).getPaymentId());
		model.setChurchName(churchField.getSelectedItemText());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<PaymentModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void next() {
		doPreparePaymentModel();
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub

	}
}
