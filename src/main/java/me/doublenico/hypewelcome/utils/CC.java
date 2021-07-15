package me.doublenico.hypewelcome.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.doublenico.hypeapi.json.JSON;
import me.doublenico.hypeapi.util.ColorChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CC {

    private final static int CENTER_PX = 154;

    public static void console(String message){
        Bukkit.getConsoleSender().sendMessage(ColorChat.color(message));
    }


    public static void playerMessage(Player player, String message){
        player.sendMessage(HexSupport.colorMessage(message));
    }

    public static String convert(Player player, String message){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            message = PlaceholderAPI.setPlaceholders(player, HexSupport.colorMessage(message));
        } else {
            message = Placeholders.convertedString(player, HexSupport.colorMessage(message));
        }
        return message;
    }

    public static void convertedMessage(Player player, String message){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            message = PlaceholderAPI.setPlaceholders(player, HexSupport.colorMessage(message));
        } else {
            message = Placeholders.convertedString(player, HexSupport.colorMessage(message));
        }
        if(message.contains("%center%")){
            CC.sendCenteredMessage(player, message.replace("%center%", ""));
        } else {
            playerMessage(player, message);
        }
    }

    public static void convertedJSON(Player player, String message){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            message = PlaceholderAPI.setPlaceholders(player, HexSupport.colorMessage(message));
        } else {
            message = Placeholders.convertedString(player, HexSupport.colorMessage(message));
        }
        if(message.contains("%center%")){
            CC.sendCenteredJSON(player, message.replace("%center%", ""));
        } else {
            JSON.sendJSON(player, message);
        }
    }




    public static void sendCenteredMessage(Player player, String message){
        if(message == null || message.equals("")) player.sendMessage("");
        message = HexSupport.colorMessage(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb + message);
    }

    public static void sendCenteredJSON(Player player, String message){
        if(message == null || message.equals("")) player.sendMessage("");
        message = HexSupport.colorMessage(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        JSON.sendJSON(player, sb + message);
    }






}
