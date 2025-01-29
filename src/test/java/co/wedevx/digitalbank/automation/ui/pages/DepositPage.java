package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static javax.swing.text.html.CSS.getAttribute;

public class DepositPage extends BaseMenuPage {

    public DepositPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "selectedAccount")
    WebElement accountForDepositDropDown;

    @FindBy(id = "amount")
    WebElement depositAmountTxtBox;

    @FindBy(xpath = "//div[@class='card-footer']/button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class='card-footer']/button[@type='reset']")
    WebElement resetButton;

    public void depositNewAmount(String accountName, String amount) {
        depositMenuButton.click();


        if (!accountName.equals("null")) {
            accountForDepositDropDown.click();
            Select select = new Select(accountForDepositDropDown);
            select.selectByVisibleText(accountName);
        }
        if (!amount.equals("null")) {
            depositAmountTxtBox.sendKeys(amount);
            submitButton.click();
        }
    }

    public String getFieldErrorMessage(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "accountname":
                return accountForDepositDropDown.getAttribute("validationMessage");
            case "amount":
                return depositAmountTxtBox.getAttribute("validationMessage");

            default:
                return "null";
        }
    }
    public void resetDeposit(String accountName, String amount) {

        depositMenuButton.click();
        if (!accountName.equals("null")) {
            accountForDepositDropDown.click();
            Select select = new Select(accountForDepositDropDown);
            select.selectByVisibleText(accountName);
        }
        if (!amount.equals("null")) {
            depositAmountTxtBox.sendKeys(amount);
        }
        resetButton.click();
    }

    public boolean validateResetDeposit(){

        if(!accountForDepositDropDown.isSelected() && depositAmountTxtBox.getText().isEmpty()){
            return true;
        }
        else {
            return false;
        }

    }

}



