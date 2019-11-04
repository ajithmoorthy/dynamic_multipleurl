package com.atmecs.sample.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;
import com.atmecs.sample.constants.FileConstants;
import com.atmecs.sample.utils.ExcelReader;
import com.atmecs.sample.utils.PropertiesReader;

public class TestNGMethod {
	ExcelReader excelread=new ExcelReader();
	public TestNGMethod() {
		excelread.dataProviderMethod(FileConstants.CLASSNAME_PATH);
	}
	PropertiesReader propread=new PropertiesReader();
	@Test(dataProvider="testdata", dataProviderClass = ExcelReader.class)
	public void XmlSuiteRunner(String classname, String classstatus,String websitename) throws IOException {
		if (classstatus.equalsIgnoreCase("y")) {
		Properties props = propread.KeyValueLoader(FileConstants.CONFIG_PATH);
		List<String> browsernames = new ArrayList<String>();
		String[] browserarray = props.getProperty("webdrivername").split(",");
		String arr1[]=browserarray[1].split(":");
		for (String name : arr1)
		{
			browsernames.add(name);
		}
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("suite");
		xmlSuite.setVerbose(1);
		xmlSuite.setParallel(ParallelMode.TESTS);
		xmlSuite.setThreadCount(browsernames.size()*5);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();

		for (String browsername : browsernames) {

			XmlTest xmlTest = new XmlTest(xmlSuite);
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("browser", browserarray[0]+","+browsername);
			parameter.put("url", websitename);
			xmlTest.setParameters(parameter);
			xmlTest.setName("Test validate" +browsername+classname);
			XmlClass xmlClass = new XmlClass();
			xmlClass.setName(classname);
			List<XmlClass> xmlClassList = new ArrayList<XmlClass>();
			xmlClassList.add(xmlClass);
			xmlTest.setXmlClasses(xmlClassList);

		}

		suites.add(xmlSuite);

		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);

		testng.run();
	}
	}

}
