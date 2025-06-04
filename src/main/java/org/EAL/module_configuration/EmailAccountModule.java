package org.EAL.module_configuration;

import org.jetbrains.annotations.NotNull;

public abstract class EmailAccountModule {

    @NotNull
    protected String emailAddress;

    public @NotNull String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NotNull String emailName) {
        this.emailAddress = emailName;
    }

}
