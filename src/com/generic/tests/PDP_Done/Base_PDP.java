/**
 * this generic test for PDP regression that will pull tests from PDPRegression tab from
 * datasheet.xlsx. 
*/
package com.generic.tests.PDP_Done;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class Base_PDP extends SelTestCase {

	private static LinkedHashMap<String, Object> invintory;
	private static LinkedHashMap<String, Object> users;

	// user types
	public static final String guestUser = "guest";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.PDPSheet;

	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	
	LinkedHashMap<String, Object> productDetails;

	@BeforeClass
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.PDPCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		invintory = Common.readLocalInventory();
		users = Common.readUsers();
	}

	@DataProvider(name = "PDPs", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@Test(dataProvider = "PDPs")
	public void PDPBaseTest(String caseId, String runTest, String desc, String proprties, String product,
			String email, String ValidationMsg) throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("PDP_" + getBrowserName()));
		setTestCaseReportName("PDP Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.PDPDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		
		String UsedEmail = "";
		LinkedHashMap<String, Object> userdetails = null; 
		if (!email.equals(""))
		{
			userdetails = (LinkedHashMap<String, Object>) users.get(email);
			UsedEmail = (String) userdetails.get(Registration.keys.email);
			Testlogs.get().debug("Mail will be used is: " + UsedEmail);
		}
		
		try {

			if (proprties.contains("Loggedin")) {
				Testlogs.get().debug("Used mail to login: "+UsedEmail);
				Testlogs.get().debug("Used Password to login: "+(String) userdetails.get(Registration.keys.password) );
				SignIn.logIn(UsedEmail, (String) userdetails.get(Registration.keys.password));
			}
			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			Testlogs.get().debug("productDetails to be visted: " + Arrays.asList(productDetails));
			Testlogs.get().debug("url key " + PDP.keys.url);
			Testlogs.get().debug("url key value " + (String) productDetails.get(PDP.keys.url));
			getDriver().get((String) productDetails.get(PDP.keys.url));

			
			//Apply color and check of the results reflected to PDP
			if (!((String) productDetails.get(PDP.keys.color)).equals("") && proprties.contains("color") ){
				logs.debug("selecting color: " + (String) productDetails.get(PDP.keys.color) );
				PDP.selectColor((String) productDetails.get(PDP.keys.color));
				logs.debug("checking PDP selected color");
				String color = PDP.getcolor();
				sassert().assertTrue(color.contains((String) productDetails.get(PDP.keys.color)), "<font color=#f442cb>product color is not expected <br>: "+color+"</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//color check
			
			//Apply size and it's family and check of the results reflected to PDP
			if (!((String) productDetails.get(PDP.keys.sizeFamily)).equals("") 
					&& proprties.contains("size") && !((String) productDetails.get(PDP.keys.size)).equals("") ){
				logs.debug("selecting sizeFamily: " + (String) productDetails.get(PDP.keys.sizeFamily) );
				PDP.selectFamilySize((String) productDetails.get(PDP.keys.sizeFamily));
				
				logs.debug("selecting size: " + (String) productDetails.get(PDP.keys.size) );
				PDP.selectSize((String) productDetails.get(PDP.keys.size));
								
				logs.debug("checking PDP selected size and family size");
				String SizeAndFamilyContent = PDP.getSelectedSizeAndFamily();
				
				String sizeAndFamily = (String) productDetails.get(PDP.keys.sizeFamily)+" - "+(String) productDetails.get(PDP.keys.size);
				sassert().assertTrue(SizeAndFamilyContent.contains(sizeAndFamily), "<font color=#f442cb>product size is not expected <br>: "+SizeAndFamilyContent+"</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//size check
			
			
			//Apply size and it's family and check of the results reflected to PDP
			if (!((String) productDetails.get(PDP.keys.length)).equals("") 
					&& proprties.contains("length") ){
				logs.debug("selecting length: " + (String) productDetails.get(PDP.keys.length) );
				PDP.selectLength((String) productDetails.get(PDP.keys.length));
				
				logs.debug("checking PDP selected length");
				String SelectedLength = PDP.getselectedLength();
				
				sassert().assertTrue(SelectedLength.toLowerCase().contains(PDP.keys.length.toLowerCase()),
						"<font color=#f442cb>product length is not expected <br>: " + SelectedLength + " from sheet:"
								+ PDP.keys.length.toLowerCase() + "</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//size check
			
			if (proprties.contains("price") ){
				logs.debug("checking PDP price");
				String price = PDP.getPrice();
				sassert().assertTrue(price.contains((String) productDetails.get(PDP.keys.price)), "<font color=#f442cb>product price is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//price check

			if (proprties.contains("title") ){
				logs.debug("checking PDP title");
				String title = PDP.getTitle();
				sassert().assertTrue(title.contains((String) productDetails.get(PDP.keys.title)), "<font color=#f442cb>product title is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//title check
			
			if (proprties.contains("add to cart button") ){
				logs.debug("checking PDP add to cart button");
				sassert().assertTrue(PDP.checkAddToCartButton(), "<font color=#f442cb>product desc is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//add to cart button check
			
			if (proprties.contains("id") ){
				logs.debug("checking PDP ID");
				String Id = PDP.getId();
				sassert().assertTrue(Id.contains((String) productDetails.get(PDP.keys.id)), "<font color=#f442cb>product id is not expected</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//id check
			
			if (proprties.contains("info") ){
				logs.debug("checking PDP info");
				String information = PDP.getProductInfo();
				sassert().assertTrue(information.trim().contains(((String) productDetails.get(PDP.keys.info)).trim()),
						"<font color=#f442cb>product info is not expected found: " + information + " <br>expected: "
								+ (String) productDetails.get(PDP.keys.info) + "</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//info check
			
			
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

}
