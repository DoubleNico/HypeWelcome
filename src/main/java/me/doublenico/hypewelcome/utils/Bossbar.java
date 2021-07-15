package me.doublenico.hypewelcome.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.doublenico.hypewelcome.HypeWelcome;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class Bossbar {

    private int taskID;
    private final HypeWelcome plugin;
    private BossBar bar;

    public Bossbar(HypeWelcome plugin){
        this.plugin = plugin;
    }

    public void removePlayer(Player player){
        bar.removePlayer(player);
    }

    public BossBar getBar(){
        return bar;
    }

    public void createBar(Player player, String barMessage, String barColor, int time) {
        if(!LegacyVersions.isLegacyVersion()){
            CC.console("&b&lHypeWelcome &eYou can''t use the bossbar!" +
                    "Because the server version is lower than 1.9");
        } else {
            barMessage = PlaceholderAPI.setPlaceholders(player, barMessage);
            bar = Bukkit.createBossBar(barMessage, BarColor.valueOf(barColor), BarStyle.SOLID);
            cast(time, barMessage, barColor);
            bar.setVisible(true);
            bar.addPlayer(player);
        }
    }

    public void cast(int timeConfig, String barMessage, String barColor){
        new BukkitRunnable() {

            double progress = 1.0;
            final double time = 1.0/timeConfig;

            @Override
            public void run() {
                bar.setProgress(progress);
                bar.setColor(BarColor.valueOf(barColor));
                bar.setTitle(barMessage);

                progress = progress - time;
                if(progress <= 0){
                    bar.setVisible(false);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

}
