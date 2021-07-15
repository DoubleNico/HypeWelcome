package me.doublenico.hypewelcome.commands;

import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.commands.impl.Commands;
import me.doublenico.hypewelcome.commands.impl.TabCompleter;

import java.util.Objects;

public class CommandManager {

    private HypeWelcome plugin;

    public CommandManager(HypeWelcome plugin){
        this.plugin = plugin;
    }


    public void registerCommands(){
        Objects.requireNonNull(plugin.getCommand("hypewelcome")).setExecutor(new Commands(plugin));
        plugin.getCommand("hypewelcome").setTabCompleter(new TabCompleter());
    }


}
