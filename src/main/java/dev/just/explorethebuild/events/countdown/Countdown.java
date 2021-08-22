package dev.just.explorethebuild.events.countdown;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.utils.Config;
import dev.just.explorethebuild.utils.ShortInteger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalField;

public class Countdown implements Listener {
    public static boolean isAllowed(CommandSender sender) {
        return sender.isOp() || sender.getName().toLowerCase().contains("mrwarp");
    }
    public static boolean isAllowed(Player sender) {
        return sender.isOp() || sender.getName().toLowerCase().contains("mrwarp");
    }
    public static Instant eventTime = null;
    public static Instant startTime = null;
    public static void main() {
        bossBar = Bukkit.createBossBar(ChatColor.GRAY + "Loading...", BarColor.WHITE, BarStyle.SOLID);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Config.getBoolean("enableDetonationCountdown", false)) {
                    if (bossBar != null) {
                        bossBar.removeAll();
                        bossBar = null;
                    }
                    this.cancel();
                }
                else if (eventTime == null) {
//                    bossBar = Bukkit.createBossBar(ChatColor.GRAY + "No detonation date set", BarColor.RED, BarStyle.SOLID);
                    bossBar.setColor(BarColor.RED);
                    bossBar.setTitle(ChatColor.GRAY + "No detonation date set");
                } else {
                    if (difference() > 0) {
//                        bossBar = Bukkit.createBossBar(ChatColor.RED + "T-" + ChatColor.DARK_RED + difference(), BarColor.GREEN, BarStyle.SOLID);
                        bossBar.setColor(BarColor.WHITE);
                        if (difference() <= 60) {
                            bossBar.setTitle(ChatColor.RED + "T-" + ChatColor.DARK_RED + difference());
                            bossBar.setProgress(Double.valueOf((difference()* 100 / 60) * 0.01D).doubleValue());
                            if (bossBar.getProgress() > 0.66D) {
                                bossBar.setColor(BarColor.GREEN);
                            } else if (bossBar.getProgress() > 0.33D) {
                                bossBar.setColor(BarColor.YELLOW);
                            } else {
                                bossBar.setColor(BarColor.RED);
                            }

                        } else {
                            bossBar.setTitle(ChatColor.RED + "T-" + ChatColor.DARK_RED + ShortInteger.run(difference()));
                        }
//                        System.out.println(startTime);
//                        System.out.println(startTime.getEpochSecond() + " epoch second");
//                        System.out.println((difference() * 100 / startTime.getEpochSecond() * 100) * 0.01D + " bossbar");
                    } else {
//                        bossBar = Bukkit.createBossBar(ChatColor.RED + "T+" + ChatColor.DARK_RED + difference(), BarColor.RED, BarStyle.SOLID);
                        bossBar.setColor(BarColor.RED);
                        if (difference() * -1 <= 60) {
                            bossBar.setTitle(ChatColor.RED + "T+" + ChatColor.DARK_RED + difference() * -1);
                        } else {
                            bossBar.setTitle(ChatColor.RED + "T+" + ChatColor.DARK_RED + ShortInteger.run(difference() * -1));
                        }
                        bossBar.setProgress(1D);
                    }
                }
                for (Player all : Bukkit.getOnlinePlayers()) {
                        bossBar.addPlayer(all);
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 20);
    }
    public static long difference() {
        Instant now = Instant.now();
        Duration diff = Duration.between(now, eventTime);
        return diff.toSeconds();
    }
    private static BossBar bossBar;
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        if (event.getPlayer().isOp()) {
            Player player = event.getPlayer();
            if (event.getMessage().equalsIgnoreCase("reloadbar")) {
                event.setCancelled(true);
                bossBar = null;
                player.sendMessage(Main.getPrefix() + "The bar will reload now. ");
            } else if (event.getMessage().equalsIgnoreCase("removeplayers")) {
                event.setCancelled(true);
                bossBar.removeAll();
                player.sendMessage(Main.getPrefix() + "Removed all players from the bar! ");
            }
        }
    }
}
