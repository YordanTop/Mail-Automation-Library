package org.EAL.automation.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.EAL.interfaces.service_interfaces.IEmailService;
import org.EAL.module_configuration.EmailModule;
import org.EAL.module_configuration.ReceiverAccountModule;
import org.EAL.module_configuration.SenderAccountModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmailAutomationService implements IEmailService {

    private Message emailMessage;

    public EmailAutomationService (Session session){
        this.emailMessage = new MimeMessage(session);
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
    public MimeMessage fillEmailInfo(EmailModule email, SenderAccountModule sender, ReceiverAccountModule receiver){
        Multipart attachmentToEmail = new MimeMultipart();
        try {
            //Embedding sender email address
            this.emailMessage.setFrom(new InternetAddress(sender.getEmailAddress(),
                                        AttachModule.AttachTitle(sender.getNickname())));

            //Embedding receiver email address
            this.emailMessage.setRecipient(jakarta.mail.Message.RecipientType.TO,
                                            new InternetAddress(receiver.getEmailAddress()));

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
            System.out.println("MIME embedding issue: "+ msg);
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
