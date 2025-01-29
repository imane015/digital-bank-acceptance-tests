package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WithdrawPage extends BaseMenuPage{
    public WithdrawPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "selectedAccount")
    WebElement accountForWithdrawDropDown;

    @FindBy(id = "amount")
    WebElement withdrawAmountTxtBox;

    @FindBy(xpath = "//div[@class='card-footer']/button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class='card-footer']/button[@type='reset']")
    WebElement resetButton;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-danger alert-dismissible fade show']//span[2]")
    WebElement confErrorMessage;



    public void withdrawNewAmount(String accountName, String amount) {

        withdrawMenuButton.click();

        if (!accountName.equals("null")) {
            accountForWithdrawDropDown.click();
            Select select = new Select(accountForWithdrawDropDown);
            select.selectByVisibleText(accountName);
        }
        if (!amount.equals("null")) {
            withdrawAmountTxtBox.sendKeys(amount);
            submitButton.click();
        }
    }

    public String getActualErrorMessage(){
        return confErrorMessage.getText();
    }


}
