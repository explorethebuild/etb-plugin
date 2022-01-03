package dev.just.explorethebuild.events;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.elytra.ElytraSystem;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EndBlockListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld().getName().contains("the_end")) {
            if (!Config.getBoolean("isEndAllowed", false)) {
                event.setCancelled(true);
                event.getPlayer().teleport(ElytraSystem.getElytraSpawnPosition());
                event.getPlayer().sendMessage(Main.getErrorPrefix() + "Da das End-Event noch nicht gestartet ist, " +
                        "wurdest du an den Spawn zurückgesetzt.");
            }
        }
    }
}
