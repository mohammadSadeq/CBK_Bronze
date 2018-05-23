package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.generic.selector.PDPSelectors;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.util.ReportUtil;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {

	public static class keys {
		public static final String id = "id";
		public static final String name = "name";
		public static final String title = "title";
		public static final String url = "url";
		public static final String qty = "qty";
		public static final String color = "color";
		public static final String sizeFamily = "sizeFamily";
		public static final String size = "size";
		public static final String info = "info";
		public static final String price = "price";
		public static final String length = "length";

	}

	//done- CBK
	public static void addProductsToCartAndClickCheckOut(LinkedHashMap<String, String> productDetails) throws Exception {
		try {
			getCurrentFunctionName(true);
			addProductsToCart(productDetails);
			clickcheckoutBtnCartPopup();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done- CBK
	public static void addProductsToCart(LinkedHashMap<String, String> productDetails) throws Exception {
		try {
			getCurrentFunctionName(true);
			getDriver().get(productDetails.get(keys.url));
			if (!PDP.keys.color.equals(""))
				selectColor((String) productDetails.get(PDP.keys.color));
			
			if (!((String) productDetails.get(PDP.keys.sizeFamily)).equals("")){
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
			if (!((String) productDetails.get(PDP.keys.length)).equals("")){
				logs.debug("selecting length: " + (String) productDetails.get(PDP.keys.length) );
				PDP.selectLength((String) productDetails.get(PDP.keys.length));
				
				logs.debug("checking PDP selected length");
				String SelectedLength = PDP.getselectedLength();
				
				sassert().assertTrue(SelectedLength.toLowerCase().contains(PDP.keys.length.toLowerCase()),
						"<font color=#f442cb>product length is not expected <br>: " + SelectedLength + " from sheet:"
								+ PDP.keys.length.toLowerCase() + "</font>");
				ReportUtil.takeScreenShot(getDriver());
			}//size check
			clickAddToCartBtn();
			Thread.sleep(1000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	
	//done-CBK - for random selection
	public static void addProductsToCart() throws Exception {
		try {
			getCurrentFunctionName(true);
			
			selectRandomColor();
			selectRandomFamilySize();
			selectRandomSize();
			selectRandomLength();
			
			clickAddToCartBtn();
			Thread.sleep(1000);
			
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-CBK
	public static String getPrice() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.price);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	
	public static void clickcheckoutBtnCartPopup() throws Exception {
		getCurrentFunctionName(true);
		//TODO: pull from config
		getDriver().get(PagesURLs.getHomePage() +PagesURLs.getShoppingCartPage());
		getCurrentFunctionName(false);
	}

	//done-CBK
	private static void clickAddToCartBtn() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.addToCartBtn);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//OOS-cbk
	private static void defineQty(String qty) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.qty);
		valuesArr.add(qty);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	//Done-CBK
	public static String getId() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.id);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//Done-CBK
	public static String getTitle() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.title);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//Done-CBK
	public static String getProductInfo() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.information);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			String information = SelectorUtil.textValue.get();
			logs.debug("product info: " +information );
			getCurrentFunctionName(false);
			return information;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}

	//done-CBK
	public static boolean checkAddToCartButton() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.addToCartBtn);
			boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
			logs.debug("existence check result is " + isDisplayed);
			getCurrentFunctionName(false);
			return isDisplayed;
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	//Done-CBK
	public static String getcolor() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.colorLable);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	//Done-CBK
	public static void selectFamilySize(String FamilySize) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(FamilySize);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}
	
	// Done-CBK
	public static void selectRandomFamilySize() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.randomFamilySize);
			List <WebElement> FSs = SelectorUtil.getAllElements(subStrArr);
			if (FSs.size()!=0)
			{
				FSs.get(0).click();
			}
			else {
				logs.debug("ignoring selecting family size");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}
	
	//Done-CBK
	public static void selectSize(String size) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add("Select size: "+size);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}
	
	
	// Done-CBK
	public static void selectRandomSize() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.randomSize);
			List <WebElement> sizes = SelectorUtil.getAllElements(subStrArr);
			if (sizes.size()!=0)
			{
				sizes.get(0).click();
			}
			else {
				logs.debug("ignoring selecting size");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	//Done-CBK
	public static String getSelectedSizeAndFamily() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.sizeAndFamilyLable);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	
	// Done-CBK
	public static void selectLength(String length) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(length);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	// Done-CBK
	public static void selectRandomLength() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.randomLength);
			List <WebElement> lengths = SelectorUtil.getAllElements(subStrArr);
			if (lengths.size()!=0)
			{
				lengths.get(0).click();
			}
			else {
				logs.debug("ignoring selecting lenghth");
			}
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	//Done-CBK
	public static String getselectedLength() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.lengthLable);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
			return SelectorUtil.textValue.get();
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
	}
	
	//Done-CBK
	public static void selectColor(String color) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.color + color);
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
	
	// Done-CBK
	public static void selectRandomColor() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.randomColor);
			List <WebElement> colors = SelectorUtil.getAllElements(subStrArr);
			if (colors.size()!=0)
			{
				colors.get(0).click();
			}
			else {
				logs.debug("ignoring selecting color");
			}
			Thread.sleep(3000);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}

	}

	
	public static void hoverMiniCart() throws Exception {
		getCurrentFunctionName(true);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SelTestCase.getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	
		WebElement field = getDriver().findElement(By.id(PDPSelectors.minicart));
		
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(false)", field);
		Thread.sleep(200);
		WebElement field2 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id(PDPSelectors.minicart));
				}
			});
	
			Actions HoverAction = new Actions(getDriver());
			HoverAction.moveToElement(field2).click().build().perform();
		getCurrentFunctionName(false);
	}

	
	public static String getProductQtyInMiniCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cartPopupProductQty);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	
	public static String getProductUnitPriceInMiniCart() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.miniCartProductUnitPrice);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static void navigateToRandomPDP(String keyword) throws Exception {
		try {
			getCurrentFunctionName(true);
			searchOnKeyword(keyword);
			pickRandomProduct();
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}

	//done-cbk
	private static void searchOnKeyword(String keyword) throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.searchBox);
			valuesArr.add(keyword+",pressEnter");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}

	//done-cbk
	private static void pickRandomProduct() throws Exception {
		try {
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			List<String> valuesArr = new ArrayList<String>();
			subStrArr.add(PDPSelectors.RandomPDP);
			valuesArr.add("");
			SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
			getCurrentFunctionName(false);
		} catch (NoSuchElementException e) {
			logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
			}.getClass().getEnclosingMethod().getName()));
			throw e;
		}
		
	}




}
