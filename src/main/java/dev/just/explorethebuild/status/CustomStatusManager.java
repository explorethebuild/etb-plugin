package dev.just.explorethebuild.status;

import dev.just.explorethebuild.utils.JColors;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CustomStatusManager {
    public static HashMap<UUID, String> customStatues = new HashMap<>();
    public static void setCustomStatus(Player player, String customStatus)  {
        player.setCustomName(customStatus);
        player.setDisplayName(customStatus);
        player.setPlayerListName(customStatus);
    }
    public static String getCustomName(Player player) {
        if (customStatues.containsKey(player.getUniqueId())) {
            return JColors.GRAY + "[" + JColors.VIOLET + ChatColor.translateAlternateColorCodes('&', customStatues.get(player.getUniqueId())) + JColors.GRAY + "] " + JColors.DARKSLATEGRAY + player.getName();
        }
        return player.getDisplayName();
    }
}
