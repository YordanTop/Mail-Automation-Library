package org.EAL.interfaces.service_interfaces;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.EmailModule;

public interface IEmailService {

    public MimeMessage fillEmailInfo(EmailModule email, EmailAccountModule account);
    public void sendEmail(Message message);
}
