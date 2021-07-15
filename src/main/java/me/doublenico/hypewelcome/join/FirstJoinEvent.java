package me.doublenico.hypewelcome.join;

import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.files.FirstJoinFile;
import me.doublenico.hypewelcome.files.SettingsFile;
import me.doublenico.hypewelcome.utils.CC;
import me.doublenico.hypewelcome.utils.Sounds;
import me.doublenico.hypewelcome.utils.TextComponents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinEvent implements Listener {

    private HypeWelcome plugin;
    private FirstJoinFile config;
    private SettingsFile settings;

    public FirstJoinEvent(HypeWelcome plugin){
        this.plugin = plugin;
        this.config = new FirstJoinFile(plugin);
        this.settings = new SettingsFile(plugin);
    }


    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        boolean isEnabled = config.getConfig().getBoolean("FIRST_JOIN_TITLE");
        if(player.hasPlayedBefore())
            return;
        if (!isEnabled)
            return;
        event.setJoinMessage(null);
        TextComponents textComponents = new TextComponents(plugin);
        boolean isTitleEnabled = settings.getConfig().getBoolean("FIRST_JOIN_TITLE");
        boolean isTitleBroadcastEnabled = settings.getConfig().getBoolean("FIRST_JOIN_BROADCAST_TITLE");
        boolean isMessageBroadcastEnabled = settings.getConfig().getBoolean("FIRST_JOIN_BROADCAST_MESSAGE");
        boolean isMessageEnabled = settings.getConfig().getBoolean("FIRST_JOIN_MESSAGE");
        boolean isActionbarEnabled = settings.getConfig().getBoolean("FIRST_JOIN_ACTIONBAR");
        boolean isActionbarBroadcastEnabled = settings.getConfig().getBoolean("FIRST_JOIN_BROADCAST_ACTIONBAR");
        boolean isBossbarEnabled = settings.getConfig().getBoolean("FIRST_JOIN_BOSSBAR");
        boolean isBossbarBroadcastEnabled = settings.getConfig().getBoolean("FIRST_JOIN_BROADCAST_BOSSBAR");
        boolean isSoundEnabled = settings.getConfig().getBoolean("FIRST_JOIN_SOUND");
        boolean isSoundBroadcastEnabled = settings.getConfig().getBoolean("JFIRST_OIN_SOUND_BROADCAST");
        if(isTitleEnabled){
            String title = config.getConfig().getString("FIRST_JOIN_TITLE.TITLE");
            String subtitle = config.getConfig().getString("FIRST_JOIN_TITLE.SUBTITLE");
            int fadein = config.getConfig().getInt("FIRST_JOIN_TITLE.FADEIN");
            int stay = config.getConfig().getInt("FIRST_JOIN_TITLE.STAY");
            int fadeout = config.getConfig().getInt("FIRST_JOIN_TITLE.FADEOUT");
            textComponents.sendTitle(player, title, subtitle, fadein, stay, fadeout);
        }
        if(isTitleBroadcastEnabled){
            String title = config.getConfig().getString("FIRST_JOIN_BROADCAST_TITLE.TITLE");
            String subtitle = config.getConfig().getString("FIRST_JOIN_BROADCAST_TITLE.SUBTITLE");
            int fadein = config.getConfig().getInt("FIRST_JOIN_BROADCAST_TITLE.FADEIN");
            int stay = config.getConfig().getInt("FIRST_JOIN_BROADCAST_TITLE.STAY");
            int fadeout = config.getConfig().getInt("FIRST_JOIN_BROADCAST_TITLE.FADEOUT");
            textComponents.sendBroadcastTitle(player, title, subtitle, fadein, stay, fadeout);
        }
        if(isMessageEnabled){
            for(String s : config.getConfig().getStringList("FIRST_JOIN_MESSAGE")) {
                if(s.contains("text")){
                    textComponents.sendJSON(player, s);
                } else {
                    CC.convertedMessage(player, s);
                }
            }
        }
        if(isMessageBroadcastEnabled){
            for(String s : config.getConfig().getStringList("FIRST_JOIN_BROADCAST_MESSAGE")) {
                if(s.contains("text")){
                    textComponents.sendBroadcastJSON(player, s);
                } else {
                    textComponents.sendBroadcastMessage(player, s);
                }
            }
        }
        if(isActionbarEnabled){
            String message = config.getConfig().getString("FIRST_JOIN_ACTIONBAR");
            textComponents.sendActionBar(player, message);
        }
        if(isActionbarBroadcastEnabled){
            String message = config.getConfig().getString("FIRST_JOIN_BROADCAST_ACTIONBAR");
            textComponents.sendActionBar(player, message);
        }
        if(isBossbarEnabled){
            String message = config.getConfig().getString("FIRST_JOIN_BOSSBAR.MESSAGE");
            String color = config.getConfig().getString("FIRST_JOIN_BOSSBAR.COLOR");
            int time = config.getConfig().getInt("FIRST_JOIN_BOSSBAR.TIME");
            textComponents.sendBossBar(player, message, color, time);
        }
        if(isBossbarBroadcastEnabled){
            String message = config.getConfig().getString("FIRST_JOIN_BROADCAST_BOSSBAR.MESSAGE");
            String color = config.getConfig().getString("FIRST_JOIN_BROADCAST_BOSSBAR.COLOR");
            int time = config.getConfig().getInt("FIRST_JOIN_BROADCAST_BOSSBAR.TIME");
            textComponents.sendBroadcastBossBar(player, message, color, time);
        }
        if(isSoundEnabled){
            String sound = config.getConfig().getString("FIRST_JOIN_SOUND.SOUND");
            int volume = config.getConfig().getInt("FIRST_JOIN_SOUND.VOLUME");
            int pitch = config.getConfig().getInt("FIRST_JOIN_SOUND.PITCH");
            Sounds.playSound(player, sound, volume, pitch);
        }
        if(isSoundBroadcastEnabled){
            String sound = config.getConfig().getString("FIRST_JOIN_SOUND_BROADCAST.SOUND");
            int volume = config.getConfig().getInt("FIRST_JOIN_SOUND_BROADCAST.VOLUME");
            int pitch = config.getConfig().getInt("FIRST_JOIN_SOUND_BROADCAST.PITCH");
            Sounds.playSound(player, sound, volume, pitch);
        }

    }

}
