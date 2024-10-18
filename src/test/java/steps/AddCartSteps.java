package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddCartPage;
import static org.junit.Assert.*;

public class AddCartSteps {
    private WebDriver driver = null;
    private AddCartPage addCartPage;

    @Given("the user is on the Daraz homepage")
    public void the_user_is_on_the_daraz_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.pk/");
        addCartPage = new AddCartPage(driver);
    }

    @When("the user searches for a product {string}")
    public void the_user_searches_for_a_product(String productName) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(productName);
        searchBox.submit();
    }

    @When("the user selects the first product from the search results")
    public void the_user_selects_the_first_product() {
        addCartPage.selectFirstProduct();
    }

    @When("the user adds the product to the cart")
    public void the_user_adds_the_product_to_the_cart() {
        addCartPage.addProductToCart();
    }

    @Then("the product should be added to the cart")
    public void the_product_should_be_added_to_the_cart() {
        assertTrue(addCartPage.isProductInCart());
    }

    @Then("the cart icon should show the number of items as {int}")
    public void the_cart_icon_should_show_the_number_of_items_as(int expectedCount) {
        assertEquals(expectedCount, addCartPage.getCartItemCount());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
