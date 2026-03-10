package retryAnalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer , ITestListener {
    private static final int maxRetryCount = 1; // Số lần retry tối đa

    // Dùng ThreadLocal để mỗi thread có một biến retryCount riêng
    private static ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);

    @Override
    public boolean retry(ITestResult result) {
        int currentCount = retryCount.get();
        if (currentCount < maxRetryCount) {
            retryCount.set(currentCount + 1);
            System.out.println("🔁 Retry test: " + result.getName() + " | Attempt: " + (currentCount + 2));
            return true; // Chạy lại test case
        }
        return false; // Không chạy lại nữa
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        retryCount.set(0); // Reset khi test thành công
    }

    @Override
    public void onTestFailure(ITestResult result) {
        retryCount.set(0); // Reset khi test thất bại
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}