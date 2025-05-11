package org.EAL.module_configuration;

import org.EAL.automation_enum.NotificationStatus;
import org.jetbrains.annotations.NotNull;

public abstract class AccountNotificationListenerModule {

    /**
     The subscript account for listening messages.
     */
    @NotNull
    private EmailAccountModule account;

    public @NotNull EmailAccountModule getAccountModule() {
        return account;
    }

    public void setAccountModule(@NotNull EmailAccountModule account) {
        this.account = account;
    }


    /**
     The observed account that's provide emails.
     */
    @NotNull
    private EmailAccountModule sender;

    public @NotNull EmailAccountModule getSenderModule() {
        return sender;
    }

    public void setSenderModule(@NotNull EmailAccountModule sender) {
        this.sender = sender;
    }


    /**
     The notification status for listening.
     */
    private NotificationStatus notificationStatus;

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

}
