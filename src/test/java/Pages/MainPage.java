package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    public WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"login_form_show_js\"]")
    private WebElement loginForm;

    @FindBy(xpath = "//*[@id=\"login\"]")
    private WebElement loginInput;

    @FindBy(xpath = "//*[@id=\"pass\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"captcha_popup-login\"]")
    private WebElement captchaInput;

    @FindBy(xpath = "//*[contains(text(), \"Войти в кабинет\")]")
    private WebElement submitBtn;

    @FindBy(className = "city-popup_toggle-button__js")
    private WebElement cityBtn;

    public void showForm() {
        loginForm.click();
    }

    public void inputLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginBtn() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(submitBtn)).click();
    }

    @FindBy(className = "auth-user-popup__text")
    private WebElement authUserPopup;

    public String getUserName() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("auth-user-popup__text"))).getText();
    }

    public void clickMyOfficeBtn() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("auth-user-popup__text")));
        authUserPopup.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Мой кабинет\")]"))).click();
    }


    public void userLogout() {
        authUserPopup.click();
        WebElement logoutBtn = driver.findElement(By.xpath("//*[contains(text(), \"Выйти\")]"));
        logoutBtn.click();
    }

    public String getCity() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(cityBtn)).getText();
    }

    public void setCity(String newCity) {
        cityBtn.click();
        WebElement city = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(newCity)));
        city.click();
    }

    @FindBy(className = "menu-item_cat_beauty")
    private WebElement beautyAndHealthCatalog;

    public void clickOnBeautyAndHealthCatalog() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("menu-item_cat_beauty")));
        beautyAndHealthCatalog.click();
    }

    @FindBy(className = "screen")
    private WebElement screen;

    public void clickToMainPage() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("screen")));
        screen.click();
    }

    public String getCartText() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("basket-storage__cart"))).getText();
    }

    @FindBy(xpath = "//*[contains(text(), \"Перейти на старую версию сайта\")]")
    private WebElement goToOldPageBtn;

    public void goToOldPage() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Перейти на старую версию сайта\")]")));
        goToOldPageBtn.click();
    }
}