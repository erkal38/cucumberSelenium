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
       |username|user1|
       |password|UserUser123|
       |firstname|John      |
       |lastname |Doe       |
       Then the user should be able to login


