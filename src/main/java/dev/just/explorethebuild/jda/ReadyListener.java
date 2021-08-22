package dev.just.explorethebuild.jda;

import dev.just.explorethebuild.Main;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;

public class ReadyListener implements EventListener {
    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            Bukkit.getLogger().info(Main.getPrefix() + "Verbindung mit Discord-Bot hergestellt. ");
        }
    }
}
