package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener  {
	
	public WebDriver driver;
	//Reports
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	
	//Thread secure objects for parallel mode
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
		extentTest = extentReport.createTest(testName+ " executed started");
		extentTestThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		extentTestThread.get().log(Status.PASS, testName + "got passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//Reports
		extentTestThread.get().fail(result.getThrowable());
		
		
		//Screenshots
		String testName = result.getName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String screenshotFilePath = takeScreenshot(testName, driver);
			// Put screenshot into extent report
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testName);
		} catch (IOException e) {
			
			//Adding screenshots into extent reports
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
	}
	
	

}
