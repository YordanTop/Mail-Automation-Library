package org.EAL.automation.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.EAL.interfaces.service_interfaces.IEmailService;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.EmailModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmailAutomationService implements IEmailService {

    private Session sessionLifeCycle;

    private Message emailMessage;

    public EmailAutomationService (Session session){
        this.sessionLifeCycle = session;
        this.emailMessage = new MimeMessage(this.sessionLifeCycle);
    }

    @Override
    public void sendEmail(Message message){
        try {

            Transport.send(message);
        }catch (MessagingException msg){
            System.out.println("Sending Error: "+msg);
        }
    }

    @Override
    public MimeMessage fillEmailInfo(EmailModule email, EmailAccountModule account){
        Multipart attachmentToEmail = new MimeMultipart();
        try {
            //Embedding sender email address
            this.emailMessage.setFrom(new InternetAddress(email.getSender().getEmailName(),
                                        AttachModule.AttachTitle(account.getEmailName())));

            //Embedding receiver email address
            this.emailMessage.setRecipient(jakarta.mail.Message.RecipientType.TO,
                                            new InternetAddress(account.getEmailName()));

            // Embedding subjects
            this.emailMessage.setSubject(email.getSubject());

            // Embedding text
            attachmentToEmail.addBodyPart(AttachModule.attachContent(email.getContext()));

            // Embedding files
            for(MimeBodyPart filePart: AttachModule.attachFiles(email.getPinnedFiles())) {
                attachmentToEmail.addBodyPart(filePart);
            }

            // attach to email.
            this.emailMessage.setContent(attachmentToEmail);

        } catch (MessagingException msg) {
            System.out.println("Input Error: "+ msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return (MimeMessage)this.emailMessage;
    }

    private static class AttachModule{

        public static String AttachTitle(String name){
            return name;
        }

        public static MimeBodyPart attachContent(String text) throws MessagingException {
            MimeBodyPart emailText = new MimeBodyPart();
            emailText.setText(text);
            return emailText;
        }

        public static ArrayList<MimeBodyPart> attachFiles(ArrayList<File> files) throws MessagingException, IOException {
            ArrayList<MimeBodyPart> emailBodyPart = new ArrayList<MimeBodyPart>();
            if(files != null){
                for(File file: files) {
                    MimeBodyPart emailFile = new MimeBodyPart();
                    emailFile.attachFile(file);

                    emailBodyPart.add(emailFile);
                }
            }

            return emailBodyPart;

        }
    }



}
