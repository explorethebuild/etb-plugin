package dev.just.explorethebuild.elytra.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.elytra.ElytraSystem;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getNoPlayer());
        } else {
            Player player = (Player) sender;
            Location location = player.getLocation();
            player.sendMessage(Main.getPrefix() + "Location geladen: ");
            player.sendMessage(Main.getPrefix() + "X-Position: " + location.getX());
            player.sendMessage(Main.getPrefix() + "Y-Position: " + location.getY());
            player.sendMessage(Main.getPrefix() + "Z-Position: " + location.getZ());
            ElytraSystem.putElytraSpawnPosition(location);
            player.sendMessage(Main.getPrefix() + "Position erfolgreich gesetzt. ");
        }
        return false;
    }
}
