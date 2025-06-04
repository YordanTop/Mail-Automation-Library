package org.EAL.handler;

import org.EAL.automation_enum.BanStatus;
import org.EAL.exception_handler.SystemHandlerLevel;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.ReceiverAccountModule;

public class AddressBanHandler extends SystemHandlerLevel<EmailAccountModule> {


    @Override
    public boolean check(EmailAccountModule accountModule) {
        if(accountModule instanceof ReceiverAccountModule receiverAccountModule) {
            if (receiverAccountModule.getBanStatus() == BanStatus.Ban) {
                return falseExpectation("This address is banned");
            }
        }
        return true;
    }
}
