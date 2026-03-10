package pageObject.nopcommerce.PageActions.HomePageActions;

import com.aventstack.extentreports.Status;
import common.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.nopcommerce.pageUI.HomePageUI.CategoryPageUI;
import pageObject.nopcommerce.pageUI.HomePageUI.RegisterPageUI;
import report.ExtentTestManager;
import utils.LogUtils;

public class CategoryPageActions extends BasePage {

    public void clickToCategoryOption(String itemName){
        WebElement element = waitForElementVisible(String.format(CategoryPageUI.categoryOptions,itemName),FindBy.XPATH,10,String.format("Click to Category options : %s",itemName));
        clickToElement(element,String.format("Category options : %s",itemName));
    }

    public void clickToCategorySubOption(String itemName){

        WebElement element = waitForElementVisible(String.format(CategoryPageUI.categorySubOptions,itemName),FindBy.XPATH,10,String.format("Click to Category sub options : %s",itemName));
        clickToElement(element,String.format("Category sub options : %s",itemName));
    }

}
