package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonProductResultsPOM extends DriverUtils{
	public AmazonProductResultsPOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//span[contains(text(),'iPhone 16')])[11]")
	private WebElement resultProduct;
	
	public WebElement getResultProduct() {
		return resultProduct;
	}
}
