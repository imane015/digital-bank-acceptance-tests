package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.SavingsAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateSavingsPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsAccountSteps {

    LoginPage loginPage = new LoginPage(getDriver());
    CreateSavingsPage createSavingsPage = new CreateSavingsPage(getDriver());
    ViewCheckingPage viewCheckingPage = new ViewCheckingPage(getDriver());

    @Given("The user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password) {
        loginPage.login(username,password);
    }
    @When("the user creates a new savings account with the following data:")
    public void the_user_creates_a_new_savings_account_with_the_following_data(List<SavingsAccountInfo> savingsAccountInfoList) {
        createSavingsPage.createNewSavingsAccount(savingsAccountInfoList);
    }
    @Then("the user should see the following message {string}")
    public void the_user_should_see_the_following_message(String expectedConfMessage) {
        assertEquals(expectedConfMessage,createSavingsPage.getValidationMessage());
    }
    @Then("the user should see the following transactions:")
    public void the_user_should_see_the_following_transactions(List<BankTransaction> expectedTransactions) {
        Map<String,String> actualMapResult = viewCheckingPage.getNewlyAddedCheckingTransactionInfoMap();
        BankTransaction expectedTransaction = expectedTransactions.get(0);

        assertEquals(expectedTransaction.getCategory(),actualMapResult.get("actualCategory"));
        assertEquals(expectedTransaction.getAmount(),Double.parseDouble(actualMapResult.get("actualAmount")));
        assertEquals(expectedTransaction.getBalance(),Double.parseDouble(actualMapResult.get("actualBalance")));
    }

}
