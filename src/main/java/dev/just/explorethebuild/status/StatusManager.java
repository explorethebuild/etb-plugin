package dev.just.explorethebuild.status;

import dev.just.explorethebuild.utils.JColors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class StatusManager {
    public static HashMap<UUID, Status> statues = new HashMap<>();

    public enum Status {
        ONLINE,
        MINING,
        AFK,
        DISCORD,
        TEAMSPEAK
    }

    public static String getCustomName(Player player) {
        if (!statues.containsKey(player.getUniqueId())) return player.getName();
        Status status = statues.get(player.getUniqueId());
        if (status.equals(Status.MINING)) {
            return JColors.GRAY + "[" + JColors.CADETBLUE + "MINING" + JColors.GRAY + "] " + JColors.DARKSLATEGRAY + player.getName();
        } else if (status.equals(Status.ONLINE)) {
            return JColors.GRAY + "[" + JColors.DARKOLIVEGREEN + "ONLINE" + JColors.GRAY + "] " + JColors.DARKSLATEGRAY + player.getName();
        } else if (status.equals(Status.AFK)) {
            return JColors.GRAY + "[" + JColors.DIMGRAY + "AFK" + JColors.GRAY + "] " + JColors.DARKSLATEGRAY + player.getName();
        } else if (status.equals(Status.DISCORD)) {
            return JColors.GRAY + "[" + JColors.BLUEVIOLET + "DISCORD" + JColors.GRAY + "] " + JColors.DARKSLATEGRAY + player.getName();
        } else if (status.equals(Status.TEAMSPEAK)) {
            return JColors.GRAY + "[" + ChatColor.of(new Color(22, 29, 43)) + "TEAMSPEAK" + JColors.GRAY + "] " + JColors.DARKSLATEGRAY + player.getName();
//            #161d2b
        }
        return "Error: Mo status was correct DEBUG: " + status.toString();
    }
}

/**
 *     fun changeStatus(player: Player) {
 *         Bukkit.broadcastMessage(statusManager[player].toString())
 *         if (statusManager[player]?.equals(Statues.MINING) == true) {
 * //            val customName = (ChatColor.GRAY + "[" + ChatColor.BLUE + "MINING" + ChatColor.GRAY + "] " + player.name)
 *             val customName : String = "${KColors.GRAY}[${KColors.CADETBLUE}MINING${KColors.GRAY}] ${KColors.DARKSLATEGRAY}${player.name}"
 *             player.customName = customName
 *             player.setDisplayName(customName)
 *             player.setPlayerListName(customName)
 *         } else if (statusManager[player]?.equals(Statues.AFK) == true) {
 * //            val customName = (ChatColor.GRAY + "[" + ChatColor.DARK_GRAY + "AFK" + ChatColor.GRAY + "] " + player.name)
 *             val customName : String = "${KColors.GRAY}[${KColors.DIMGRAY}AFK${KColors.GRAY}] ${KColors.DARKSLATEGRAY}${player.name}"
 *             player.customName = customName
 *             player.setDisplayName(customName)
 *             player.setPlayerListName(customName)
 *         } else if (statusManager[player]?.equals(Statues.ONLINE) == true) {
 * //            val customName = (ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "AFK" + ChatColor.GRAY + "] " + player.name)
 *             val customName : String = "${KColors.GRAY}[${KColors.DARKOLIVEGREEN}ONLINE${KColors.GRAY}] ${KColors.DARKSLATEGRAY}$ {player.name}"
 *             player.customName = customName
 *             player.setDisplayName(customName)
 *             player.setPlayerListName(customName)
 *         }
 *     }
 */
