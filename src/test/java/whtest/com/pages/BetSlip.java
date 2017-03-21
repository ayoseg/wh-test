package whtest.com.pages;

import org.openqa.selenium.By;

import whtest.com.Actions;
import whtest.com.SharedDriver;


public class BetSlip extends Actions{

    public BetSlip(SharedDriver driver){
        super(driver);
    }

    public void enterStake(By locator, String amount){
        setTextInField(locator, amount);
    }

    //Unable to login, so I am getting return price and odds on the betslip
    public String getSomeText(By locator){
        return getText(locator);
    }

    public void placeBet(By locator) {
        click(locator);
    }

}
