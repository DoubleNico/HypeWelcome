package me.doublenico.hypewelcome.join;

import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.actions.Actions;
import me.doublenico.hypewelcome.actions.EventAction;
import me.doublenico.hypewelcome.actions.EventActionTask;
import me.doublenico.hypewelcome.files.JoinCustomFile;
import me.doublenico.hypewelcome.files.SettingsFile;
import me.doublenico.hypewelcome.utils.CC;
import me.doublenico.hypewelcome.utils.CustomConfiguration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JoinCustom implements Listener {

    private HypeWelcome plugin;
    private final SettingsFile settings;
    private final JoinCustomFile config;

    public JoinCustom(HypeWelcome plugin){
        this.plugin = plugin;
        this.settings = new SettingsFile(plugin);
        this.config = new JoinCustomFile(plugin);
    }


    @EventHandler
    public void onJoinCustom(PlayerJoinEvent event){
        Player player = event.getPlayer();
        boolean isEnabled = settings.getConfig().getBoolean("custom_join_file");
        if (!isEnabled)
            return;
        if(!event.getPlayer().hasPlayedBefore())
            return;
        event.setJoinMessage(null);
        CustomConfiguration.getCustomConfig(config.getConfig(), player, "JOINS", plugin);
    }


}
