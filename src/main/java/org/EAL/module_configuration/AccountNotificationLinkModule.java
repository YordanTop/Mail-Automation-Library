package org.EAL.module_configuration;

import org.jetbrains.annotations.NotNull;

public abstract class AccountNotificationLinkModule {

    /**
     The subscript account for listening messages.
     */
    @NotNull
    private ReceiverAccountModule account;

    public @NotNull ReceiverAccountModule getAccountModule() {
        return account;
    }

    public void setAccountModule(@NotNull ReceiverAccountModule account) {
        this.account = account;
    }


    /**
     The observed account that's provide emails.
     */
    @NotNull
    private SenderAccountModule sender;

    public @NotNull SenderAccountModule getSenderModule() {
        return sender;
    }

    public void setSenderModule(@NotNull SenderAccountModule sender) {
        this.sender = sender;
    }

}
