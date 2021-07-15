package me.doublenico.hypewelcome.utils;

import org.bukkit.entity.Player;

public class Placeholders {

    public static void placeholders(Player player, String message){
        CC.playerMessage(player, convertedString(player, message));
    }

    public static String convertedString(Player player, String message){
        return message
                .replace("{player}", player.getDisplayName())
                .replace("{player_x}", "" + player.getLocation().getX())
                .replace("{player_y}", "" + player.getLocation().getY())
                .replace("{player_z}", "" + player.getLocation().getZ())
                .replace("{player_xp}", "" + player.getTotalExperience())
                .replace("{player_health}", "" + player.getHealth())
                .replace("{player_item_mainhand}", player.getInventory().getItemInMainHand().toString())
                .replace("{player_world}", player.getWorld().toString())
                .replace("{player_gamemode}", player.getGameMode().name())
                .replace("{player_uuid}", player.getUniqueId().toString());
    }

}
