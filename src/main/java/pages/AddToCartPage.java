package pages;

<<<<<<< Updated upstream
import org.openqa.selenium.*;

public class AddToCartPage {
    private WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        WebElement firstProduct = driver.findElement(By.cssSelector("div.s-result-item:first-child"));
        firstProduct.click();
    }

    public void addProductToCart() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
        addToCartButton.click();
    }

    public boolean isProductInCart() {
        driver.get("https://www.daraz.pk/cart");
        return driver.findElements(By.cssSelector(".cart-item")).size() > 0;
    }

    public int getCartItemCount() {
        WebElement cartIcon = driver.findElement(By.cssSelector(".cart-icon"));
        String itemCountText = cartIcon.getText();
        return itemCountText.isEmpty() ? 0 : Integer.parseInt(itemCountText);
    }
}
=======
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartPage {

    WebDriver driver;
    public WebDriverWait wait;


    By addToCartButton = By.xpath("//button[contains(text(), 'Add to cart')]");
    By cartIcon = By.className("sc-1h98xa9-2");
    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddToCart() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        button.click();
    }

    public String getCartItemCount() {
        WebElement cartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
        return cartCountElement.getText();
    }
}
>>>>>>> Stashed changes
