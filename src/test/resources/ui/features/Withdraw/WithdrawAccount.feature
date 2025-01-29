Feature: Withdraw a new amount from a new account

  Scenario: Positive case. As a user, I want to create a new savings account and withdraw a new amount from it.
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new savings account with a valid amount with the following data
      | savingsAccountType | savingsAccountOwnership | accountName | initialDepositAmount |
      | Savings            | Individual              | Imane33     | 5000.00              |
    When the user is on withdraw page and selects the new account name "Imane33 (Savings)" and withdraw the amount "3000.00"
    Then the user should see the view savings accounts with the following transactions
      | date             | category | description                     | amount   | balance |
      | 2024-01-30 05:30 | Misc     | 845325809 (DPT) -Online Deposit | -3000.00 | 2000.00 |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) -Online Deposit | 5000.00  | 5000.00 |

  Scenario: Negative case. As Digital Bank admin, I want to make sure users cannot withdraw an amount greater that the available balance of their accounts
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new savings account with a valid amount with the following data
      | savingsAccountType | savingsAccountOwnership | accountName | initialDepositAmount |
      | Savings            | Individual              | Zeyad123    | 5000.00              |
    When the user is on withdraw page and selects the new account name "Zeyad123 (Savings)" and withdraw the amount "8000.00"
    Then the user should see an error message "The withdraw amount ($8000.00) is greater than the available balance ($5000.00) and overdraft limit ($25.00)."

  Scenario Outline: Negative case. As Digital Bank admin, I want to make sure users cannot withdraw an amount without selecting the account field or the amount
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new savings account with a valid amount with the following data
      | savingsAccountType | savingsAccountOwnership | accountName | initialDepositAmount |
      | Savings            | Individual              | Deena16     | 300000.00            |
    When the user is on withdraw page and selects the new account name "<accountName>" and withdraw the amount "<amount>"
    Then the user should see an error message "<expectedErrorMessage>" in the "<errorField>"
    Examples:
      | accountName       | amount    | expectedErrorMessage               | errorField  |
      | null              | 200000.00 | Please select an item in the list. | accountName |
      | Deena16 (Savings) | null      | Please fill out this field.        | amount      |
      | Deena16 (Savings) | abcdef    | Please match the requested format. | amount      |
      | Deena16 (Savings) | 8000,00!  | Please match the requested format. | amount      |
