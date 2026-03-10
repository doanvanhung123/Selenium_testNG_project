package common;

import helper.PropertiesHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utils.LogUtils;


import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static helper.PropertiesHelper.setFile;

public class BaseTest {


    //    protected static ThreadLocal<String> logFileName = new ThreadLocal<>();
    protected static Logger log = LogManager.getLogger(BaseTest.class);


    @BeforeSuite
    public void readProperties() throws MalformedURLException {
        log.info("Read properties file");
        PropertiesHelper.loadAllFiles();

    }

    @BeforeTest
    @Parameters({"platform"})
    public void setup(@Optional("chrome") String platform, org.testng.ITestContext context) throws MalformedURLException {
//        String testName = context.getCurrentXmlTest().getName();
//        String timestamp = new java.text.SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new java.util.Date());
//        String fileName = "logs/" + testName + "_" + timestamp + ".log";
//
//        System.out.println(fileName);
//        ThreadContext.put("logFilename", fileName);
//        LogUtils.log.set(LogManager.getLogger(getClass()));

        GlobalVariable.setPlatform(PropertiesHelper.getValue("browser"));
        DriverManager.setDriver();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (!GlobalVariable.getPlatform().equals("android")) {
            DriverManager.getDriver().manage().window().maximize();
        }
        DriverManager.getDriver().get((PropertiesHelper.getValue("url")));
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverManager.getDriver().quit();

    }
}
