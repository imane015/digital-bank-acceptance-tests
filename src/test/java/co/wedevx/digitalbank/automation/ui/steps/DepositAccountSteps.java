package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.CheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.SavingsAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositAccountSteps {

    CreateCheckingPage createCheckingPage = new CreateCheckingPage(getDriver());
    RegistrationPage registrationPage = new RegistrationPage(getDriver());
    ViewCheckingPage viewCheckingPage = new ViewCheckingPage(getDriver());
    LoginPage loginpage = new LoginPage(getDriver());
    DepositPage depositpage = new DepositPage(getDriver());
    CreateSavingsPage createSavingsPage = new CreateSavingsPage(getDriver());

    @Given("the user registered a new account with the following data")
    public void the_user_registered_a_new_account_with_the_following_data(List<Map<String,String>> registrationDataListMap) {
        getDriver().get(getPropertiesValue("digitalbank.registrationpageurl"));
        registrationPage.registerNewAccount(registrationDataListMap);
        assertEquals("Success Registration Successful. Please Login.",registrationPage.getMessage(),"success message mismatch");
    }

    @Given("the user login with valid password {string}")
    public void the_user_login_with_valid_password(String password) {
        loginpage.loginForNewResisterUser(password);
    }

    @Given("the user created a new checking account with a valid amount with the following data")
    public void the_user_created_a_new_checking_account_with_a_valid_amount_with_the_following_data(List<CheckingAccountInfo> checkingAccountList) {
        createCheckingPage.createNewCheckingAccount(checkingAccountList);
    }

    @Given("the user created a new savings account with a valid amount with the following data")
    public void the_user_created_a_new_savings_account_with_a_valid_amount_with_the_following_data(List<SavingsAccountInfo> savingsAccountInfoList) {
        createSavingsPage.createNewSavingsAccount(savingsAccountInfoList);
    }

    @When("the user is on deposit page and selects the new account name {string} and input the amount to deposit {string}")
    public void the_user_is_on_deposit_page_and_selects_the_new_account_name_and_input_the_amount_to_deposit(String accountName, String amount) {
        depositpage.depositNewAmount(accountName, amount);
    }
    @When("the user is on deposit page and select the account {string} and inputs the amount to deposit {string}")
    public void the_user_is_on_deposit_page_and_select_the_account_and_inputs_the_amount_to_deposit(String accountName, String amount) {
        depositpage.depositNewAmount(accountName,amount);
    }

    @When("the user is on deposit page and selects the new account name {string} and input the amount to deposit {string} and click on reset button")
    public void the_user_is_on_deposit_page_and_selects_the_new_account_name_and_input_the_amount_to_deposit_and_click_on_reset_button(String accountName, String amount) {
        depositpage.resetDeposit(accountName,amount);
    }

    @Then("the user should see an error message {string} in the {string}")
    public void the_user_should_see_an_error_message_in_the(String expectedMessage,String errorField) {
        String actualErrorMessage = depositpage.getFieldErrorMessage(errorField);
        assertEquals(expectedMessage,actualErrorMessage);
    }

    @Then("the user should see in the view checking the following transactions")
    public void the_user_should_see_in_the_view_checking_the_following_transactions(List<BankTransaction> expectedTransactionsList) {
        Map<String, String> actualMapResult = viewCheckingPage.getNewlyAddedCheckingTransactionInfoMap();

        BankTransaction expectedTransaction = expectedTransactionsList.get(0);

        Assertions.assertEquals(expectedTransaction.getCategory(), actualMapResult.get("actualCategory"), "Category mismatch");
        //Assertions.assertEquals(expectedTransaction.getDescription(), actualMapResult.get("actualDescription"), "Description mismatch");
        Assertions.assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualMapResult.get("actualAmount")), "Amount mismatch");
        Assertions.assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualMapResult.get("actualBalance")), "Balance mismatch");
    }
    @Then("the user should see the view savings accounts with the following transactions")
    public void the_user_should_see_the_view_savings_accounts_with_the_following_transactions(List<BankTransaction> expectedTransactions) {
        Map<String,String> actualMapResult = viewCheckingPage.getNewlyAddedCheckingTransactionInfoMap();
        BankTransaction expectedTransaction = expectedTransactions.get(0);

        assertEquals(expectedTransaction.getCategory(),actualMapResult.get("actualCategory"));
        assertEquals(expectedTransaction.getAmount(),Double.parseDouble(actualMapResult.get("actualAmount")));
        assertEquals(expectedTransaction.getBalance(),Double.parseDouble(actualMapResult.get("actualBalance")));
    }

    @Then("all the fields on deposit page should be reset to default")
    public void all_the_fields_on_deposit_page_should_be_reset_to_default() {
        boolean actualResult = depositpage.validateResetDeposit();
        assertTrue(actualResult,"my conditions for reset to default page are not true");

    }


}

