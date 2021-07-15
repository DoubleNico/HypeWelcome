package me.doublenico.hypewelcome.actions;

public enum Actions {

    CONSOLE("[console]", "Sends a command from console"),
    PLAYER("[player]", "Sends a command for the executor"),
    MESSAGE("[message]", "Sends a message to the chat"),
    CHAT("[chat]", "Sends a message to the chat as the player"),
    CHAT_BROADCAST("[broadcast]", "Sends a message to all players"),
    JSON("[json]", "Sends a json message to the chat"),
    JSON_BROADCAST("[json_broadcast]", "Sends a json message to all players"),
    PLAY_SOUND("[sound]", "Play a sound for the executor"),
    PLAY_SOUND_BROADCAST("[sound_broadcast]", "Play a sound for every player"),
    ACTIONBAR("[actionbar]", "Sends and actionbar to the executor"),
    ACTIONBAR_BROADCAST("[actionbar_broadcast]", "Sends an actionbar to all players"),
    TITLE("[title]", "Sends a title to the executor"),
    TITLE_BROADCAST("[title_broadcast]", "Sends a title to all players"),
    BOSSBAR("[bossbar]", "Sends a bossbar to the executor"),
    BOSSBAR_BROADCAST("[bossbar_broadcast]", "Sends a bossbar to all players");


    public String command;
    public String description;

    Actions(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand(){
        return this.command;
    }

    public String getDescription(){
        return this.description;
    }

    public static Actions checkCommand(String command){
        for(Actions action : values()){
            if(command.startsWith(action.getCommand())){
                return action;
            }
        }
        return null;
    }

}
