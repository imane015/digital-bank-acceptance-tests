package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends BasePage{

    public BaseMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "checking-menu")
    WebElement checkingMenuButton;

    @FindBy(id = "new-checking-menu-item")
    WebElement newCheckingButton;

    @FindBy(id = "view-checking-menu-item")
    WebElement viewCheckingButton;

    @FindBy(id = "home-menu-item")
    WebElement homeButton;

    @FindBy(id = "savings-menu")
    WebElement savingsMenuButton;

    @FindBy(id = "view-savings-menu-item")
    WebElement viewSavingsButton;

    @FindBy(id = "new-savings-menu-item")
    WebElement newSavingsButton;

    @FindBy(id = "external-accounts-menu")
    WebElement externalMenuButton;

    @FindBy(id = "link-external-menu-item")
    WebElement linkExternalAccountButton;

    @FindBy(id = "view-external-menu-item")
    WebElement viewExternalAccountsButton;

    @FindBy(id = "deposit-menu-item")
    WebElement depositMenuButton;

    @FindBy(id = "withdraw-menu-item")
    WebElement withdrawMenuButton;

    @FindBy(id = "transfer-menu-item")
    WebElement transferBetweenAccountsMenuButton;

    @FindBy(id = "visa-transfer-menu-item")
    WebElement visaDirectTransferMenuButton;














    public void goHomePage() {
        homeButton.click();
    }


}
