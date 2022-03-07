package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(id = "login2")
    WebElement homeLoginBtn;
    @FindBy(id = "loginusername")
    WebElement userNameField;
    @FindBy(id = "loginpassword")
    WebElement passWordField;
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElement loginBtn;
    @FindBy(id = "nameofuser")
    WebElement welcomeText;
    @FindBy(id = "logout2")
    WebElement logoutBtn;
    @FindBy(xpath = "//body/div[@id='contcont']/div[1]/div[2]/div[1]/div[1]/div[1]/a[1]/img[1]")
    WebElement firstProduct;


    String userName = "magedTest";
    String pass = "1234";

    public void doLogin() throws InterruptedException {
        homeLoginBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(userNameField));
        userNameField.sendKeys(userName);
        passWordField.sendKeys(pass);
        loginBtn.click();
        Thread.sleep(3000);
        Assert.assertTrue(welcomeText.isDisplayed());
    }

    public void goToFirstPDP() {
        firstProduct.click();
    }

}
