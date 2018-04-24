package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.generic.selector.CheckOutSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class CheckOut extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	public static class guestCheckout {
		public static void typeGuestMail(String email) throws Exception {
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			getCurrentFunctionName(true);
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", email));
			subStrArr.add(CheckOutSelectors.guestMail);
			valuesArr.add(email);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeGuestConfMail(String email) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest confirmation mail", email));
			subStrArr.add(CheckOutSelectors.guestConfirmationMail);
			valuesArr.add(email);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		// done-cbk
		public static void clickCheckoutAsGuest() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.CLICK_ELEMENT_SEL, "checkout as guest button"));
				subStrArr.add(CheckOutSelectors.guestCheckoutButton);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-cbk
		public static void fillAndClickGuestCheckout(String email) throws Exception {
			try {
				getCurrentFunctionName(true);
				// typeGuestMail(email);
				// typeGuestConfMail(email);
				clickCheckoutAsGuest();
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static void fillPreRegFormAndClickRegBtn(String password, boolean newsLetterOptin) throws Exception {
			getCurrentFunctionName(true);
			typeGuestPassPreForm(password);
			typeGuestPassConfPreForm(password);
			checkNewsletterOptin(newsLetterOptin);
			clickCreatAnaccount();
			getCurrentFunctionName(false);

		}

		public static void clickCreatAnaccount() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICK_ELEMENT_SEL, "guest creat an account"));
			subStrArr.add(CheckOutSelectors.guestCreateAccButton);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void checkNewsletterOptin(boolean newsLetterOptin) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CHECKBOX_SEL_VAL, "News Letter Optin", newsLetterOptin));
			subStrArr.add(CheckOutSelectors.guestCreateAccOtpin);
			valuesArr.add(String.valueOf(newsLetterOptin));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeGuestPassConfPreForm(String password) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password confirmation ", password));
			subStrArr.add(CheckOutSelectors.guestCreateAccCPwd);
			valuesArr.add(password);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static void typeGuestPassPreForm(String password) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "password ", password));
			subStrArr.add(CheckOutSelectors.guestCreateAccPwd);
			valuesArr.add(password);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		// done-cbk
		public static void returningCustomerLogin(String username, String password) throws Exception {
			try {
				getCurrentFunctionName(true);
				typeRetuRningcustomerUserName(username);
				typeRetuRningcustomerPassword(password);
				clickRetuRningcustomerLogin();
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// done-cbk
		private static void clickRetuRningcustomerLogin() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, "Log In and Check Out"));
				subStrArr.add(CheckOutSelectors.returningcustomerloginBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-cbk
		private static void typeRetuRningcustomerPassword(String password) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "returning customer password ",
						password));
				subStrArr.add(CheckOutSelectors.returningcustomerPassword);
				valuesArr.add(password);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-cbk
		private static void typeRetuRningcustomerUserName(String username) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "returning customer username ",
						username));
				subStrArr.add(CheckOutSelectors.returningcustomerUsername);
				valuesArr.add(username);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// done-cbk
		public static void clickOnLoginForFasterCheckout() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug("chose to Login");
				subStrArr.add(CheckOutSelectors.choseToLogin);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(1500);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

	}//guest-checkout

	public static class paymentType {
		
		public static void checkCardPayment() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.SAVING_ADDRESS);
			subStrArr.add(CheckOutSelectors.PaymentTypeSelection_CARD);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void checwkAccountPayment() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.SAVING_ADDRESS);
			subStrArr.add(CheckOutSelectors.PaymentTypeSelection_ACCOUNT);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		public static void typePurchaseOrderNumber(String PurchaseOrderNumber) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "Purchase Order Number ", PurchaseOrderNumber));
			subStrArr.add(CheckOutSelectors.PurchaseOrderNumber);
			valuesArr.add(PurchaseOrderNumber);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void selectCostCenter(String CostCenter) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.PaymentTypeCostCenterSelect);
			valuesArr.add(CostCenter);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.CLICKING_SHIPPING_NEXT_BTN);
			subStrArr.add(CheckOutSelectors.PaymentType_continue_button);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
	}
	
	public static class shippingAddress {

		public static class keys {

			public static final String isSavedShipping = "saved-shipping";

			public static final String countery = "countery";
			public static final String title = "title";
			public static final String lastName = "lastName";
			public static final String firstName = "firstName";
			public static final String adddressLine = "adddressLine";
			public static final String city = "city";
			public static final String zipcode = "postal";
			public static final String phone = "phone";
		}

		//done-cbk
		public static void selectCountery(String countery) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "Countery ", countery));
				subStrArr.add(CheckOutSelectors.countery);
				valuesArr.add(countery);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(1500);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		
		//done-cbk
		public static void typeFirstName(String firstName) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "firstname ", firstName));
				subStrArr.add(CheckOutSelectors.firstName);
				valuesArr.add(firstName);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		//done-cbk
		public static void typeAddress(String address) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "address ", address));
				subStrArr.add(CheckOutSelectors.address);
				valuesArr.add(address);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		// done-cbk
		public static void typeLastName(String lastName) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "lastname ", lastName));
				subStrArr.add(CheckOutSelectors.lastName);
				valuesArr.add(lastName);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		public static void typeCity(String city) throws Exception {
			try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "city ", city));
			subStrArr.add(CheckOutSelectors.city);
			valuesArr.add(city);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

		}

		//done-cbk
		public static void typeZipCode(String zip) throws Exception {
			try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "postal", zip));
			subStrArr.add(CheckOutSelectors.zipcode);
			valuesArr.add(zip);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

		}

		// done-cbk
		public static void typePhone(String phone) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "phone", phone));
				subStrArr.add(CheckOutSelectors.phone);
				valuesArr.add(phone);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		
		public static void checkSaveAddress(boolean check) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.SAVING_ADDRESS);
			subStrArr.add(CheckOutSelectors.CheckSaveAddress);
			valuesArr.add(String.valueOf(check));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalShippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrdersubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderSubTotalShippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		
		public static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(LoggingMsg.CLICKING_SHIPPING_NEXT_BTN);
			subStrArr.add(CheckOutSelectors.submitShippingAddressBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		// done-cbk
		public static void fillAndClickNext(String Countery, String firstName, String lastName, String address,
				String city, String state, String postal, String phone) throws Exception {
			try {
				getCurrentFunctionName(true);

				if (!"".equals(firstName))
					typeFirstName(firstName);

				if (!"".equals(lastName))
					typeLastName(lastName);

				if (!"".equals(address))
					typeAddress(address);

				if (!"".equals(city))
					typeCity(city);

				if (!"".equals(state))
					selectState(state);

				if (!"".equals(postal))
					typeZipCode(postal);

				if (!"".equals(Countery))
					selectCountery(Countery);

				if (!"".equals(phone))
					typePhone(phone);

				logs.debug("Waiting shipping address verification system");
				Thread.sleep(2000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		
		// in case of using the address book
		public static void fillAndClickNext(boolean selectFromAddressBook) throws Exception {
			getCurrentFunctionName(true);
			selectFirstAddress();
			getCurrentFunctionName(false);
		}
		
		// done- cbk
		private static void selectFirstAddress() throws Exception {
			try {
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				getCurrentFunctionName(true);
				subStrArr.add(CheckOutSelectors.addresses);
				valuesArr.add("FFF2");// pick first Address
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		//OOS- cbk
		private static void clickOnAddressBook() throws Exception {
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			getCurrentFunctionName(true);
			subStrArr.add(CheckOutSelectors.addressBookBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}
		
		public static String getAlertInfo() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.alertInfo));
			subStrArr.add(CheckOutSelectors.alertInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCountryError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.countryError));
			subStrArr.add(CheckOutSelectors.countryError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getTitelError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.titleError));
			subStrArr.add(CheckOutSelectors.titleError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getFirstNameError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.fnameError));
			subStrArr.add(CheckOutSelectors.fnameError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getLastNameError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.lnameError));
			subStrArr.add(CheckOutSelectors.lnameError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getAddress1Error() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.address1Error));
			subStrArr.add(CheckOutSelectors.address1Error);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getCityError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.cityError));
			subStrArr.add(CheckOutSelectors.cityError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getPostCodeEerror() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.postcodeEerror));
			subStrArr.add(CheckOutSelectors.postcodeEerror);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		
		public static void typeEmailAddress(String mail) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.emailAddress);
			valuesArr.add(mail);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			
		}
		
		// done-cbk
		public static void selectState(String state) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.state);
				valuesArr.add(state);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		//done-cbk
		public static void addAddress() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.addAddressChekbx);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
			
		}

	}// shipping address

	public static class shippingMethod {
		
		// done-cbk
		public static void fillAndclickNext(String shippingMethod) throws Exception {
			try {
				getCurrentFunctionName(true);
				logs.debug(shippingMethod);
				if (!shippingMethod.contains("AAGRND")) {
					// clickaddShippingMethod();
					selectShippingMethod(shippingMethod);
					// clickNext();
				} else {
					logs.debug("ignoring reselecting the shipping method");
				}
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		//OOS-cbk
		private static void clickaddShippingMethod() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.addshippingMethodBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		// done-cbk
		private static void selectShippingMethod(String shippingMethod) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				if (!shippingMethod.contains("AAGRND")) {
					subStrArr.add(CheckOutSelectors.shippingMethod + shippingMethod);
					valuesArr.add("");
					SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
					Thread.sleep(2000);
				}
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		//OOS-cbk
		private static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.submitShippingMethodbtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		// done-cbk
		public static void shippingMethodType(String shipType) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				if (shipType.toLowerCase().equals("home"))
					subStrArr.add(CheckOutSelectors.ShippingMethodType+"1");
				else
					subStrArr.add(CheckOutSelectors.ShippingMethodType+"2");
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		//done-cbk
		public static void clickContinueToPayement() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.continueToPaymentMethodBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(3000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
			
		}

		//done-cbk
		public static void writeMail(String pemail) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.ShippingMail);
				valuesArr.add(pemail);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(3000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

	}// shipping method

	public static class paymentInnformation {

		public static class keys {
			public static final String isSavedPayement = "saved-payment";

			public static final String name = "name";
			public static final String number = "number";
			public static final String expireYear = "expireYear";
			public static final String expireMonth = "expireMonth";
			public static final String CVCC = "CVCC";

		}
		
		
		// done-cbk
		public static void fill(String cardtype, String cardHolder, String cardNumber, String expireDay,
				String expireYear, String CVC) throws Exception {
			try {
				getCurrentFunctionName(true);
				if (!"".equals(cardtype))
					selectCardType(cardtype);
				if (!"".equals(cardHolder))
					typeCardholder(cardHolder);
				if (!"".equals(cardNumber))
					typeCardNumber(cardNumber);
				if (!"".equals(expireDay))
					selectExpireDay(expireDay);
				if (!"".equals(expireDay))
					typeExpireYear(expireYear);
				if (!"".equals(CVC))
					typeCVC(CVC);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}
		

		//TODO: if bw allow
		public static void fillAndclickNext(String cardtype, String cardholder, String cardNumber, String expireDay,
				String expireYear, String CVC, boolean savePayment, boolean billSameShip, String countery,
				String firstName, String lastName, String address, String city, String postal, String phone)
				throws Exception {
			getCurrentFunctionName(true);
			clickAddPaymentMethod();
			fill(cardtype, cardholder ,  cardNumber, expireDay, expireYear, CVC);

			savePaymentMethod(savePayment);

			checkBillingAddressSameshipping(billSameShip);

			//TODO: wrap this 
			if (!billSameShip && false) {
				fillBillingAddress(countery, firstName, lastName, address, city, postal, phone);
			}

			clickNext();
			Thread.sleep(1000);
			getCurrentFunctionName(false);
		}

		
		// done-cbk
		public static void fillAndclickNext(String cardtype, String cardholder, String cardNumber, String expireDay,
				String expireYear, String CVC, boolean billSameShip, String countery, String firstName, String lastName,
				String address, String city, String postal, String phone) throws Exception {
			try {
				getCurrentFunctionName(true);
				Thread.sleep(1500);
				// clickAddPaymentMethod();
				fill(cardtype, cardholder, cardNumber, expireDay, expireYear, CVC);

				// checkBillingAddressSameshipping(billSameShip);

				// if (!billSameShip) {
				// fillBillingAddress(countery, firstName, lastName, address, city, postal,
				// phone);
				// }
				clickNext();
				Thread.sleep(1000);
				getCurrentFunctionName(false);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		private static void clickAddPaymentMethod() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.addPaymentBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			
		}


		//TODO: do it in lowser envs
		// in case of using wallet
		public static void fillAndclickNext(boolean useAlreadySavedPayment) throws Exception {
			getCurrentFunctionName(true);
			//clickAddPaymentMethod();
			//clickOnUseedSavedCard();
			pickFirstpaymentsaved();
			Thread.sleep(4000);
			enterCvc("333");
			Thread.sleep(4000);
			getCurrentFunctionName(false);
		}

		//done-cbk
		private static void enterCvc(String cvc) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.CVC);
				valuesArr.add(cvc);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		
		// done-cbk
		public static void clickContinueToOrderReview() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.orderReviewBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(2000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static void fillBillingAddress(String countery, String firstName, String lastName, String address,
				String city, String postal, String phone) throws Exception {
			getCurrentFunctionName(true);

			if (!"".equals(countery))
				shippingAddress.selectCountery(countery);
			if (!"".equals(firstName))
				shippingAddress.typeFirstName(firstName);
			if (!"".equals(lastName))
				shippingAddress.typeLastName(lastName);
			if (!"".equals(address))
				shippingAddress.typeAddress(address);
			if (!"".equals(city))
				shippingAddress.typeCity(city);
			if (!"".equals(postal))
				shippingAddress.typeZipCode(postal);
			if (!"".equals(phone))
				shippingAddress.typePhone(phone);

			getCurrentFunctionName(false);
		}

		//done-cbk
		private static void pickFirstpaymentsaved() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.selectFirstPayment);
				valuesArr.add("FFF1");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		private static void clickOnUseedSavedCard() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.savedPaymentBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}
		
		
		public static void clickNext() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.submitPayment);
			valuesArr.add(String.valueOf(""));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		
		public static void checkBillingAddressSameshipping(boolean billSameShip) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.checkSame);
			valuesArr.add(String.valueOf(!billSameShip));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		
		private static void savePaymentMethod(boolean savePayment) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.checkSavePayment);
			valuesArr.add(String.valueOf(savePayment));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		}

		// done-cbk
		public static void typeCVC(String CVC) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.CVC);
				valuesArr.add(CVC);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// done-cbk
		public static void typeExpireYear(String expireYear) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.expireYear);
				valuesArr.add(expireYear);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		// done-cbk
		public static void selectExpireDay(String expireDay) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.expireDay);
				valuesArr.add(expireDay);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		//done-cbk
		public static void typeCardNumber(String cardNumber) throws Exception {
			try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.cardNumber);
			valuesArr.add(cardNumber);
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

		}
		
		// done-cbk
		public static void typeCardholder(String cardHolder) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.cardHolder);
				valuesArr.add(cardHolder);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}
		
		//done-cbk
		public static void selectCardType(String cardtype) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.cardtype);
				valuesArr.add(cardtype);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(4000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrdershipping() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderShippingPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getOrderSubTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderSubtotalPymentInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getAlertInfo() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.alertInfo));
			subStrArr.add(CheckOutSelectors.alertInfo);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCardTypeError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.cardTypeError));
			subStrArr.add(CheckOutSelectors.cardTypeError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCardNumberError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.accountNumberError));
			subStrArr.add(CheckOutSelectors.accountNumberError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getExpirationMonthError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.expirationMonthError));
			subStrArr.add(CheckOutSelectors.expirationMonthError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getExpirationYearError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.expirationYearError));
			subStrArr.add(CheckOutSelectors.expirationYearError);
			valuesArr.add("index,1");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
		
		public static String getCVNumberError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.cvNumberError));
			subStrArr.add(CheckOutSelectors.cvNumberError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
	}// payment info

	public static class reviewInformation {

		//done-cbk
		public static String getSubtotal() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.summaryTotal);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
				getCurrentFunctionName(false);
				return SelectorUtil.textValue.get().split(":")[1].trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static String shippingCost() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.shippingCost);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static void acceptTerms(boolean acceptTerm) throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.acceptTerm);
			valuesArr.add(String.valueOf(acceptTerm));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);

		}

		// done-cbk
		public static void clickPlaceOrderBtn() throws Exception {
			try {
				getCurrentFunctionName(true);
				Thread.sleep(1500);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.placeOrderBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalOrderSumary);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(SelectorUtil.textValue.get());
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String gettotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderTotalOrderSumary);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// order review information

	public static class orderConfirmation {

		public static class keys {
			public static final String isSavedPayement = "saved-payment";

			public static final String orderId = "orderId";
			public static final String email = "email";
			public static final String orderTotal = "orderTotal";
			public static final String orderSubtotal = "orderSubtotal";
			public static final String orderTax = "orderTax";
			public static final String orderShipping = "orderShipping";

		}

		// done-cbk
		public static String getOrderId() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.orderId);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
				getCurrentFunctionName(false);
				return SelectorUtil.textValue.get().split("ORDER NUMBER")[0].trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderConfirmationTotal);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		// done-cbk
		public static String getSubTotal() throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(CheckOutSelectors.orderConfirmationSubtotal);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
				getCurrentFunctionName(false);
				return SelectorUtil.textValue.get().toLowerCase().split("subtotal")[1].trim();
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}

		public static String getShippingCost() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderConfirmationShippingCost);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getBillingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderconfirmationBillingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getShippingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.orderconfirmationshippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

		public static String getBillToCountryError() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR,CheckOutSelectors.billToCountryError));
			subStrArr.add(CheckOutSelectors.billToCountryError);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.ERROR_MSG, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}

	}// order confirmation page
	
	// B2B order confirmation page
	public static class B2BOrderConfirmation {
	
		public static String getOrderTotal() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.B2BorderConfirmationTotal);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}
		public static String getBillingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.B2BorderconfirmationBillingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();

		}

		public static String getShippingAddrerss() throws Exception {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(CheckOutSelectors.B2BorderconfirmationshippingAddress);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			logs.debug(MessageFormat.format(LoggingMsg.SEL_TEXT, SelectorUtil.textValue.get()));
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		}
	}
}
