package dev.just.explorethebuild.elytra.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

public class EntityToggleGlideListener implements Listener {
    @EventHandler
    public void onToggle(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (MoveListener.playerFlying.contains(player)) {
            if (player.getFallDistance() == 0) {
                MoveListener.playerFlying.remove(player);
            } else {
                event.setCancelled(true);
            }
        }
    }
}
