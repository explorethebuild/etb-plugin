package dev.just.explorethebuild.elytra.teleportup;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.elytra.ElytraSystem;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class TeleportUp implements Listener, CommandExecutor {
    private static HashMap<Player, Integer> playerTeleportState = new HashMap<>();

    private static Location getTeleportUpLocation() {
        World world = ElytraSystem.getElytraSpawnPosition().getWorld();
        Location location = new Location(world, Config.getDouble("spawn.x.tp", 0), Config.getDouble("spawn.y.tp", 60), Config.getDouble("spawn.z.tp", 0));
        return location;
    }
    private static void putTeleportUpLocation(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        Config.set("spawn.x.tp", x);
        Config.set("spawn.y.tp", y);
        Config.set("spawn.z.tp", z);
    }
    private static int getTpX() {
        return getTeleportUpLocation().getBlockX();
    }
    private static int getTpY() {
        return getTeleportUpLocation().getBlockY();
    }
    private static int getTpZ() {
        return getTeleportUpLocation().getBlockZ();
    }

    public TeleportUp() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Location loc = player.getLocation();
                    if (loc.getBlockX() == getTpX() && loc.getBlockY() == getTpY() && loc.getBlockZ() == getTpZ()) {
                        if (playerTeleportState.containsKey(player)) {
                            int state = playerTeleportState.get(player);
                            if (state == 4) {
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        player.teleport(ElytraSystem.getElytraSpawnPosition());
                                    }
                                }.runTask(Main.getPlugin(Main.class));
                            } else {
                                if (state == 1) {
                                    player.spawnParticle(Particle.END_ROD, player.getLocation(), 50);
                                } else if (state == 2) {
                                    player.spawnParticle(Particle.END_ROD, player.getLocation(), 100);
                                } else if (state == 3) {
                                    player.spawnParticle(Particle.END_ROD, player.getLocation(), 200);
                                }
                                playerTeleportState.put(player, state + 1);
                            }
                        } else {
                            playerTeleportState.put(player, 1);
                        }
                    } else if (playerTeleportState.containsKey(player)) {
                        playerTeleportState.remove(player);
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 20);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getNoPlayer());
        } else if (!sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
        } else {
            Player player = (Player) sender;
            Location location = player.getLocation();
            player.sendMessage(Main.getPrefix() + "Location geladen: ");
            player.sendMessage(Main.getPrefix() + "X-Position: " + location.getX());
            player.sendMessage(Main.getPrefix() + "Y-Position: " + location.getY());
            player.sendMessage(Main.getPrefix() + "Z-Position: " + location.getZ());
            putTeleportUpLocation(location);
            player.sendMessage(Main.getPrefix() + "Position erfolgreich gesetzt. ");
        }
        return false;
    }
}