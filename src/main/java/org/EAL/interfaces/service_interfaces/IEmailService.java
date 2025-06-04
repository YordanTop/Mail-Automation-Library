package org.EAL.interfaces.service_interfaces;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.EAL.module_configuration.EmailModule;
import org.EAL.module_configuration.ReceiverAccountModule;
import org.EAL.module_configuration.SenderAccountModule;

public interface IEmailService {

    MimeMessage fillEmailInfo(EmailModule email, SenderAccountModule sender, ReceiverAccountModule account);

    void sendEmail(Message message);
}
