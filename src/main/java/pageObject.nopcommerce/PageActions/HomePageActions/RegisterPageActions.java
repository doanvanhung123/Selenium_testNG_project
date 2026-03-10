package pageObject.nopcommerce.PageActions.HomePageActions;

import common.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.nopcommerce.pageUI.HomePageUI.RegisterPageUI;

public class RegisterPageActions extends BasePage {


    //input register information
    public void clickToMaleRadioButton(){
        WebElement element = waitForElementVisible(RegisterPageUI.genderRadioMale,FindBy.XPATH,10,"Male Radio button");
        clickToElement(element,"Password Input");
    }

    public void inputToFirstName(String value){
        WebElement element = waitForElementVisible(RegisterPageUI.firstNameInput,FindBy.XPATH,10,"First Name Input");
        setTextToElement(element,value,"First Name Input");
    }

    public void inputToLastName(String value){
        WebElement element = waitForElementVisible(RegisterPageUI.lastNameInput,FindBy.XPATH,10,"Last Name Input");
        setTextToElement(element,value,"Last Name Input");
    }

    public void inputToEmail(String value){
        WebElement element = waitForElementVisible(RegisterPageUI.emailInput,FindBy.XPATH,10,"Email Input");
        setTextToElement(element,value,"Email Input");
    }

    public void inputToPassWord(String value){
        WebElement element = waitForElementVisible(RegisterPageUI.passwordInput,FindBy.XPATH,10,"Password Input");
        setTextToElement(element,value,"Password Input");
    }

    public void inputToConfirmPassword(String value){
        WebElement element = waitForElementVisible(RegisterPageUI.confirmPasswordInput,FindBy.XPATH,10,"Confirm Password Input");
        setTextToElement(element,value,"Confirm Password Input");
    }

    public void clickRegisterButton(){
        WebElement element = waitForElementVisible(RegisterPageUI.registerButton,FindBy.XPATH,10,"Register button");
        clickToElement(element,"Register button");
    }

    public void verifyCompleteMessage(String value){
        WebElement element = waitForElementVisible(RegisterPageUI.completeMessage,FindBy.XPATH,10,"Complete Message");
        Assert.assertEquals(value,getElementText(element,"Complete Message"));
    }

}
