package me.doublenico.hypewelcome.utils;

import me.doublenico.hypewelcome.actions.Actions;
import me.doublenico.hypewelcome.actions.EventAction;
import me.doublenico.hypewelcome.actions.EventActionTask;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomConfiguration {

    public static void getCustomConfig(FileConfiguration config, Player player, String path, Plugin plugin){
        for (String key : Objects.requireNonNull(
                config.getConfigurationSection(path)).getKeys(false)) {
            ConfigurationSection block = config.getConfigurationSection(path + "." + key);
            String getPermission = block.getString("permission");
            boolean disableOP = block.getBoolean("disableop");
            if(disableOP)
                if(player.isOp())
                    return;
            if(!player.hasPermission(getPermission))
                return;
            List<String> commands = block.getStringList("commands");
            if (commands == null || commands.isEmpty())
                return;
            List<EventAction> actions = new ArrayList<>();
            for(String message : commands){
                if(message == null){
                    if(message.isEmpty())
                        continue;
                }
                Actions action = Actions.checkCommand(message);
                if (action == null)
                    continue;
                message = message.replace(action.getCommand(), "");
                if(message.startsWith(" "))
                    message = message.trim();
                EventAction eventAction = new EventAction(action, message);
                eventAction.setMessage(message);
                actions.add(eventAction);
            }
            for(EventAction action : actions){
                String message = CC.convert(player, action.getMessage());
                new EventActionTask(player.getName(), action.getAction(), message).runTask(plugin);
            }

        }
    }


}
