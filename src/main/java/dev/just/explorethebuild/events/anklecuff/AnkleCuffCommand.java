package dev.just.explorethebuild.events.anklecuff;

import dev.just.explorethebuild.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnkleCuffCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getNoPlayer());
        } else if (sender.getName().equalsIgnoreCase("MrWarp_3000") || sender.getName().equalsIgnoreCase("justDE") || sender.getName().equalsIgnoreCase("Thaladunka"))  {
            ((Player) sender).openInventory(AnkleCuffGUI.getInventory());
        } else {
            sender.sendMessage(Main.getNoPermission());
        }
        return false;
    }
}
