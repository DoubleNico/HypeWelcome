package me.doublenico.hypewelcome.quit;

import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.files.QuitFile;
import me.doublenico.hypewelcome.files.SettingsFile;
import me.doublenico.hypewelcome.utils.CC;
import me.doublenico.hypewelcome.utils.Sounds;
import me.doublenico.hypewelcome.utils.TextComponents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private final HypeWelcome plugin;
    private final QuitFile config;
    private final SettingsFile settings;

    public QuitEvent(HypeWelcome plugin){
        this.plugin = plugin;
        this.config = new QuitFile(plugin);
        this.settings = new SettingsFile(plugin);
    }



    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        boolean isEnabled = settings.getConfig().getBoolean("custom_quit_file");
        if (isEnabled)
            return;
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        TextComponents textComponents = new TextComponents(plugin);
        boolean isTitleBroadcastEnabled = settings.getConfig().getBoolean("QUIT_BROADCAST_TITLE");
        boolean isMessageBroadcastEnabled = settings.getConfig().getBoolean("QUIT_BROADCAST_MESSAGE");
        boolean isActionbarBroadcastEnabled = settings.getConfig().getBoolean("QUIT_BROADCAST_ACTIONBAR");
        boolean isBossbarBroadcastEnabled = settings.getConfig().getBoolean("QUIT_BROADCAST_BOSSBAR");
        boolean isSoundBroadcastEnabled = settings.getConfig().getBoolean("QUIT_SOUND_BROADCAST");
        if(isTitleBroadcastEnabled){
            String title = config.getConfig().getString("QUIT_BROADCAST_TITLE.TITLE");
            String subtitle = config.getConfig().getString("QUIT_BROADCAST_TITLE.SUBTITLE");
            int fadein = config.getConfig().getInt("QUIT_BROADCAST_TITLE.FADEIN");
            int stay = config.getConfig().getInt("QUIT_BROADCAST_TITLE.STAY");
            int fadeout = config.getConfig().getInt("QUIT_BROADCAST_TITLE.FADEOUT");
            textComponents.sendBroadcastTitle(player, title, subtitle, fadein, stay, fadeout);
        }
        if(isMessageBroadcastEnabled){
            for(String s : config.getConfig().getStringList("QUIT_BROADCAST_MESSAGE")) {
                if(s.contains("text")){
                    textComponents.sendBroadcastJSON(player, s);
                } else {
                    textComponents.sendBroadcastMessage(player, s);
                }
            }
        }
        if(isActionbarBroadcastEnabled){
            String message = config.getConfig().getString("QUIT_BROADCAST_ACTIONBAR");
            textComponents.sendActionBar(player, message);
        }
        if(isBossbarBroadcastEnabled){
            String message = config.getConfig().getString("QUIT_BROADCAST_BOSSBAR.MESSAGE");
            String color = config.getConfig().getString("QUIT_BROADCAST_BOSSBAR.COLOR");
            int time = config.getConfig().getInt("QUIT_BROADCAST_BOSSBAR.TIME");
            textComponents.sendBroadcastBossBar(player, message, color, time);
        }
        if(isSoundBroadcastEnabled){
            String sound = config.getConfig().getString("QUIT_SOUND_BROADCAST.SOUND");
            int volume = config.getConfig().getInt("QUIT_SOUND_BROADCAST.VOLUME");
            int pitch = config.getConfig().getInt("QUIT_SOUND_BROADCAST.PITCH");
            Sounds.playSound(player, sound, volume, pitch);
        }

    }

}
