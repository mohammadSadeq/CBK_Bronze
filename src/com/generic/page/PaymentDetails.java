package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.generic.page.CheckOut.shippingAddress;
import com.generic.selector.PaymentDetailsSelectors;
import com.generic.setup.SelTestCase;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.util.SelectorUtil;

public class PaymentDetails extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}
	
	public static class paymentInnformation {
	
			public static class keys {
				public static final String isSavedPayement = "saved-payment";
	
				public static final String name = "name";
				public static final String number = "number";
				public static final String expireYear = "expireYear";
				public static final String expireMonth = "expireMonth";
				public static final String CVCC = "CVCC";
	
			}
			
			
			public static void fill(String cardtype, String cardNumber, String expireDay, String expireYear,
					String CVC) throws Exception {
				getCurrentFunctionName(true);
				if (!"".equals(cardtype))
					selectCardType(cardtype);
				if (!"".equals(cardtype))
					writeCardHolder("Tester");
				if (!"".equals(cardNumber))
					typeCardNumber(cardNumber);
				if (!"".equals(expireDay))
					selectExpireMonth(expireDay);
				if (!"".equals(expireDay))
					typeExpireYear(expireYear);
				if (!"".equals(CVC))
					typeCVC(CVC);
				getCurrentFunctionName(false);
			}
			
			
		// done-cbk
		private static void writeCardHolder(String holder) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.CardHolder);
				valuesArr.add(holder);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);

			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}

		}


			//TODO: if bw allow
			public static void fillAndclickNext(String cardtype, String cardNumber, String expireDay,
					String expireYear, String CVC, boolean savePayment, boolean billSameShip, String countery,
					String firstName, String lastName, String address, String city, String postal, String phone)
					throws Exception {
				getCurrentFunctionName(true);
				clickAddPaymentMethod();
				fill(cardtype, cardNumber, expireDay, expireYear, CVC);
	
				savePaymentMethod(savePayment);
	
	
				//TODO: wrap this 
				if (!billSameShip && false) {
					fillBillingAddress(countery, firstName, lastName, address, city, postal, phone);
				}
	
				clickNext();
				Thread.sleep(1000);
				getCurrentFunctionName(false);
			}
	
			
			
			public static void fillAndclickNext(String cardtype, String cardNumber, String expireDay, String expireYear,
					String CVC, boolean billSameShip, String countery, String firstName, String lastName, String address,
					String city, String postal, String phone) throws Exception {
				getCurrentFunctionName(true);
				Thread.sleep(1500);
				clickAddPaymentMethod();
				fill(cardtype, cardNumber, expireDay, expireYear, CVC);
				
	
				//checkBillingAddressSameshipping(billSameShip);
	
	//			if (!billSameShip) {
	//				fillBillingAddress(countery, firstName, lastName, address, city, postal, phone);
	//			}
	
				clickNext();
				Thread.sleep(1000);
				getCurrentFunctionName(false);
			}
	
			
			private static void clickAddPaymentMethod() throws Exception {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.addPaymentBtn);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
				
			}
	
	
			//TODO: do it in lowser envs
			// in case of using wallet
			public static void fillAndclickNext(boolean useAlreadySavedPayment) throws Exception {
				getCurrentFunctionName(true);
				clickAddPaymentMethod();
				//clickOnUseedSavedCard();
				pickFirstpaymentsaved();
				getCurrentFunctionName(false);
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
	
			
			private static void pickFirstpaymentsaved() throws Exception {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.selectFirstPayment);
				valuesArr.add("");
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
	
			}
	
			
			public static void clickNext() throws Exception {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.submitPayment);
				valuesArr.add(String.valueOf(""));
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			}
	
			
			private static void savePaymentMethod(boolean savePayment) throws Exception {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.checkSavePayment);
				valuesArr.add(String.valueOf(savePayment));
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			}
	
			
			public static void typeCVC(String CVC) throws Exception {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.CVC);
				valuesArr.add(CVC);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
			}
	
			
			public static void typeExpireYear(String expireYear) throws Exception {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.expireYear);
				valuesArr.add(expireYear);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
	
			}
	
		// done-cbk
		public static void selectExpireMonth(String expireDay) throws Exception {
			try {
				getCurrentFunctionName(true);
				List<String> subStrArr = new ArrayList<String>();
				List<String> valuesArr = new ArrayList<String>();
				subStrArr.add(PaymentDetailsSelectors.expireMonth);
				valuesArr.add(expireDay);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				getCurrentFunctionName(false);
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
				subStrArr.add(PaymentDetailsSelectors.cardNumber);
				valuesArr.add(cardNumber);
				SelectorUtil.getNthElement(subStrArr, 1).sendKeys(cardNumber);;
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
				subStrArr.add(PaymentDetailsSelectors.cardtype);
				valuesArr.add(cardtype);
				SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
				Thread.sleep(1000);
				getCurrentFunctionName(false);
			} catch (NoSuchElementException e) {
				logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
				}.getClass().getEnclosingMethod().getName()));
				throw e;
			}
			}
	
		}// payment info

	public static void clickRemovePaymentDetailsBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_REMOVE_BUTTON, "remove address"));
		subStrArr.add(PaymentDetailsSelectors.removePaymentDetailsBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}

	public static String getFirstPaymentDetails() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.PaymentDetailsList);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.PAMENT_DETAILS, SelectorUtil.textValue.get()));
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static void clickDeleteBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.deletePaymentDetails);
		valuesArr.add("");
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_DELETE_BUTTON, "confirm delete address"));
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	//done-cbk
	public static void clickSavePayment() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PaymentDetailsSelectors.savePaymentBtn);
			valuesArr.add("");
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_DELETE_BUTTON, "confirm delete address"));
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	
	public static void fillandClickSave(String cardtype, String cardNumber, String expireDay, String expireYear,
			String CVC) throws Exception {
		getCurrentFunctionName(true);
		paymentInnformation.fill(cardtype, cardNumber, expireDay, expireYear, CVC);
		clickSavePayment();
		getCurrentFunctionName(false);
	}
	
	
	public static void clickOnAddBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PaymentDetailsSelectors.addPaymentBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		Thread.sleep(1500);
		getCurrentFunctionName(false);
	}
}
