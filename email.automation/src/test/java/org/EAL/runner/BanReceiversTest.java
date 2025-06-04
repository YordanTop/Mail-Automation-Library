package org.EAL.runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.EAL.handler.SystemExpectationHandler;
import org.EAL.automation.module.ReceiverAccount;
import org.EAL.automation_enum.BanStatus;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.setup_session.HandlersContext;

import static org.junit.Assert.assertEquals;

public class BanReceiversTest {

    private ReceiverAccount receiverAccount = new ReceiverAccount();

    private String handelResponse;

    @Given("the system selects the email account {string}")
    public void setReceiverAccount(String email) {
        receiverAccount.setEmailAddress(email);
    }

    @When("an email account with the status {string}")
    public void checkBanStatus(String receiverStatus) {

        switch (receiverStatus) {
            case "Ban" -> receiverAccount.setBanStatus(BanStatus.Ban);
            case "Unban" -> receiverAccount.setBanStatus(BanStatus.Unban);
            default -> {
                return;
            }
        }
    }

    @Then("an message: {string} is provided to the system for verification")
    public void checkMessage(String message) {
        SystemExpectationHandler<EmailAccountModule> receiverHandler = new SystemExpectationHandler<>(HandlersContext.EMAIL_ADDRESS_HANDLER_LEVEL);
        receiverHandler.setTaskHandlerCompletionMessage("Email address has been verified");

        handelResponse = receiverHandler.handelMessage(receiverAccount);

        assertEquals(handelResponse, message);
    }

    @And("if the system verified the email bans the email account")
    public void banReceiver() {
        if(handelResponse.equals("Email address has been verified"))
            receiverAccount.setBanStatus(BanStatus.Ban);
    }

    @And("if the system verified the email unbans the email account")
    public void unbanReceiver() {
        if(handelResponse.equals("Email address has been verified"))
            receiverAccount.setBanStatus(BanStatus.Unban);
    }
}
