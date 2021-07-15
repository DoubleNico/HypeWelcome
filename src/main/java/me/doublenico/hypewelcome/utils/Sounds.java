package me.doublenico.hypewelcome.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {


    public static void playSound(Player player, String sound, int volume, int pitch){
        Sound convertedSound = XSound.matchXSound(Sound.valueOf(sound)).parseSound();
        player.playSound(player.getLocation(), convertedSound, volume, pitch);

    }

    public static void playSoundBroadcast(String sound, int volume, int pitch){
        Sound convertedSound = XSound.matchXSound(Sound.valueOf(sound)).parseSound();
        for(Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(), convertedSound, volume, pitch);
        }
    }


}
