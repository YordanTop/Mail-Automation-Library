package org.EAL.handler;

import org.EAL.exception_handler.SystemHandlerLevel;
import org.EAL.module_configuration.EmailModule;

public class EmailContextHandler extends SystemHandlerLevel<EmailModule> {

    @Override
    public boolean check(EmailModule emailModule) {
        if(emailModule.getContext() == null || emailModule.getContext().isEmpty()){
            return falseExpectation("Empty context body");
        }

        return true;
    }

}
