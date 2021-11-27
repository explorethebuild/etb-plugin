package dev.just.explorethebuild.elytra.listeners;

import dev.just.explorethebuild.start.IsStarted;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class MoveListener implements Listener {
    public static ArrayList<Player> playerFlying = new ArrayList<>();
    private boolean isInSpawnAre(Location location) {
        if (location.getWorld().getName().equalsIgnoreCase("world")) {
            if (location.getX() > Config.getInt("spawn.x.min") &&
            location.getX() < Config.getInt("spawn.x.max")) {
                if (location.getY() > Config.getInt("spawn.y.min")) {
                    if (location.getZ() > Config.getInt("spawn.z.min") &&
                    location.getZ() < Config.getInt("spawn.z.max")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!IsStarted.isStarted) return;
        if (isInSpawnAre(event.getPlayer().getLocation())) {
            if (event.getPlayer().getFallDistance() > 0) {
                Player player = event.getPlayer();
                player.setGliding(true);
                playerFlying.add(player);
            }
        }
    }
}
