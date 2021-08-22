package dev.just.explorethebuild.events;

import dev.just.explorethebuild.start.IsStarted;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageBeforeStart implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!IsStarted.isStarted) {
            event.setCancelled(true);
        }
    }
}
