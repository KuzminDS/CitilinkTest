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
        submitBtn.click();
    }

    public String getUserName() {
        WebElement userForm = driver.findElement(By.className("auth-user-popup__text"));
        String userName = userForm.getText();
        return userName;
    }

    public void clickMyOfficeBtn() {
        WebElement userForm = driver.findElement(By.className("auth-user-popup__text"));
        userForm.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Мой кабинет\")]"))).click();
    }

    public void userLogout() {
        WebElement userForm = driver.findElement(By.className("auth-user-popup__text"));
        userForm.click();
        WebElement logoutBtn = driver.findElement(By.xpath("//*[contains(text(), \"Выйти\")]"));
        logoutBtn.click();
    }

    public String getCity() {
        return cityBtn.getText();
    }

    public void setCity(String newCity) {
        cityBtn.click();
        WebElement city = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(newCity)));
        city.click();
    }

    public void clickOnBeautyAndHealthCatalog() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("menu-item_cat_beauty"))).click();
    }

    public void clickToMainPage() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("screen"))).click();
    }
}
