package dev.just.explorethebuild.status.listeners;

import dev.just.explorethebuild.utils.AntiNazi;
import dev.just.explorethebuild.utils.JColors;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(event.getPlayer().getDisplayName() + " " + JColors.SLATEGRAY + event.getMessage());
        String blockReason = AntiNazi.getBlockReason(event.getMessage());
        if (blockReason != null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(event.getPlayer().getDisplayName() + " " + JColors.SLATEGRAY + event.getMessage());
            AntiNazi.logBlock(event.getMessage(), event.getPlayer().getName(), "chat message", blockReason);
        }
    }
}
