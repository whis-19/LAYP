package pages;

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