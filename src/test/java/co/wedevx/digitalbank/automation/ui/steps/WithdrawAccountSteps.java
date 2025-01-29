package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.WithdrawPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawAccountSteps {

    WithdrawPage withdrawPage = new WithdrawPage(getDriver());
    @When("the user is on withdraw page and selects the new account name {string} and withdraw the amount {string}")
    public void the_user_is_on_withdraw_page_and_selects_the_new_account_name_and_withdraw_the_amount(String accountName, String amount) {
        withdrawPage.withdrawNewAmount(accountName,amount);
    }

    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedMessage) {
        assertEquals(expectedMessage,withdrawPage.getActualErrorMessage());
    }
}
