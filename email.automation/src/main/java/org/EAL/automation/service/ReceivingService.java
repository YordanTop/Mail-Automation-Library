package org.EAL.automation.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.FlagTerm;
import jakarta.mail.search.FromTerm;
import jakarta.mail.search.SearchTerm;
import org.EAL.interfaces.service_interfaces.IReceivingService;
import org.EAL.module_configuration.EmailAccountModule;


public class ReceivingService implements IReceivingService {

   private Store mailBoxConnection;

   private final boolean MESSAGE_SEEN = false;


   public ReceivingService(Store mailBoxConnection){
       this.mailBoxConnection = mailBoxConnection;
   }

    @Override
    public Message receiveMessage(EmailAccountModule sender) throws MessagingException {


        Folder inbox = mailBoxConnection.getFolder("INBOX");

        inbox.open(Folder.READ_ONLY);

        SearchTerm fromTerm = new FromTerm(new InternetAddress(sender.getEmailName()));
        SearchTerm unseenTerm = new FlagTerm(new Flags(Flags.Flag.SEEN),MESSAGE_SEEN);
        SearchTerm combine = new AndTerm(fromTerm,unseenTerm);

        Message[] messages = inbox.search(combine);

        Message latestMessage = searchLatestMessage(messages);

        return latestMessage;
    }

    private Message searchLatestMessage(Message[] messages) throws MessagingException {
       if(messages == null)
           return null;

       Message youngestMessage = messages[0];
            for(int i = 0; i < messages.length-1; i++){
                if(messages[i].getSentDate().after(youngestMessage.getSentDate())){
                    youngestMessage = messages[i];
                }
            }
            return youngestMessage;

    }
}
