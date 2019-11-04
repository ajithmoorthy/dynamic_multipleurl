package com.atmecs.sample.extentreport;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.atmecs.sample.constants.FileConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * This the Extent report class is for generate  the report for the project in web application manner 
 * and give pie chart of project application with Test failures and Test passes with the screenshot.  
 * @author ajith.periyasamy
 */
public class Extent {
	public WebDriver driver;
	public static ExtentReports extentObject;
	public ExtentTest extentlogger;
	/**
	 * startReport is method is used to load the configuration files
	 * and create the Extent.html file for Extent report.
	 */
	@BeforeSuite
	public void startReport() {

		extentObject = new ExtentReports(FileConstants.EXTENT_OUPUT_PATH, true);
		extentObject.loadConfig(new File(FileConstants.EXTENT_CONFIG_PATH));
	}
/**
 * This method takes below parameters:
 * @param driver
 * @param testname
 * and return the screenshot image destination path as a String .
 * @return
 * @throws Exception
 */
	public static String getScreenshot(WebDriver driver, String testname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = FileConstants.SCREENSHOT_PATH + testname+".png";
		File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	/**
	 * This method take the input as Test result details and take the screenshot based on the failure,pass or skip.
	 * @param result
	 * @throws Exception
	 */
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentlogger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			extentlogger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
			// extent report
			String screenshotPath = Extent.getScreenshot(driver, result.getName());
			extentlogger.log(LogStatus.FAIL, extentlogger.addScreenCapture(screenshotPath));			
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentlogger.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentlogger.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			String screenshotPath = Extent.getScreenshot(driver, result.getName());
			extentlogger.log(LogStatus.PASS, extentlogger.addScreenCapture(screenshotPath));
		}
		extentObject.endTest(extentlogger);
	}
	/**
	 * end report call the web driver quit and extent report object flush.
	 * flush is function is used to save the extend report. 
	 */
	  @AfterSuite 
	  public void endReport() 
	  { 
		 extentObject.flush();
	  }
	  	@AfterTest
		 public void endDriver() {
			 driver.quit();
		 }
}