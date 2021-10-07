@Test123
Feature: Test Personal home calculator

  Background: 
    Given user launch the apllication

  @TesthomeCalculator
  Scenario Outline: validate the data entered by the user
    When user fill all the details and vaidate the borrowing estimate
    Then user clear all the data clicking on the start over
    And User enter the "<Living expense>" and leaving all other fields
    Then user validate the Error message

    Examples: 
      | Living expense |
      |              1 |
