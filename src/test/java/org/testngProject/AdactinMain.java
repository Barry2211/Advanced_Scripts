package org.testngProject;

import org.baseclass.DriverUtils;
import org.baseclass.MethodUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdactinMain extends DriverUtils{
String userName = "LifeHacker11";
	@BeforeClass
	public void browserInit() {
		driverInit(MethodUtils.EDGE, "https://adactinhotelapp.com/");
		windowOp(MethodUtils.max);
		driverWait(10);
	}
	
	@Test
	public void test() {
		System.out.println("test1");
	}
}
