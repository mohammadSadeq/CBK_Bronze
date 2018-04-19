package com.generic.tests.MyAccount_Payment;

import java.text.MessageFormat;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import java.util.LinkedHashMap;
import com.generic.page.PaymentDetails;
import com.generic.page.Registration;
import com.generic.page.CheckOut;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.dataProviderUtils;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class PaymentDetailsValidationBase extends SelTestCase {

	private static LinkedHashMap<String, Object> addresses = null;
	private static LinkedHashMap<String, Object> invintory = null;
	private static LinkedHashMap<String, Object> paymentCards = null;
	private static LinkedHashMap<String, Object> users = null;
	String DefaultPaymentDetails;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.PaymentDetailsSheet;

	//private String email;
	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger("paymentDetails_setup"));
		testObject = test;
		addresses = Common.readAddresses();
		paymentCards = Common.readPaymentcards();
		users = Common.readUsers();
	}

	@DataProvider(name = "Payment", parallel = true)
	// concurrency maintenance on sheet reading
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Payment")
	public void PaymentDetailsTest(String caseId, String runTest, String desc, String proprties,
			String payment,  String billingAddress, String email) throws Exception {
		Testlogs.set(new SASLogger("Payment_" + getBrowserName()));
		// Important to add this for logging/reporting
		setTestCaseReportName("Payment Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.PAYMENTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- "), payment, ""));
		
		String url =  PagesURLs.getPaymentDetailsPage();
		
		String caseMail = "";
		LinkedHashMap<String, Object> userdetails = null; 
		if (!email.equals(""))
		{
			userdetails = (LinkedHashMap<String, Object>) users.get(email);
			caseMail = (String) userdetails.get(Registration.keys.email);
			Testlogs.get().debug("Mail will be used is: " + caseMail);
		}
		
		try {

			// you need to maintain the concurrency and get the main account
			// information and log in in browser account
			Testlogs.get().debug(caseMail);
			Testlogs.get().debug((String) userdetails.get(Registration.keys.password));
			
			SignIn.logIn(caseMail, (String) userdetails.get(Registration.keys.password));
			// Go to Payment details page.
			getDriver().get(url);

			// checkout- payment
			LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String, Object>) paymentCards.get(payment);
			//LinkedHashMap<String, Object> billAddressDetails = (LinkedHashMap<String, Object>) addresses.get(billingAddress);

			logs.debug(Arrays.asList(paymentDetails)+"");
			
			PaymentDetails.clickOnAddBtn();
			
			PaymentDetails.fillandClickSave(payment,
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.number),
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireMonth),
					(String) paymentDetails.get(CheckOut.paymentInnformation.keys.expireYear),"");
			
			if (desc.contains("delete")) {
				//TODO:tweak
				PaymentDetails.clickRemovePaymentDetailsBtn();
				PaymentDetails.clickDeleteBtn();
				Thread.sleep(1000);
			}
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
