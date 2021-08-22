package dev.just.explorethebuild.elytra.listeners;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.start.IsStarted;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveListener implements Listener {
    private static HashMap<Player, Integer> playerTimes = new HashMap<>();
    private static ArrayList<Player> ressistance = new ArrayList<>();
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!IsStarted.isStarted) {
            return;
        }
        Location location = event.getTo();
        World world = Bukkit.getWorld(Config.getString("spawn.world", "world"));
        if (location.getWorld().equals(world)) {
            Player player = event.getPlayer();
            int minx = Config.getInt("spawn.x.min", -20);
            int maxx = Config.getInt("spawn.x.max", 20);
            double x = location.getX();
            if (playerTimes.containsKey(player) && playerTimes.get(player) > 0) {
                if (player.getFallDistance() < 1) {
                    playerTimes.remove(player);
                }
                if (player.getFallDistance() < 3) {
                }
                if (!player.isGliding()) {
                    player.setGliding(true);
                }
                return;
            }
            if (x > minx && x < maxx) {
                int miny = Config.getInt("spawn.y.min", 150);
                double y = location.getY();
                if (y > miny) {
                    int minz = Config.getInt("spawn.z.min", -20);
                    int maxz = Config.getInt("spawn.z.max", 20);
                    double z = location.getZ();
                    if (z > minz && z < maxz) {
                        if (player.getFallDistance() > 3) {
                            if (!player.isGliding()) {
                                playerTimes.put(player, 20);
                                player.setGliding(true);
                                ressistance.add(player);
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        ressistance.remove(player);
                                    }
                                }.runTaskLaterAsynchronously(Main.getPlugin(Main.class), 460);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (playerTimes.containsKey(player)) {
                        playerTimes.put(player, (playerTimes.get(player) - 1));
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 20,20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player) && ressistance.contains(( (Player) event.getEntity()))) {
            event.setCancelled(true);
        }
    }
}
