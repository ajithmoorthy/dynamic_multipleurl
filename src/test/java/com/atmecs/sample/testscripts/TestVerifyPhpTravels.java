package com.atmecs.sample.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.atmecs.sample.constants.FileConstants;
import com.atmecs.sample.helper.SeleniumHelper;
import com.atmecs.sample.helper.ValidaterHelper;
import com.atmecs.sample.helper.WaitForElement;
import com.atmecs.sample.testbase.TestBase;
import com.atmecs.sample.utils.PropertiesReader;

public class TestVerifyPhpTravels extends TestBase{
SeleniumHelper seleniumhelp=new SeleniumHelper();
PropertiesReader propread=new PropertiesReader();
WaitForElement waitobject=new WaitForElement();
ValidaterHelper validate=new ValidaterHelper();
	@Test
	public void verifyPhpTravels() throws IOException {
		driver.get(prop.getProperty("url2"));
		Properties prop=propread.KeyValueLoader(FileConstants.LOCATORS_PATH);
		extentlogger=extentObject.startTest("hgasfgdh");
		driver.manage().window().maximize();
		waitobject.waitForPageLoadTime(driver);
		waitobject.waitForElementToBeClickable(driver,prop.getProperty("loc.menu.flights"));
		seleniumhelp.mouseOver(prop.getProperty("loc.menu.flights"),driver);
		seleniumhelp.clickElement(driver,prop.getProperty("loc.menu.flights") );
		for(int count=0; count<2; count++)
		{
			seleniumhelp.clickElement(driver,prop.getProperty("loc.btn.positiveadults"));
			seleniumhelp.clickElement(driver,prop.getProperty("loc.menu.flights"));
		}
		JavascriptExecutor js= (JavascriptExecutor)driver;
		String text=js.executeScript("return document.querySelector(\"div.col-4:nth-child(1) input\").value;").toString();
		System.out.println(text);
		validate.assertValidater(text, "3");
		for(int count=0; count<2; count++)
		{
			seleniumhelp.clickElement(driver,prop.getProperty("loc.btn.positivechild"));
			seleniumhelp.clickElement(driver,prop.getProperty("loc.menu.flights"));
		}
		String text1=js.executeScript("return document.querySelector(\"div.col-4:nth-child(2) input\").value;").toString();
		System.out.println(text1);
	}
}
