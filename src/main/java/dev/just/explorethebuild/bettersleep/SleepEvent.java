package dev.just.explorethebuild.bettersleep;

import dev.just.explorethebuild.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SleepEvent implements Listener {
    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        if (areEnoughPlayersInBed()) {
            skipNight(event.getPlayer().getWorld());
        } else {
            sendInformation();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (isNightTime(Bukkit.getWorld("world").getTime(), event.getPlayer().getWorld())) {
            sendInformation();
        }

    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (isNightTime(Bukkit.getWorld("world").getTime(), event.getPlayer().getWorld())) {
            sendInformation();
        }
    }

    public static boolean areEnoughPlayersInBed() {
        int needed;
        int canSleep = 0;
        int areSleeping = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().getName().equals("world")) {
                canSleep++;
            }
            if (player.isSleeping()) {
                areSleeping++;
            }
        }
        needed = (int) Math.round(canSleep * 0.5);
        //            Bukkit.getWorld("world").setTime(6000);
        //            for (Player player : Bukkit.getOnlinePlayers()) {
        //                player.spigot().sendMessage(new TextComponent(ChatColor.YELLOW + "Guten Morgen! "));
        return areSleeping >= needed;
    }

    public static int getNeededPlayers() {
        int needed;
        int canSleep = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().getName().equals("world")) {
                canSleep++;
            }
        }
        needed = (int) Math.round(canSleep * 0.5);
        return needed;
    }
    public static int getSleepingPlayers() {
        int areSleeping = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isSleeping()) {
                areSleeping++;
            }
        }
        return areSleeping;
    }
    public static void sendInformation() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Es schlafen " + ChatColor.GOLD + getSleepingPlayers() + ChatColor.YELLOW + " von " + getNeededPlayers() + ChatColor.YELLOW + " Spielern! "));
        }
    }
    public static boolean isNightTime(long time, World world) {
        return time > 12541 && time < 24000 || world.isThundering();
    }

    public static void skipNight(World world) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (world.isThundering()) {
                    world.setWeatherDuration(3);
                }
                world.setTime(6000);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + "Guten Morgen! "));
                }
            }
        }.runTask(Main.getPlugin(Main.class));
    }
}
