Feature: Contacts Page

  Scenario: Default page number
    Given the user is on the login page
    When the user enter the driver information
    Then the user navigates "Customers" "Contacts"
    Then default page number should be 1

  Scenario: Verify Create Calendar Event
    Given the user is on the login page
    And the user enter the sales manager information
    When the user navigates "Activities" "Calendar Events"
@smoke
  Scenario: Menu Options
    Given the user logged in as "driver"
    When the user should see following options
      | Fleet      |
      | Customers  |
      | Activities |
      | System     |

  Scenario: login as a given user
    Given the user is on the login page
    When the user should use following credentials
      | username  | user1       |
      | password  | UserUser123 |
      | firstname | John        |
      | lastname  | Doe         |
    Then the user should be able to login


  Scenario: Contacts test with email
    Given the user logged in as "store manager"
    And the user navigates "Customers" "Contacts"
    When the user clicks the "mbrackstone9@example.com" from contacts
    Then the information should be same with database

  @wip @db
  Scenario Outline: Contacts test with email
    Given the user logged in as "store manager"
    And the user navigates "Customers" "Contacts"
    When the user clicks the "<email>" from contacts
    Then the information "<email>" should be same with database

    Examples:
      | email                    |
      | mbrackstone9@example.com |
      | mike.jorden@hotmail.com  |
      | Asan@gmail.com           |
