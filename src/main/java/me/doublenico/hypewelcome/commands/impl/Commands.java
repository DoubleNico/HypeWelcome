package me.doublenico.hypewelcome.commands.impl;

import me.doublenico.hypeapi.util.ColorChat;
import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.files.*;
import me.doublenico.hypewelcome.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    private HypeWelcome plugin;
    private JoinFile joinFile;
    private SettingsFile settings;
    private JoinCustomFile joinCustomFile;
    private FirstJoinFile firstJoinFile;
    private QuitFile quitFile;

    public Commands(HypeWelcome plugin){

        this.plugin = plugin;
        this.joinFile = new JoinFile(plugin);
        this.settings = new SettingsFile(plugin);
        this.joinCustomFile = new JoinCustomFile(plugin);
        this.firstJoinFile = new FirstJoinFile(plugin);
        this.quitFile = new QuitFile(plugin);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            if(sender.hasPermission("hypewelcome.help")){
                sender.sendMessage(ColorChat.color("&bHype&eWelcome &fby &b&lDoubleNico"));
                sender.sendMessage(ColorChat.color("&b "));
                sender.sendMessage(ColorChat.color("&bAliases: /hwb, /hw"));
                sender.sendMessage(ColorChat.color("&b "));
                sender.sendMessage(ColorChat.color("&b/hypewelcome &ereload &f- &bReloads the commands.."));
                sender.sendMessage(ColorChat.color("&b/hypewelcome &ehello &4<Player> &f- &bSends a welcome message to a player"));
                sender.sendMessage(ColorChat.color("&b "));
            }
        }else if(args[0].equals("reload")){
            if(sender.hasPermission("hypewelcome.reload")) {
                Bukkit.getPluginManager().disablePlugin(plugin);
                Bukkit.getPluginManager().getPlugin("HypeWelcome").reloadConfig();
                joinFile.reloadConfig();
                settings.reloadConfig();
                joinCustomFile.reloadConfig();
                firstJoinFile.reloadConfig();
                quitFile.reloadConfig();
                Bukkit.getPluginManager().enablePlugin(plugin);
                sender.sendMessage(ColorChat.color("&bConfig has been reloaded!"));
                return true;
            }
        }else if(args[0].equals("hello")){
            if(sender.hasPermission("hypewelcome.welcome")){
                if(args.length > 1){
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if(target != null && sender instanceof Player){
                        Player player = (Player) sender;
                        if(target != player){
                            String message = settings.getConfig().getString("COMMANDS.WELCOME");
                            CC.convertedMessage(target, message.replace("%sender%", player.getDisplayName()));
                        }
                    }
                    if(target != null && sender instanceof ConsoleCommandSender){
                        String message = settings.getConfig().getString("COMMANDS.WELCOME");
                        CC.convertedMessage(target, message.replace("%sender%", "CONSOLE"));
                    }
                }
            }
        }

        return true;
    }
}
