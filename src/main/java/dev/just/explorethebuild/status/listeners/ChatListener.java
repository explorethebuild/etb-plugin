package dev.just.explorethebuild.status.listeners;

import dev.just.explorethebuild.utils.JColors;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(event.getPlayer().getDisplayName() + " " + JColors.SLATEGRAY + event.getMessage());
    }

    /**
            listen<AsyncPlayerChatEvent> {
        var player : Player = it.player
        it.format = "${player.customName} ${KColors.SLATEGRAY}${it.message}"
    **/
}
