package org.EAL.runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.EAL.automation.controller.EmailAutomation;
import org.EAL.automation.module.Email;
import org.EAL.automation.module.ReceiverAccount;
import org.EAL.automation.module.SenderAccount;
import org.EAL.automation_enum.BanStatus;
import org.EAL.handler.SystemExpectationHandler;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.EmailModule;
import org.EAL.setup_session.HandlersContext;
import org.EAL.setup_session.SessionContext;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SendEmailTest {

    private EmailAutomation emailAutomation;

    private SenderAccount senderAccount = new SenderAccount();

    private ReceiverAccount receiverAccount = new ReceiverAccount();

    private Email emailBody;

    private String handelResponse;


    @Given("the sender trying to send an email automatically")
    public void sendingMessage() {
        emailAutomation = new EmailAutomation(SessionContext.startSession());

        emailBody = new Email();
    }



    @And("if the receiver ban status is {string}")
    public void checkBanStatus(String receiverStatus){
        switch (receiverStatus) {
            case "Ban" -> receiverAccount.setBanStatus(BanStatus.Ban);
            case "Unban" -> receiverAccount.setBanStatus(BanStatus.Unban);
            default -> {
                return;
            }
        }
    }

    @And("the sender input the receiver address for the email {string}")
    public void setReceiver(String receiverAddress){

        receiverAccount.setEmailAddress(receiverAddress);

    }


    @And("the sender input the subject for the email {string}")
    public void attachSubjectToEmail(String subject) {
        emailBody.setSubject(subject);
    }

    @And("the sender input the context for the email {string}")
    public void attachContextToEmail(String context) {
        emailBody.setContext(context);
    }

    @And("the sender attaches its email address {string}")
    public void attachSenderAddressToEmail(String senderAddress) {
        senderAccount.setEmailAddress(senderAddress);
    }

    @And("the sender attaches the pinned files for the email {string}")
    public void attachPinnedFilesToEmail(String filePath) {
        ArrayList<File> files = new ArrayList<>();

        files.add(new File(filePath));

        emailBody.setPinnedFiles(files);
    }


    @Then("the sender receive the message {string}")
    public void theSenderReceiveSTheMessageAfterSendingTheEmail(String message) {

        ArrayList<EmailAccountModule> accounts = new ArrayList<>();
        accounts.add(senderAccount);
        accounts.add(receiverAccount);

        //check if the email are verified
        for(var account: accounts){
            if(verifyEmailHandler(account).equals(message)) {
                assertEquals(verifyEmailHandler(account), message);
                return;
            }
        }

        //Check if the receiver email is banned

        if(verifyBanStatus(receiverAccount).equals(message)) {
            assertEquals(verifyBanStatus(receiverAccount), message);
            return;
        }


        //adding exception handlers to the email
        SystemExpectationHandler<EmailModule> emailExpectation = new SystemExpectationHandler<>(HandlersContext.EMAIL_HANDLER_LEVEL);
        emailExpectation.setTaskHandlerCompletionMessage("Email message has been successfully sent");

        handelResponse = emailExpectation.handelMessage(emailBody);

        assertEquals(handelResponse, message);
    }

    @And("after that sending the email to specific email")
    public void sendEmailToSomeone(){
        if(handelResponse.equals("Email message has been successfully sent"))
            emailAutomation.sendMessageToSelectedAccount(emailBody,senderAccount,receiverAccount);
    }


    private String verifyEmailHandler(EmailAccountModule email) {
        SystemExpectationHandler<EmailAccountModule> emailExpectation = new SystemExpectationHandler<>(HandlersContext.EMAIL_ADDRESS_HANDLER_LEVEL);
        emailExpectation.setTaskHandlerCompletionMessage("Email address has been verified");

        handelResponse = emailExpectation.handelMessage(email);

        return handelResponse;
    }

    private String verifyBanStatus(ReceiverAccount receiverAccount){
        SystemExpectationHandler<EmailAccountModule> emailExpectation = new SystemExpectationHandler<>(HandlersContext.RECEIVER_BAN_HANDLER_LEVEL);
        emailExpectation.setTaskHandlerCompletionMessage("Email address is not banned");

        handelResponse = emailExpectation.handelMessage(receiverAccount);

        return handelResponse;
    }
}
