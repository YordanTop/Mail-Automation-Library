package org.EAL.interfaces.service_interfaces;

import org.EAL.module_configuration.AccountNotificationLinkModule;

public interface INotificationService {

    void notificationSubscription(AccountNotificationLinkModule accountListener);

    void notificationUnsubscription(AccountNotificationLinkModule accountListener);

    void notificationOff(AccountNotificationLinkModule accountListener);

    void notificationOn(AccountNotificationLinkModule accountListener);

    boolean notifyReceiver(AccountNotificationLinkModule accountListener);
}
