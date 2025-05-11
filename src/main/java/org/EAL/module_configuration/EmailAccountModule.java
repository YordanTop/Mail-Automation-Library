package org.EAL.module_configuration;

import org.EAL.automation_enum.BanStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EmailAccountModule {

    @NotNull
    protected String emailName;

    public @NotNull String getEmailName() {
        return emailName;
    }

    public void setEmailAddress(@NotNull String emailName) {
        this.emailName = emailName;
    }
    @Nullable
    protected DomainModule domain;

    public @Nullable DomainModule getDomain() {
        return domain;
    }

    public void setDomain(@Nullable DomainModule domain) {
        this.domain = domain;
    }

    @Nullable
    protected BanStatus status;

    public @Nullable BanStatus getStatus() {
        return status;
    }

    public void setStatus(@Nullable BanStatus status) {
        this.status = status;
    }
}
