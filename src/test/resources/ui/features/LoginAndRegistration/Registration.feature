@Registration
Feature: Digital Bank Registration Page

  Background:
    Given User navigates to Digital Bank signup page
    And the user with "Test123@gmail.com" is not in DB


  Scenario: Positive Case. As a user, I want to successfully create Digital Bank account

    When The user creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email             | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-45-6471 | Test123@gmail.com | Tester123 | 12 main st | City     | CA     | 99921      | US      | 2136591208 | true           |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be in the db
      | title | firstName | lastName | gender | dob        | ssn         | email             | password  | address    | locality | region | postalCode | country | phone      | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-45-6471 | Test123@gmail.com | Tester123 | 12 main st | City     | CA     | 99921      | US      | 2136591208 | true           |

  @NegativeRegistrationCases
  Scenario Outline: Negative Case. As a Digital Bank admin, I want to make sure users can not register without providing all valid data

    When The user creates account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | phone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <phone> | <termsCheckMark> |
    Then The user should see the "<fieldWithError>" required field error message "<errorMessage>" .

    Examples:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password  | address     | locality | region | postalCode | country | phone      | termsCheckMark | fieldWithError | errorMessage                                  |
      |       |           |          |        |            |        |        |           |             |          |        |            |         |            |                | title          | Please select an item in the list.            |
      | Mr.   |           |          |        |            |        |        |           |             |          |        |            |         |            |                | firstName      | Please fill out this field.                   |
      | Mr.   | John      |          |        |            |        |        |           |             |          |        |            |         |            |                | lastName       | Please fill out this field.                   |
      | Mr.   | John      | Test     |        |            |        |        |           |             |          |        |            |         |            |                | gender         | Please select one of these options.           |
      | Mr.   | John      | Test     | M      |            |        |        |           |             |          |        |            |         |            |                | dob            | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 |        |        |           |             |          |        |            |         |            |                | ssn            | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random |        |           |             |          |        |            |         |            |                | email          | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random |           |             |          |        |            |         |            |                | password       | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 |             |          |        |            |         |            |                | address        | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 | 123 main st |          |        |            |         |            |                | locality       | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 | 123 main st | City     |        |            |         |            |                | region         | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 | 123 main st | City     | CA     |            |         |            |                | postalCode     | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 | 123 main st | City     | CA     | 99921      |         |            |                | country        | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 | 123 main st | City     | CA     | 99921      | us      |            |                | phone          | Please fill out this field.                   |
      | Mr.   | John      | Test     | M      | 12/12/1990 | random | random | Tester123 | 123 main st | City     | CA     | 99921      | us      | 9996451847 |                | termsCheckMark | Please check this box if you want to proceed. |
