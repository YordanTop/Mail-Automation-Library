package org.EAL.interfaces.automation_interfaces;

import jakarta.mail.Message;
import org.EAL.module_configuration.*;

import java.util.ArrayList;

public interface IAutomationEmail {
    /**
     Sending email to somebody.
     */
    void sendMessageToSelectedAccount(EmailModule email, SenderAccountModule sender, ReceiverAccountModule receiver);

    /**
     Sending email to many based on selected accounts.
     */
    void sendMessageToAllAccounts(EmailModule email, SenderAccountModule sender, ArrayList<ReceiverAccountModule> receiver);

    /**
     Notify upon receiving an email from a specific sender.
     */
    void receiverNotifierSubscription(AccountNotificationLinkModule accountListener);

    void receiverNotifierCancelSubscription(AccountNotificationLinkModule accountListener);

    void notificationTurnedOn(AccountNotificationLinkModule accountListener);

    void notificationTurnedOff(AccountNotificationLinkModule accountListener);

    boolean notifyForNewMessage(AccountNotificationLinkModule accountListener);

    Message receiveMessage(AccountNotificationLinkModule accountListener);
}
