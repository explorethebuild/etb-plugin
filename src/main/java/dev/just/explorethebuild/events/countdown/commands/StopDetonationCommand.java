package dev.just.explorethebuild.events.countdown.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.events.countdown.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopDetonationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Countdown.isAllowed(sender)) {
            Countdown.eventTime = null;
            sender.sendMessage(Main.getPrefix() + "Successfully stopped the countdown! ");
            Bukkit.broadcastMessage(Main.getPrefix() + "The countdown has been stopped! ");
        }
        return false;
    }
}
