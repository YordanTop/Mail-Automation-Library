package org.EAL.module_configuration;

import org.EAL.automation_enum.BanStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class ReceiverAccountModule extends EmailAccountModule{

    @Nullable
    protected BanStatus status;

    public @Nullable BanStatus getBanStatus() {
        return status;
    }

    public void setBanStatus(@Nullable BanStatus status) {
        this.status = Objects.requireNonNullElse(status, BanStatus.Unban);
    }

}
