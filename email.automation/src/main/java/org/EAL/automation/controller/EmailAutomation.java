package org.EAL.automation.controller;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import org.EAL.automation.service.EmailAutomationService;
import org.EAL.automation.service.NotificationService;
import org.EAL.automation.service.ReceivingService;
import org.EAL.automation_enum.NotificationStatus;
import org.EAL.interfaces.automation_interfaces.IAutomationEmail;
import org.EAL.module_configuration.*;

import java.util.ArrayList;

import jakarta.mail.Session;

public class EmailAutomation implements IAutomationEmail {

    private EmailAutomationService emailService;

    private NotificationService notifyService;

    private ReceivingService receiveService;

    public EmailAutomation(Session session){
        this.emailService = new EmailAutomationService(session);
        this.notifyService = new NotificationService();
    }

    public EmailAutomation(Session session, Store store){
        this.emailService = new EmailAutomationService(session);
        this.receiveService = new ReceivingService(store);
        this.notifyService = new NotificationService();
    }


    @Override
    public void sendMessageToSelectedAccount(EmailModule email, SenderAccountModule sender, ReceiverAccountModule receiver) {
        Message message = this.emailService.fillEmailInfo(email,sender,receiver);
        this.emailService.sendEmail(message);
    }

    @Override
    public void sendMessageToAllAccounts(EmailModule email, SenderAccountModule sender, ArrayList<ReceiverAccountModule> receivers) {
        for(ReceiverAccountModule receiver: receivers){
            sendMessageToSelectedAccount(email,sender,receiver);
        }
    }

    @Override
    public void receiverNotifierSubscription(AccountNotificationLinkModule accountListener) {
        this.notifyService.notificationSubscription(accountListener);
    }


    @Override
    public void receiverNotifierCancelSubscription(AccountNotificationLinkModule accountListener) {
        this.notifyService.notificationUnsubscription(accountListener);
    }

    @Override
    public void notificationTurnedOn(AccountNotificationLinkModule accountListener) {
        this.notifyService.notificationOn(accountListener);
    }

    @Override
    public void notificationTurnedOff(AccountNotificationLinkModule accountListener) {
        this.notifyService.notificationOff(accountListener);
    }

    @Override
    public boolean notifyForNewMessage(AccountNotificationLinkModule accountListener) {
        return this.notifyService.notifyReceiver(accountListener);
    }

    public NotificationStatus getNotificationStatus(AccountNotificationLinkModule accountListener){
        return this.notifyService.getNotificationStatus(accountListener);
    }

    @Override
    public Message receiveMessage(AccountNotificationLinkModule accountListener) {
        if(this.notifyForNewMessage(accountListener)){
            try {
                return this.receiveService.receiveMessage(accountListener.getSenderModule());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
