package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.SavingsAccountInfo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CreateSavingsPage extends BaseMenuPage{

    public CreateSavingsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "Savings")
    WebElement savingsRadioButton;

    @FindBy(id = "Money Market")
    WebElement moneyMarketRadioButton;

    @FindBy(id = "Individual")
    WebElement individualRadioButton;

    @FindBy(id = "Joint")
    WebElement jointRadioButton;

    @FindBy(id = "name")
    WebElement accountNameTxtBox;

    @FindBy(id = "openingBalance")
    WebElement initialDepositTxtBox;

    @FindBy(id = "newSavingsSubmit")
    WebElement submitButton;

    @FindBy(id = "new-account-msg")
    WebElement confirmationMessage;


    public void createNewSavingsAccount(List<SavingsAccountInfo> savingsAccountInfoList){
        savingsMenuButton.click();
        newSavingsButton.click();

        SavingsAccountInfo testDataForOneSavingsAccount = savingsAccountInfoList.get(0);
        if(testDataForOneSavingsAccount.getSavingsAccountType().equalsIgnoreCase("Savings")){
            savingsRadioButton.click();
        }
        else if(testDataForOneSavingsAccount.getSavingsAccountType().equalsIgnoreCase("Money Market")){
            moneyMarketRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid Savings account type option");
        }

        if(testDataForOneSavingsAccount.getSavingsAccountOwnership().equalsIgnoreCase("Individual")){
            individualRadioButton.click();
        }
        else if(testDataForOneSavingsAccount.getSavingsAccountOwnership().equalsIgnoreCase("Joint")){
            jointRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid Savings account ownership option");
        }

        accountNameTxtBox.sendKeys(testDataForOneSavingsAccount.getAccountName());
        initialDepositTxtBox.sendKeys(testDataForOneSavingsAccount.getInitialDepositAmount());
        submitButton.click();
    }

    public String getValidationMessage(){
        return confirmationMessage.getText();
    }




}
