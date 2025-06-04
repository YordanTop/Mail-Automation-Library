package org.EAL.handler;

import org.EAL.exception_handler.SystemHandlerLevel;
import org.EAL.module_configuration.EmailAccountModule;

public class EmailAddressesHandler extends SystemHandlerLevel<EmailAccountModule> {

    @Override
    public boolean check(EmailAccountModule accountModule) {
        if(accountModule.getEmailAddress() == null || accountModule.getEmailAddress().isEmpty()){
            return falseExpectation("Not specified email address");
        }

        if(!accountModule.getEmailAddress().contains("@")){
            return falseExpectation("Incorrect email address");
        }

        return true;
    }



}
