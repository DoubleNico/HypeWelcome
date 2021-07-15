package me.doublenico.hypewelcome.utils;

import me.doublenico.hypeapi.actionbar.ActionBar;
import me.doublenico.hypeapi.json.JSON;
import me.doublenico.hypeapi.title.Title;
import me.doublenico.hypeapi.util.ColorChat;
import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.files.SettingsFile;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TextComponents {

    private Bossbar bar;
    private HypeWelcome plugin;
    private SettingsFile settings;

    public TextComponents(HypeWelcome plugin){
        this.plugin = plugin;
        this.settings = new SettingsFile(plugin);
    }

    public void sendTitle(Player player, String title, String subtitle, int fadein, int stay, int fadeout){
        title = CC.convert(player, title);
        subtitle = CC.convert(player, subtitle);
        if(!LegacyVersions.isLegacyVersion()){
            Title.sendTitle(player, title, subtitle, fadein, stay, fadeout);
        } else {
            player.sendTitle(title, subtitle, fadein, stay, fadeout);
        }

    }

    public void sendBroadcastTitle(Player player, String title, String subtitle, int fadein, int stay, int fadeout){
        title = CC.convert(player, title);
        subtitle = CC.convert(player, subtitle);
        if(!LegacyVersions.isLegacyVersion()){
            for(Player p : Bukkit.getOnlinePlayers()){
                if (settings.getConfig().getBoolean("exclude")){
                    if(!p.equals(player)){
                        Title.sendTitle(p, title, subtitle, fadein, stay, fadeout);
                    }
                } else {
                    Title.sendTitle(p, title, subtitle, fadein, stay, fadeout);
                }
            }
        } else {
            for(Player p : Bukkit.getOnlinePlayers()){
                if (settings.getConfig().getBoolean("exclude")){
                    if(!p.equals(player)){
                        p.sendTitle(title, subtitle, fadein, stay, fadeout);
                    }
                } else {
                    p.sendTitle(title, subtitle, fadein, stay, fadeout);
                }
            }
        }
    }

    public void sendActionBar(Player player, String message){
        message = CC.convert(player, message);
        if(!LegacyVersions.isLegacyVersion()){
            ActionBar.sendBar(player, message);
        } else {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new ComponentBuilder(ColorChat.color(message)).create());
        }

    }

    public void sendBroadcastActionBar(Player player, String message){
        message = CC.convert(player, message);
        if(!LegacyVersions.isLegacyVersion()){
            for(Player p : Bukkit.getOnlinePlayers()){
                if (settings.getConfig().getBoolean("exclude")){
                    if(!p.equals(player)){
                        ActionBar.sendBar(p, message);
                    }
                } else {
                    ActionBar.sendBar(p, message);
                }
            }
        } else {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if (settings.getConfig().getBoolean("exclude")){
                    if(!p.equals(player)){
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                new ComponentBuilder(ColorChat.color(message)).create());
                    }
                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new ComponentBuilder(ColorChat.color(message)).create());
                }
            }
        }

    }

    public void sendBossBar(Player player, String barMessage, String barColor, int time){
        barMessage = CC.convert(player, barMessage);
        bar = new Bossbar(JavaPlugin.getPlugin(HypeWelcome.class));
        bar.createBar(player, barMessage, barColor, time);
    }

    public void sendBroadcastBossBar(Player player, String barMessage, String barColor, int time){
        barMessage = CC.convert(player, barMessage);
        for (Player p : Bukkit.getOnlinePlayers()){
            if (settings.getConfig().getBoolean("exclude")){
                if(!p.equals(player)){
                    bar = new Bossbar(JavaPlugin.getPlugin(HypeWelcome.class));
                    bar.createBar(p, barMessage, barColor, time);
                }
            } else {
                bar = new Bossbar(JavaPlugin.getPlugin(HypeWelcome.class));
                bar.createBar(p, barMessage, barColor, time);
            }
        }
    }

    public void sendJSON(Player player, String message){
        message = CC.convert(player, message);
        CC.convertedJSON(player, message);
    }

    public void sendBroadcastJSON(Player player, String message){
        message = CC.convert(player, message);
        for(Player p : Bukkit.getOnlinePlayers()){
            if (settings.getConfig().getBoolean("exclude")){
                if(!p.equals(player)){
                    if(message.contains("%center%")){
                        CC.sendCenteredJSON(p, message.replace("%center%", ""));
                    } else {
                        JSON.sendJSON(p, message);
                    }
                }
            } else {
                if(message.contains("%center%")){
                    CC.sendCenteredJSON(p, message.replace("%center%", ""));
                } else {
                    JSON.sendJSON(p, message);
                }
            }
        }

    }

    public void sendBroadcastMessage(Player player, String message){
        message = CC.convert(player, message);
        for(Player p : Bukkit.getOnlinePlayers()){
            if (settings.getConfig().getBoolean("exclude")){
                if(!p.equals(player)){
                    if(message.contains("%center%")){
                        CC.sendCenteredMessage(p, message.replace("%center%", ""));
                    } else {
                        CC.playerMessage(p, message);
                    }
                }
            } else {
                if(message.contains("%center%")){
                    CC.sendCenteredMessage(p, message.replace("%center%", ""));
                } else {
                    CC.playerMessage(p, message);
                }
            }
        }
    }



}
