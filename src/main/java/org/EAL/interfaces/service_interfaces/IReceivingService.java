package org.EAL.interfaces.service_interfaces;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.EAL.module_configuration.EmailAccountModule;

public interface IReceivingService {

    public Message receiveMessage(EmailAccountModule sender) throws MessagingException;

}
