package com.beta.rsatech.churchcradle.client.controller;

import java.util.ArrayList;

import com.beta.rsatech.churchcradle.client.Controller;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.biblereading.AddBibleReadingPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.biblereading.AddBibleReadingPopup.AddBibleReadingPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.event.AddEventPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.event.AddEventPopup.AddEventPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.funeral.AddFuneralPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.funeral.AddFuneralPopup.AddFuneralPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.marriage.AddMarriagePopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.marriage.AddMarriagePopup.AddMarriagePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.AddMemberPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.member.AddMemberPopup.AddMemberPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.offering.AddOfferingPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.offering.AddOfferingPopup.AddOfferingPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.settings.MemberSettingsPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.settings.MemberSettingsPopup.MemberSettingsPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.specialoffering.AddSpecialOfferingPopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.specialoffering.AddSpecialOfferingPopup.AddSpecialOfferingPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.tithe.AddTithePopup;
import com.beta.rsatech.churchcradle.client.app.html.admin.popup.tithe.AddTithePopup.AddTithePopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.composites.contents.ContentHTMLCompositeEnder;
import com.beta.rsatech.churchcradle.client.app.html.composites.header.BrandHTMLComposite;
import com.beta.rsatech.churchcradle.client.app.html.composites.header.LogoutHTMLComposite;
import com.beta.rsatech.churchcradle.client.app.html.composites.sidebar.SideBarHTMLComposite;
import com.beta.rsatech.churchcradle.client.app.html.forms.popup.AddClassLeaderFormPopup;
import com.beta.rsatech.churchcradle.client.app.html.forms.popup.AddGroupFormPopup;
import com.beta.rsatech.churchcradle.client.app.html.forms.popup.AddPowerLeaderFormPopup;
import com.beta.rsatech.churchcradle.client.app.html.forms.popup.SMSPopup;
import com.beta.rsatech.churchcradle.client.app.html.forms.popup.SMSPopup.SMSPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.html.modules.announcements.events.EventElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.announcements.funeral.FuneralElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.announcements.marriage.MarriageElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.ApproveElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.approve.soffering.SOfferingElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.appstats.AppStatsElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.biblereadings.BibleReadingsElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.birthday.BirthdayElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.classes.ClassesElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.dashboard.DashboadHTMLComposite;
import com.beta.rsatech.churchcradle.client.app.html.modules.members.MembersElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.myoffering.MyOfferingsElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.offerings.OfferingsElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.payments.cancel.CancelElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.payments.confirmation.ConfirmationElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.powerleaders.PowerLeadersElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.sms.SMSElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.tithes.TitheElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinedonations.OnlineDonationElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlineoffering.OnlineOfferingElement;
import com.beta.rsatech.churchcradle.client.app.html.modules.viewpayments.onlinetithes.OnlineTitheElement;
import com.beta.rsatech.churchcradle.client.app.payment.offering.OfferingPopup;
import com.beta.rsatech.churchcradle.client.app.payment.offering.OfferingPopup.AddOfferingPaymentPopupEventHandler;
import com.beta.rsatech.churchcradle.client.app.payment.tithe.TithePopup;
import com.beta.rsatech.churchcradle.client.app.payment.tithe.TithePopup.AddTithePaymentPopupEventHandler;
import com.beta.rsatech.churchcradle.client.events.AddClassLeaderEvent;
import com.beta.rsatech.churchcradle.client.events.AddClassLeaderEventHandler;
import com.beta.rsatech.churchcradle.client.events.AddGroupEvent;
import com.beta.rsatech.churchcradle.client.events.AddGroupEventHandler;
import com.beta.rsatech.churchcradle.client.events.AddPowerLeaderEvent;
import com.beta.rsatech.churchcradle.client.events.AddPowerLeaderEventHandler;
import com.beta.rsatech.churchcradle.client.events.BibleReadingEvent;
import com.beta.rsatech.churchcradle.client.events.BibleReadingEventHandler;
import com.beta.rsatech.churchcradle.client.events.ClassesEvent;
import com.beta.rsatech.churchcradle.client.events.ClassesEventHandler;
import com.beta.rsatech.churchcradle.client.events.DashboardEvent;
import com.beta.rsatech.churchcradle.client.events.DashboardEventHandler;
import com.beta.rsatech.churchcradle.client.events.LogoutEvent;
import com.beta.rsatech.churchcradle.client.events.LogoutEventHandler;
import com.beta.rsatech.churchcradle.client.events.MembersEvent;
import com.beta.rsatech.churchcradle.client.events.MembersEventHandler;
import com.beta.rsatech.churchcradle.client.events.OfferingsEvent;
import com.beta.rsatech.churchcradle.client.events.OfferingsEventHandler;
import com.beta.rsatech.churchcradle.client.events.SendMessageEvent;
import com.beta.rsatech.churchcradle.client.events.SendMessageEventHandler;
import com.beta.rsatech.churchcradle.client.events.TithePaymentEvent;
import com.beta.rsatech.churchcradle.client.events.TithePaymentEventHandler;
import com.beta.rsatech.churchcradle.client.events.UpdateMemberEvent;
import com.beta.rsatech.churchcradle.client.events.UpdateMemberEventHandler;
import com.beta.rsatech.churchcradle.client.resources.GlobalResources;
import com.beta.rsatech.churchcradle.client.utils.CookieVerifier;
import com.beta.rsatech.churchcradle.client.utils.GeneralEventHandler;
import com.beta.rsatech.churchcradle.client.utils.HashURL;
import com.beta.rsatech.churchcradle.client.utils.PageDirector;
import com.beta.rsatech.churchcradle.client.utils.URLConstants;
import com.beta.rsatech.churchcradle.client.utils.Utils;
import com.beta.rsatech.churchcradle.client.widgets.CustomDraggablePopupPanel;
import com.beta.rsatech.churchcradle.shared.AppConstants;
import com.beta.rsatech.churchcradle.shared.MemberModel;
import com.beta.rsatech.churchcradle.shared.UserModel;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class AppController implements Controller, ValueChangeHandler<String> {
	private UserModel user;
	private RootPanel header, sideBar, content, pluginsContainer;
	private ArrayList<Integer> entryModuleList;
	private HandlerManager eventBus;
	private boolean isFewPluginsLoaded = false, isSMSFewPluginsLoaded = false;
	private int moduleId = 0;

	public AppController(RootPanel header, RootPanel sideBar, RootPanel content, RootPanel pluginsContainer, HandlerManager eventBus){
		this.eventBus = eventBus;
		this.header = header;
		this.sideBar = sideBar;
		this.pluginsContainer = pluginsContainer;
		this.content = content;
	}

	private void init(){
		History.addValueChangeHandler(this);
		if(History.getToken().trim().isEmpty()){
			History.newItem(URLConstants.DASHBOARD);
		}else{
			History.fireCurrentHistoryState();
		}
	}

	private void bind(){
		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {

			@Override
			public void onLogoutInvoked(LogoutEvent event) {
				// TODO Auto-generated method stub

			}
		});

		eventBus.addHandler(DashboardEvent.TYPE, new DashboardEventHandler() {

			@Override
			public void onDashboardInvoked(DashboardEvent event) {
				if(History.getToken().equals(URLConstants.DASHBOARD)){
					History.fireCurrentHistoryState();
				}else{
					History.newItem(URLConstants.DASHBOARD);
				}
			}
		});

		eventBus.addHandler(MembersEvent.TYPE, new MembersEventHandler() {

			@Override
			public void onMembersInvoked(MembersEvent event) {
				if(History.getToken().equals(URLConstants.MEMBERS)){
					History.fireCurrentHistoryState();
				}else{
					History.newItem(URLConstants.MEMBERS);
				}
			}
		});

		eventBus.addHandler(ClassesEvent.TYPE, new ClassesEventHandler() {

			@Override
			public void onClassesEventInvoked(ClassesEvent event) {
				if(History.getToken().equals(URLConstants.CLASSES)){
					History.fireCurrentHistoryState();
				}else{
					History.newItem(URLConstants.CLASSES);
				}
			}
		});

		eventBus.addHandler(AddGroupEvent.TYPE, new AddGroupEventHandler() {

			@Override
			public void onAddGroupEventInvoked(AddGroupEvent event) {
				initGroupAddPopUp();
			}
		});

		eventBus.addHandler(OfferingsEvent.TYPE, new OfferingsEventHandler() {

			@Override
			public void onOfferingsEventInvoked(OfferingsEvent event) {
				// TODO Auto-generated method stub

			}
		});

		eventBus.addHandler(SendMessageEvent.TYPE, new SendMessageEventHandler() {

			@Override
			public void onSendMessageInvoked(SendMessageEvent event) {
				showSendMessagePopup(event.getMsisdn());
			}
		});

		eventBus.addHandler(BibleReadingEvent.TYPE, new BibleReadingEventHandler() {

			@Override
			public void onBibleReadingInvoked(BibleReadingEvent event) {
				// TODO Auto-generated method stub

			}
		});

		eventBus.addHandler(UpdateMemberEvent.TYPE, new UpdateMemberEventHandler() {

			@Override
			public void onMemberUpdateInvoked(UpdateMemberEvent event) {
				showMemberPopup(event.getModel());
			}
		});

		eventBus.addHandler(AddClassLeaderEvent.TYPE, new AddClassLeaderEventHandler() {

			@Override
			public void onAddClassLeaderEventInvoked(AddClassLeaderEvent event) {
				initClassLeaderAddPopUp();
			}
		});

		eventBus.addHandler(TithePaymentEvent.TYPE, new TithePaymentEventHandler() {

			@Override
			public void onTithePaymentEvent(TithePaymentEvent event) {
				showTithePaymentPopup(event.getMonth());
			}
		});

		eventBus.addHandler(AddPowerLeaderEvent.TYPE, new AddPowerLeaderEventHandler() {

			@Override
			public void onAddPowerLeaderEventInvoked(AddPowerLeaderEvent event) {
				initPowerLeaderAddPopUp();
			}
		});
	}

	private void showSendMessagePopup(String msisdn){
		SMSPopup popup = new SMSPopup(msisdn);
		popup.setSMSPopupEventHandler(new SMSPopupEventHandler() {

			@Override
			public void onSMSSent() {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initGroupAddPopUp(){
		AddGroupFormPopup addGroup = new AddGroupFormPopup();
	}

	private void initClassLeaderAddPopUp(){
		AddClassLeaderFormPopup addClassLeader = new AddClassLeaderFormPopup();
	}

	private void initPowerLeaderAddPopUp(){
		AddPowerLeaderFormPopup addClassLeader = new AddPowerLeaderFormPopup();
	}

	private void addFewPlugins(){

		if(!isFewPluginsLoaded){
			Scheduler.get().scheduleDeferred(new Command() {
				@Override public void execute() {
					doFewPluginLoad();
					isFewPluginsLoaded = !isFewPluginsLoaded;
				}
			});
		}
	}

	private void addSMSFewPlugins(){

		//pluginsContainer.getElement().removeAllChildren();

		if(!isSMSFewPluginsLoaded){
			Scheduler.get().scheduleDeferred(new Command() {
				@Override public void execute() {
					//doSMSFewPluginLoad();
					isSMSFewPluginsLoaded = !isSMSFewPluginsLoaded;
				}
			});
		}
	}

	private native void doFewPluginLoad()/*-{
		var app = this;
		var plugin = "<script src='js/app.v1.js'></script>";
		$wnd.$('#sels_plugins').append(plugin);
	}-*/;

	//	private native void doSMSFewPluginLoad()/*-{
	//		var app = this;
	//		var plugin = "";
	//		$wnd.$('#sels_plugins').append(plugin);
	//	}-*/;

	private native void doNativeCSSLoad()/*-{
		$wnd.$('head').append('<link rel="stylesheet" href="css/app.v1.css" type="text/css" /><script src="js/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>');
		$wnd.$('body').removeClass('hide');
	}-*/;

	private void loadCSS(){
		Scheduler.get().scheduleDeferred(new Command() {
			@Override public void execute() {
				doNativeCSSLoad();
			}
		});
	}

	private void renderApp(){
		initHeader();
		initSideBar();
		initAccentEvent();
		initTitheAndOfferingPay();
		loadCSS();
	}

	private void initSideBar(){
		sideBar.clear();
		SideBarHTMLComposite sideBar = new SideBarHTMLComposite();
		this.sideBar.getElement().appendChild((sideBar.getElement()));
	}

	private void initHeader(){
		header.clear();
		BrandHTMLComposite brand = new BrandHTMLComposite(Utils.getTruncatedText(GlobalResources.getInstance().getChurchModel().getName(), 14));
		LogoutHTMLComposite otherMenu = new LogoutHTMLComposite();

		this.header.getElement().appendChild(brand.getElement());
		this.header.getElement().appendChild(otherMenu.getElement());
	}

	private void initAccentEvent(){
		if(GlobalResources.getInstance().getChurchModel().isValid()){
			Element accentEvent = DOM.getElementById("accent");
			DOM.sinkEvents(accentEvent, Event.ONCLICK);
			DOM.setEventListener(accentEvent, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					if(GlobalResources.getInstance().getModule().equals(AppConstants.MEMBERS)){
						showMemberPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.OFFERINGS)){
						showOfferingPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.B_READINGS)){
						showBibleReadingPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.MARRIAGE_ANNOUNCEMENTS)){
						showMarriagePopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.FUNERAL_ANNOUNCEMENTS)){
						showFuneralPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.EVENT_ANNOUNCEMENTS)){
						showEventPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.TITHES)){
						showTithePopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.CLASSES)){
						showMemberPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.S_OFFERING)){
						showSpecialOfferingPopup();
					}else if(GlobalResources.getInstance().getModule().equals(AppConstants.POWER_LEADERS)){
						initPowerLeaderAddPopUp();
					}
				}
			});
		}
	}

	private void initTitheAndOfferingPay(){
		if(GlobalResources.getInstance().getChurchModel().isValid()){
			Element offeringEvent = DOM.getElementById("payOfferingAnchor");
			Element titheEvent = DOM.getElementById("payTitheAnchor");

			DOM.sinkEvents(offeringEvent, Event.ONCLICK);
			DOM.sinkEvents(titheEvent, Event.ONCLICK);

			DOM.setEventListener(offeringEvent, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					showOfferingPaymentPopup();
				}
			});

			DOM.setEventListener(titheEvent, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					showTithePaymentPopup(null);
				}
			});
		}
	}

	private void showAccent(){
		boolean isAppValid = GlobalResources.getInstance().getChurchModel().isValid();
		Element accentEvent = DOM.getElementById("accent");
		if(user == null) user = GlobalResources.getInstance().getModel();


		if(entryModuleList != null && entryModuleList.contains(moduleId) && isAppValid){
			accentEvent.removeClassName("hide");

		}else if(moduleId == AppConstants.M_POWER_LEADERS && user.isAdmin() && isAppValid){
			//Exception on power leaders

			accentEvent.removeClassName("hide");
		}else{
			accentEvent.addClassName("hide");
		}
	}

	private void showTithePaymentPopup(String month){
		final CustomDraggablePopupPanel popup = getPopup();

		if(month == null){
			Utils.retrieveServerMonth(new GeneralEventHandler<String>() {

				@Override
				public void onSuccess(String t) {
					TithePopup paymentPopup = new TithePopup(t);
					paymentPopup.setAddTithePaymentPopupEventHandler(new AddTithePaymentPopupEventHandler() {

						@Override
						public void onClose() {
							popup.hide();
						}
					});

					popup.add(paymentPopup);
					popup.center();					
				}

				@Override
				public void onError() {
					// TODO Auto-generated method stub

				}
			});
		}else{
			TithePopup paymentPopup = new TithePopup(month);
			paymentPopup.setAddTithePaymentPopupEventHandler(new AddTithePaymentPopupEventHandler() {

				@Override
				public void onClose() {
					popup.hide();
				}
			});

			popup.add(paymentPopup);
			popup.center();
		}
	}

	private void showOfferingPaymentPopup(){
		final CustomDraggablePopupPanel popup = getPopup();

		Utils.retrieveServerMonth(new GeneralEventHandler<String>() {

			@Override
			public void onSuccess(String t) {
				OfferingPopup paymentPopup = new OfferingPopup(t);
				paymentPopup.setAddOfferingPaymentPopupEventHandler(new AddOfferingPaymentPopupEventHandler() {

					@Override
					public void onClose() {
						popup.hide();
					}
				});

				popup.add(paymentPopup);
				popup.center();					
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub

			}
		});
	}

	private void showMemberPopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddMemberPopup memberPopup = new AddMemberPopup();
		memberPopup.setAddMemberPopupEventHandler(new AddMemberPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(memberPopup);
		popup.center();
	}

	private void showMarriagePopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddMarriagePopup marriagePopup = new AddMarriagePopup();
		marriagePopup.setAddMarriagePopupEventHandler(new AddMarriagePopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(marriagePopup);
		popup.center();
	}

	private void showFuneralPopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddFuneralPopup funeralPopup = new AddFuneralPopup();
		funeralPopup.setAddFuneralPopupEventHandler(new AddFuneralPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(funeralPopup);
		popup.center();
	}

	private void showMemberPopup(MemberModel model){
		final CustomDraggablePopupPanel popup = getPopup();
		AddMemberPopup memberPopup = new AddMemberPopup(model);
		memberPopup.setAddMemberPopupEventHandler(new AddMemberPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(memberPopup);
		popup.center();
	}

	private void showEventPopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddEventPopup eventPopup = new AddEventPopup();
		eventPopup.setAddEventPopupEventHandler(new AddEventPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(eventPopup);
		popup.center();
	}

	private void showOfferingPopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddOfferingPopup offeringPopup = new AddOfferingPopup();
		offeringPopup.setAddOfferingPopupEventHandler(new AddOfferingPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(offeringPopup);
		popup.center();
	}

	private void showTithePopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddTithePopup tithePopup = new AddTithePopup();
		tithePopup.setAddTithePopupEventHandler(new AddTithePopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(tithePopup);
		popup.center();
	}

	private void showSpecialOfferingPopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddSpecialOfferingPopup specialOfferingPopup = new AddSpecialOfferingPopup();
		specialOfferingPopup.setAddSpecialOfferingPopupEventHandler(new AddSpecialOfferingPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(specialOfferingPopup);
		popup.center();
	}

	private void showBibleReadingPopup(){
		final CustomDraggablePopupPanel popup = getPopup();
		AddBibleReadingPopup bibleReadingPopup = new AddBibleReadingPopup();
		bibleReadingPopup.setAddBibleReadingPopupEventHandler(new AddBibleReadingPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
			}
		});
		popup.add(bibleReadingPopup);
		popup.center();
	}

	private CustomDraggablePopupPanel getPopup(){
		CustomDraggablePopupPanel popup = new CustomDraggablePopupPanel();
		popup.setAutoHideEnabled(false);
		popup.setGlassEnabled(true);
		popup.setGlassStyleName("glassPanel");
		popup.setDraggable(true);

		return popup;
	}

	private void loadDashboardModule(){
		DashboadHTMLComposite dash = new DashboadHTMLComposite();

		ContentHTMLCompositeEnder content = new ContentHTMLCompositeEnder();

		this.content.getElement().appendChild(dash.getElement());
		this.content.getElement().appendChild(content.getElement());
		addFewPlugins();
		dash.load();
	}

	private void loadOrganisationModule(){
		MembersElement members = new MembersElement();
		this.content.getElement().appendChild(members.getElement());
		members.load();
		addFewPlugins();
	}

	private void loadClassesModule(){
		ClassesElement members = new ClassesElement();
		this.content.getElement().appendChild(members.getElement());
		members.load();
		addFewPlugins();
	}

	private void loadSpecialOfferingModule(){
		SOfferingElement sOffering = new SOfferingElement();
		this.content.getElement().appendChild(sOffering.getElement());
		sOffering.load();
	}

	private void loadMyOfferingModule(){
		MyOfferingsElement myOffering = new MyOfferingsElement();
		this.content.getElement().appendChild(myOffering.getElement());
		myOffering.load();
		addFewPlugins();
	}

	private void loadBirthDayModule(){
		BirthdayElement birthday = new BirthdayElement();
		this.content.getElement().appendChild(birthday.getElement());
		birthday.load();
	}

	private void loadApproveModule(){
		ApproveElement approve = new ApproveElement();
		this.content.getElement().appendChild(approve.getElement());
		approve.load();
		addFewPlugins();
	}

	private void loadSettingsModule(){
		final CustomDraggablePopupPanel popup = getPopup();

		MemberSettingsPopup memberSettings = new MemberSettingsPopup(GlobalResources.getInstance().getMemberModel());
		memberSettings.setMemberSettingsPopupEventHandler(new MemberSettingsPopupEventHandler() {

			@Override
			public void onClose() {
				popup.hide();
				eventBus.fireEvent(new DashboardEvent());
			}
		});
		popup.add(memberSettings);
		popup.center();
	}

	private void loadSMSModule(){
		SMSElement sms = new SMSElement();
		this.content.getElement().appendChild(sms.getElement());
		sms.load();
		addFewPlugins();
		addSMSFewPlugins();

	}
	private void loadLibraryModules(){}
	private void loadTithesModules(){
		TitheElement tithe = new TitheElement();
		this.content.getElement().appendChild(tithe.getElement());
		tithe.load();
		addFewPlugins();
	}
	private void loadOfferingsModules(){
		OfferingsElement offering = new OfferingsElement();
		this.content.getElement().appendChild(offering.getElement());
		offering.load();
		addFewPlugins();
	}
	private void loadBibleReadings(){
		BibleReadingsElement readings = new BibleReadingsElement();
		this.content.getElement().appendChild(readings.getElement());
		readings.load();
		addFewPlugins();
	}
	private void loadMarriageModules(){
		MarriageElement marriage = new MarriageElement();
		this.content.getElement().appendChild(marriage.getElement());
		marriage.load();
	}

	private void loadEventModules(){
		EventElement event = new EventElement();
		this.content.getElement().appendChild(event.getElement());
		event.load();
	}

	private void loadFuneralModules(){
		FuneralElement funeral = new FuneralElement();
		this.content.getElement().appendChild(funeral.getElement());
		funeral.load();
	}

	private void loadOnlineOfferings(){
		OnlineOfferingElement onlineOffering = new OnlineOfferingElement();
		this.content.getElement().appendChild(onlineOffering.getElement());
		onlineOffering.load();
		addFewPlugins();
	}

	private void loadOnlineTithes(){
		OnlineTitheElement onlineTithe = new OnlineTitheElement();
		this.content.getElement().appendChild(onlineTithe.getElement());
		onlineTithe.load();
		addFewPlugins();
	}

	private void loadOnlineDonations(){
		OnlineDonationElement onlineDonation = new OnlineDonationElement();
		this.content.getElement().appendChild(onlineDonation.getElement());
		onlineDonation.load();
		addFewPlugins();
	}

	private void loadPowerLeaders(){
		PowerLeadersElement powerLeader = new PowerLeadersElement();
		this.content.getElement().appendChild(powerLeader.getElement());
		powerLeader.load();
		addFewPlugins();
	}

	private void loadAppStats(){
		AppStatsElement appStats = new AppStatsElement();
		this.content.getElement().appendChild(appStats.getElement());
		appStats.load();
		addFewPlugins();
	}

	private void loadConfirmation(){
		int paymentId = Integer.parseInt(HashURL.getValue("id"));
		String token = HashURL.getValue("g");
		String type = HashURL.getValue("t");
		String externalToken = HashURL.getAfterSymbolToken();
		if(externalToken == null){
			externalToken = "failed";
		}

		if(type.equalsIgnoreCase("O")){
			GlobalResources.getInstance().getPaymentRPC().addMyOfferingBySystem(paymentId, token, externalToken, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
					}
					showConfirmPage();
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
		}else if(type.equalsIgnoreCase("T")){
			GlobalResources.getInstance().getPaymentRPC().addTitheBySystem(paymentId, token, externalToken, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
					}	
					showConfirmPage();
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
		}else if(type.equalsIgnoreCase(AppConstants.SMS_PURCHASE)){
			GlobalResources.getInstance().getPaymentRPC().updateSMSPayment(paymentId, token, externalToken, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						History.newItem(URLConstants.APP_STATS);
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
		}else if(type.equalsIgnoreCase(AppConstants.APP_RENEW)){
			GlobalResources.getInstance().getPaymentRPC().updateAppRenewPayment(paymentId, token, externalToken, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						History.newItem(URLConstants.APP_STATS);
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
		}

	}

	private void showConfirmPage(){
		ConfirmationElement confirm = new ConfirmationElement();
		this.content.getElement().appendChild(confirm.getElement());
		confirm.load();
	}

	private void loadCancel(){
		CancelElement cancel = new CancelElement();
		this.content.getElement().appendChild(cancel.getElement());
		cancel.load();
	}

	private void clearContentPanel(){
		content.getElement().removeAllChildren();
	}

	private void goToLogin(){
		CookieVerifier.clearCookie();
		PageDirector.getInstance().directTo("index.html", "logout");

	}

	private void initContent(String token){
		clearContentPanel();
		setModuleId(token);

		if(token.equals(URLConstants.DASHBOARD)){
			loadDashboardModule();
		}else if(token.equals(URLConstants.MEMBERS)){
			GlobalResources.getInstance().setModule(AppConstants.MEMBERS);
			if(user.isAdmin()){
				loadOrganisationModule();
			}else{
				//throw 404
			}
		}else if(token.equals(URLConstants.APPROVE)){
			if(!user.getApproveModules().trim().isEmpty()){
				loadApproveModule();
			}else{
				//throw 404
			}
		}else if(token.equals(URLConstants.SMS)){
			if(user.isSMSEnabled()){
				loadSMSModule();
			}else{
				//throw 404 
			}
		}else if(token.equals(URLConstants.SETTINGS)){
			loadSettingsModule();
		}else if(token.equals(URLConstants.ELIBRARY)){
			loadLibraryModules();
		}else if(token.equals(URLConstants.TITHES)){
			GlobalResources.getInstance().setModule(AppConstants.TITHES);
			loadTithesModules();
		}else if(token.equals(URLConstants.MARRIAGE)){
			GlobalResources.getInstance().setModule(AppConstants.MARRIAGE_ANNOUNCEMENTS);
			loadMarriageModules();
		}else if(token.equals(URLConstants.OFFERINGS)){
			GlobalResources.getInstance().setModule(AppConstants.OFFERINGS);
			loadOfferingsModules();
		}else if(token.equals(URLConstants.EVENTS)){
			GlobalResources.getInstance().setModule(AppConstants.EVENT_ANNOUNCEMENTS);
			loadEventModules();
		}else if(token.equals(URLConstants.FUNERAL)){
			GlobalResources.getInstance().setModule(AppConstants.FUNERAL_ANNOUNCEMENTS);
			loadFuneralModules();
		}else if(token.equals(URLConstants.BIBLE_READING)){
			GlobalResources.getInstance().setModule(AppConstants.B_READINGS);
			loadBibleReadings();
		}else if(token.equals(URLConstants.DONATION)){
			GlobalResources.getInstance().setModule(AppConstants.DONATIONS);
			loadBibleReadings();
		}else if(token.equals(URLConstants.CLASSES)){
			GlobalResources.getInstance().setModule(AppConstants.CLASSES);
			loadClassesModule();
		}else if(token.equals(URLConstants.S_OFFERING)){
			GlobalResources.getInstance().setModule(AppConstants.S_OFFERING);
			loadSpecialOfferingModule();
		}else if(token.equals(URLConstants.MY_OFFERING)){
			GlobalResources.getInstance().setModule(AppConstants.MY_OFFERING);
			loadMyOfferingModule();
		}else if(token.equals(URLConstants.BIRTHDAY)){
			GlobalResources.getInstance().setModule(AppConstants.BIRTHDAY);
			loadBirthDayModule();
		}else if(token.equals(URLConstants.PAY_TITHE)){
			//showTithePaymentPopup(null);
		}else if(token.equals(URLConstants.PAY_OFFERING)){
			//showOfferingPaymentPopup();
		}else if(token.equals(URLConstants.LOGOUT)){
			goToLogin();
		}else if(token.contains(URLConstants.CONFIRMATION) && HashURL.getValue() != null){
			GlobalResources.getInstance().setModule(AppConstants.CONFIRMATION);
			loadConfirmation();
		}else if(token.contains(URLConstants.CANCEL) && HashURL.getValue() != null){
			GlobalResources.getInstance().setModule(AppConstants.CANCEL);
			loadCancel();
		}else if(token.equals(URLConstants.ONLINE_TITHE)){
			GlobalResources.getInstance().setModule(AppConstants.ONLINE_TITHE);
			loadOnlineTithes();
		}else if(token.equals(URLConstants.ONLINE_OFFERING)){
			GlobalResources.getInstance().setModule(AppConstants.ONLINE_OFFERING);
			loadOnlineOfferings();
		}else if(token.equals(URLConstants.ONLINE_DONATION)){
			GlobalResources.getInstance().setModule(AppConstants.ONLINE_DONATION);
			loadOnlineDonations();
		}else if(token.equals(URLConstants.POWER_LEADERS)){
			GlobalResources.getInstance().setModule(AppConstants.POWER_LEADERS);
			loadPowerLeaders();
		}else if(token.equals(URLConstants.APP_STATS)){
			if(user.isAdmin()){
				GlobalResources.getInstance().setModule(AppConstants.APP_STATS);
				loadAppStats();
			}else{
				//throw 404 
			}
		}else{
			//throw 404
		}

		showAccent();
	}

	private void setModuleId(String token){
		if(token.equals(URLConstants.DASHBOARD)){
			moduleId = 0;
		}else if(token.equals(URLConstants.MEMBERS)){
			moduleId = AppConstants.M_MEMBERS;
		}else if(token.equals(URLConstants.CLASSES)){
			moduleId = AppConstants.M_MEMBERS;
		}else if(token.equals(URLConstants.APPROVE)){
			moduleId = 0;
		}else if(token.equals(URLConstants.SMS)){
			moduleId = 0;
		}else if(token.equals(URLConstants.SETTINGS)){
			moduleId = 0;
		}else if(token.equals(URLConstants.ELIBRARY)){
			moduleId = AppConstants.M_LIBRARY;
		}else if(token.equals(URLConstants.TITHES)){
			moduleId = AppConstants.M_TITHE;
		}else if(token.equals(URLConstants.MARRIAGE)){
			moduleId = AppConstants.M_MARRIAGE_ANNOUNCE;
		}else if(token.equals(URLConstants.OFFERINGS)){
			moduleId = AppConstants.M_OFFERING;
		}else if(token.equals(URLConstants.EVENTS)){
			moduleId = AppConstants.M_EVENT_ANNOUNCE;
		}else if(token.equals(URLConstants.FUNERAL)){
			moduleId = AppConstants.M_FUNERAL_ANNOUNCE;
		}else if(token.equals(URLConstants.BIBLE_READING)){
			moduleId = AppConstants.M_BIBLE_READING;
		}else if(token.equals(URLConstants.DONATION)){
			moduleId = AppConstants.M_DONATION;
		}else if(token.equals(URLConstants.S_OFFERING)){
			moduleId = AppConstants.M_SPECIAL_OFFERING;
		}else if(token.equals(URLConstants.POWER_LEADERS)){
			moduleId = AppConstants.M_POWER_LEADERS;
		}else{
			moduleId = 0;
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		HashURL.load();
		if(user == null) user = GlobalResources.getInstance().getModel();
		initContent(event.getValue().trim());
	}

	@Override
	public void go() {
		bind();
		init();
		load();
	}

	private void load(){
		user = GlobalResources.getInstance().getModel();
		entryModuleList = Utils.getTokenList(",", user.getEntryModules());
		renderApp();
	}
}
