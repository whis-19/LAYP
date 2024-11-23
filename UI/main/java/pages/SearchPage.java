package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    By searchBar = By.xpath("//input[@type='search']");
    By searchButton = By.cssSelector("a.search-box__button--1oH7");

    public void enterSearchTerm(String searchTerm) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        driver.findElement(searchBar).clear();
        driver.findElement(searchBar).sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
    }
}
