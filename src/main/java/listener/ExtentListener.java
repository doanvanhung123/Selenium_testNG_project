package listener;

import com.aventstack.extentreports.Status;
import helper.PropertiesHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.ExtentReportManager;
import report.ExtentTestManager;

public class ExtentListener implements ITestListener {
    private static Logger log = LogManager.getLogger(ExtentListener.class);

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        //Khởi tạo report (Extent và Allure)
    }

    @Override
    public void onFinish(ITestContext result) {
        log.info("End testing " + result.getName());

        //Kết thúc và thực thi Extents Report
        ExtentReportManager.createExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Running test case " + result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.startTest(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test case " + result.getName() + " is passed.");

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test case " + result.getName() + " is failed.");
        //Screenshot khi fail
        //CaptureHelper.captureScreenshot(result.getName());
        log.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.addScreenshot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.error("Test case " + result.getName() + " is skipped.");
        log.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

}