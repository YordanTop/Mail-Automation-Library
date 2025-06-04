package org.EAL.handler;

import org.EAL.exception_handler.SystemHandlerLevel;

public class SystemExpectationHandler<T> {

    private String TaskHandlerCompletionMessage;

    public void setTaskHandlerCompletionMessage(String message){
        TaskHandlerCompletionMessage = message;
    }



    private SystemHandlerLevel<T> handlerChain;

    public SystemExpectationHandler(SystemHandlerLevel<T> handlerChain){
        this.handlerChain = handlerChain;
    }



    public String handelMessage(T module){

        SystemHandlerLevel<T> newHandler = handlerChain;

        while (newHandler != null){

            if(!newHandler.check(module)){

                return newHandler.getExceptionMessage();

            }
            newHandler = newHandler.getNextLevel();
        }

        if(TaskHandlerCompletionMessage.isEmpty())
            return null;

        return TaskHandlerCompletionMessage;
    }

}
