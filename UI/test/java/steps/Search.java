package steps;

import org.openqa.selenium.WebDriver;

import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

public class Search {

    WebDriver driver;
    SearchPage HomePage;

    @Before
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver();
        HomePage = new SearchPage(driver);
    }

    @Given("the user is on the Daraz homepage")
    public void user_is_on_homepage() {
        driver.get("https://www.daraz.pk/#?");
    }

    @When("the user searches for {string}")
    public void user_searches_for(String searchTerm) {
    	HomePage.enterSearchTerm(searchTerm);
    	HomePage.clickSearchButton();
    }

    @Then("results for {string} should be displayed")
    public void results_should_be_displayed(String searchTerm) {
        System.out.println("Search results displayed for: " + searchTerm);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
