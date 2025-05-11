package org.EAL.interfaces.listener_interface;

import jakarta.mail.Message;
import jakarta.mail.Store;
import org.EAL.module_configuration.EmailAccountModule;

public interface IEmailMessageNotificationListener {

    public void setAccount(EmailAccountModule account);

    public EmailAccountModule getAccount();

    public Message notificationFromUpdate(Store mailBoxConnection,EmailAccountModule receiver , EmailAccountModule sender);
}
