package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    List<Map<String,Object>> nextIdList;


    RegistrationPage registrationPage = new RegistrationPage(getDriver());

    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(getPropertiesValue("digitalbank.registrationpageurl"));
        assertEquals("Digital Bank",getDriver().getTitle(),"Registration page title mismatch");
    }
    @When("The user creates account with following fields")
    public void the_user_creates_account_with_following_fields(List<Map<String,String>> registrationDataMap) {
        registrationPage.registerNewAccount(registrationDataMap);
    }
    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessMessage) {
        assertEquals(expectedSuccessMessage,registrationPage.getMessage(),"success message mismatch");
    }


    @Then("The user should see the {string} required field error message {string} .")
    public void theUserShouldSeeTheRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage) {
        assertEquals(expectedErrorMessage,registrationPage.getRequiredFieldErrorMessage(fieldName));
    }

    @Then("the following user info should be in the db")
    public void the_following_user_info_should_be_in_the_db(List<Map<String,String>> expectedUserProfileInfoInDB) {
        Map<String,String> expectedUserInfoMap = expectedUserProfileInfoInDB.get(0);
        List<Map<String,Object>> queryUserProfileList = DBUtils.runSQLSelectQuery(String.format("Select * from user_profile where email_address = '%s'",expectedUserInfoMap.get("email")));
        List<Map<String,Object>> queryUsersList = DBUtils.runSQLSelectQuery(String.format("Select * from users where username = '%s'",expectedUserInfoMap.get("email")));
        Map<String,Object> actualInfoUserProfile = queryUserProfileList.get(0);
        Map<String,Object> actualInfoUsers = queryUsersList.get(0);

        assertEquals(1,queryUserProfileList.size(),"size of the user profile query is less or more than 1");
        assertEquals(expectedUserInfoMap.get("title"), actualInfoUserProfile.get("title"));
        assertEquals(expectedUserInfoMap.get("firstName"), actualInfoUserProfile.get("first_name"));
        assertEquals(expectedUserInfoMap.get("lastName"), actualInfoUserProfile.get("last_name"));
        assertEquals(expectedUserInfoMap.get("gender"), actualInfoUserProfile.get("gender"));
        assertEquals(expectedUserInfoMap.get("ssn"), actualInfoUserProfile.get("ssn"));
        assertEquals(expectedUserInfoMap.get("email"), actualInfoUserProfile.get("email_address"));
        assertEquals(expectedUserInfoMap.get("address"), actualInfoUserProfile.get("address"));
        assertEquals(expectedUserInfoMap.get("locality"), actualInfoUserProfile.get("locality"));
        assertEquals(expectedUserInfoMap.get("region"), actualInfoUserProfile.get("region"));
        assertEquals(expectedUserInfoMap.get("postalCode"), actualInfoUserProfile.get("postal_code"));
        assertEquals(expectedUserInfoMap.get("country"), actualInfoUserProfile.get("country"));
        assertEquals(expectedUserInfoMap.get("phone"), actualInfoUserProfile.get("home_phone"));
        assertEquals(nextIdList.get(0).get("next_val"), actualInfoUsers.get("id"));

        long nextIdIncrementedForUserProfile = Integer.parseInt(String.valueOf(nextIdList.get(0).get("next_val")));
        assertEquals(++nextIdIncrementedForUserProfile, actualInfoUserProfile.get("id"));
    }

    @Given("the user with {string} is not in DB")
    public void the_user_with_is_not_in_db(String email) {
        String queryForUserProfile = String.format("delete from user_profile where email_address = '%s'",email);
        String queryForUsers = String.format("delete from users where username = '%s'" ,email);
        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);

        String queryToGetNextIdInHibernateSequenceTable = "select * from hibernate_sequence";
        nextIdList = DBUtils.runSQLSelectQuery(queryToGetNextIdInHibernateSequenceTable);


    }

}
