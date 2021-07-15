package me.doublenico.hypewelcome.actions;

public class EventAction {

    private Actions action;
    private String message;


    public EventAction(Actions action, String message){
        this.action = action;
        this.message = message;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String runnable) {
        this.message = runnable;
    }

}
