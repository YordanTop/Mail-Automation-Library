package org.EAL.interfaces.service_interfaces;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.SenderAccountModule;

public interface IReceivingService {

    Message receiveMessage(SenderAccountModule sender) throws MessagingException;

}
