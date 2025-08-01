package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipKartResultPOM extends DriverUtils{
	public FlipKartResultPOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='Nx9bqj CxhGGd']")
	private WebElement productPrice;
	
	public WebElement getProductPrice() {
		return productPrice;
	}
}
