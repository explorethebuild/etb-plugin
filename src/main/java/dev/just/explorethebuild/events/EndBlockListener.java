package dev.just.explorethebuild.events;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.elytra.ElytraSystem;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class EndBlockListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld().getName().contains("the_end")) {
            if (!Config.getBoolean("isEndAllowed", false)) {
                event.getPlayer().sendMessage(Main.getErrorPrefix() + "Da das End-Event noch nicht gestartet ist, " +
                        "wurdest du an den Spawn zurückgesetzt.");
                event.getPlayer().teleport(ElytraSystem.getElytraSpawnPosition());
            }
        }
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (event.getTo().getWorld().getName().contains("the_end")) {
            if (!Config.getBoolean("isEndAllowed", false)) {
                event.setTo(ElytraSystem.getElytraSpawnPosition());
                event.getPlayer().sendMessage(Main.getErrorPrefix() + "Da das End-Event noch nicht gestartet ist, " +
                        "wurdest du an den Spawn zurückgesetzt.");
            }
        }
    }
}
