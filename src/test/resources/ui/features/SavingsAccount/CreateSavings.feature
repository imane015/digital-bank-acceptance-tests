Feature: Creating a new savings account

  Scenario: Create an individual Savings account
    Given The user logged in as "nayanayat@gmail.com" "Nayanayat1990"
    When the user creates a new savings account with the following data:
      | savingsAccountType | savingsAccountOwnership | accountName | initialDepositAmount |
      | Savings            | Individual              | Deena16     | 200000.00             |
    Then the user should see the following message "Successfully created new Savings account named Deena16"
    And the user should see the following transactions:
      | date             | category | description               | amount   | balance   |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) - Deposit | 200000.00 | 200000.00 |
