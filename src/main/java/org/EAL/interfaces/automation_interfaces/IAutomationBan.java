package org.EAL.interfaces.automation_interfaces;


import org.EAL.module_configuration.ReceiverAccountModule;

public interface IAutomationBan {

    /**
     Add the person in the blacklist.
     */
    void ban(ReceiverAccountModule receiverAccount);

    /**
     Remove the person from the blacklist.
     */
    void unban(ReceiverAccountModule receiverAccount);

}
