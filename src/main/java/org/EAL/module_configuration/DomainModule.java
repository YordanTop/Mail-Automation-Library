package org.EAL.module_configuration;

import org.jetbrains.annotations.NotNull;

public abstract class DomainModule {

    @NotNull
    protected String domainName;

    public @NotNull String getDomainName() {
        return domainName;
    }

    public void setDomainName(@NotNull String domainName) {
        this.domainName = domainName;
    }
}
