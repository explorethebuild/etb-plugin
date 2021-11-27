package dev.just.explorethebuild.status.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.start.IsStarted;
import dev.just.explorethebuild.status.CustomStatusManager;
import dev.just.explorethebuild.status.StatusManager;
import dev.just.explorethebuild.utils.ItemBuilder;
import dev.just.explorethebuild.utils.JColors;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static dev.just.explorethebuild.status.StatusManager.statues;

public class StatusCommand implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getNoPlayer());
        } else if (!IsStarted.isStarted) {
            sender.sendMessage(Main.getErrorPrefix() + "Das Event ist noch nicht gestartet! ");
        } else {
            Player player = (Player) sender;
            player.openInventory(inventory(player));
        }
        return false;
    }

    public static Inventory inventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Status wählen");
        UUID uuid = player.getUniqueId();
        inv.setItem(0, getItem(StatusManager.Status.ONLINE, statues.containsKey(uuid) && statues.get(uuid).equals(StatusManager.Status.ONLINE)));
        inv.setItem(1, getItem(StatusManager.Status.AFK, statues.containsKey(uuid) && statues.get(uuid).equals(StatusManager.Status.AFK)));
        inv.setItem(2, getItem(StatusManager.Status.MINING, statues.containsKey(uuid) && statues.get(uuid).equals(StatusManager.Status.MINING)));
        inv.setItem(3, getItem(StatusManager.Status.DISCORD, statues.containsKey(uuid) && statues.get(uuid).equals(StatusManager.Status.DISCORD)));
        inv.setItem(4, getItem(StatusManager.Status.TEAMSPEAK, statues.containsKey(uuid) && statues.get(uuid).equals(StatusManager.Status.TEAMSPEAK)));
        inv.setItem(5, getCustomStatusItem(player));
        return inv;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.GREEN + "Status wählen") && event.getCurrentItem() != null) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            UUID uuid = event.getWhoClicked().getUniqueId();
            if (clickedItem.equals(getItem(StatusManager.Status.ONLINE, false))) {
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                statues.put(uuid, StatusManager.Status.ONLINE);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.ONLINE, true))) {
                statues.remove(uuid);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.AFK, false))) {
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                statues.put(uuid, StatusManager.Status.AFK);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.AFK, true))) {
                statues.remove(uuid);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.MINING, false))) {
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                statues.put(uuid, StatusManager.Status.MINING);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.MINING, true))) {
                statues.remove(uuid);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.DISCORD, false))) {
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                statues.put(uuid, StatusManager.Status.DISCORD);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.DISCORD, true))) {
                statues.remove(uuid);
                player.openInventory(inventory(player));
                changeStatus(player);
                player.updateInventory();
            } else if (clickedItem.equals(getItem(StatusManager.Status.TEAMSPEAK, false))) {
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                statues.put(uuid, StatusManager.Status.TEAMSPEAK);
                player.openInventory(inventory(player));
                changeStatus(player);
            } else if (clickedItem.equals(getItem(StatusManager.Status.TEAMSPEAK, true))) {
                statues.remove(uuid);
                player.openInventory(inventory(player));
                changeStatus(player);
            } else if (clickedItem.equals(getCustomStatusItem(true))) {
                CustomStatusManager.customStatues.remove(player.getUniqueId());
                player.openInventory(inventory(player));
                changeStatus(player);
            } else if (clickedItem.equals(getCustomStatusItem(false))) {
                new AnvilGUI.Builder()
                        .onClose(player1 -> {
                            player.openInventory(inventory(player1));
                        })
                        .onComplete((player1, s) -> {
                            if (s == null) {
                                return AnvilGUI.Response.text("Bitte trage den Status ein! ");
                            }
                            CustomStatusManager.customStatues.put(player1.getUniqueId(), s);
                            changeStatus(player1);
                            player1.openInventory(inventory(player1));
                            return AnvilGUI.Response.close();
                        })
                        .text("&cDumm")
                        .title("Setze deinen Status. ")
                        .plugin(Main.getPlugin(Main.class))
                        .open(player);
            }
        }
    }

    public static ItemStack getItem(StatusManager.Status status, boolean isEnabled) {
        if (status.equals(StatusManager.Status.ONLINE)) {
            ItemStack online;
            if (isEnabled) {
                online = new ItemBuilder(Material.GREEN_DYE)
                        .setName(ChatColor.GREEN + "ONLINE")
                        .addLoreLine(JColors.AZURE + "Dies ist dein aktueller Status. ")
                        .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                        .toItemStack();
            } else {
                online = new ItemBuilder(Material.GREEN_DYE)
                        .setName(ChatColor.GRAY + "ONLINE")
                        .addLoreLine(JColors.GREENYELLOW + "Klicke, um dies als deinen Status zu setzen. ")
                        .setName(ChatColor.GRAY + "ONLINE")
                        .toItemStack();
            }
            return online;
        } else if (status.equals(StatusManager.Status.AFK)){
            ItemStack afk;
            if (!isEnabled) {
                afk = new ItemBuilder(Material.LIGHT_GRAY_DYE)
                        .setName(ChatColor.GRAY + "AFK")
                        .addLoreLine(JColors.GREENYELLOW + "Klicke, um dies als deinen Status zu setzen. ")
                        .toItemStack();
            } else {
                afk = new ItemBuilder(Material.LIGHT_GRAY_DYE)
                        .setName(ChatColor.GREEN + "AFK")
                        .addLoreLine(JColors.AZURE + "Dies ist dein aktueller Status. ")
                        .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                        .toItemStack();
            }
            return afk;
        } else if (status.equals(StatusManager.Status.MINING)) {
            ItemStack mining;
            if (isEnabled) {
                mining = new ItemBuilder(Material.IRON_PICKAXE)
                        .setName(ChatColor.GREEN + "MINING")
                        .addLoreLine(JColors.AZURE + "Dies ist dein aktueller Status. ")
                        .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                        .toItemStack();
            } else {
                mining = new ItemBuilder(Material.IRON_PICKAXE)
                        .setName(ChatColor.GRAY + "MINING")
                        .addLoreLine(JColors.GREENYELLOW + "Klicke, um dies als deinen Status zu setzen. ")
                        .toItemStack();
            }
            return mining;
        } else if (status.equals(StatusManager.Status.DISCORD)) {
            ItemStack discord;
            if (isEnabled) {
                discord = new ItemBuilder(Material.LIGHT_BLUE_WOOL)
                        .setName(ChatColor.GREEN + "DISCORD")
                        .addLoreLine(JColors.AZURE + "Dies ist dein aktueller Status. ")
                        .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                        .toItemStack();
            } else {
                discord = new ItemBuilder(Material.LIGHT_BLUE_WOOL)
                        .setName(ChatColor.GRAY + "DISCORD")
                        .addLoreLine(JColors.GREENYELLOW + "Klicke, um dies als deinen Status zu setzen. ")
                        .toItemStack();
            }
            return discord;
        } else if (status.equals(StatusManager.Status.TEAMSPEAK)) {
            ItemStack ts;
            if (isEnabled) {
                ts = new ItemBuilder(Material.BLUE_WOOL)
                        .setName(ChatColor.GREEN + "TEAMSPEAK")
                        .addLoreLine(JColors.AZURE + "Dies ist dein aktueller Status. ")
                        .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                        .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                        .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                        .toItemStack();
            } else {
                ts = new ItemBuilder(Material.BLUE_WOOL)
                        .setName(ChatColor.GRAY + "TEAMSPEAK")
                        .addLoreLine(JColors.GREENYELLOW + "Klicke, um dies als deinen Status zu setzen. ")
                        .toItemStack();
            }
            return ts;
        }
        return null;
    }

    public static void changeStatus(Player player) {
        if (CustomStatusManager.customStatues.containsKey(player.getUniqueId())) {
            CustomStatusManager.setCustomStatus(player, CustomStatusManager.getCustomName(player));
            return;
        }
        String customName = StatusManager.getCustomName(player);
        player.setPlayerListName(customName);
        player.setCustomName(customName);
        player.setDisplayName(customName);
    }

    public static ItemStack getCustomStatusItem(Player player) {
        ItemStack itemStack;
        if (CustomStatusManager.customStatues.containsKey(player.getUniqueId())) {
            itemStack = new ItemBuilder(Material.OAK_SIGN)
                    .setName(ChatColor.GREEN + "CUSTOM NAME")
                    .addLoreLine(JColors.AZURE + "Du hast einen benutzerdefinierten Status!")
                    .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                    .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                    .toItemStack();
        } else {
            itemStack = new ItemBuilder(Material.OAK_SIGN)
                    .setName(ChatColor.GRAY + "CUSTOM NAME")
                    .addLoreLine(JColors.GREENYELLOW + "Klicke, um einen CustomName festzulegen")
                    .toItemStack();
        }
        return itemStack;
    }

    /**
     *
     * @param hasCustomStatus Specifies the status of the item
     * @return CustomStatus Item for GUIs
     *
     */

    public static ItemStack getCustomStatusItem(boolean hasCustomStatus) {
        ItemStack itemStack;
        if (hasCustomStatus) {
            itemStack = new ItemBuilder(Material.OAK_SIGN)
                    .setName(ChatColor.GREEN + "CUSTOM NAME")
                    .addLoreLine(JColors.AZURE + "Du hast einen benutzerdefinierten Status!")
                    .addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
                    .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .addItemFlag(ItemFlag.HIDE_ENCHANTS)
                    .toItemStack();
        } else {
            itemStack = new ItemBuilder(Material.OAK_SIGN)
                    .setName(ChatColor.GRAY + "CUSTOM NAME")
                    .addLoreLine(JColors.GREENYELLOW + "Klicke, um einen CustomName festzulegen")
                    .toItemStack();
        }
        return itemStack;
    }
}
