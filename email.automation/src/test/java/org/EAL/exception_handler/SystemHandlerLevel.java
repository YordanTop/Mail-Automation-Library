package org.EAL.exception_handler;

public abstract class SystemHandlerLevel<T> {

    private SystemHandlerLevel<T> nextLevel;

    public SystemHandlerLevel<T> getNextLevel(){
        return nextLevel;
    }


    /***
     * Explanation message;
     */
    protected String exceptionMessage ;

    protected void setExceptionMessage(String message){
        exceptionMessage = message;
    }

    public String getExceptionMessage() {
        return  exceptionMessage;
    }

    public boolean falseExpectation(String message){
        setExceptionMessage(message);
        return false;
    }
    /***
     * Linking handlers for meeting certain expectations.
     * @param first first responsibility that will handel.
     * @param chainOfResponsibilities next group responsibilities that will be chained.
     * @return chained email handlers.
     */
    public SystemHandlerLevel<T> linkResponsibilities(SystemHandlerLevel<T> first, SystemHandlerLevel<T>... chainOfResponsibilities){

        SystemHandlerLevel<T> head = first;

        for(SystemHandlerLevel<T> nextResponsibility: chainOfResponsibilities){
            head.nextLevel = nextResponsibility;
            head = nextResponsibility;
        }

        return first;
    }

    public SystemHandlerLevel<T> linkResponsibility(SystemHandlerLevel<T> nextResponsibility){
        this.nextLevel = nextResponsibility;

        return this.nextLevel;
    }


    /***
     * Handles a certain conditions if their not met.
     * @param template non-specific parameter
     * @return status of the checked condition.
     */
    public boolean check(T template){
        return true;
    }

}
