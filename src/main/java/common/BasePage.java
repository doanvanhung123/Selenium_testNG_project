package common;

import com.aventstack.extentreports.Status;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import report.ExtentTestManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage {
    private final WebDriverWait webDriverWaitFinal = new WebDriverWait(DriverManager.getDriver(), GlobalVariable.timeWait);
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
    Actions actions = new Actions(DriverManager.getDriver());

    protected static Logger log = LogManager.getLogger(BaseTest.class);
//    private static final ThreadLocal<Logger> threadLogger = ThreadLocal.withInitial(() -> Logger.getLogger("ThreadLogger"));
//
//    public static void log(String message) {
//        threadLogger.get().info(message);
//    }

    private List<WebElement> getListElement(String value, FindBy findType, String elementName) {
        log.info(String.format("get webElements %s", elementName));
        return DriverManager.getDriver().findElements(getFindBy(findType, value));
    }

    private WebElement getElement(String value, FindBy findType, String elementName) {
        log.info(String.format("get webElement %s", elementName));
        return DriverManager.getDriver().findElement(getFindBy(findType, value));
    }


    private By getFindBy(FindBy findType, String value) {
        return switch (findType) {
            case XPATH -> By.xpath(value);
            case ID -> By.id(value);
            case CLASS -> By.className(value);
        };
    }

    protected enum FindBy {
        XPATH,
        ID,
        CLASS
    }

    protected void scrollToElement(WebElement element, String elementName) {
        log.info(String.format("Scroll to Element %s", elementName));
        try {
            if (!GlobalVariable.getPlatform().equals("android")) {
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

                //scrolling behavior of mobile is different with web , so we need implement another block
            } else {
                while (true) {
                    try {
                        if (element.isDisplayed()) {
                            break;
                        }
                    } catch (Exception e) {
                    }
                    new TouchAction<>((PerformsTouchActions) DriverManager.getDriver())
                            .press(PointOption.point(500, 1500)) // Điểm bắt đầu (giữa màn hình)
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(500, 500)) // Vuốt lên trên
                            .release()
                            .perform();
                }
            }
        } catch (Exception e) {
            log.info(String.format("Scroll to Element %s", elementName));
            throw new RuntimeException(e);
        }
    }

    //web and android still use one type of click , it's "click" method
    protected void clickToElement(WebElement element, String elementName) throws RuntimeException {
        log.info(String.format("Click/tap on %s", elementName));
        try {
            if (!GlobalVariable.getPlatform().equals("android")) {
                element.click();
            } else {
                element.click();
            }
            ExtentTestManager.getTest().log(Status.PASS, "Click to " + elementName +"display passed");
        } catch (Exception e) {
            log.info(String.format("Click/tap on %s failed", elementName));
            ExtentTestManager.getTest().log(Status.FAIL, "Click to " + elementName +"display failed");
            throw new RuntimeException(String.format("Click/tap on %s failed", elementName));
        }
    }

    //web and android still use one type of click , it's "click" method
    protected void setTextToElement(WebElement element, String value, String elementName) throws RuntimeException {
        log.info(String.format("Set text [%s] on %s", value, elementName));
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            log.info(String.format("Set text [%s] on %s failed", value, elementName));
            throw new RuntimeException(String.format("Set text [%s] on %s failed", value, elementName));
        }
    }

    protected WebElement waitForElementVisible(String locator, FindBy findBy, int timeout, String elementName) throws RuntimeException {
        log.info(String.format("Wait for element visible %s", elementName));
        try {
            return new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.visibilityOfElementLocated(getFindBy(findBy, locator)));
        } catch (Exception e) {
            log.info(String.format("Wait for element visible %s, element not visible", elementName));
            // Log thành công
            ExtentTestManager.logMessage(Status.PASS, "Wait for " + elementName +"display failed");
//            ExtentTestManager.getTest().log(Status.PASS, "Wait for " + elementName +"display failed");
            throw new RuntimeException(String.format("Wait for element visible %s, element not visible", elementName));
        }
    }

    protected WebElement waitForElementClickable(String locator, FindBy findBy, int timeout, String elementName) throws RuntimeException {
        log.info(String.format("Wait for element clickable %s", elementName));
        try {
            return new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(getFindBy(findBy, locator)));
        } catch (Exception e) {
            log.info(String.format("Wait for element visible %s, element not visible", elementName));
            throw new RuntimeException(String.format("Wait for element visible %s, element not visible", elementName));
        }
    }

    protected WebElement waitForElementPresent(String locator, FindBy findBy, int timeout, String elementName) throws RuntimeException {
        log.info(String.format("Wait for element clickable %s", elementName));
        try {
            return new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.presenceOfElementLocated(getFindBy(findBy, locator)));
        } catch (Exception e) {
            log.info(String.format("Wait for element visible %s, element not visible", elementName));
            throw new RuntimeException(String.format("Wait for element visible %s, element not visible", elementName));
        }
    }

    protected void goToUrl(String url) throws Exception {
        log.info("Navigate to url: " + url);
        DriverManager.getDriver().get(url);
    }

    protected void refreshPage() throws Exception {
        log.info("Refresh page");
        DriverManager.getDriver().navigate().refresh();
    }

    protected void acceptAlert() throws Exception {
        log.info("Accept Alert");
        waitForAlertPresent().accept();
    }

    protected void cancelAlert() throws Exception {
        log.info("Cancel Alert");
        waitForAlertPresent().dismiss();
    }

    protected void sendKeyToAlert(String value) throws Exception {
        log.info("SendKey to Alert");
        waitForAlertPresent().sendKeys(value);
    }

    protected Alert waitForAlertPresent() throws Exception {
        log.info("Wait for alert present");
        return webDriverWaitFinal.until(ExpectedConditions.alertIsPresent());
    }

    protected void switchToWindowByTitle(String title) {
        log.info("Switch to window title: " + title);
        Set<String> allWindows = DriverManager.getDriver().getWindowHandles();
        for (String window : allWindows) {
            DriverManager.getDriver().switchTo().window(window);
            String currentWindow = DriverManager.getDriver().getTitle();
            if (currentWindow.equals(title)) {
                break;
            }
        }
    }


    protected void closeAllWindowExceptParent() {
        log.info("Close all windows without current page");
        Set<String> allWindows = DriverManager.getDriver().getWindowHandles();
        String currentWindow = DriverManager.getDriver().getWindowHandle();

        for (String window : allWindows) {
            DriverManager.getDriver().switchTo().window(window);
            if (!DriverManager.getDriver().getWindowHandle().equals(currentWindow)) {
                DriverManager.getDriver().close();
            }
        }
        DriverManager.getDriver().switchTo().window(currentWindow);
    }

    protected void selectItemInDropdown(WebElement element, String textItem, String dropdownName) {
        log.info(String.format("Select item: %s in dropdown: %s", textItem, dropdownName));
        new Select(element).selectByVisibleText(textItem);
    }

    protected String getSelectedOptionInDropdown(WebElement element) {
        log.info(String.format("Close all windows without current page"));
        return new Select(element).getFirstSelectedOption().getText();
    }

    protected void selectItemsInCustomDropdown(String dropdownLocator, String optionsLocator, String items, String dropdownName) {
        log.info(String.format("Select item: %s in dropdown: %s", items, dropdownName));
        WebElement dropdown = webDriverWaitFinal.until(ExpectedConditions.visibilityOfElementLocated(getFindBy(FindBy.XPATH, dropdownLocator)));
        clickToElement(dropdown, dropdownName);
        List<WebElement> allItems = webDriverWaitFinal.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getFindBy(FindBy.XPATH, optionsLocator)));

        sleepInSeconds(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(items)) {
                javascriptExecutor.executeScript("argument[0].scrollIntoView(True)", item);
                item.click();
            }
        }
    }

    public void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getElementAttribute(WebElement element, String attributeName, String elementName) {
        log.info("Get Element: " + elementName + " attribute" + attributeName);
        return element.getAttribute(attributeName);
    }

    public String getElementText(WebElement element, String elementName) {
        log.info("Get Element: " + elementName + " text");
        return element.getText();
    }

    public void checkToCheckBox(WebElement element, boolean isSelect, String elementName) {
        log.info(String.format("Check[%s] Element: %s text", isSelect, elementName));
        if (element.isSelected() != isSelect) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(String locator, FindBy findBy, int timeout) {
        WebElement element;
        try {
            element = new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.presenceOfElementLocated(getFindBy(findBy, locator)));
        } catch (TimeoutException e) {
            return false;
        }
        return element.isDisplayed();
    }

    protected boolean isElementSelected(String locator, FindBy findBy, int timeout) {
        WebElement element = new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.presenceOfElementLocated(getFindBy(findBy, locator)));
        return element.isSelected();
    }

    public void switchToFrame(WebElement frame) {
        DriverManager.getDriver().switchTo().frame(frame);
    }

    public void switchToDefaultFrame() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void hoverToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void doubleClickToElement(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void pressKeyToElement(WebElement element, String value, String elementName) {
        log.info(String.format("Send Key :%s to element %s", value, elementName));
        actions.sendKeys(element, elementName).perform();
    }

    public void scrollToBottomPage() {
        log.info(String.format("Scroll to Bottom Page"));
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }


}
