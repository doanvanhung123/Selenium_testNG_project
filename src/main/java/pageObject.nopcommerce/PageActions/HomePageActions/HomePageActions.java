package pageObject.nopcommerce.PageActions.HomePageActions;

import common.BasePage;

import org.openqa.selenium.WebElement;
import pageObject.nopcommerce.pageUI.HomePageUI.HomePageUI;
import utils.LogUtils;

public class HomePageActions extends BasePage {

    public void clickToRegisterLink(){
        WebElement element = waitForElementVisible(HomePageUI.registerButton,FindBy.XPATH,10,"Register Button");
        clickToElement(element,"Register Button");
    }


    public void clickToProductLink(){
        log.info("Precondition: Navigate to Home Page");
        WebElement element = waitForElementVisible(HomePageUI.productLink,FindBy.XPATH,10,"Product Button");
        clickToElement(element,"Product Button");
    }

    public void clickToHomeLink(){
        WebElement element = waitForElementVisible(HomePageUI.productLink,FindBy.XPATH,10,"Home Button");
        clickToElement(element,"Home Button");
    }



}
