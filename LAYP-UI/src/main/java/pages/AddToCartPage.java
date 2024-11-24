package pages;

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
