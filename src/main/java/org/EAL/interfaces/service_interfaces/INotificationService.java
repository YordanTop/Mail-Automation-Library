package org.EAL.interfaces.service_interfaces;

import org.EAL.module_configuration.AccountNotificationListenerModule;
import org.EAL.module_configuration.EmailAccountModule;

public interface INotificationService {

    public void notificationSubscription(AccountNotificationListenerModule accountListener);

    public void notificationUnsubscription(AccountNotificationListenerModule accountListener);

    public boolean notifyReceiver(EmailAccountModule receiver, EmailAccountModule sender);
}
