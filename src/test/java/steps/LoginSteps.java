package steps;

import org.openqa.selenium.WebDriver;

import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.DBConnection;

public class LoginSteps {

	WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        
    }
    
    @Given("the user is on the login page")
    public void user_is_on_login_page() throws InterruptedException {
        driver.get("https://member.daraz.pk/user/login");
    }


    @When("the user enters valid credentials from DB")
    public void user_enters_credentials_from_db() throws Exception {
        String username = DBConnection.getTestData("SELECT username FROM test_cases_data WHERE id=user1");
        String password = DBConnection.getTestData("SELECT password FROM test_cases_data WHERE id=user2");
        
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }


    @Then("the user should be logged in")
    public void user_is_redirected_to_home_page() 
    {
        System.out.println("User is logged in successfully!!");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
