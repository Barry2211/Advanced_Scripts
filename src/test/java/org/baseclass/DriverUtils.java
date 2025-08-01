package org.baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.baseclass.MethodUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class DriverUtils implements javaScriptCommands{
	
	public static WebDriver driver;
	private static final String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe"; // Use your actual SID or service name
    public static final String db_user = "HR";
    public static final String db_password = "abc";

	public static void driverInit(MethodUtils browser,String url) {
		switch(browser) {
		case CHROME:
			driver = new ChromeDriver();
			driver.get(url);
			break;
		case EDGE:
			driver = new EdgeDriver();
			driver.get(url);
			break;
		default:
			System.out.println("Invalid command");
		}
	}
	
	public static void driverWait(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public static void windowOp(MethodUtils window) {
		switch(window) {
		case min:
			driver.manage().window().minimize();
			break;
		case max:
			driver.manage().window().maximize();
			break;
		case newWindow:
			driver.switchTo().newWindow(WindowType.TAB);
			break;
		default:
			System.out.println("invalid command");
		}
	}
	
	public static void manageOps(MethodUtils command,String url) {
		switch(command) {
		case page:
			driver.navigate().to(url);
			break;
		case reload:
			driver.navigate().refresh();
			break;
		case back:
			driver.navigate().back();
			break;
		case forward:
			driver.navigate().forward();;
			break;
		default:
			System.out.println("invalid command");
		}
	}
	
	public static void driverOps(MethodUtils command) {
		switch(command) {
		case closeTab:
			driver.close();
			break;
		case closeBrowser:
			driver.quit();
			break;
		case currentUrl:
			System.out.println(driver.getCurrentUrl());
			break;
		case title:
			System.out.println(driver.getTitle());
			break;
		case source:
			System.out.println(driver.getPageSource());
			break;
		default:
			System.out.println("invalid command");
		}
	}
	
	public static WebElement locate(MethodUtils locate,String locator) {
		WebElement element;
		switch(locate) {
		case id:
			element = driver.findElement(By.id(locator));
			break;
		case className:
			element = driver.findElement(By.className(locator));
			break;
		case name:
			element = driver.findElement(By.name(locator));
			break;
		case tagName:
			element = driver.findElement(By.tagName(locator));
			break;
		case xPath:
			element = driver.findElement(By.xpath(locator));
			break;
		default:
			System.out.println("Invalid Locator");
			return null;
		}
		return element;	
	}
	
	public static List<WebElement> listElements(MethodUtils locate, String locator){
		List <WebElement> elements;
		switch(locate) {
		case id:
			elements = driver.findElements(By.id(locator));
			break;
		case className:
			elements = driver.findElements(By.className(locator));
			break;
		case tagName:
			elements = driver.findElements(By.tagName(locator));
			break;
		case name:
			elements = driver.findElements(By.name(locator));
			break;
		case xPath:
			elements = driver.findElements(By.xpath(locator));
			break;
		default:
			System.out.println("Invalid Locator");
			return null;
		}
		return elements;	
	}
	
	public static void action(MethodUtils action,WebElement target,WebElement destination) {
		switch(action) {
		case click:
			new Actions(driver).click(target).perform();
			break;
		case hower:
			new Actions(driver).moveToElement(target).perform();
			break;
		case doubleClick:
			new Actions(driver).doubleClick(target).perform();
			break;
		case rightClick:
			new Actions(driver).contextClick(target).perform();
			break;
		case dragAndDrop:
			new Actions(driver).dragAndDrop(target,destination).build().perform();
			break;
		default:
			System.out.println("Invalid Action");
		}
	}
	
	public static void type(CharSequence context,WebElement element) {
		element.sendKeys(context);
	}
	
	public static void robot(int keycode,MethodUtils key) throws AWTException {
		switch(key) {
		case press:
			new Robot().keyPress(keycode);
			break;
		case release:
			new Robot().keyRelease(keycode);
			break;		
		default:
			System.out.println("Invalid key");
		}
	}
	
	public static void alert(MethodUtils action,String keys) {
		switch(action) {
		case accept:
			driver.switchTo().alert().accept();
			break;
		case cancel:
			driver.switchTo().alert().dismiss();
			break;	
		case text:
			driver.switchTo().alert().sendKeys(keys);;
			break;	
		default:
			System.out.println("Invalid command!");
		}
	}
	
	public static void screenShot(String fileName) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("D:\\Eclipse\\eclipse\\bin\\TestNGProjects\\target\\ScreenShot\\"+fileName+".jpg");
		FileUtils.copyFile(source, destination);
		System.out.println("ScreenShot taken");
	}
	
	public static void javascript(String command){
		((JavascriptExecutor) driver).executeScript(command);
	}
	
	 public static List<ProductModels> getTestData(String filePath, String sheetName) {
	        List<ProductModels> testData = new ArrayList<>();
	        File file = new File(filePath);
	        try (
	        	FileInputStream fis = new FileInputStream(file);
	            Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                Map<String, String> dataRow = new HashMap<>();
	                Row row = sheet.getRow(i);
	                for (int j = 0; j < row.getLastCellNum(); j++) {
	                	if(row.getCell(j).getCellType()==CellType.STRING) {
	                    dataRow.put(headerRow.getCell(j).getStringCellValue(),
	                                row.getCell(j).getStringCellValue());
	                	}
	                	if(row.getCell(j).getCellType()==CellType.NUMERIC) {
		                    dataRow.put(headerRow.getCell(j).getStringCellValue(),
		                                row.getCell(j).getNumericCellValue()+"");
		                	}
	                }
	                ProductModels data = new ProductModels(
	                		dataRow.get(headerRow.getCell(0).getStringCellValue()), 
	                		dataRow.get(headerRow.getCell(1).getStringCellValue())
	                		);
	                testData.add(data);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return testData;
	    }
	 
	 public List<ProductModels> getLoginCredentials() {
	        List<ProductModels> credentialsList = new ArrayList<>();
	        String query = "SELECT * FROM Students";

	        try (Connection connection = DriverManager.getConnection(jdbc_url, db_user, db_password);
	             PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	              //  int id = resultSet.getInt("ID");
	                String name = resultSet.getString("NAME");
	                String mark = resultSet.getString("MARK");

	                credentialsList.add(new ProductModels(name, mark));
	            }

	        } catch (SQLException e) {
	            System.err.println("Database connection or query failed:");
	            e.printStackTrace();
	        }

	        return credentialsList;
	    }


}
