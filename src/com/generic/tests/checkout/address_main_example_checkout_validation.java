package com.generic.tests.checkout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.generic.setup.Common;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.TestUtilities;

public class address_main_example_checkout_validation extends SelTestCase {

	private static int testCaseID;

	static List<String> subStrArr = new ArrayList<String>();
	static List<String> valuesArr = new ArrayList<String>();

	@BeforeClass
	public static void initialSetUp() throws Exception {
		tempTCID = SheetVariables.checkoutTestCaseId + "_" + testCaseID;
		caseIndex = 2;
		TestUtilities.initialize();
	}

	
	@Test
	public void signIn() throws Exception {
		setStartTime(ReportUtil.now(time_date_format));

		try {
			
			//read addresses
			LinkedHashMap<String, Object> addresses = new LinkedHashMap<>();
			addresses = Common.readAddresses();
			logs.debug(Arrays.asList(addresses));
			
			//read products
			LinkedHashMap<String, Object> products = new LinkedHashMap<>();
			products = Common.readProducts();
			logs.debug(Arrays.asList(products));
			
			//read payments
			LinkedHashMap<String, Object> payments = new LinkedHashMap<>();
			payments = Common.readPayments();
			logs.debug(Arrays.asList(payments));
			
			//Parameterized class with flow control 
			
			
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			logs.debug(t.getMessage());
			t.printStackTrace();
			String temp = getTestCaseId();
			Common.testFail(t, temp);
			Common.takeScreenShot();
			Assert.assertTrue(t.getMessage(), false);
		}

	}

}
