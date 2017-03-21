package whtest.com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public abstract class Actions extends Locators {

    private SharedDriver driver;
    private static final Integer WAIT_TIMEOUT = 30;

    public Actions(SharedDriver driver){
        this.driver = driver;
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void setTextInField(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

    public void navigateToUrl(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public String getText(By locator){
        return driver.findElement(locator).getText();
    }
    public String getAttribute(By locator, String attributeName){
        return driver.findElement(locator).getAttribute(attributeName);
    }

    public void waitForElememtToBeClickable(By locator){
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public static void waitForAjax(SharedDriver driver) {
        new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }


    public List<WebElement> getElementsList(By locator){
        return driver.findElements(locator);
    }
}
