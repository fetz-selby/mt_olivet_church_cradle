package com.beta.rsatech.churchcradle.shared;

public interface AppConstants {
	String CURRENCY_REQUEST_URL = "https://openexchangerates.org/api/latest.json?app_id=0a585f05be3e44cba00fd1713460091b";

	String GET_URL = "http://smsgateway.me/api/v3/messages?";
	String EMAIL = "fetz.selby@gmail.com";
	String PASSWORD = "134119601Hello";
	String DEVICE_ID = "13260";
	String RECEIVED = "received";
	String SENT = "manual send";
	String QUEUED = "queued";

	String BIRTHDAY_MESSAGE = "God richly bless you and strengthens you for more years ahead";
	String PARLIAMENTARY = "M";
	String PRESIDENTIAL = "P";
	String HELP = "help";
	String INFO = "info";
	String INF = "inf";
	String INFORMATION = "information";
	String ABOUT = "about";
	String TITHE = "tithe";
	String SMS_TITHES = "tithes";
	String TITHS = "tiths";
	String TITH = "tith";
	String TT = "tt";

	String CLASS = "class";
	String CLAS = "clas";
	String CLASE = "clase";
	String CLS = "cls";
	String CL = "cl";
	String CLASES = "clases";
	String SMS_CLASSES = "classes";


	String SMS_PURCHASE = "S";
	String APP_RENEW = "R";

	String DEFAULT_GROUP = "All Members";
	String OFFERINGS = "OFFERINGS";
	String MEMBERS = "MEMBERS";
	String DONATIONS = "DONATIONS";
	String MARRIAGE_ANNOUNCEMENTS = "MARRIAGE ANNOUNCEMENTS";
	String FUNERAL_ANNOUNCEMENTS = "FUNERAL ANNOUNCEMENTS";
	String EVENT_ANNOUNCEMENTS = "EVENTS ANNOUNCEMENTS";
	String SMS = "SMS";
	String B_READINGS = "BIBLE READINGS";
	String LIBRARY = "LIBRARY";
	String TITHES = "TITHES";
	String PENDING = "P";
	String ACTIVE = "A";
	String SMS_LABEL = "SMS";
	String CLASSES = "CLASSES";
	String S_OFFERING = "SPECIAL OFFERINGS";
	String MY_OFFERING = "MY OFFERINGS";
	String BIRTHDAY = "BIRTHDAY";
	String CONFIRMATION = "CONFIRM";
	String CANCEL = "CANCEL";
	String ONLINE_OFFERING = "ONLINE OFFERINGS";
	String ONLINE_TITHE = "ONLINE TITHES";
	String ONLINE_DONATION = "ONLINE DONATIONS";
	String POWER_LEADERS = "POWER LEADERS";
	String APP_HEALTH = "App Health";
	String APP_STATS = "APP STATISTICS";



	String SMS_CHARACTER_LABEL = "Character(s)";
	int SMS_THRESHOLD = 621;
	int SMS_SENDER_ID_LIMIT = 12;
	int NATIVE = 0;
	int REGULAR = 1;
	int EPOC = 1900;
	String SMS_NUMBER_PLACEHOLDER = "eg 23324*******,23280********";
	String JAN = "01";
	String FEB = "02";
	String MAR = "03";
	String APR = "04";
	String MAY = "05";
	String JUN = "06";
	String JUL = "07";
	String AUG = "08";
	String SEP = "09";
	String OCT = "10";
	String NOV = "11";
	String DEC = "12";
	String BG_SUCCESS = "bg-success";
	String BG_DANGER = "bg-danger";
	String BG_INFO = "bg-info";
	String BG_PRIMARY = "bg-primary";
	String JANUARY = "January";
	String FEBUARY = "February";
	String MARCH = "March";
	String APRIL = "April";
	String MAYW = "May";
	String JUNE = "June";
	String JULY = "July";
	String AUGUST = "August";
	String SEPTEMBER = "September";
	String OCTOBER = "October";
	String NOVEMBER = "November";
	String DECEMBER = "December";
	String NO_IMAGE = "images/no-image.jpg";
	String MALE = "M";
	String FEMALE = "F";
	String BUTTON_DONE = "Done";
	String BUTTON_NEXT = "Next";
	String BUTTON_CHECKOUT = "Checkout >";
	
	String MARRIED = "M";
	String SINGLE = "S";
	String DIVORCED = "D";
	String WIDOW = "W";

	//Truncation
	int MEMBER_ORGANISATION_LIMIT = 18;

	int APPROVE_SMS_MESSAGE_LIMIT = 40;
	int APPROVE_MA_DESCRIPTION_LIMIT = 24;
	int APPROVE_MA_VENUE_LIMIT = 16;
	int APPROVE_MEM_OCCUPATION_LIMIT = 26;
	int APPROVE_OFFERING_DESCRIPTION_LIMIT = 32;
	int APPROVE_BR_THEME_LIMIT = 17;
	int APPROVE_BR_VERSE_LIMIT = 17;
	int APPROVE_FA_NOTICE_LIMIT = 40;
	int APPROVE_EA_DESCRIPTION_LIMIT = 26;
	int APPROVE_EA_VENUE_LIMIT = 16;
	int APPROVE_DONATION_DESCRIPTION_LIMIT = 40;

	int ANNOUNCE_FUNERAL_TITLE = 17;
	int ANNOUNCE_EVENT_TITLE = 60;
	int ANNOUNCE_VENUE = 40;
	int ANNOUNCE_MARRIAGE_OCCUPATION = 28;
	int ANNOUNCE_MARRIAGE_NAME = 15;
	int SPECIAL_OFFERING_TITLE = 60;
	int SPECIAL_OFFERING_NAME = 40;



	//Module Map
	int M_MARRIAGE_ANNOUNCE = 1;
	int M_LIBRARY = 2;
	int M_MEMBERS = 4;
	int M_OFFERING = 5;
	int M_TITHE = 6;
	int M_BIBLE_READING = 7;
	int M_FUNERAL_ANNOUNCE = 8;
	int M_EVENT_ANNOUNCE = 9;
	int M_DONATION = 10;
	int M_SPECIAL_OFFERING = 11;
	int M_POWER_LEADERS = 12;

	//Add Member
	String ADD_MEMBER_TITLE = "Add Member";
	String UPDATE_MEMBER_TITLE = "Update Member";

	String UPDATE_PASSWORD = "Update Password";

	String FORGOT_PASSWORD = "Fogot Password";

	//Member profile
	String PROFILE_EMPTY_PLACEHOLDER = "Not Specified";

	//Add Marriage
	String ADD_MARRIAGE_TITLE = "Add Marriage";
	int REVIEW_DESCRIPTION_LIMIT = 21;

	//Add Funeral
	String ADD_FUNERAL_TITLE = "Add Funeral";

	//Add Event
	String ADD_EVENT_TITLE = "Add Event";
	int EVENT_REVIEW_DESCRIPTION = 60;

	//Add Offering
	String ADD_OFFERING_TITLE = "Add Offering";

	//SMS Purchase
	String SMS_PURCHASE_TITLE = "SMS Purchase";

	//App Renew
	String APP_RENEW_TITLE = "App Renew Purchase";

	//Add Tithe
	String ADD_TITHE_TITLE = "Add Tithe";

	//Add Special Offering
	String ADD_SPECIAL_OFFERING_TITLE = "Add Special Offering";

	//Add Bible Reading
	String ADD_BIBLE_READING_TITLE = "Add Bible Reading";

	//Pay Tithe
	String ADD_PAYMENT_TITLE = "Pay Tithe";

	//Pay Offering
	String PAY_OFFERING_TITLE = "Pay Offering";

	//Annoymous Donation
	String ANNOYMOUS_DONATION_TITLE = "Annoymous Donation";

	String GROUPS = "G";
	String MSISDN = "M";

	String LITE = "L";
	String BASIC = "B";
	String FULL = "F";

}
