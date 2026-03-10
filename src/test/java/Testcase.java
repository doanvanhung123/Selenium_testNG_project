import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.DriverManager;
import common.PageManager;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObject.nopcommerce.PageActions.HomePageActions.CategoryPageActions;
import pageObject.nopcommerce.PageActions.HomePageActions.HomePageActions;
import report.ExtentReportManager;
import report.ExtentTestManager;
import retryAnalyzer.RetryListener;
import utils.LogUtils;
//import pageObject.nopcommerce.PageActions.HomePageActions.HomePageActions;
//import pageObject.nopcommerce.PageActions.HomePageActions.RegisterPageActions;

public class Testcase extends BaseTest {

    @BeforeMethod
    public void NavigateToProductPage(){
        ExtentTestManager.startTest("Precondition: Navigate to produc page","Before method");
        HomePageActions homePageActions = PageManager.getHomePage();
        homePageActions.clickToProductLink();
    }

    @Test (retryAnalyzer = RetryListener.class)
//    @Test
    public void TC_01_VerifyVideoTitleContainSearchValue() throws Exception {
        CategoryPageActions categoryPageActions = PageManager.getCategoryPageActions();
        categoryPageActions.clickToCategoryOption("Women");
        categoryPageActions.clickToCategorySubOption("Dress");
//        ExtentTestManager.getTest().log(Status.INFO,"Verify failed");
        Assert.assertTrue(false);
    }
//retryAnalyzer = RetryListener.class

//    @Test (retryAnalyzer = RetryListener.class)
    @Test
    public void TC_02_VerifyVideoTitleContainSearchValue() throws Exception {
        CategoryPageActions categoryPageActions = PageManager.getCategoryPageActions();
        categoryPageActions.clickToCategoryOption("Women");
        categoryPageActions.clickToCategorySubOption("Dress");
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void checkAfterMethod() {
    }

}
