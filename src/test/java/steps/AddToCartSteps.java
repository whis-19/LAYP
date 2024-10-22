package steps;

<<<<<<< Updated upstream
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddToCartPage;
import static org.junit.Assert.*;

public class AddToCartSteps {
    private WebDriver driver = null;
    private AddToCartPage addCartPage;

    @Given("the user is on the Daraz homepage")
    public void the_user_is_on_the_daraz_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.pk/");
        addCartPage = new AddToCartPage(driver);
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
=======
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
>>>>>>> Stashed changes
    }

    @After
    public void tearDown() {
<<<<<<< Updated upstream
        driver.quit();
=======
        try {
            DriverFactory.quitDriver();
        } catch (Exception e) {
            System.err.println("Error quitting driver: " + e.getMessage());
        }
>>>>>>> Stashed changes
    }
}
