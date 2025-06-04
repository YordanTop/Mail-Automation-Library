package org.EAL.runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.EAL.automation.controller.EmailAutomation;
import org.EAL.automation.module.AccountNotificationLink;
import org.EAL.automation.module.ReceiverAccount;
import org.EAL.automation.module.SenderAccount;
import org.EAL.automation_enum.NotificationStatus;
import org.EAL.setup_session.SessionContext;

import static org.junit.Assert.assertEquals;

public class NotificationTest {

    private ReceiverAccount receiverAccount;

    private SenderAccount senderAccount;

    private final EmailAutomation emailAutomationService = new EmailAutomation(SessionContext.startSession());

    private AccountNotificationLink accountNotificationLink;

    @And("a receiver and a sender are connected to each other")
    public void aReceiverAndASenderAreConnectedToEachOther() {
        accountNotificationLink = new AccountNotificationLink();
        accountNotificationLink.setAccountModule(receiverAccount);
        accountNotificationLink.setSenderModule(senderAccount);

    }

    @Given("a receiver has email account named {string}")
    public void setReceiverAccount(String email) {
        receiverAccount = new ReceiverAccount();

        receiverAccount.setEmailAddress(email);
    }


    @And("the receiver select the sender {string}")
    public void setSenderAccount(String email) {
        senderAccount = new SenderAccount();

        senderAccount.setEmailAddress(email);
    }

    @And("the receiver {string} to the sender")
    public void theReceiverIsToTheSender(String subscriptionStatus) {
        switch (subscriptionStatus){
            case "Subscribe" -> emailAutomationService.receiverNotifierSubscription(accountNotificationLink);
            case "Unsubscribe" -> emailAutomationService.receiverNotifierCancelSubscription(accountNotificationLink);
            default -> {
                return;
            }
        }
    }


    @And("receiver notification is turn {string}")
    public void changeNotification(String turnNotification) {
        switch (turnNotification){
            case "Off" -> emailAutomationService.notificationTurnedOff(accountNotificationLink);
            case "On" -> emailAutomationService.notificationTurnedOn(accountNotificationLink);
            default -> {
                return;
            }


        }
    }


    @Then("receiver notification should be turn {string}")
    public void receiverNotificationShouldBeTurn(String turnNotification) {
        NotificationStatus receiverNotificationStatus = emailAutomationService.getNotificationStatus(accountNotificationLink);

        if(turnNotification.equals("On"))
            assertEquals(receiverNotificationStatus, NotificationStatus.On);
        else
            assertEquals(receiverNotificationStatus, NotificationStatus.Off);

    }

}
