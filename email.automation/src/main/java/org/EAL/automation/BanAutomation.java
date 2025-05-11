package org.EAL.automation;

import org.EAL.interfaces.automation_interface.IAutomationBan;
import org.EAL.module_configuration.DomainModule;
import org.EAL.module_configuration.EmailAccountModule;

public class BanAutomation implements IAutomationBan {
    @Override
    public void ban(EmailAccountModule emailAccount) {

    }

    @Override
    public void ban(DomainModule domain) {

    }

    @Override
    public void unban(EmailAccountModule emailAccount) {

    }

    @Override
    public void unban(DomainModule domain) {

    }
}
