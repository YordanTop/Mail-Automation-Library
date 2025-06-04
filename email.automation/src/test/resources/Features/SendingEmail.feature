Feature: Testing the sending functionalities of EAL

  Scenario: Try sending email to specific email address when it's banned
      Given the sender trying to send an email automatically
      And the sender input the receiver address for the email "dani.topov1@gmail.com"
      And if the receiver ban status is "Ban"
      And the sender input the subject for the email "Title of the email"
      And the sender input the context for the email "Context body of the email"
      And the sender attaches its email address "yordantop1@gmail.com"
      Then the sender receive the message "This address is banned"
      And after that sending the email to specific email

    Scenario Outline: Try sending email to specific email address when it's not banned
      Given the sender trying to send an email automatically
      And the sender input the receiver address for the email "<receiverAddress>"
      When if the receiver ban status is "Unban"
      And the sender input the subject for the email "<subject>"
      And the sender input the context for the email "<context>"
      And the sender attaches its email address "<senderAddress>"
      Then the sender receive the message "<message>"
      And after that sending the email to specific email

      Examples:
        | receiverAddress       | subject            | context                   | senderAddress        | message                                  |
        | dani.topov1@gmail.com | Title of the email | Context body of the email | yordantop1@gmail.com | Email message has been successfully sent |
        | dani.topov1@gmail.com |                    | Context body of the email | yordantop1@gmail.com | Empty email subject |
        | dani.topov1@gmail.com | Title of the email |                           | yordantop1@gmail.com | Empty context body |
        | dani.topov1@gmail.com | Title of the email | Context body of the email | yordantop1           | Incorrect email address |
        | dani.topov1           | Title of the email | Context body of the email | yordantop1@gmail.com | Incorrect email address |
        | dani.topov1@gmail.com | Title of the email | Context body of the email |                      | Not specified email address |
        |                       | Title of the email | Context body of the email | yordantop1@gmail.com | Not specified email address |


    Scenario Outline: Sending email to specific email address with pinned files
      Given the sender trying to send an email automatically
      And the sender input the receiver address for the email "<receiverAddress>"
      When if the receiver ban status is "Unban"
      And the sender input the subject for the email "<subject>"
      And the sender input the context for the email "<context>"
      And the sender attaches the pinned files for the email "<pinFiles>"
      And the sender attaches its email address "<senderAddress>"
      Then the sender receive the message "<message>"
      And after that sending the email to specific email


    Examples:
      | receiverAddress       | subject            | context                   | pinFiles                                             | senderAddress        | message                                  |
      | dani.topov1@gmail.com | Title of the email | Context body of the email | C:\\Users\\Dani\\Desktop\\informaciq za student.docx | yordantop1@gmail.com | Email message has been successfully sent |
      | dani.topov1@gmail.com |                    | Context body of the email | C:\\Users\\Dani\\Desktop\\informaciq za student.docx | yordantop1@gmail.com | Empty email subject  |
      | dani.topov1@gmail.com | Title of the email |                           | C:\\Users\\Dani\\Desktop\\informaciq za student.docx | yordantop1@gmail.com | Empty context body |
      | dani.topov1@gmail.com | Title of the email | Context body of the email | C:\\Users\\Dani\\Desktop\\informaciq za student.docx | yordantop1           | Incorrect email address |
      | dani.topov1           | Title of the email | Context body of the email | C:\\Users\\Dani\\Desktop\\informaciq za student.docx | yordantop1@gmail.com | Incorrect email address |
      | dani.topov1@gmail.com | Title of the email | Context body of the email | C:\\Users\\Dani\\Desktop\\informaciq za student.docx |                      | Not specified email address |
      |                       | Title of the email | Context body of the email | C:\\Users\\Dani\\Desktop\\informaciq za student.docx | yordantop1@gmail.com | Not specified email address |

