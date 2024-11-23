package steps;

import org.openqa.selenium.WebDriver;
import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.AddToCartPage;

public class AddToCartSteps {

    private WebDriver driver;
    private AddToCartPage addToCartPage;
    private int initialCartCount;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        addToCartPage = new AddToCartPage(driver);
    }

    @Given("the user is on the product details page")
    public void the_user_is_on_the_product_details_page() {
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
        initialCartCount = Integer.parseInt(addToCartPage.getCartItemCount());
    }

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String buttonName) {
        if ("Add to Cart".equalsIgnoreCase(buttonName)) {
            addToCartPage.clickAddToCart();
        } else {
            throw new IllegalArgumentException("No action defined for button: " + buttonName);
        }
    }

    @Then("the product should be added to the cart")
    public void the_cart_icon_should_show_the_correct_number_of_items() {
        String cartItemCount = addToCartPage.getCartItemCount();

        int expectedCount = 1;
        Assert.assertEquals("The cart should indicate items are added", expectedCount, Integer.parseInt(cartItemCount));
    }

    @After
    public void tearDown() {
        try {
            DriverFactory.quitDriver();
        } catch (Exception e) {
            System.err.println("Error quitting driver: " + e.getMessage());
        }
    }
}
