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
            event.setCancelled(!Config.getBoolean("isEndAllowed", false));
            if (!Config.getBoolean("isEndAllowed", false)) {
                event.getPlayer().sendMessage(Main.getErrorPrefix() + "Das Wechseln ins End ist noch nicht erlaubt. Warte auf eine Nachricht im Discord. ");
                event.getPlayer().teleport(ElytraSystem.getElytraSpawnPosition());
            }
        }
    }
}
