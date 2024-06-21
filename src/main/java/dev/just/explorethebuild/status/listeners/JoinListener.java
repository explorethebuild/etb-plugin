package dev.just.explorethebuild.status.listeners;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.start.IsStarted;
import dev.just.explorethebuild.start.commands.StartCommand;
import dev.just.explorethebuild.status.CustomStatusManager;
import dev.just.explorethebuild.status.StatusManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        if (!IsStarted.isStarted && player.getGameMode().equals(GameMode.SURVIVAL)) {
            player.setGameMode(GameMode.ADVENTURE);
        }
        if (StartCommand.preshow) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 255, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999, 255, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 999999, 128, false, false, false));
        }
    }
}
