package org.EAL.interfaces.automation_interface;


import org.EAL.module_configuration.DomainModule;
import org.EAL.module_configuration.EmailAccountModule;

public interface IAutomationBan {

    /**
     Add the person in the blacklist.
     */
    void ban(EmailAccountModule emailAccount);

    /**
     Add the domain in the blacklist.
     */
    void ban(DomainModule domain);


    /**
     Remove the person from the blacklist.
     */
    void unban(EmailAccountModule emailAccount);

    /**
     Remove the domain from the blacklist.
     */
    void unban(DomainModule domain);
}
