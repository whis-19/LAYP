package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenProductPage {

    WebDriver driver;
    public WebDriverWait wait;

    public OpenProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Locator for the product image that contains "mug-white" in the src
    By ProductImage = By.cssSelector("img[src*='mug-white']");

    // Method to click on the product image
    public void clickFirstProduct() {
        WebElement productImageElement = driver.findElement(ProductImage);
        productImageElement.click();
    }
}
