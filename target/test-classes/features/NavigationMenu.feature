@navigate
Feature: Navigation Menu
  Scenario: Navigation Fleet--Vehicles
    Given the user is on the login page
    When the user enter the sales manager information
    Then the user navigates to Fleet, Vehicles
    Then the title should be Vehicles
    @db
   Scenario: Navigating Marketing--Campaigns
     Given the user is on the login page
     When the user enter the sales manager information
     Then the user navigates Marketing, Campaigns
     Then the title should be Campaigns

  Scenario: Navigating Activities--Calendar Events
    Given the user is on the login page
    When the user enter the sales manager information
    Then the user navigates Activities, Calendar Events
    Then the title should be Calendars


