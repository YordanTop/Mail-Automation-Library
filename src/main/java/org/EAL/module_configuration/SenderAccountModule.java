package org.EAL.module_configuration;

import org.jetbrains.annotations.Nullable;

public abstract class SenderAccountModule extends EmailAccountModule{

    @Nullable
    private String nickname;

    public @Nullable String getNickname(){
        return nickname;
    }

    public void setNickname(@Nullable String nickname){
        if(nickname.isEmpty())
            this.nickname = "Unknown";
        else
            this.nickname = nickname;
    }

}
