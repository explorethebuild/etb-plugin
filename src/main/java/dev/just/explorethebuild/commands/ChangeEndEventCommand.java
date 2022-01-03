package dev.just.explorethebuild.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeEndEventCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
            return false;
        }
        if (!Config.getBoolean("isEndAllowed", false)) {
            Config.set("isEndAllowed", true);
            sender.sendMessage(Main.getPrefix() + "Du hast das End erfolgreich aktiviert.");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.BLUE + "Eine neue Dimension öffnet sich...",
                        ChatColor.GRAY + "Das End ist nun aktiv", 20, 60, 20);
            }
            Bukkit.broadcastMessage(Main.getPrefix() + "Das End-Event hat begonnen. ");
        } else {
            if (args.length == 1 && args[0].equalsIgnoreCase("confirm")) {
                Config.set("isEndAllowed", false);
                sender.sendMessage(Main.getPrefix() + "Du hast das End erfolgreich deaktiviert.");
            } else {
                sender.sendMessage(Main.getPrefix() + "Das End ist bereits aktiv. Nutze " + ChatColor.BLUE +
                        "/toggleend confirm" + ChatColor.GRAY + ", um es zu deaktivieren. ");
            }
        }
        return false;
    }
}
