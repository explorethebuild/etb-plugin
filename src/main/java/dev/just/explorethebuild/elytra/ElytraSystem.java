package dev.just.explorethebuild.elytra;

import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class ElytraSystem {
    public static Location getElytraSpawnPosition() {
        World world = Bukkit.getWorld(Config.getString("spawn.world"));
        Location location = new Location(world, Config.getDouble("spawn.x.exact", 0), Config.getDouble("spawn.y.exact", 100), Config.getDouble("spawn.z.exact", 0));
        return location;
    }
    public static void putElytraSpawnPosition(Location location) {
        String worldName = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        Config.set("spawn.world", worldName);
        Config.set("spawn.y.exact", y);
        Config.set("spawn.z.exact", z);
        Config.set("spawn.x.exact", x);
    }
    public static WorldBorder getWorldBorder() {
        return getElytraSpawnPosition().getWorld().getWorldBorder();
    }
}
