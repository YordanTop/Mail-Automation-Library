package org.EAL.handler;

import org.EAL.exception_handler.SystemHandlerLevel;
import org.EAL.module_configuration.EmailModule;

public class EmailSubjectHandler extends SystemHandlerLevel<EmailModule> {

    @Override
    public boolean check(EmailModule emailModule) {
        if(emailModule.getSubject() == null || emailModule.getSubject().isEmpty()){
            return falseExpectation("Empty email subject");
        }

        return true;
    }
}
