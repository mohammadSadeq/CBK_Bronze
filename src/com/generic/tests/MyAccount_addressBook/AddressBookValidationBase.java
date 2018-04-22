package com.generic.tests.MyAccount_addressBook;

import java.text.MessageFormat;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import java.util.LinkedHashMap;

import com.generic.page.Registration;
import com.generic.page.AddressBook;
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

public class AddressBookValidationBase extends SelTestCase {
	private static LinkedHashMap<String, Object> addresses = null;
	private static LinkedHashMap<String, Object> users = null;
	private String numberofaddresses;
	// used sheet in test
	public static final String testDataSheet = SheetVariables.AddressBookSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		addresses = Common.readAddresses();
		users = Common.readUsers();
		testObject = test;
	}

	@DataProvider(name = "AddressBook", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		return data;
		
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "AddressBook")
	public void BookAddressTest(String caseId, String runTest,String desc, String prop, String email, String newaddress)
			throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("Address_Book " + getBrowserName()));
		setTestCaseReportName("Address Book Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.ADDRESSPOOKDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		
		String caseMail = "";
		LinkedHashMap<String, Object> userdetails = null; 
		if (!email.equals(""))
		{
			userdetails = (LinkedHashMap<String, Object>) users.get(email);
			caseMail = (String) userdetails.get(Registration.keys.email);
			Testlogs.get().debug("Mail will be used is: " + caseMail);
		}
		
		
		String url = PagesURLs.getAddressBookPage();
		boolean defaultAddress = prop.contains("default Address") ;
		
		try {
			Testlogs.get().debug(caseMail);
			Testlogs.get().debug((String) userdetails.get(Registration.keys.password));
			
			SignIn.logIn(caseMail, (String) userdetails.get(Registration.keys.password));
			
			getDriver().get(url);
			
			if (prop.contains("new") || prop.contains("default") || prop.contains("delete")) {
				Thread.sleep(1000);
				AddressBook.clickAddNewAddress();
				LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses
						.get(newaddress);
				AddressBook.fillAndClickSave(caseMail,
						addressDetails.get(CheckOut.shippingAddress.keys.countery),
						RandomStringUtils.randomAlphabetic(5),
						addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						addressDetails.get(CheckOut.shippingAddress.keys.city),
						addressDetails.get(CheckOut.shippingAddress.keys.zipcode),
						addressDetails.get(CheckOut.shippingAddress.keys.phone));
				if (prop.contains("new")) {
					String siteAddress = AddressBook.getFirstAddressDetails();
					Testlogs.get().debug(siteAddress);
					sassert().assertTrue(siteAddress.toLowerCase().contains((String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine).toLowerCase()),
							"Address "+ siteAddress+" doesnt contain line1: " + (String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine));
				}
				
			}
			
			if (prop.contains("edit")) {
				String addressbook = AddressBook.getFirstAddressDetails();
				AddressBook.clickEditAddress();
				LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses.get(newaddress);
				AddressBook.fillAndClickUpdate(caseMail,
						(String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
						RandomStringUtils.randomAlphabetic(5),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine)+"nue",
						(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.zipcode),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), false);
				String newAddress = AddressBook.getFirstAddressDetails();
				sassert().assertNotEquals(addressbook,newAddress , "Address is not updated correctely : new " + newAddress +" <br> old one: "+addressbook);
			}
			
			//Remove the created address.
			if (prop.contains("delete"))
				AddressBook.clickRemoveAddress();
			
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
