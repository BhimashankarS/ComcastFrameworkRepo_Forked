package com.comcast.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithPhoneNo {

	public static void main(String[] args) throws Throwable {
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("Browser");
		String URL = fLib.getDataFromPropertiesFile("Url");
		String USERNAME = fLib.getDataFromPropertiesFile("Username");
		String PASSWORD = fLib.getDataFromPropertiesFile("Password");
		
		String orgName = eLib.getDataFromExcel("Organization", 1, 2)+jLib.getRandomNumber();
		String phoneno = eLib.getDataFromExcel("Organization", 1, 7);
		
		WebDriver driver = wLib.launchBrowser("BROWSER");
		
		driver.manage().window().maximize();
		
		wLib.waitForPageToLoad(driver);
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phoneno);
		driver.findElement(By.name("button")).click();
		
		String actOrgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgname.equals(orgName))
		{
			System.out.println(orgName+" information is created");
		}else {
			System.out.println(orgName+" information is not created");
		}
		
		String actPhoneno = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actPhoneno.equals(phoneno))
		{
			System.out.println(phoneno+" information is created");
		}else {
			System.out.println(phoneno+" information is not created");
		}
		driver.quit();
	}

}