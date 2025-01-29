Feature: Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "nayanayat@gmail.com" "Nayanayat1990"
    When the user creates a new checking account with the following data
      | checkingAccountType | accountOwnership | accountName               | initialDepositAmount |
      | Standard            | Individual       | Elon Musk second checking | 100000.0             |
    Then the user should see the green "Successfully created new Standard Checking account named Elon Musk second checking" message
    And the user should see newly added account card
      | accountName               | accountType       | ownership  | interestRate | balance   |
      | Elon Musk second checking | Standard Checking | Individual | 0.0%         | 100000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount   | balance  |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) - Deposit | 100000.0 | 100000.00 |

