package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipKartResultPOM extends DriverUtils{
	public FlipKartResultPOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//div[contains(text(),'₹') and not(contains(text(),'/'))])[1]")
	private WebElement productPrice;
	
	public WebElement getProductPrice() {
		return productPrice;
	}
}
