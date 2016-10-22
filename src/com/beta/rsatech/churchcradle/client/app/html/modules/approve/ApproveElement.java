package com.beta.rsatech.churchcradle.client.app.html.modules.approve;

import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.GroupApproveLevel.GroupApprovalLevelEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.BibleReadingApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.BibleReadingApprovePage.BibleReadingApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.DonationApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.DonationApprovePage.DonationApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.EventApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.EventApprovePage.EventApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.FuneralApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.FuneralApprovePage.FuneralApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.MarriageApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.MarriageApprovePage.MarriageApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.MembersApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.MembersApprovePage.MembersApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.OfferingApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.OfferingApprovePage.OfferingApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.SMSApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.SMSApprovePage.SMSApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.SpecialOfferingApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.SpecialOfferingApprovePage.SpecialOfferingApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.TitheApprovePage;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.pages.TitheApprovePage.TitheApprovePageEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.BibleReadingApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.BibleReadingApprovePopup.BibleReadingApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.DonationApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.DonationApprovePopup.DonationApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.EventApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.EventApprovePopup.EventApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.FuneralApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.FuneralApprovePopup.FuneralApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.MarriageApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.MarriageApprovePopup.MarriageApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.MemberApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.MemberApprovePopup.MemberApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.OfferingApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.OfferingApprovePopup.OfferingApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.SMSApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.SMSApprovePopup.SMSApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.SpecialOfferingApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.SpecialOfferingApprovePopup.SpecialOfferingApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.TitheApprovePopup;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.popup.TitheApprovePopup.TitheApprovePopupEventHandler;
import com.beta.rsatech.churchcradle.client.elements.SectionElement;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.BibleReadingModel;
import com.beta.rsatech.churchcradle.shared.DonationModel;
import com.beta.rsatech.churchcradle.shared.EAnnounceModel;
import com.beta.rsatech.churchcradle.shared.FAnnounceModel;
import com.beta.rsatech.churchcradle.shared.MarriageModel;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.OfferingModel;
import com.beta.rsatech.churchcradle.shared.SMSModel;
import com.beta.rsatech.churchcradle.shared.SpecialOfferingModel;
import com.beta.rsatech.churchcradle.shared.TitheModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class ApproveElement extends UIObject {

	private GroupApproveLevel groupApprove;
	private ApproveListLevel listLevel;
	
	private static ApproveElementUiBinder uiBinder = GWT
			.create(ApproveElementUiBinder.class);

	interface ApproveElementUiBinder extends UiBinder<Element, ApproveElement> {
	}
	
	@UiField SectionElement approveContainer;
	public ApproveElement() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	private void initElement(){
		groupApprove = new GroupApproveLevel(GlobalResources.getInstance().getModel().getApproveModules());
		listLevel = new ApproveListLevel();
		
		approveContainer.appendChild(groupApprove.getElement());
		approveContainer.appendChild(listLevel.getElement());
		approveContainer.appendChild(new ContentHTMLCompositeEnder().getElement());
	}
	
	private void initGroupEvent(){
		groupApprove.setGroupApprovalLevelEventHandler(new GroupApprovalLevelEventHandler() {
			
			@Override
			public void onItemClicked(String module) {
				loadPage(module);
			}
		});
	}
	
	private void loadPage(String item){
		
		if(item.equalsIgnoreCase(AppConstants.OFFERINGS)){
			renderOfferingsPage();
		}else if(item.equalsIgnoreCase(AppConstants.MEMBERS)){
			renderMembersPage();
		}else if(item.equalsIgnoreCase(AppConstants.TITHES)){
			renderTithePage();
		}else if(item.equalsIgnoreCase(AppConstants.DONATIONS)){
			renderDonationPage();
		}else if(item.equalsIgnoreCase(AppConstants.MARRIAGE_ANNOUNCEMENTS)){
			renderMarriagePage();
		}else if(item.equalsIgnoreCase(AppConstants.SMS)){
			renderSMSPage();
		}else if(item.equalsIgnoreCase(AppConstants.EVENT_ANNOUNCEMENTS)){
			renderEAnnouncePage();
		}else if(item.equalsIgnoreCase(AppConstants.FUNERAL_ANNOUNCEMENTS)){
			renderFAnnouncePage();
		}else if(item.equalsIgnoreCase(AppConstants.B_READINGS)){
			renderBibleReadingPage();
		}else if(item.equalsIgnoreCase(AppConstants.S_OFFERING)){
			renderSpecialOfferingsPage();
		}
	}
	
	private void renderOfferingsPage(){
		final OfferingApprovePage page = new OfferingApprovePage();
		page.setOfferingApprovePageEventHandler(new OfferingApprovePageEventHandler() {
			
			@Override
			public void onOfferingEditInvoked(OfferingModel model) {
				OfferingApprovePopup popup = new OfferingApprovePopup(model);
				popup.setOfferingApprovePopupEventHandler(new OfferingApprovePopupEventHandler() {
					
					@Override
					public void onOfferingApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderSpecialOfferingsPage(){
		final SpecialOfferingApprovePage page = new SpecialOfferingApprovePage();
		page.setSpecialOfferingApprovePageEventHandler(new SpecialOfferingApprovePageEventHandler() {
			
			@Override
			public void onSpecialOfferingEditInvoked(SpecialOfferingModel model) {
				SpecialOfferingApprovePopup popup = new SpecialOfferingApprovePopup(model);
				popup.setSpecialOfferingApprovePopupEventHandler(new SpecialOfferingApprovePopupEventHandler() {
					
					@Override
					public void onSpecialOfferingApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderMembersPage(){
		final MembersApprovePage page = new MembersApprovePage();
		page.setMembersApprovePageEventHandler(new MembersApprovePageEventHandler() {
			
			@Override
			public void onMemberEditInvoked(MemberModel model) {
				MemberApprovePopup popup = new MemberApprovePopup(model);
				popup.setMemberApprovePopupEventHandler(new MemberApprovePopupEventHandler() {
					
					@Override
					public void onMemberApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderMarriagePage(){
		final MarriageApprovePage page = new MarriageApprovePage();
		page.setMarriageApprovePageEventHandler(new MarriageApprovePageEventHandler() {
			
			@Override
			public void onMarriageEditInvoked(MarriageModel model) {
				MarriageApprovePopup popup = new MarriageApprovePopup(model);
				popup.setMarriageApprovePopupEventHandler(new MarriageApprovePopupEventHandler() {
					
					@Override
					public void onMarriageApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderTithePage(){
		final TitheApprovePage page = new TitheApprovePage();
		page.setTitheApprovePageEventHandler(new TitheApprovePageEventHandler() {
			
			@Override
			public void onTitheEditInvoked(TitheModel model) {
				TitheApprovePopup popup = new TitheApprovePopup(model);
				popup.setTitheApprovePopupEventHandler(new TitheApprovePopupEventHandler() {
					
					@Override
					public void onTitheApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderBibleReadingPage(){
		final BibleReadingApprovePage page = new BibleReadingApprovePage();
		page.setBibleReadingApprovePageEventHandler(new BibleReadingApprovePageEventHandler() {
			
			@Override
			public void onBibleReadingEditInvoked(BibleReadingModel model) {
				BibleReadingApprovePopup popup = new BibleReadingApprovePopup(model);
				popup.setBibleReadingApprovePopupEventHandler(new BibleReadingApprovePopupEventHandler() {
					
					@Override
					public void onBibleReadingApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderDonationPage(){
		final DonationApprovePage page = new DonationApprovePage();
		page.setDonationApprovePageEventHandler(new DonationApprovePageEventHandler() {
			
			@Override
			public void onDonationEditInvoked(DonationModel model) {
				DonationApprovePopup popup = new DonationApprovePopup(model);
				popup.setDonationApprovePopupEventHandler(new DonationApprovePopupEventHandler() {
					
					@Override
					public void onDonationApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderSMSPage(){
		final SMSApprovePage page = new SMSApprovePage();
		page.setSMSApprovePageEventHandler(new SMSApprovePageEventHandler() {
			
			@Override
			public void onSMSEditInvoked(SMSModel model) {
				SMSApprovePopup popup = new SMSApprovePopup(model);
				popup.setSMSApprovePopupEventHandler(new SMSApprovePopupEventHandler() {
					
					@Override
					public void onSMSApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderEAnnouncePage(){
		final EventApprovePage page = new EventApprovePage();
		page.setEventApprovePageEventHandler(new EventApprovePageEventHandler() {
			
			@Override
			public void onEventEditInvoked(EAnnounceModel model) {
				EventApprovePopup popup = new EventApprovePopup(model);
				popup.setEventApprovePopupEventHandler(new EventApprovePopupEventHandler() {
					
					@Override
					public void onEventApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	private void renderFAnnouncePage(){
		final FuneralApprovePage page = new FuneralApprovePage();
		page.setFuneralApprovePageEventHandler(new FuneralApprovePageEventHandler() {
			
			@Override
			public void onFuneralEditInvoked(FAnnounceModel model) {
				FuneralApprovePopup popup = new FuneralApprovePopup(model);
				popup.setFuneralApprovePopupEventHandler(new FuneralApprovePopupEventHandler() {
					
					@Override
					public void onFuneralApproved(int id) {
						page.removeList(id);
					}
				});
			}
		});
		listLevel.setListPage(page.getElement());
		page.load();
	}
	
	public void load(){
		initElement();
		initGroupEvent();
	}

}
