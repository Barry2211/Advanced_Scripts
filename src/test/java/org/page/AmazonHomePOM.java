package org.page;

import org.baseclass.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePOM extends DriverUtils{
	
	public AmazonHomePOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	@FindBy(id="nav-search-submit-button")
	private WebElement searchBtn;
	
	public WebElement getSearchBox() {
		return searchBox;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
}