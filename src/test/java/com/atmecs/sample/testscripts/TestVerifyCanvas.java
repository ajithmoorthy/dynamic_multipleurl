package com.atmecs.sample.testscripts;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.atmecs.sample.helper.SeleniumHelper;
import com.atmecs.sample.helper.ValidaterHelper;
import com.atmecs.sample.helper.WaitForElement;
import com.atmecs.sample.testbase.TestBase;
import com.atmecs.sample.utils.PropertiesReader;

public class TestVerifyCanvas extends TestBase
{
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	WaitForElement waitobject=new WaitForElement();
	ValidaterHelper validate=new ValidaterHelper();
	@Test
	public void verifyCanvas() throws IOException, InterruptedException 
	{
		extentlogger=extentObject.startTest("red bus");
		driver.get(prop.getProperty("url"));
		waitobject.waitForPageLoadTime(driver);
		seleniumhelp.sendKeys("CSS,input#src", driver, "Chennai (All Locations)");
		seleniumhelp.sendKeys("CSS,input#dest", driver, "Trichy");
		seleniumhelp.sendKeysEnter("CSS,input#dest",driver, Keys.TAB);
		seleniumhelp.clickElement(driver,"CSS,input#onward_cal");
		seleniumhelp.clickElement(driver, "CSS,div#rb-calendar_onward_cal tbody > tr:nth-child(7) > td:nth-child(4)");
		seleniumhelp.sendKeysEnter("CSS,input#onward_cal",driver, Keys.TAB);
		seleniumhelp.clickElement(driver, "CSS,div#rb-calendar_return_cal tbody > tr:nth-child(7) > td:nth-child(4)");
		seleniumhelp.sendKeysEnter("CSS,input#return_cal",driver, Keys.TAB);
		WebElement element=waitobject.WaitForFluent(driver, "CSS,button#search_btn");
		Thread.sleep(3000);
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
		action.moveToElement(element).click().build().perform();
		seleniumhelp.scrollPageMethod(driver,"CSS,div.button.view-seats.fr");
		seleniumhelp.clickElement(driver,"CSS,div.button.view-seats.fr");
		seleniumhelp.scrollPageMethod(driver, "CSS,canvas[data-type='lower']");
		seleniumhelp.mouseOver("CSS,canvas[data-type='lower']", driver);
		action.moveByOffset(100,217).click().perform();
	}
}
