package dev.just.explorethebuild.start.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.elytra.ElytraSystem;
import dev.just.explorethebuild.utils.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static dev.just.explorethebuild.elytra.ElytraSystem.getElytraSpawnPosition;
import static dev.just.explorethebuild.elytra.ElytraSystem.getWorldBorder;
import static dev.just.explorethebuild.start.IsStarted.isStarted;

public class StartCommand implements CommandExecutor {

    public static boolean preshow = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
        } else if (args.length == 1 && args[0].equals("confirm") && isStarted) {
            sender.sendMessage(Main.getPrefix() + "Das Event wird gestoppt. ");
            isStarted = false;
            Config.set("started", false);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(getElytraSpawnPosition());
            }
            getWorldBorder().setCenter(getElytraSpawnPosition());
            getWorldBorder().setSize(Config.getDouble("border.stopped", 20), Config.getInt("border.time", 5));
            sender.sendMessage(Main.getPrefix() + "Das Event wurde gestoppt. ");
        } else if (isStarted) {
            sender.sendMessage(Main.getErrorPrefix() + "Das Event ist bereits gestartet. ");
            sender.sendMessage(Main.getErrorPrefix() + "Zum stoppen nutze: /start confirm");
        } else {
            sender.sendMessage(Main.getPrefix() + "Das Event wird gestartet. ");
            startPreshow();
            sender.sendMessage(Main.getPrefix() + "Die Preshow wurde erfolgreich gestartet. Eigentlicher Start in ca 2:30 Minuten. ");
        }
        return false;
    }

    private void startPreshow() {
        Bukkit.getWorld("world").playSound(ElytraSystem.getElytraSpawnPosition(), Sound.MUSIC_DISC_STAL, Integer.MAX_VALUE, 1.2F);
        new BukkitRunnable(){
            int start_time = 0;
            @Override
            public void run() {
                if (start_time == 0) {
                    Bukkit.getWorld("world").playSound(ElytraSystem.getElytraSpawnPosition(), Sound.MUSIC_DISC_STAL, Integer.MAX_VALUE, 1.2F);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 255, false, false, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999, 255, false, false, false));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 999999, 128, false, false, false));
                    }
                } else if (start_time == 1) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "Eine leere Welt...", "", 10, 40, 10);
                    }
                } else if (start_time == 4) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "Erstellt, um sie zu bebauen...", "", 10, 40, 10);
                    }
                } else if (start_time == 7) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "Technik zu erstellen...", "", 10, 40, 10);
                    }
                } else if (start_time == 10) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "und um sie zu kämpfen...", "", 10, 40, 10);
                    }
                } else if (start_time == 13) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "oder was auch immer ihr sonst tun wollt.", "", 10, 40, 10);
                    }
                } else if (start_time == 16) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "Macht etwas draus!", "", 10, 40, 10);
                    }
                } else if (start_time == 19) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.GRAY + "Es gelten die bekannten Regeln...", "", 5, 30, 5);
                    }
                } else if (start_time == 21) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.GRAY + "und Ahndungen.", "", 5, 30, 5);
                    }
                } else if (start_time == 23) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.GOLD + "justEvents präsentiert...", "", 5, 30, 5);
                    }
                } else if (start_time == 25) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.GOLD + "eine weitere Episode: ", "", 5, 30, 5);
                    }
                } else if (start_time == 27) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.BLUE + "Explore the Build 5", "", 20, 50, 10);
                    }
                } else if (start_time == 31) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.RED + "Habt Spaß!", "", 10, 40, 10);
                    }
                } else if (start_time == 34) {
                    for (Player player : Bukkit.getOnlinePlayers()) {

                        player.removePotionEffect(PotionEffectType.SLOWNESS);
                        player.removePotionEffect(PotionEffectType.BLINDNESS);
                        player.removePotionEffect(PotionEffectType.JUMP_BOOST);
                        startEvent();
                    }
                } else if (start_time == 60) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.stopSound(Sound.MUSIC_DISC_STAL);
                    }
                    cancel();
                }
                start_time++;
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0, 20);
    }

    private void startEvent() {
        preshow = false;
        isStarted = true;
        Config.set("started", true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitle(player);
        }
        getWorldBorder().setSize(Config.getDouble("border.started", 20000000), Config.getInt("border.time", 5));
        sendInformation();
    }

    private void sendTitle(Player player) {
        player.sendTitle(ChatColor.DARK_BLUE + "Explore the Build 5", ChatColor.BLUE + "Good luck & and have fun", 3, 20, 3);
    }

    private void sendInformation() {
        Bukkit.broadcastMessage(Main.getPrefix() + "Das Event wurde erfolgreich gestartet.");
        Bukkit.broadcastMessage(Main.getPrefix() + "Schau auf den " + ChatColor.BLUE + "Discord-Server" + ChatColor.GRAY + " zum Lesen der Regeln!");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.BLUE + "Springe einfach von der Insel. "));
        }
    }
}
