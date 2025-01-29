package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.CheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage{



    public CreateCheckingPage(WebDriver driver) {
       super(driver);
    }

    @FindBy(id = "Standard Checking")
    WebElement standardCheckingRadioButton;

    @FindBy(id = "Interest Checking")
    WebElement interestCheckingRadioButton;

    @FindBy(id = "Individual")
    WebElement individualOwnershipRadioButton;

    @FindBy(id = "Joint")
    WebElement jointOwnershipRadioButton;

    @FindBy(id = "name")
    WebElement accountNameTxtBox;

    @FindBy(id = "openingBalance")
    WebElement initialDepositAmountTxtBox;

    @FindBy(id = "newCheckingSubmit")
    WebElement submitButton;


    public void createNewCheckingAccount(List<CheckingAccountInfo> checkingAccountInfoList){

        checkingMenuButton.click();
        newCheckingButton.click();

        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"),getDriver().getCurrentUrl());

        CheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);

        if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard")) {
            standardCheckingRadioButton.click();
        } else if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest")) {
            interestCheckingRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid Checking account type option");
        }

        if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")){
            individualOwnershipRadioButton.click();
        }
        else if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")){
            jointOwnershipRadioButton.click();
        }
        else {
            throw new NoSuchElementException("Invalid Checking account ownership option");
        }

        accountNameTxtBox.sendKeys(testDataForOneCheckingAccount.getAccountName());

        initialDepositAmountTxtBox.sendKeys(testDataForOneCheckingAccount.getInitialDepositAmount());

        submitButton.click();

    }




}





