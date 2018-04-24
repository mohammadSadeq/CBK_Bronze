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
import com.generic.page.PaymentDetails;
import com.generic.page.Registration;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class AccountsSetup extends SelTestCase {

	private static LinkedHashMap<String, Object> addresses = null;
	private static LinkedHashMap<String, Object> invintory = null;
	private static LinkedHashMap<String, Object> paymentCards = null;
	private static LinkedHashMap<String, Object> users = null;

	// user types
	public static final String guestUser = "guest";
	public static final String freshUser = "fresh";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = "AccountSetup";


	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger("Account_setup"));
		testObject = test;
		addresses = Common.readAddresses();
		invintory = Common.readLocalInventory();
		paymentCards = Common.readPaymentcards();
		users = Common.readUsers();
	}

	@DataProvider(name = "Account_Setup", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency mentainance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Account_Setup")
	public void accountSetupBaseTest(String caseId, String runTest, String products, String shippingType,String shippingMethod, String payment,
			String shippingAddress, String billingAddress, String email) throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("AccountSetup_" + getBrowserName()));
		setTestCaseReportName("AccountSetup Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CHECKOUTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), email, email, payment, shippingMethod));

		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses
				.get(shippingAddress);
		
		String Pemail = "";
		LinkedHashMap<String, Object> userdetails = null; 
		if (!email.equals(""))
		{
			userdetails = (LinkedHashMap<String, Object>) users.get(email);
			Pemail = (String) userdetails.get(Registration.keys.email);
			Testlogs.get().debug("Mail will be used is: " + Pemail);
		}
		
		try {
			Testlogs.get().debug(Pemail);
			Testlogs.get().debug((String) userdetails.get(Registration.keys.password));

			Registration.goToRegistrationForm();
			Registration.fillAndClickRegister("Accept", "tester", Pemail,
					(String) userdetails.get(Registration.keys.password),
					(String) userdetails.get(Registration.keys.password), addressDetails);

			getDriver().get(PagesURLs.getPaymentDetailsPage());

			PaymentDetails.clickOnAddBtn();

			LinkedHashMap<String, String> paymentDetails = (LinkedHashMap<String, String>) paymentCards.get(payment);

			PaymentDetails.fillandClickSave(payment, paymentDetails.get(CheckOut.paymentInnformation.keys.number),
					paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth),
					paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear), "");
			
			/*
			
			if(!SignIn.checkUserAccount())
			{
				Testlogs.get().debug("Registeration failed");
				throw new Exception("Registeration failed");
			}
			//SignIn.logIn(Pemail, "1234567");

			Testlogs.get().debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, products.split("\n")[0]));
			LinkedHashMap<String, String> productDetails = (LinkedHashMap<String, String>) invintory
					.get(products.split("\n")[0]);
			PDP.addProductsToCartAndClickCheckOut(productDetails);

			Cart.clickCheckout();
			Thread.sleep(1000);
			// checkout- shipping address
			
			CheckOut.shippingAddress.fillAndClickNext(
					addressDetails.get(CheckOut.shippingAddress.keys.countery),
					addressDetails.get(CheckOut.shippingAddress.keys.firstName),
					addressDetails.get(CheckOut.shippingAddress.keys.lastName),
					addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
					addressDetails.get(CheckOut.shippingAddress.keys.city),
					addressDetails.get(CheckOut.shippingAddress.keys.city),
					addressDetails.get(CheckOut.shippingAddress.keys.zipcode),
					addressDetails.get(CheckOut.shippingAddress.keys.phone));
					

			// Shipping method
			CheckOut.shippingMethod.fillAndclickNext(shippingMethod);
			
			
			//click on continue to payment button 
			CheckOut.shippingMethod.shippingMethodType(shippingType);
			CheckOut.shippingMethod.clickContinueToPayement();
			
			// do not save address if scenario is guest user
			boolean saveBilling = true;
			LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String, Object>) paymentCards.get(payment);
			LinkedHashMap<String, Object> billAddressDetails = (LinkedHashMap<String, Object>) addresses
					.get(billingAddress);
			
			
			CheckOut.paymentInnformation.fill(payment, "Tester",
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.number),
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth),
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear),
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.CVCC));

			
			//go to order review
			CheckOut.paymentInnformation.clickContinueToOrderReview();
			
			CheckOut.reviewInformation.clickPlaceOrderBtn();
		*/
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
