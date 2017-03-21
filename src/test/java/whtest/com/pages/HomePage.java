package whtest.com.pages;

import org.openqa.selenium.By;
import whtest.com.Actions;
import whtest.com.SharedDriver;


public class HomePage extends Actions {

    public HomePage(SharedDriver driver){
        super(driver);
    }

    public void open(String url) {
        navigateToUrl(url);
    }

    //Selecting a premiership event by Left hand Nav, I could have used the search,
    // but that gives me markets option, can't find events there
    // Waiting for element to be clickable before clicking
    public void selectLeftNavCat(By locator){
        waitForElememtToBeClickable(locator);
        click(locator);
    }

    // Selecting Competitions to get into the football competition listing
    public void selectLeftNavSubCat(By locator){
        waitForPageToLoad();
        waitForElememtToBeClickable(locator);
        click(locator);
    }


}
