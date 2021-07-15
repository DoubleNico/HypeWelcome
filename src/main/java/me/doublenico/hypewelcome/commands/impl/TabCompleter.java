package me.doublenico.hypewelcome.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();
            if(sender.hasPermission("hypewelcome.reload")){
                arguments.add("reload");
            }
            if(sender.hasPermission("hypewelcome.welcome")){
                arguments.add("hello");
            }
            return arguments;
        }else if (args.length == 2){
            List<String> arguments = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            if(sender.hasPermission("hypewelcome.welcome") && args[0].equals("hello")){
                for (int i = 0; i < players.length; i++){
                    arguments.add(players[i].getName());
                }
            }
            return arguments;
        }

        return null;
    }

}
