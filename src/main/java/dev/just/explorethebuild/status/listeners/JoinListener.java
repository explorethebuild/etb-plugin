package dev.just.explorethebuild.status.listeners;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.status.CustomStatusManager;
import dev.just.explorethebuild.status.StatusManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (StatusManager.statues.containsKey(player.getUniqueId())) {
            String cname = StatusManager.getCustomName(player);
            player.setCustomName(cname);
            player.setDisplayName(cname);
            player.setPlayerListName(cname);
            player.sendMessage(Main.getPrefix() + "Du heißt aktuell " + cname + ChatColor.GRAY + "!");
        } else if (CustomStatusManager.customStatues.containsKey(player.getUniqueId())) {
            String cname = CustomStatusManager.getCustomName(player);
            player.setCustomName(cname);
            player.setDisplayName(cname);
            player.setPlayerListName(cname);
            player.sendMessage(Main.getPrefix() + "Du heißt aktuell " + cname + ChatColor.GRAY + "!");
        }
    }
}
