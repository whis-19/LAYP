package pages;

import org.openqa.selenium.*;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        WebElement firstProduct = driver.findElement(By.cssSelector("div.s-result-item:first-child"));
        firstProduct.click();
    }

    public boolean isDisplayed() {
        return driver.getTitle() != null && !driver.getTitle().isEmpty();
    }

    public String getProductTitle() {
        WebElement productTitle = driver.findElement(By.cssSelector("h1.product-title")); 
        return productTitle.getText();
    }

    public boolean isPriceVisible() {
        try {
            WebElement productPrice = driver.findElement(By.cssSelector(".product-price"));
            return productPrice.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAddToCartButtonEnabled() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
        return addToCartButton.isEnabled();
    }
}