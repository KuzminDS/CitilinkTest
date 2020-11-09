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

public class CartPage {
    public WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCheckout() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Оформить заказ\")]"))).click();
    }

    public void selectDelivery() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Далее\")]"))).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("for_delivery_type_0"))).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"delivery_save\"]"))).click();
    }

    public Integer getFullPrice() {
        String price = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("js--order-amount__amount-num"))).getText();
        return Integer.parseInt(price);
    }

    public Integer getProductPrice() {
        String price = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("js--order-amount-without-discount__amount-num"))).getText();
        return Integer.parseInt(price);
    }

    public Integer getDeliveryPrice() {
        WebElement deliveryPriceBlock = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[./td[contains(text(), \"Доставка\")]]")));
        String price = deliveryPriceBlock.findElement(By.className("num")).getText();
        return Integer.parseInt(price);
    }

    public void setProductCount(Integer count) {
        WebElement productCountBlock = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("product_amount_control")));
        productCountBlock.sendKeys("\b\b\b\b\b\b");
        productCountBlock.sendKeys(count.toString());
    }

    public void removeAllProducts() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("remove_all"))).click();
    }
}
