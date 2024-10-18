package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ProductPage;
import static org.junit.Assert.*;

public class OpenProductSteps {
    private WebDriver driver;
    private ProductPage productPage;

    @Given("the user is on the Daraz homepage")
    public void the_user_is_on_the_daraz_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.pk/");
        productPage = new ProductPage(driver);
    }

    @When("the user searches for a product {string}")
    public void the_user_searches_for_a_product(String productName) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(productName);
        searchBox.submit();
    }

    @When("the user selects the first product from the search results")
    public void the_user_selects_the_first_product() {
        productPage.selectFirstProduct();
    }

    @Then("the product page should be displayed")
    public void the_product_page_should_be_displayed() {
        assertTrue(productPage.isDisplayed());
    }

    @Then("the product title should contain {string}")
    public void the_product_title_should_contain(String productName) {
        assertTrue(productPage.getProductTitle().contains(productName));
    }

    @Then("the product price should be visible")
    public void the_product_price_should_be_visible() {
        assertTrue(productPage.isPriceVisible());
    }

    @Then("the add to cart button should be enabled")
    public void the_add_to_cart_button_should_be_enabled() {
        assertTrue(productPage.isAddToCartButtonEnabled());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}