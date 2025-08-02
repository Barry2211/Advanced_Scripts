package org.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.baseclass.DriverUtils;
import org.baseclass.MethodUtils;
import org.baseclass.ProductModels;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.page.AmazonHomePOM;
import org.page.AmazonProductResultsPOM;
import org.page.AmazonResultPOM;
import org.page.FlipKartHomePOM;
import org.page.FlipKartProductResultPOM;
import org.page.FlipKartResultPOM;

public class ProductComparing extends DriverUtils{
	double flipkartPrice=0.0;
	double amazonPrice=0.0;
	static String productName="";
	static Map<String,String> tabIDs=new HashMap<>();
	@BeforeClass
	public static void driverConfig() {
		System.out.println(100);
		driverInit(MethodUtils.EDGE,"https://www.amazon.in/");
		driverWait(5);
		List<ProductModels> testData = getTestData("D:\\Eclipse\\eclipse\\bin\\FileReadingProducts\\src\\test\\resources\\Excel Data\\TestData.xlsx", "Sheet1");
		productName = testData.get(0).getBrand();
		tabIDs.put("amazon",driver.getWindowHandle());
	//	windowOp(MethodUtils.max);
		try {
			if(locate(MethodUtils.xPath, "//button[@class='a-button-text']").isDisplayed()) {
				locate(MethodUtils.xPath, "//button[@class='a-button-text']").click();
			}
		}catch(NoSuchElementException e) {}
	}
	@Test
	public void test1() {
		System.out.println(1);
		AmazonHomePOM homePom=new AmazonHomePOM();
		type(productName, homePom.getSearchBox());
		homePom.getSearchBtn().click();
		AmazonProductResultsPOM productPom = new AmazonProductResultsPOM();
		productPom.getResultProduct().click();
		AmazonResultPOM resultPom = new AmazonResultPOM();
		String text = resultPom.getProductPrice().getText().replaceAll("[^0-9]", "");
		amazonPrice = Double.parseDouble(text);
		System.out.println(amazonPrice);
	}
	@Test
	public void test2() {
		System.out.println(2);
		windowOp(MethodUtils.newWindow);
		Set<String> windows = driver.getWindowHandles();
		for(String winID:windows) {
			System.out.println(winID);
			if(!winID.equals(tabIDs.get("amazon"))) {
				driver.switchTo().window(winID);
			}
		}
		manageOps(MethodUtils.page, "https://www.flipkart.com/");
		tabIDs.put("flipkart", driver.getWindowHandle());
		FlipKartHomePOM homePom=new FlipKartHomePOM();
		type(productName, homePom.getSearchBox());
		homePom.getSearchBox().submit();
		FlipKartProductResultPOM productPom = new FlipKartProductResultPOM();
		productPom.getProduct().click();
		windows = driver.getWindowHandles();
		for(String winID:windows) {
			System.out.println(winID);
			if(!tabIDs.containsValue(winID)) {
				driver.switchTo().window(winID);
				System.out.println("Switched");
			}
		}
		FlipKartResultPOM resultPom = new FlipKartResultPOM();
		String text = resultPom.getProductPrice().getText();
		String replace = text.replaceAll("[^0-9]", "");
		flipkartPrice = Double.parseDouble(replace);
		System.out.println(flipkartPrice);
	}
	@Test
	public void test3() {
		double diff = 0.0;
		System.out.println(3);
		if(amazonPrice < flipkartPrice) {
			diff = flipkartPrice - amazonPrice;
			System.out.println("Amazon is cost effective for the product: "+productName+" with difference of "+diff);
		}else if(amazonPrice > flipkartPrice){
			diff = amazonPrice - flipkartPrice;
			System.out.println("FlipKart is cost effective for the product: "+productName+" with difference of "+diff);
		}else {
			System.out.println("Both are cost effective for the product: "+productName);
		}
	}
}
