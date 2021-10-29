package dev.just.explorethebuild.start.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.utils.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dev.just.explorethebuild.elytra.ElytraSystem.getElytraSpawnPosition;
import static dev.just.explorethebuild.elytra.ElytraSystem.getWorldBorder;
import static dev.just.explorethebuild.start.IsStarted.isStarted;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
        } else if (args.length == 1 && args[0].equals("confirm") && isStarted) {
            sender.sendMessage(Main.getPrefix() + "Das Event wird gestoppt. ");
            isStarted = false;
            Config.set("started", false);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.teleport(getElytraSpawnPosition());
            }
            getWorldBorder().setCenter(getElytraSpawnPosition());
            getWorldBorder().setSize(Config.getDouble("border.stopped", 20), Config.getInt("border.time", 5));
            sender.sendMessage(Main.getPrefix() + "Das Event wurde gestoppt. ");
        } else if (isStarted) {
            sender.sendMessage(Main.getErrorPrefix() + "Das Event ist bereits gestartet. ");
            sender.sendMessage(Main.getErrorPrefix() + "Zum stoppen nutze: /start confirm");
        } else {
            sender.sendMessage(Main.getPrefix() + "Das Event wird gestartet. ");
            startEvent();
            sender.sendMessage(Main.getPrefix() + "Das Event wurde erfolgreich gestartet. ");
        }
        return false;
    }

    private void startEvent() {
        isStarted = true;
        Config.set("started", true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitle(player);
        }
        getWorldBorder().setSize(Config.getDouble("border.started", 20000000), Config.getInt("border.time", 5));
        sendInformation();
    }

    private void sendTitle(Player player) {
        player.sendTitle(ChatColor.DARK_BLUE + "Explore the Build 2", ChatColor.BLUE + "Good luck & and have fun", 3, 20, 3);
    }

    private void sendInformation() {
        Bukkit.broadcastMessage(Main.getPrefix() + "Das Event wurde erfolgreich gestartet.");
        Bukkit.broadcastMessage(Main.getPrefix() + "Guck auf den " + ChatColor.BLUE + "Discord-Server" + ChatColor.GRAY + " zum Lesen der Regeln!");
        Bukkit.broadcastMessage(Main.getPrefix() + "Kurzum: Hab Spaß, aber cheate nich wie ein Idiot. ");
        Bukkit.broadcastMessage(Main.getPrefix() + "Und bevor du auch nur auf die Idee kommst: XRay-Protection ist aktiviert. ");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.BLUE + "Springe einfach von der Insel. "));
        }
    }
}
