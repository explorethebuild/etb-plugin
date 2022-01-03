package dev.just.explorethebuild.elytra.listeners;

import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().isSneaking() && event.getPlayer().isOp()) {

        } else if (isSpawnProtected(event.getBlock())) event.setCancelled(true);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().isSneaking() && event.getPlayer().isOp()) {

        } else if (isSpawnProtected(event.getBlock())) event.setCancelled(true);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getPlayer().isSneaking() && event.getPlayer().isOp()) {

        } else if (event.getClickedBlock() != null && isSpawnProtected(event.getClickedBlock())) event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().isSneaking() && event.getPlayer().isOp()) {

        } else if (isSpawnProtected(event.getRightClicked().getLocation().getBlock())) event.setCancelled(true);
    }

    private static boolean isSpawnProtected(Block block) {
        World world = Bukkit.getWorld(Config.getString("spawn.world", "world"));
        if (block.getWorld().equals(world)) {
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
