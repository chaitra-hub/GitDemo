package ChaitraAcademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import chaitraAcandemy.resources.ExtentReporterNG;

//import org.testng.ITestListener;

public class Listenersafterconcurrencybackup extends BaseTestsJSON implements ITestListener{
	
	ExtentTest test;
	
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  // thread safe
	 @Override
	    public void onTestStart(ITestResult result) {
	        // This method is invoked each time a test method is about to start
		test =  extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //for this test it will assign unique thread id >> x	id of error validqtaion test
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // This method is invoked each time a test method succeeds
	    	extentTest.get().log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // This method is invoked each time a test method fails
	    	//test.log(Status.FAIL, "Test Failed");
	    	extentTest.get().fail(result.getThrowable()); // if we give this, we want the error message
	    	
	    	
	  try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
	    	
	    	
	    	String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // this we are telling where to store screenshot
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // This method is invoked each time a test method is skipped
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // This method is invoked each time a test method fails but is within success percentage
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // This method is invoked before any test method is invoked
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // This method is invoked after all test methods have run
	    	extent.flush();
	    }

}
