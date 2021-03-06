package com.generic.tests.Registration;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.TestUtilities;
import com.generic.util.dataProviderUtils;

public class RegistrationBase extends SelTestCase {
	private static LinkedHashMap<String, Object> users =null ;
	private static LinkedHashMap<String, Object> addresses = null; 

	// possible scenarios
	public static final String freshUser = "fresh";
	public static final String existingUser = "existing";
	public static final String emptyData = "empty";
	public static final String invalidUserID = "UserID";
	public static final String passwordMismatch = "Mismatch";
	public static final String invalidPassword = "UserPassword";
	
	//messagesValidations
	public static final String successMessage = "success";
	public static final String invalidEmail = "invalidEmail";
	public static final String titleError = "titleError";
	public static final String firstNameError = "firstNameError";
	public static final String lastNameError = "lastNameError";
	public static final String passwordError = "passwordError";
	public static final String confPasswordError = "confirmPasswordError";
	public static final String passwordRulesError = "passwordRuleError";
	public static final String passwordMisatchError = "passwordMismatchError";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.registrationRegressionSheet;

	private static XmlTest testObject;
	
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>() ;

	
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		users = Common.readUsers();
		addresses = Common.readAddresses();
	}

	@DataProvider(name = "Registration", parallel = true)
	//concurrency maintenance on sheet reading 
	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Registration")
	public void registrationRegressionTest(String caseId, String runTest, String desc, String proprties, String fieldsValidation) throws Exception {
		Testlogs.set(new SASLogger("registration "+getBrowserName()));
		//Important to add this for logging/reporting 
		setTestCaseReportName("Registration Case");
		//Testlogs.get().debug("Case Browser: "  + testObject.getParameter("browserName") );
		logCaseDetailds(MessageFormat.format(LoggingMsg.REGISTRATIONDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		
		String thankUMsg = (fieldsValidation.split("ThankyouValidation:").length >2) ? fieldsValidation.split("ThankyouValidation:")[0].split("\n")[0]:"";
		String emailValidation = (fieldsValidation.split("EmailValidation:").length >2) ? fieldsValidation.split("EmailValidation:")[0].split("\n")[0]:"";
		String firstNameValidation =(fieldsValidation.split("firstNameValidation:").length >2) ?  fieldsValidation.split("firstNameValidation:")[0].split("\n")[0]:"";
		String lastNameValidation = (fieldsValidation.split("lastNameValidation:").length >2) ? fieldsValidation.split("lastNameValidation:")[0].split("\n")[0]:"";
		String passwordValidation =(fieldsValidation.split("PasswordValidation:").length >2) ?  fieldsValidation.split("PasswordValidation:")[0].split("\n")[0]:"";
		String passwordConfValidation = (fieldsValidation.split("PasswordConfValidation:").length >2) ? fieldsValidation.split("PasswordConfValidation:")[0].split("\n")[0]:"";
		
		//click on register new user button
		Registration.goToRegistrationForm();
		
		//prepare random address details
		LinkedHashMap<String, String> addressDetails = (LinkedHashMap<String, String>) addresses.get("A3");
		//Prepare registration data 
		String firstName = RandomUtilities.getRandomName();
		String lastName = RandomUtilities.getRandomName();
		String password = "P11p"+RandomUtilities.getRandomPassword(8);
		String email = RandomUtilities.getRandomEmail();
		
		try {
			if (proprties.contains(freshUser)) {
				//register new user and validate the results
				Registration.fillAndClickRegister(firstName,lastName,email,password,password,addressDetails);
				String registrationSuccessMsg = Registration.getRegistrationSuccessMessage();
				sassert().assertTrue(registrationSuccessMsg.toLowerCase().contains(firstName.toLowerCase()), 
						"Regestration Success, validation failed Expected to have in message: " + firstName +" but Actual message is: " + registrationSuccessMsg);
			}
			if (proprties.contains(existingUser)) {
				// take any user as template
				LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.entrySet().iterator()
						.next().getValue();
				email = "fnsh9109@random.com";//(String) userdetails.get(Registration.keys.email);
				logs.debug("Registration mail: "+email);
				Registration.fillAndClickRegister(firstName,lastName,email,password,password,addressDetails);
				String validationMsg = Registration.getEmailAddressError();
				sassert().assertTrue(validationMsg.contains(emailValidation), "Mail validation failed, Expected: " + emailValidation +" Actual: " + validationMsg);
			}
			if (proprties.contains(emptyData)) {
				Registration.clickRegisterButton();
				
				String validationMsg = Registration.getFirstNameError();
				sassert().assertTrue(validationMsg.contains(firstNameValidation),
						"first name validation failed Expected: " + firstNameValidation + " Actual: " + validationMsg);

				validationMsg = Registration.getLastNameError();
				sassert().assertTrue(validationMsg.contains(lastNameValidation),
						"last name validation failed Expected: " + lastNameValidation + " Actual: " + validationMsg);

				validationMsg = Registration.getEmailAddressErrorInvalid();
				sassert().assertTrue(validationMsg.contains(emailValidation),
						"Mail validation failed Expected: " + emailValidation + " Actual: " + validationMsg);

			}
			if (proprties.contains(invalidUserID)) {
				email = "invalid@valid";
				Registration.fillAndClickRegister(firstName,lastName,email,password,password,addressDetails);
				
				String validationMsg = Registration.getEmailAddressErrorInvalid();
				sassert().assertTrue(validationMsg.contains(emailValidation),
						"Mail validation failed Expected: " + emailValidation + " Actual: " + validationMsg);
			}
			
			Thread.sleep(2000);
			
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
