package com.cjc.ReadExcelData;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MercuryTest 
{
	public WebDriver driver;
	
  @Test(dataProvider = "test")
  public void loginWithValid1(String username, String password) 
  {
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name='login']")).click();
	  System.out.println("Login Successfully");
	  driver.findElement(By.linkText("SIGN-OFF")).click();
  }
  @BeforeMethod
  public void addCookies() 
  {
	  Set<Cookie>coo=driver.manage().getCookies();
	  for(Cookie cookies : coo)
	  {
		  System.out.println("Cokkies name: "+cookies.getName());
	  }
  }

  @AfterMethod
  public void screenshot() throws IOException 
  {
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("E:\\Automation\\ExcelHandling\\src\\test\\resources\\Screenshot\\test.jpg"));
  }


  @Test
@DataProvider
  public Object[][] test() throws IOException 
  {
	  ExcelDataConfig config=new ExcelDataConfig();
	  config.exceldata("E:\\Automation\\ExcelHandling\\src\\test\\resources\\TourExample.xlsx");
	  int row=config.getrowcount("Sheet1");
	  int column=config.getcolumncount("Sheet1");
	  
	  Object[][] user=new Object[row][column];
	  for(int i=0;i<row;i++)
	  {
		  for(int j=0;j<column;j++)
		  {
			  user[i][j]=config.getdata("Sheet1", i, j);
		  }
	  }
	  
    return user;
  }
  @BeforeClass
  public void max() 
  {
	  driver.manage().window().maximize();
	  System.out.println("Window get maximized");
  }

  @AfterClass
  public void deleteAllCookies() 
  {
	 driver.manage().deleteAllCookies();
  }

  @BeforeTest
  public void applicationUrl() 
  {
	  driver.get("http://newtours.demoaut.com/");
	  System.out.println("Application URL get open successfully");
  }

  @AfterTest
  public void dbConnection() 
  {
	  System.out.println("Database connection closed");
  }

  @BeforeSuite
  public void openBrowser() 
  {
	  System.setProperty("webdriver.chrome.driver","F:\\Selenium Setup\\Setup\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  System.out.println("Chrome Browser open successfully");
  }

  @AfterSuite
  public void closeBrowser() 
  {
	  driver.close();
	  System.out.println("Browser closed successfully");
  }

}
