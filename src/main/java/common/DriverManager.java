package common;

//import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver() throws MalformedURLException {
        BrowserList browserList = BrowserList.valueOf(GlobalVariable.getPlatform().toUpperCase());
        switch (browserList) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");
                options.setAcceptInsecureCerts(true);
                options.addArguments("--disable-notifications");
//                options.addArguments("--headless");
                driver.set(new ChromeDriver(options));
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            case ANDROID:
                DesiredCapabilities androidCaps = new DesiredCapabilities();
                androidCaps.setCapability("platformName", "Android");
                androidCaps.setCapability("deviceName", "emulator-5554"); // Hoặc "real-device-name"
//                androidCaps.setCapability("app", System.getProperty("user.dir") + "/apks/YouTube_20.06.34_APKPure.apk"); // Đường dẫn chính xác
                androidCaps.setCapability("appPackage", "com.google.android.youtube");
                androidCaps.setCapability("appActivity", "com.google.android.youtube.HomeActivity");
//                androidCaps.setCapability(CapabilityType.BROWSER_NAME, "");
                androidCaps.setCapability("noReset", true);
                driver.set(new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), androidCaps));
                break;

        }
    }
}

