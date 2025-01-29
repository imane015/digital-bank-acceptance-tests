package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.CheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.Map;


public class CheckingAccountSteps{

    WebDriver driver = Driver.getDriver();

    private LoginPage loginPage = new LoginPage(driver);
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private ViewCheckingPage viewCheckingPage = new ViewCheckingPage(driver);

    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password) {

        loginPage.login(username, password);
    }

    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<CheckingAccountInfo> checkingAccountInfoList) {

        createCheckingPage.createNewCheckingAccount(checkingAccountInfoList);
    }


    @Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_successfully_created_new_standard_checking_account_named_ann_s_checking_account_message(String expectedConfirmationMessage) {

        expectedConfirmationMessage = "Confirmation "+ expectedConfirmationMessage + "\n×";
        Assertions.assertEquals(expectedConfirmationMessage, viewCheckingPage.getActualConfirmationMessage());
    }

    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {

        Map<String, String> actualMapResult = viewCheckingPage.getLastAddedCheckingCardInfoMap();

        AccountCard expectedResult = accountCardList.get(0);

        Assertions.assertEquals(expectedResult.getAccountName(), actualMapResult.get("actualAccountName"));
        Assertions.assertEquals("Account: "+expectedResult.getAccountType(), actualMapResult.get("actualAccType"));
        Assertions.assertEquals("Ownership: "+expectedResult.getOwnership(), actualMapResult.get("actualOwnership"));
        Assertions.assertEquals("Interest Rate: "+expectedResult.getInterestRate(), actualMapResult.get("actualInterestRate"));
        String expectedBalance = String.format("%.2f",expectedResult.getBalance());
        Assertions.assertEquals("Balance: $"+expectedBalance, actualMapResult.get("actualBalance"));
    }

    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<BankTransaction> expectedTransactions) {

        Map<String, String> actualMapResult = viewCheckingPage.getNewlyAddedCheckingTransactionInfoMap();

        BankTransaction expectedTransaction = expectedTransactions.get(0);

        Assertions.assertEquals(expectedTransaction.getCategory(), actualMapResult.get("actualCategory"), "Category mismatch");
        //Assertions.assertEquals(expectedTransaction.getDescription(), actualMapResult.get("actualDescription"), "Description mismatch");
        Assertions.assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualMapResult.get("actualAmount")), "Amount mismatch");
        Assertions.assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualMapResult.get("actualBalance")), "Balance mismatch");


    }

}





