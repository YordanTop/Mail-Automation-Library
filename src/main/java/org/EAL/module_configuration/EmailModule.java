package org.EAL.module_configuration;

import org.EAL.module.EmailAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;

public abstract class EmailModule {

    /**
     The Subject of the email entity.
     */
    @NotNull
    protected String subject;

    public @NotNull String getSubject() {
        return subject;
    }

    public void setSubject(@NotNull String subject) {
        this.subject = subject;
    }


    /**
     The text of the email entity.
     */
    @NotNull
    protected String context;

    public @NotNull String getContext() {
        return context;
    }

    public void setContext(@NotNull String context) {
        this.context = context;
    }


    /**
     The files that are selected for sending.
     */
    @Nullable
    protected ArrayList<File> pinnedFiles;

    public @Nullable ArrayList<File> getPinnedFiles() {
        return pinnedFiles;
    }

    public void setPinnedFiles(@Nullable ArrayList<File> pinnedFiles) {
        this.pinnedFiles = pinnedFiles;
    }


    /**
     Email account which will send the email.
     */
    @NotNull
    protected EmailAccount sender;

    public @NotNull EmailAccount getSender() {
        return sender;
    }

    public void setSender(@NotNull EmailAccount sender) {
        this.sender = sender;
    }

}
