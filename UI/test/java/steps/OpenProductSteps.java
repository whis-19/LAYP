package steps;

import org.openqa.selenium.WebDriver;
import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import pages.OpenProductPage;

public class OpenProductSteps {

    WebDriver driver;
    OpenProductPage inventoryPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        inventoryPage = new OpenProductPage(driver);
    }

    @Given("the user is on the demo page")
    public void the_user_is_on_the_inventory_page() {
        driver.get("https://websitedemos.net/custom-printing-02/?customize=template");
    }

    @When("the user clicks on the product")
    public void the_user_clicks_on_the_first_product() {
        inventoryPage.clickFirstProduct();
    }

    @Then("the user should see the product details page")
    public void the_user_should_see_the_product_details_page() {
        WebElement productTitle = driver.findElement(By.cssSelector(".product_title.entry-title"));
        Assert.assertTrue("Product details are displayed", productTitle.isDisplayed());
    }


    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
