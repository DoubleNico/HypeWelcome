package me.doublenico.hypewelcome.actions;

import me.doublenico.hypeapi.json.JSON;
import me.doublenico.hypewelcome.HypeWelcome;
import me.doublenico.hypewelcome.utils.CC;
import me.doublenico.hypewelcome.utils.Sounds;
import me.doublenico.hypewelcome.utils.TextComponents;
import me.doublenico.hypewelcome.utils.XSound;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventActionTask extends BukkitRunnable {

    private String playerName;
    private Actions action;
    private String message;

    public EventActionTask(String playerName, Actions action, String message){
        this.playerName = playerName;
        this.action = action;
        this.message = message;
    }


    public void run() {
        Player player = Bukkit.getPlayerExact(playerName);
        TextComponents textComponents = new TextComponents(JavaPlugin.getPlugin(HypeWelcome.class));
        switch (this.action){
            case CHAT:
                player.chat(CC.convert(player, message));
                break;
            case CHAT_BROADCAST:
                textComponents.sendBroadcastMessage(player, message);
                break;
            case PLAYER:
                player.chat("/" + CC.convert(player, message));
                break;
            case CONSOLE:
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
                break;
            case JSON:
                textComponents.sendJSON(player, message);
                break;
            case JSON_BROADCAST:
                textComponents.sendBroadcastJSON(player, message);
                break;
            case PLAY_SOUND_BROADCAST:
            case PLAY_SOUND:
                Sound sound = null;
                int volume = 10;
                int pitch = 1;
                if (message.contains(" ")){
                    String[] args = this.message.split(" ");
                    try {
                        sound = XSound.matchXSound(args[0]).get().parseSound();
                    } catch (Exception e){
                        CC.console("&bSound " + args[0] + " doesn't exists!");
                        return;
                    }
                    if(args.length >= 2){
                        if(args.length == 3){
                            pitch = Integer.parseInt(args[2]);
                        }
                            volume = Integer.parseInt(args[1]);
                    }
                }
                if(action == Actions.PLAY_SOUND_BROADCAST){
                    Sounds.playSoundBroadcast(sound.toString(), volume, pitch);
                    break;
                }
                assert player != null;
                Sounds.playSound(player, sound.toString(), volume, pitch);
                break;
            case MESSAGE:
                CC.convertedMessage(player, message);
                break;
            case ACTIONBAR:
                textComponents.sendActionBar(player, message);
                break;
            case ACTIONBAR_BROADCAST:
                textComponents.sendBroadcastActionBar(player, message);
                break;
            case TITLE_BROADCAST:
            case TITLE:
                String title;
                String subtitle = null;
                int fadein = 20;
                int stay = 60;
                int fadeout = 20;
                if(message.contains(" ")){
                    String[] args = this.message.split(" ");
                    title = args[0];
                    if(args.length >= 2){
                        subtitle = args[1];
                    }
                    if(args.length >= 3){
                        fadein = Integer.parseInt(args[2]);
                    }
                    if(args.length >= 4){
                        stay = Integer.parseInt(args[3]);
                    }
                    if(args.length >= 5){
                        fadeout = Integer.parseInt(args[4]);
                    }

                } else {
                    title = message;
                }
                if (title == null)
                    return;
                if (action == Actions.TITLE_BROADCAST){
                    textComponents.sendBroadcastTitle(player, title.replace("_", " "), subtitle.replace("_", " "), fadein, stay, fadeout);
                    break;
                }
                textComponents.sendTitle(player, title.replace("_", " "), subtitle.replace("_", " "), fadein, stay, fadeout);
                break;
            case BOSSBAR_BROADCAST:
            case BOSSBAR:
                String barMessage;
                String barColor = "PURPLE";
                int time = 5;
                if (message.contains(" ")){
                    String[] args = this.message.split(" ");
                    barMessage = args[0];
                    if(args.length >= 2){
                        if(args.length == 3){
                            time = Integer.parseInt(args[2]);
                        }
                        barColor = args[1];
                    }
                } else {
                    barMessage = message;
                }
                if(barMessage == null){
                    return;
                }
                if(this.action == Actions.BOSSBAR_BROADCAST){
                    textComponents.sendBroadcastBossBar(player, barMessage.replace("_", " "), barColor.replace("_", " "), time);
                    break;
                }
                textComponents.sendBossBar(player, barMessage.replace("_", " "), barColor.replace("_", " "), time);
                break;
        }
    }

}
