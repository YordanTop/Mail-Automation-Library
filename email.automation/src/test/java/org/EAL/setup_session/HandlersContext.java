package org.EAL.setup_session;

import org.EAL.handler.AddressBanHandler;
import org.EAL.handler.EmailAddressesHandler;
import org.EAL.handler.EmailContextHandler;
import org.EAL.handler.EmailSubjectHandler;
import org.EAL.exception_handler.SystemHandlerLevel;
import org.EAL.module_configuration.EmailAccountModule;
import org.EAL.module_configuration.EmailModule;

public class HandlersContext {

    public static final SystemHandlerLevel<EmailModule> EMAIL_HANDLER_LEVEL = new EmailSubjectHandler()
                                                                            .linkResponsibilities(new EmailSubjectHandler(),
                                                                                                new EmailContextHandler());

    public static SystemHandlerLevel<EmailAccountModule> RECEIVER_BAN_HANDLER_LEVEL = new AddressBanHandler();

    public static SystemHandlerLevel<EmailAccountModule> EMAIL_ADDRESS_HANDLER_LEVEL = new EmailAddressesHandler();

}
