@Deposit
Feature: Deposit a new amount to a new account

  Scenario: Positive case. As a user, I want to create a new checking account and deposit a new amount to it.
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new checking account with a valid amount with the following data
      | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
      | Standard            | Individual       | Deena16     | 300000.00            |
    When the user is on deposit page and selects the new account name "Deena16 (Standard Checking)" and input the amount to deposit "200000.00"
    Then the user should see in the view checking the following transactions
      | date             | category | description                     | amount    | balance   |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) -Online Deposit | 200000.00 | 500000.00 |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) - Deposit       | 300000.00 | 300000.00 |

  Scenario Outline: Negative case. As Digital Bank admin, I want to make sure users cannot deposit an amount without selecting the account field or the amount
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new checking account with a valid amount with the following data
      | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
      | Standard            | Individual       | Deena16     | 300000.00            |
    When the user is on deposit page and select the account "<accountName>" and inputs the amount to deposit "<amount>"
    Then the user should see an error message "<expectedErrorMessage>" in the "<errorField>"
    Examples:
      | accountName                 | amount    | expectedErrorMessage               | errorField  |
      | null                        | 200000.00 | Please select an item in the list. | accountName |
      | Deena16 (Standard Checking) | null      | Please fill out this field.        | amount      |


  Scenario: Positive case. As a user, I want to create a new savings account and deposit a new amount to it.
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new savings account with a valid amount with the following data
      | savingsAccountType | savingsAccountOwnership | accountName | initialDepositAmount |
      | Savings            | Individual              | Hamza33     | 7000.00              |
    When the user is on deposit page and selects the new account name "Hamza33 (Savings)" and input the amount to deposit "5000.00"
    Then the user should see the view savings accounts with the following transactions
      | date             | category | description                     | amount  | balance  |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) -Online Deposit | 5000.00 | 12000.00 |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) - Deposit       | 7000.00 | 7000.00  |

  Scenario: Positive case. As a user, I want to create a new savings account and deposit a new amount amount to it and then reset it to default page
    Given the user registered a new account with the following data
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mrs.  | Deena     | Test     | F      | 07/02/2016 | random | random | Tester123 | 12 main st | edison   | NJ     | 04412      | US      | 2136591208 | true           |
    And the user login with valid password "Tester123"
    And the user created a new savings account with a valid amount with the following data
      | savingsAccountType | savingsAccountOwnership | accountName | initialDepositAmount |
      | Savings            | Individual              | Hamza33     | 7000.00              |
    When the user is on deposit page and selects the new account name "Hamza33 (Savings)" and input the amount to deposit "5000.00" and click on reset button
    Then all the fields on deposit page should be reset to default
