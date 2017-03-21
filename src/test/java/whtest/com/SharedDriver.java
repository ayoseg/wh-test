package whtest.com;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Capabilities;


/**
 * Based on shared webdriver implementation in cucumber-jvm examples
 * A new instance of SharedDriver is created for each Scenario and passed to  Stepdef classes via Dependency Injection
 */
public class SharedDriver extends EventFiringWebDriver {

    enum BrowserDriver {
        FIREFOX,
        CHROME,
        CHROME_EMULATED,
        SAFARI,
        IE,
    }

    public static WebDriver REAL_DRIVER = null;
    public static boolean IS_FIREFOX_DRIVER = false;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };

    static {
        try {
            setChromeDriverInClassPath();
            initiateRealDriver(BrowserDriver.valueOf(System.getProperty("browser").toUpperCase()));
            customiseRealDriver();
            Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void customiseRealDriver() {
        REAL_DRIVER.manage().window().maximize();
        REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    private static void setChromeDriverInClassPath() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

    private static DesiredCapabilities setCapability(){
        final DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setCapability(ChromeOptions.CAPABILITY, new ChromeOptions() {
            {
                setExperimentalOption("mobileEmulation", new HashMap<String, Object>() {
                    {
                        put("deviceName", "Google Nexus 5");
                    }
                });
            }
        });
        return dc;
    }


    private static void initiateRealDriver(BrowserDriver browserDriver) {
        switch (browserDriver) {
            case FIREFOX:
                REAL_DRIVER = new FirefoxDriver();
                IS_FIREFOX_DRIVER = true;
                return;
            case CHROME:
                REAL_DRIVER = new ChromeDriver();
                return;
            case CHROME_EMULATED:
                REAL_DRIVER = new ChromeDriver(setCapability());
                return;
            case SAFARI:
                REAL_DRIVER = new SharedDriver();
                return;
            case IE:
                REAL_DRIVER = new InternetExplorerDriver();
                return;
            default:
                throw new NotImplementedException("Unknown Driver:" + browserDriver);
        }


    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }


    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        scenario.write("Current Page URL is " + getCurrentUrl());
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    public static String getBrowserName()
    {
        Capabilities cp = ((RemoteWebDriver)REAL_DRIVER).getCapabilities();
        String browserName = cp.getBrowserName().toLowerCase();
        return browserName;
    }
}