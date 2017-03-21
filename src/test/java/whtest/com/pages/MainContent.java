package whtest.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import whtest.com.Actions;
import whtest.com.SharedDriver;

import java.util.List;

public class MainContent extends Actions {
    private SharedDriver driver;
    private int tabIndex;

    public MainContent(SharedDriver driver){
        super(driver);
        this.driver = driver;
    }

    public String getSomeText(By locator){
        return getText(locator);
    }

    //Getting tab header
    public String getTabHeaderText(){
        By locator = By.cssSelector(".matches>ul>li:nth-of-type(2)>ul>li:nth-of-type("+ getTabIndex() +")>p");
        return getText(locator);
    }

    // Selecting the actual event.
    // Added one to the Table index as there is another list just below the tab name which contains the events
    public void selectEvent(){
        int getTabIndex = getTabIndex()+1;
        By locator = By.cssSelector("li:nth-of-type(2) ul>li:nth-of-type(" +getTabIndex+ ") .btmarket__wrapper>div:nth-of-type(2) .btmarket__actions>div:nth-of-type(1)>button");
        waitForElememtToBeClickable(locator);
        click(locator);
    }

    //Here I am passing the dynamic Index to the locator, Also passing the tabname to know which tab I need to click
    //Clicking on the tab to check to open the events underneath.
    // Could be improved by checking whether it is already expanded
    public void selectTabMain(String tabName){
        setTabIndex(getHMainTabIndex(tabName));
        By locator = By.cssSelector(".matches>ul>li:nth-of-type(2)>ul>li:nth-of-type("+ getTabIndex() +")");
        click(locator);
    }

    //Getting the Home Odds from the event so that it can be compared with odds on Betslip
    public String getOldsFromButtonAttribute(String attributeName){
        int getTabIndex = getTabIndex()+1;
        By locator = By.cssSelector("li:nth-of-type(2) ul>li:nth-of-type(" +getTabIndex+ ") .btmarket__wrapper>div:nth-of-type(2) .btmarket__actions>div:nth-of-type(1)>button");
        return getAttribute(locator, attributeName);
    }

    // As there are many tabs on the football competition page,
    // I had to pass the header of the tab and check through all tabs until I find the one of interest
    // Added 1 to the index as list index starts from 0
    // Index will be use in the locator to find EPL tab
    public int getHMainTabIndex(String text) {
        waitForAjax(driver);
        List<WebElement> tablist = getElementsList(mainAllTabs);
        for (WebElement tab: tablist) {
            if(tab.getText().contains(text)){
                return tablist.indexOf(tab) + 1;
            }
        }
        return -1;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
}
