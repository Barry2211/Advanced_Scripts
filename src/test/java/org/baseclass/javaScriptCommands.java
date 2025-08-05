package org.baseclass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public interface javaScriptCommands {

	public static String scrollUp = "arguments[0].scrollIntoView(true);";
	public static String scrollDown = "arguments[0].scrollIntoView(false);";
	public static String click = "arguments[0].click;";
	public static String newWindow = "window.open('https://google.com', '_blank');";
	
}
