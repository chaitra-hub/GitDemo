package chaitraAcandemy.resources;

//import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	//ExtentReports extent;
	//@BeforeTest
	public static ExtentReports getReportObject()
	{
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html"; // report folder will be automatically will be created
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); // this is the method, reporter is the object for ExtentSparkReporter class 
		reporter.config().setReportName("Chaitra Web Automation Results");
		reporter.config().setDocumentTitle("Chaitra Test Result");
		
		//ExtentReports extent = new ExtentReports();
		ExtentReports extent = new ExtentReports();
		 extent.attachReporter(reporter); //main class extent will have knowledge of object that is reporter
		 extent.setSystemInfo("Tester", "Chaitra");
		 return extent;
	}

}
