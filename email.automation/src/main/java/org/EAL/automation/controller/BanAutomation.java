package org.EAL.automation.controller;

import org.EAL.automation_enum.BanStatus;
import org.EAL.interfaces.automation_interfaces.IAutomationBan;
import org.EAL.module_configuration.ReceiverAccountModule;

public class BanAutomation implements IAutomationBan {

    @Override
    public void ban(ReceiverAccountModule receiverAccount) {
        receiverAccount.setBanStatus(BanStatus.Ban);
    }

    @Override
    public void unban(ReceiverAccountModule receiverAccount) {
        receiverAccount.setBanStatus(BanStatus.Unban);
    }
}
