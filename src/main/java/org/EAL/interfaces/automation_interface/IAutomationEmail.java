package org.EAL.interfaces.automation_interface;

import jakarta.mail.Message;
import org.EAL.module_configuration.AccountNotificationListenerModule;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.EmailModule;

import java.util.ArrayList;

public interface IAutomationEmail {
    /**
     Sending email to somebody.
     */
    void sendMessageToSelectedAccount(EmailModule email, EmailAccountModule account);

    /**
     Sending email to many based on selected accounts.
     */
    void sendMessageToAllAccounts(EmailModule email, ArrayList<EmailAccountModule> accounts);

    /**
     Notify upon receiving an email from a specific sender.
     */
    void receiverNotifierSubscription(AccountNotificationListenerModule accountListener);

    void receiverNotifierUnsubscription(AccountNotificationListenerModule accountListener);

    boolean notifyForNewMessage(EmailAccountModule receiver, EmailAccountModule sender);

    Message receiveMessage(EmailAccountModule receiver, EmailAccountModule sender);
}
