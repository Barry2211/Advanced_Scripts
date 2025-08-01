package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonResultPOM extends DriverUtils{
	public AmazonResultPOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//span[@class='a-price-whole'])[5]")
	private WebElement productPrice;
	
	public WebElement getProductPrice() {
		return productPrice;
	}
}
