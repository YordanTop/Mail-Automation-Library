package org.EAL.automation.service;

import org.EAL.automation_enum.NotificationStatus;
import org.EAL.interfaces.service_interfaces.INotificationService;
import org.EAL.module_configuration.AccountNotificationLinkModule;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationService implements INotificationService {


    private final Map<AccountNotificationLinkModule,NotificationStatus> listenerList = new ConcurrentHashMap<>();


    @Override
    public void notificationSubscription(AccountNotificationLinkModule accountListener) {
        synchronized (listenerList){
            listenerList.put(accountListener,NotificationStatus.On);
        }
    }

    @Override
    public void notificationUnsubscription(AccountNotificationLinkModule accountListener) {

        if(listenerList.isEmpty())
            return;

        synchronized (listenerList){
            listenerList.remove(accountListener);
        }

    }

    @Override
    public void notificationOff(AccountNotificationLinkModule accountListener) {
        synchronized (listenerList){
            listenerList.put(accountListener,NotificationStatus.Off);
        }
    }

    @Override
    public void notificationOn(AccountNotificationLinkModule accountListener) {
        synchronized (listenerList){
            listenerList.put(accountListener,NotificationStatus.On);
        }
    }

    public NotificationStatus getNotificationStatus(AccountNotificationLinkModule accountListener){
        return listenerList.get(accountListener);
    }

    @Override
    public boolean notifyReceiver(AccountNotificationLinkModule accountListener) {

        NotificationStatus notificationStatus = listenerList.get(accountListener);

        return accountListener != null && notificationStatus == NotificationStatus.On;
    }


}
