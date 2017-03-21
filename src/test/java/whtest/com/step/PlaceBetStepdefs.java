package whtest.com.step;

//import cucumber.api.java.En;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import whtest.com.SharedDriver;
import whtest.com.pages.BetSlip;
import whtest.com.pages.MainContent;
import whtest.com.pages.HomePage;



public class PlaceBetStepdefs {

    private SharedDriver driver = new SharedDriver();
    private String getOddsValue;


    HomePage homePage = new HomePage(driver);
    MainContent main = new MainContent(driver);
    BetSlip betSlip = new BetSlip(driver);

    @Given("^that I am on sports\\.williamhill\\.com/sr-admin-set-white-list-cookie\\.html$")
    public void that_I_am_on_sports_williamhill_com_sr_admin_set_white_list_cookie_html() throws Throwable {
        homePage.open("http://sports.williamhill.com/sr-admin-set-white-list-cookie.html");
    }

    @Given("^I navigate to a Premiership football event$")
    public void i_navigate_to_a_Premiership_football_event() throws Throwable {
        homePage.selectLeftNavCat(homePage.navFootball);
        homePage.selectLeftNavSubCat(homePage.navFootballCompetitions);

    }

    @When("^I select the event and place a \"([^\\\"]*)\" bet for the home team to Win$")
    public void i_select_the_event_and_place_a_bet_for_the_home_team_to_Win(String amount) throws Throwable {
        main.selectTabMain("English Premier League");
        Assert.assertEquals("Checking tabs is EPL before continuing to select event",
                "English Premier League", main.getTabHeaderText());

        getOddsValue = main.getOldsFromButtonAttribute(main.oddsAttributeName);
        main.selectEvent();
        betSlip.enterStake(betSlip.betSlipInput, amount);
        betSlip.placeBet(betSlip.placeBetBtn);
    }

    @Then("^I should see the odds and returns offered$")
    public void i_should_see_the_and_offered() throws Throwable {
        Assert.assertEquals("checking odds values match", getOddsValue, betSlip.getSomeText(betSlip.betSlipOdds));
        //This could be improved, if the odd changes , this will fail. But it is good enough for now
        // This is no fixed value except if I know the method of calculating the returns.
        // I could easily have checked for the text "To return:"  on the betslip
        Assert.assertEquals("checking returns value", "0.08", betSlip.getSomeText(betSlip.returnPrice));
    }



}
