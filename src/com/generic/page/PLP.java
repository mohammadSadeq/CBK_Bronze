package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.generic.selector.PLPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PLP extends SelTestCase {

	public static class keys {
		public static final String caseId = "caseId";
	}

	// done-cbk
	public static void clickOnSorting() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PLPSelectors.sortingDropDown);
			valuesArr.add("ForceAction,hover");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-cbk
	public static void clickOnPriceHighToLow() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("High to low sorting");
			subStrArr.add(PLPSelectors.sortPHTL);
			SelectorUtil.getNthElement(subStrArr, 1).click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-cbk
	public static void clickOnPriceLowToHigh() throws Exception {
		try {

			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("low to High sorting");
			subStrArr.add(PLPSelectors.sortPLTH);
			SelectorUtil.getNthElement(subStrArr, 0).click();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// done-cbk
	public static boolean validateSorting(String sortType) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("verifying the PLP pricing, sort type is: " + sortType);
			subStrArr.add(PLPSelectors.productsPrices);
			String Price1 = SelectorUtil.getNthElement(subStrArr, 0).getText();
			String Price2 = SelectorUtil.getNthElement(subStrArr, 1).getText();
			String Price3 = SelectorUtil.getNthElement(subStrArr, 2).getText();
			logs.debug("Prices for first 3 products is " + Price1 + ", " + Price2 + ", " + Price3);

			double valuePrice1 = Double.parseDouble(Price1.trim().replace("$", ""));
			double valuePrice2 = Double.parseDouble(Price2.trim().replace("$", ""));
			double valuePrice3 = Double.parseDouble(Price3.trim().replace("$", ""));

			getCurrentFunctionName(false);
			if (sortType.contains("LTH"))
				return ((valuePrice1 <= valuePrice2) && (valuePrice2 <= valuePrice3));
			else
				return ((valuePrice1 >= valuePrice2) && (valuePrice2 >= valuePrice3));

		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	// done-cbk
	public static boolean sortAndValidate(String sortType) throws Exception {
		try {
			getCurrentFunctionName(true);
			logs.debug("Sorting: " + sortType);
			clickOnSorting();

			if (sortType.contains("HTL"))
				clickOnPriceHighToLow();
			if (sortType.contains("LTH"))
				clickOnPriceLowToHigh();

			Thread.sleep(7000);

			boolean validation = validateSorting(sortType);

			getCurrentFunctionName(false);
			return validation;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	public static int countProductsInPage() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug("counting the products ");
			subStrArr.add(PLPSelectors.productsSalePrices);
			int itemsNumber = SelectorUtil.getAllElements(subStrArr).size();
			logs.debug("product count is :" + itemsNumber);
			getCurrentFunctionName(false);
			return itemsNumber;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	// OOS-cbk
	public static String getNumberOfproductsInSite() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			logs.debug("getting number of items per PLP ");
			subStrArr.add(PLPSelectors.productNmber);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			String count = SelectorUtil.textValue.get();
			logs.debug("umber of items: " + count);
			getCurrentFunctionName(false);
			return count;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

}
