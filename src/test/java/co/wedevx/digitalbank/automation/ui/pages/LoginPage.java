package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="username")
    private WebElement usernameTxtBox;

    @FindBy(id="password")
    private WebElement passwordTxtBOx;

    @FindBy(id="remember-me")
    private WebElement rememberMeButton;

    @FindBy(id="submit")
    private WebElement signInButton;

    @FindBy(xpath="a[href='/bank/signup']")
    private WebElement signUpLink;

    public void login(String username,String password) {

        usernameTxtBox.sendKeys(username);
        passwordTxtBOx.sendKeys(password);
        rememberMeButton.click();
        signInButton.click();
    }
    public void loginForNewResisterUser(String password){
        passwordTxtBOx.sendKeys(password);
        rememberMeButton.click();
        signInButton.click();
    }




}
