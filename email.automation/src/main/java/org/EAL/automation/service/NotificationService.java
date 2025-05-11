package org.EAL.automation.service;

import org.EAL.automation_enum.NotificationStatus;
import org.EAL.interfaces.service_interfaces.INotificationService;
import org.EAL.module_configuration.AccountNotificationListenerModule;
import org.EAL.module_configuration.EmailAccountModule;

import java.util.ArrayList;

public class NotificationService implements INotificationService {


    private final ArrayList<AccountNotificationListenerModule> listenerList = new ArrayList<>();


    @Override
    public void notificationSubscription(AccountNotificationListenerModule accountListener) {
        accountListener.setNotificationStatus(NotificationStatus.On);

        synchronized (listenerList){
            listenerList.add(accountListener);
        }
    }

    @Override
    public void notificationUnsubscription(AccountNotificationListenerModule accountListener) {
        accountListener.setNotificationStatus(NotificationStatus.Off);

        if(!listenerList.isEmpty())
            synchronized (listenerList){
                listenerList.remove(accountListener);
            }

    }

    @Override
    public boolean notifyReceiver(EmailAccountModule receiver, EmailAccountModule sender) {
        boolean shouldNotify = false;

        if(listenerList.isEmpty())
            return shouldNotify;

        for (AccountNotificationListenerModule listener : listenerList) {

            //checking if the notification is ON.
            if (listener.getNotificationStatus() == NotificationStatus.On) {
                continue;
            }

            //checking if the receiver and the sender are compatible.
            if(listener.getSenderModule().getEmailName().equals(sender.getEmailName()) &&
                    listener.getAccountModule().getEmailName().equals(receiver.getEmailName())) {
                    shouldNotify = true;

                    break;
                }
            }


        return shouldNotify;
    }


}
