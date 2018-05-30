package com.beta.rsatech.churchcradle.client.app.html.admin.popup.member;

import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomFileUpload;
import com.beta.rsatech.churchcradle.client.widgets.CustomFileUpload.CustomFileUploadEventHandler;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class StageOne extends Composite implements IsWizardable<MemberModel>{

	private HasWizardEvent<MemberModel> wizardHandler;
	private MemberModel model;

	private static StageOneUiBinder uiBinder = GWT
			.create(StageOneUiBinder.class);

	interface StageOneUiBinder extends UiBinder<Widget, StageOne> {
	}

	@UiField Image img;
	@UiField CustomFileUpload uploadWidget;
	@UiField TextBox firstNameField, lastNameField, otherNameField, emailField, msisdnField;
	public StageOne(MemberModel model) {
		this.model = model;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(model);
		initEvent();
	}

	public StageOne() {
		model = null;
		initWidget(uiBinder.createAndBindUi(this));
		initComponent(null);
		initEvent();
	}

	private void initComponent(MemberModel model){
		img.setUrl(AppConstants.NO_IMAGE);

		firstNameField.getElement().setAttribute("placeholder", "First Name");
		lastNameField.getElement().setAttribute("placeholder", "Last Name");
		otherNameField.getElement().setAttribute("placeholder", "Middle/Other Name");
		emailField.getElement().setAttribute("placeholder", "Email");
		msisdnField.getElement().setAttribute("placeholder", "Mobile Number");

		if(model != null){
			firstNameField.setText(model.getFirstName());
			lastNameField.setText(model.getLastName());
			otherNameField.setText(model.getMiddleName());
			emailField.setText(model.getEmail());
			msisdnField.setText(model.getMsisdn());
			
			if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
				img.setUrl(AppConstants.PHOTO_URL+model.getAvatar());
			}else{
				img.setUrl(AppConstants.NO_IMAGE);
			}
			
//			if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
//				Utils.retrieveFromBlobstore(model.getAvatar(), new GeneralEventHandler<BlobstoreModel>() {
//					
//					@Override
//					public void onSuccess(BlobstoreModel t) {
//						//Window.alert("Image url => "+t.getUrl());
//						img.setUrl(t.getUrl());
//					}
//					
//					@Override
//					public void onError() {
//						// TODO Auto-generated method stub
//						
//					}
//				});
//			}
			
			if(model.getAvatar() != null){
				img.setUrl(model.getAvatar());
			}
			

			if(model.isUpdate()){
				msisdnField.setEnabled(false);
			}
		}
	}

	private void initEvent(){
		uploadWidget.setCustomFileUploadEventHandler(new CustomFileUploadEventHandler() {

			@Override
			public void onFormSubmit(final String avatarString, final String blobKey) {
				//Delete old blobKey
				if(model == null){
					model = new MemberModel();
				}
				
				if(model.getAvatar() != null && !model.getAvatar().trim().isEmpty()){
					GlobalResources.getInstance().getUpdateRPC().deleteOrphanBlob(model.getAvatar(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							//Window.alert("Image url => "+avatarString);
							img.setUrl(avatarString);
							//model.setAvatar(blobKey);	
							
							model.setAvatar(avatarString);							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}else{
					img.setUrl(avatarString);
					//model.setAvatar(blobKey);
					model.setAvatar(avatarString);
				}
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
		
//		if(isAvatarEmpty()){
//			doPublishError("Please select a picture");
//			return;
//		}

		if(firstNameField.getText().trim().isEmpty()){
			doPublishError("First Name cannot be empty!");
			return;
		}
		if(lastNameField.getText().trim().isEmpty()){
			doPublishError("Last Name cannot be empty!");
			return;
		}
		if(msisdnField.getText().trim().isEmpty()){
			doPublishError("Mobile cannot be empty!");
			return;
		}
		if(!(Utils.isMsisdnValid(msisdnField.getText()) && msisdnField.getText().trim().contains("+"))){
			doPublishError("Invalid mobile number!");
			return;
		}
		
		doMsisdnValidation();
		
	}

	private void doEmailValidation(){
		boolean isEmailEmpty = false;
		
		if(emailField.getText().trim().isEmpty()){
			isEmailEmpty = true;
		}
		
		if(!isEmailEmpty && !Utils.isEmailValidFormat(emailField.getText().trim())){
			doPublishError("Invalid email format");
			return;
		}
		
		if((model == null && !isEmailEmpty) || (model != null && !isEmailEmpty)){
			Utils.validateEmail(emailField.getText().trim(), GlobalResources.getInstance().getModel().getChurchId(), new GeneralEventHandler<Boolean>() {

				@Override
				public void onSuccess(Boolean t) {
					if(!t){
						if(wizardHandler != null){
							next();
						}	
					}else{
						if(model != null && model.isUpdate() && (model.getEmail().equalsIgnoreCase(emailField.getText().trim()))){
							next();
						}else{
							if(wizardHandler != null){
								wizardHandler.onError("Email already exits");
								return;
							}
						}
					}
				}

				@Override
				public void onError() {
					doPublishError("Connection error, please try later");

				}
			});
		}else{
			if(wizardHandler != null){
				next();
			}
		}
	}
	
	private void doMsisdnValidation(){
	
		if(model != null && model.isUpdate()){
			doEmailValidation();
		}else{
			Utils.validateMsisdn(msisdnField.getText().trim(), GlobalResources.getInstance().getModel().getChurchId(), new GeneralEventHandler<Boolean>() {
				
				@Override
				public void onSuccess(Boolean t) {
					if(!t){
						doEmailValidation();
					}else{
						doPublishError("Mobile number already exist");
						return;
					}
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}

	private void doPrepareMemberModel(){
		if(model == null){
			model = new MemberModel();
		}

		model.setFirstName(firstNameField.getText().trim());
		model.setLastName(lastNameField.getText().trim());
		model.setMiddleName(otherNameField.getText().trim());
		model.setMsisdn(msisdnField.getText().trim());
		model.setEmail(emailField.getText().trim());
		model.setChurchId(GlobalResources.getInstance().getModel().getChurchId());
	}

	@Override
	public void setHasWizardEvent(HasWizardEvent<MemberModel> event) {
		this.wizardHandler = event;
	}

	@Override
	public void validateAndReturn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void next() {
		doPrepareMemberModel();
		wizardHandler.onValidateComplete(WizardStage.TWO, model);		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub

	}
}
