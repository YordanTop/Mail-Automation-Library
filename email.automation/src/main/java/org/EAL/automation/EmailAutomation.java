package org.EAL.automation;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import org.EAL.automation.service.EmailAutomationService;
import org.EAL.automation.service.NotificationService;
import org.EAL.automation.service.ReceivingService;
import org.EAL.interfaces.automation_interface.IAutomationEmail;
import org.EAL.module_configuration.AccountNotificationListenerModule;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.EmailModule;

import java.util.ArrayList;

import jakarta.mail.Session;

public class EmailAutomation implements IAutomationEmail {

    private EmailAutomationService emailService;

    private NotificationService notifyService;

    private ReceivingService receiveService;

    public EmailAutomation(Session session, Store store){
        this.emailService = new EmailAutomationService(session);
        this.receiveService = new ReceivingService(store);
        this.notifyService = new NotificationService();
    }


    @Override
    public void sendMessageToSelectedAccount(EmailModule email, EmailAccountModule account) {
        Message message = this.emailService.fillEmailInfo(email,account);
        this.emailService.sendEmail(message);
    }

    @Override
    public void sendMessageToAllAccounts(EmailModule email, ArrayList<EmailAccountModule> accounts) {
        for(EmailAccountModule account: accounts){
            sendMessageToSelectedAccount(email,account);
        }
    }

    @Override
    public void receiverNotifierSubscription(AccountNotificationListenerModule accountListener) {
        this.notifyService.notificationSubscription(accountListener);
    }

    @Override
    public void receiverNotifierUnsubscription(AccountNotificationListenerModule accountListener) {
        this.notifyService.notificationUnsubscription(accountListener);
    }

    @Override
    public boolean notifyForNewMessage(EmailAccountModule receiver, EmailAccountModule sender) {
        if(this.notifyService.notifyReceiver(receiver,sender)){
            return true;
        }
        return false;
    }

    @Override
    public Message receiveMessage(EmailAccountModule receiver, EmailAccountModule sender) {
        if(this.notifyForNewMessage(receiver, sender)){
            try {
                return this.receiveService.receiveMessage(sender);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
