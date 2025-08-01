package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipKartProductResultPOM extends DriverUtils{
	public FlipKartProductResultPOM() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//div[contains(text(),'iPhone 16')])[2]")
	private WebElement product;

	public WebElement getProduct() {
		return product;
	}
}
