package com.beta.rsatech.churchcradle.client.app.html.admin.popup.specialoffering;

import java.util.HashMap;

import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.HasWizardEvent;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.IsWizardable;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.WizardStage;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.widgets.ReviewPopupTextArea;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<SpecialOfferingModel>{

	private String member;
	private int memberId;
	private SuggestBox memberSearch;

	private HasWizardEvent<SpecialOfferingModel> wizardHandler;
	private SpecialOfferingModel model;
	private static StageThreeUiBinder uiBinder = GWT
			.create(StageThreeUiBinder.class);

	interface StageThreeUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField SimplePanel searchContainer;
	@UiField TextBox amountField;
	@UiField (provided = true) ReviewPopupTextArea descField;

	public StageOne(SpecialOfferingModel model) {
		this.model = model;
		descField = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}
	
	public StageOne() {
		descField = new ReviewPopupTextArea(true);
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	private void initComponent(SpecialOfferingModel model){
		initSearchBox();
		amountField.getElement().setAttribute("placeholder", "GHS");
		descField.getElement().setAttribute("placeholder", "Message ...");

		if(model != null){
			amountField.setText(""+model.getAmount());
			descField.setText(model.getMessage());
		}
	}

	private void initEvent(){

	}

	private void doPublishError(String message){
		if(wizardHandler != null){
			wizardHandler.onError(message);
		}
	}
	
	private void initSearchBox(){
		final HashMap<Integer, String> membersMap = GlobalResources.getInstance().getMembersMap();
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for(Integer key : membersMap.keySet()){
			oracle.add(membersMap.get(key));
		}
		
		memberSearch = new SuggestBox(oracle);
		memberSearch.setStyleName("form-control");
		memberSearch.getElement().setAttribute("placeholder", "Enter Member Name");
		memberSearch.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
			
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				member = event.getSelectedItem().getReplacementString();
				memberId = fetchId(member, membersMap);
			}
		});
		
		searchContainer.setWidget(memberSearch);
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

	@Override
	public void validateAndProceed() {
		if(amountField.getText().trim().isEmpty()){
			doPublishError("Amount cannot be empty!");
			return;
		}
		if(memberId <= 0){
			doPublishError("Member Name cannot be empty!");
			return;
		}
		
		if(descField.getText().trim().isEmpty()){
			doPublishError("Message cannot be empty!");
			return;
		}

		doPrepareSpecialOfferingModel();
		next();
	}

	private void doPrepareSpecialOfferingModel(){
		if(model == null){
			model = new SpecialOfferingModel();
		}

		model.setMessage(descField.getText().trim());
		model.setAmount(Double.parseDouble(amountField.getText().trim()));
		model.setMemberId(memberId);
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<SpecialOfferingModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		doPrepareSpecialOfferingModel();
		back();
	}

	@Override
	public void next() {
		wizardHandler.onValidateComplete(WizardStage.REVIEW, model);		
	}

	@Override
	public void back() {
	}

}
