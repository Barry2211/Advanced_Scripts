package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipKartHomePOM extends DriverUtils{
	public FlipKartHomePOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@class='Pke_EE']")
	private WebElement searchBox;
	
	public WebElement getSearchBox() {
		return searchBox;
	}
}
