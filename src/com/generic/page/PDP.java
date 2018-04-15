package com.generic.page;

import java.util.ArrayList;
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
import com.generic.setup.SelTestCase;
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

	
	public static void addProductsToCartAndClickCheckOut(String url, String qty) throws Exception {
		getCurrentFunctionName(true);
		addProductsToCart(url, qty);
		clickcheckoutBtnCartPopup();
		getCurrentFunctionName(false);
	}

	
	public static void addProductsToCart(String url, String qty) throws Exception {
		getCurrentFunctionName(true);
		getDriver().get(url);
		defineQty(qty);
		clickAddToCartBtn();
		Thread.sleep(1000);
		getCurrentFunctionName(false);
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
			throw e;
		}
	}

	
	private static void clickcheckoutBtnCartPopup() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cart_popup);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	
	private static void clickAddToCartBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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
			logs.debug("<p style='font-weight: bold; color:red'>Page function failed:" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "</p>");
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






}
