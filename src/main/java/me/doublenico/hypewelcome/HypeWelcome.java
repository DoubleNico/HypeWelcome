package me.doublenico.hypewelcome;

import me.doublenico.hypewelcome.commands.CommandManager;
import me.doublenico.hypewelcome.files.*;
import me.doublenico.hypewelcome.join.FirstJoinEvent;
import me.doublenico.hypewelcome.join.JoinCustom;
import me.doublenico.hypewelcome.join.JoinEvent;
import me.doublenico.hypewelcome.quit.QuitEvent;
import me.doublenico.hypewelcome.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class HypeWelcome extends JavaPlugin {

    @Override
    public void onEnable() {
        registerConfigs();
        registerEvents();
        new CommandManager(this).registerCommands();
        consoleOnEnable();
    }

    @Override
    public void onDisable() {
        consoleOnDisable();
    }


    private void consoleOnEnable(){

        CC.console("&b{------------------&e&lHypeWelcome&b------------------}");
        CC.console("&b");
        CC.console("&bPlugin created by &e&lDoubleNico");
        CC.console("&bVersion: &e&l" + this.getDescription().getVersion());
        CC.console("&bYou are using&e " + Bukkit.getName() + ", on " + Bukkit.getVersion());
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            CC.console("&bYou have PlaceholderAPI!");
            CC.console("&bUsing... PlaceholderAPI");
        } else {
            CC.console("&bYou don't have PlaceholderAPI!");
            CC.console("&bUsing... Plugin Placeholders");
        }
        CC.console("&b");
        CC.console("&b{------------------&e&lHypeWelcome&b------------------}");
    }

    private void consoleOnDisable(){
        CC.console("&b{------------------&e&lHypeWelcome&b------------------}");
        CC.console("&b");
        CC.console("&b&lHypeWelcome " + this.getDescription().getVersion() + " &b&lis now disabled");
        CC.console("&b");
        CC.console("&b{------------------&e&lHypeWelcome&b------------------}");
    }

    private void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinCustom(this), this);
        this.getServer().getPluginManager().registerEvents(new FirstJoinEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new QuitEvent(this), this);
    }

    private void registerConfigs(){
        new JoinFile(this);
        new JoinCustomFile(this);
        new SettingsFile(this);
        new QuitFile(this);
        new FirstJoinEvent(this);
    }

}
