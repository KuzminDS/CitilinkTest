package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.List;

public class CatalogPage {
    public WebDriver driver;
    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnToothBrushCatalog() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Зубные щетки и аксессуары"))).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Зубные щетки"))).click();
    }

    public void setMinPrice(Integer minPrice) {
        WebElement inputElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("min")));
        inputElement.sendKeys("\b\b\b\b\b\b\b\b");
        inputElement.sendKeys(minPrice.toString());
    }

    public void setMaxPrice(Integer maxPrice) throws InterruptedException {
        WebElement inputElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("max")));
        inputElement.sendKeys("\b\b\b\b\b\b\b\b");
        inputElement.sendKeys(maxPrice.toString());
        Thread.sleep(2000);
    }

    public List<WebElement> getProductList() {
        List<WebElement> products = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("js--subcategory-product-item")));
        return products;
    }

    public void addToCart(WebElement product) {
        product.findElement(By.className("add_to_cart")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-basket__close-btn"))).click();
    }

    public void clickOnCart() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("cart"))).click();
    }

    public String getCartText() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("basket_storage"))).getText();
    }
}
