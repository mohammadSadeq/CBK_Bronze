package com.generic.tests.checkout;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.LinkedHashMap;

import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class Base_checkout extends SelTestCase {

	private static LinkedHashMap<String, Object> addresses = null ;
	private static  LinkedHashMap<String, Object> invintory = null ;
	private static  LinkedHashMap<String, Object> paymentCards = null;
	private static  LinkedHashMap<String, Object> users =null ;

	// user types
	public static final String guestUser = "guest";
	public static final String freshUser = "fresh";
	public static final String loggedInUser = "loggedin";
	public static final String loggedDuringChcOt = "logging During Checkout";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.checkoutSheet;

	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ; 
	
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger("checkout_setup"));
		testObject = test;
		addresses = Common.readAddresses();
		invintory = Common.readLocalInventory();
		paymentCards = Common.readPaymentcards();
		users = Common.readUsers();
	}

	@DataProvider(name = "Orders", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		//concurrency mentainance on sheet reading 
		getBrowserWait(testObject.getParameter("browserName"));
		
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Orders")
	public void checkOutBaseTest(String caseId, String runTest, String desc, String proprties, String products,
			String shippingType, String shippingMethod, String payment, String shippingAddress, String billingAddress,
			String coupon, String email) throws Exception {
		//Important to add this for logging/reporting 
		Testlogs.set(new SASLogger("checkout_"+getBrowserName()));
		setTestCaseReportName("Checkout Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CHECKOUTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- "), payment, shippingMethod));
		
		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses.get(shippingAddress);
		
		String Pemail;
		String orderId;
		String orderTotal;
		String orderSubtotal;
		String orderTax;
		String orderShipping;
		
		
		Pemail = "";
		LinkedHashMap<String, Object> userdetails = null; 
		if (!email.equals(""))
		{
			userdetails = (LinkedHashMap<String, Object>) users.get(email);
			Pemail = (String) userdetails.get(Registration.keys.email);
			Testlogs.get().debug("Mail will be used is: " + Pemail);
		}
		
		try {
			if (proprties.contains(loggedInUser)) {
				//you need to maintain the concurrency and get the main account information and log in in browser account 
				Testlogs.get().debug(Pemail);
				Testlogs.get().debug((String) userdetails.get(Registration.keys.password) );
				SignIn.logIn(Pemail, (String) userdetails.get(Registration.keys.password));
			}
			if (proprties.contains(freshUser)) {
				Pemail = RandomUtilities.getRandomEmail();

				// take any user as template
				LinkedHashMap<String, Object> RandomUserdetails = (LinkedHashMap<String, Object>) users.entrySet().iterator()
						.next().getValue();

				Registration.goToRegistrationForm();
				Registration.fillAndClickRegister((String) RandomUserdetails.get(Registration.keys.firstName),
						(String) RandomUserdetails.get(Registration.keys.lastName),
						Pemail, (String) RandomUserdetails.get(Registration.keys.password),
						(String) RandomUserdetails.get(Registration.keys.password), addressDetails);
			}
			
			if (products.contains("auto") || products.equals("")) {
				PDP.navigateToRandomPDP("Tank");
				PDP.addProductsToCart();
				PDP.clickcheckoutBtnCartPopup();
				
			} else {
				for (String product : products.split("\n")) {
					Testlogs.get().debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
					LinkedHashMap<String, String> productDetails = (LinkedHashMap<String, String>) invintory
							.get(product);
					PDP.addProductsToCartAndClickCheckOut(productDetails);
				}
			}
			

			// flow to support coupon validation
			if (!"".equals(coupon)) {
				Cart.applyPromotion(coupon);
				if (coupon.contains(Cart.keys.invalidCoupon)) {
					Cart.validateCoupon();
				}
			}
			orderSubtotal = Cart.getOrderSubTotal();
			Cart.clickCheckout();
			
			if (proprties.contains(loggedDuringChcOt)) {
				Testlogs.get().debug("Login during checkout with: "+Pemail);
				Testlogs.get().debug("Using password: "+(String) userdetails.get(Registration.keys.password) );
				CheckOut.guestCheckout.clickOnLoginForFasterCheckout();
				CheckOut.guestCheckout.returningCustomerLogin(Pemail, (String) userdetails.get(Registration.keys.password));
			}
			if (proprties.contains(guestUser)) {
				Pemail = RandomUtilities.getRandomEmail();
				CheckOut.guestCheckout.fillAndClickGuestCheckout(Pemail);
			}

			Thread.sleep(1000);

			//checkout Shipping method Type
			CheckOut.shippingMethod.shippingMethodType(shippingType);
			ReportUtil.takeScreenShot(getDriver());
			
			// checkout- shipping address
			if ((proprties.contains(CheckOut.shippingAddress.keys.isSavedShipping) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser) ) || proprties.contains(loggedInUser) || proprties.contains(loggedDuringChcOt)
					&& !proprties.contains(freshUser) ){
				CheckOut.shippingAddress.fillAndClickNext(true);//pick first saved option 
				Thread.sleep(1000);
			} else {
				
				if (!proprties.contains(guestUser)) 
					CheckOut.shippingAddress.addAddress(); //this button is not exist in guest checkout
				
				// in case guest the save shipping check-box is not exist
				CheckOut.shippingAddress.fillAndClickNext(
						addressDetails.get(CheckOut.shippingAddress.keys.countery),
						addressDetails.get(CheckOut.shippingAddress.keys.firstName),
						addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						addressDetails.get(CheckOut.shippingAddress.keys.city),
						addressDetails.get(CheckOut.shippingAddress.keys.city),
						addressDetails.get(CheckOut.shippingAddress.keys.zipcode),
						addressDetails.get(CheckOut.shippingAddress.keys.phone));
				CheckOut.shippingMethod.writeMail(Pemail);
			}
			ReportUtil.takeScreenShot(getDriver());
			
			// Shipping method
			CheckOut.shippingMethod.fillAndclickNext(shippingMethod);
			ReportUtil.takeScreenShot(getDriver());
			
			
			//click on continue to payment button 
			CheckOut.shippingMethod.clickContinueToPayement();
			ReportUtil.takeScreenShot(getDriver());
			
			// checkout- payment
			if ((proprties.contains(CheckOut.paymentInnformation.keys.isSavedPayement) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser))|| proprties.contains(loggedInUser)|| proprties.contains(loggedDuringChcOt)) {
				ReportUtil.takeScreenShot(getDriver());
				CheckOut.paymentInnformation.fillAndclickNext(true);
				ReportUtil.takeScreenShot(getDriver());
			} else {

				// do not save address if scenario is guest user
				boolean saveBilling = !proprties.contains(guestUser);
				LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String, Object>) paymentCards
						.get(payment);
				LinkedHashMap<String, Object> billAddressDetails = (LinkedHashMap<String, Object>) addresses
						.get(billingAddress);

			
				CheckOut.paymentInnformation.fill(payment, "Tester",
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.number),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear),
						(String) paymentDetails.get(CheckOut.paymentInnformation.keys.CVCC));
			}
			//Waiting payment to be processed
			if(getBrowserName().equals("firefox"))
			Thread.sleep(1000);
			
			
			//go to order review
			CheckOut.paymentInnformation.clickContinueToOrderReview();
			ReportUtil.takeScreenShot(getDriver());
			
			String orderReviewSubtotal  = CheckOut.reviewInformation.getSubtotal();
			sassert().assertEquals(orderReviewSubtotal, orderSubtotal ,
					"Subtotal in cart "+orderSubtotal+" is not matching chekout review pages: " + orderReviewSubtotal);
			CheckOut.reviewInformation.clickPlaceOrderBtn();
			ReportUtil.takeScreenShot(getDriver());
			
			
			
			// Validate the order sub total in order review section
			String orderconfirmationsubtotal = CheckOut.orderConfirmation.getSubTotal();
			sassert().assertEquals(orderconfirmationsubtotal, orderSubtotal,
					"Subtotal in cart "+orderSubtotal+" is not matching chekout order confirmation pages: " + orderconfirmationsubtotal);
			orderId = CheckOut.orderConfirmation.getOrderId();

			/*
			//TODO: lower
			orderTotal = CheckOut.orderConfirmation.getOrderTotal();
			orderShipping = CheckOut.orderConfirmation.getShippingCost();

			if (proprties.contains(guestUser) && proprties.contains("register-guest")) {
				CheckOut.guestCheckout.fillPreRegFormAndClickRegBtn("1234567", false);
			}
			 */
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.CHECKOUT_RESULT , Pemail,orderId,orderSubtotal));
			
			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test
}// class
