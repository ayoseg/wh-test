package whtest.com;

import org.openqa.selenium.By;


public abstract class Locators extends Data {


    public By navFootball = By.cssSelector("#nav-football>a");
    public By navFootballCompetitions = By.cssSelector("#nav-football-competitions>a");
    //public By eplTabheader = By.cssSelector("li:nth-of-type(2) ul>li:nth-of-type(1)>p");
    public By mainAllTabs = By.cssSelector(".matches>ul>li:nth-of-type(2)>ul>li");
    public By firstListedeplEvent = By.cssSelector("li:nth-of-type(2) ul>li:nth-of-type(2)  .btmarket__wrapper>div:nth-of-type(2) .btmarket__name--featured");
    //public By getFirstListedEventButton = By.cssSelector("li:nth-of-type(2) ul>li:nth-of-type(2) .btmarket__wrapper>div:nth-of-type(2) .btmarket__actions>div:nth-of-type(1)>button");
    //public By getFirstListedEventButton = By.cssSelector("li:nth-of-type(2) ul>li:nth-of-type("+new Football(driver).getTabIndex()+") .btmarket__wrapper>div:nth-of-type(2) .btmarket__actions>div:nth-of-type(1)>button");
    public By betSlipInput = By.cssSelector(".betslip-selection__stake-input");
    public By placeBetBtn = By.id("place-bet-button");
    public By returnPrice = By.id("total-to-return-price");
    public By betSlipOdds = By.cssSelector(".betslip-selection__name>span:nth-of-type(2)");
}
