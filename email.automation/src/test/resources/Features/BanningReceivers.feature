Feature: Testing email account banning automation

  Scenario Outline: Unban an email account
    Given the system selects the email account "<email>"
    When an email account with the status "Ban"
    Then an message: "<message>" is provided to the system for verification
    And if the system verified the email unbans the email account

    Examples:
      | email                 | message                         |
      | dani.topov1@gmail.com | Email address has been verified |
      | dani.topov1           | Incorrect email address         |
      |                       | Not specified email address     |

  Scenario Outline: Ban an email account
    Given the system selects the email account "<email>"
    When an email account with the status "Unban"
    Then an message: "<message>" is provided to the system for verification
    And if the system verified the email bans the email account

    Examples:
      | email                 | message                         |
      | dani.topov1@gmail.com | Email address has been verified |
      | dani.topov1           | Incorrect email address         |
      |                       | Not specified email address     |
