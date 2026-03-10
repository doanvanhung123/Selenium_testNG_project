import common.BaseTest;
import common.PageManager;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.nopcommerce.PageActions.HomePageActions.CategoryPageActions;
import pageObject.nopcommerce.PageActions.HomePageActions.HomePageActions;
import utils.LogUtils;
//import pageObject.nopcommerce.PageActions.HomePageActions.HomePageActions;
//import pageObject.nopcommerce.PageActions.HomePageActions.RegisterPageActions;

public class Testcase2 extends BaseTest {

    @BeforeMethod
    public void NavigateToProductPage(){
        HomePageActions homePageActions = PageManager.getHomePage();
        homePageActions.clickToProductLink();
    }

    @Test
    public void TC_01_VerifyVideoTitleContainSearchValue() throws Exception {
        CategoryPageActions categoryPageActions = PageManager.getCategoryPageActions();
        categoryPageActions.clickToCategoryOption("Women");
    }


    @Test
    public void TC_02_VerifyVideoTitleContainSearchValue() throws Exception {
        CategoryPageActions categoryPageActions = PageManager.getCategoryPageActions();
        categoryPageActions.clickToCategoryOption("Women");
    }

    @AfterMethod
    public void checkAfterMethod(ITestResult result) {
        //Bắt trạng thái hoặc tên Test case sau khi chạy xong để xử lý gì đó
    }

}
