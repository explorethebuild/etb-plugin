package dev.just.explorethebuild.commands;

import dev.just.explorethebuild.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Main.getPrefix() + "Bitte beachte die Regeln auf dem offiziellen Discord-Server! ");
        return false;
    }
}
