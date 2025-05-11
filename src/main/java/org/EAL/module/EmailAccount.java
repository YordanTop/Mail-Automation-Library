package org.EAL.module;

import org.EAL.automation_enum.BanStatus;
import org.EAL.module_configuration.EmailAccountModule;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EmailAccount extends EmailAccountModule {

    //Setting the status of the sender.
    public void setStatus(@Nullable BanStatus status) {
        this.status = Objects.requireNonNullElse(status, BanStatus.Unban);
    }



}
