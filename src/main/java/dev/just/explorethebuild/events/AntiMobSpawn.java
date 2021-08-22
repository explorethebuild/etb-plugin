package dev.just.explorethebuild.events;

import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class AntiMobSpawn implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            if (isSpawnProtected(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    private static boolean isSpawnProtected(Entity entity) {
        World world = Bukkit.getWorld(Config.getString("spawn.world", "world"));
        if (entity.getWorld().equals(world)) {
            Location block = entity.getLocation();
            int minx = Config.getInt("spawn.x.min", -20);
            int maxx = Config.getInt("spawn.x.max", 20);
            if (block.getX() > minx && block.getX() < maxx) {
                int miny = Config.getInt("spawn.y.min", 150);
                if (block.getY() > miny) {
                    int minz = Config.getInt("spawn.z.min", -20);
                    int maxz = Config.getInt("spawn.z.max", 20);
                    if (block.getZ() > minz && block.getZ() < maxz) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
