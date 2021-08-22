package dev.just.explorethebuild.events.anklecuff;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class AnkleCuffGUI implements Listener {
    public static Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Fußfessel-Menü");
        ItemStack cc7 = new ItemBuilder(Material.PLAYER_HEAD)
                .setSkullOwner("CrazyCreeper_7")
                .setName(ChatColor.GRAY + "Überwache " + ChatColor.GREEN + "CrazyCreeper_7")
                .addLoreLine(ChatColor.GRAY + "Klicke, um den Spieler zu überwachen! ")
                .toItemStack();
        ItemStack s82 = new ItemBuilder(Material.PLAYER_HEAD)
                .setSkullOwner("saturn_82")
                .setName(ChatColor.GRAY + "Überwache " + ChatColor.GREEN + "saturn_82")
                .addLoreLine(ChatColor.GRAY + "Klicke, um den Spieler zu überwachen! ")
                .toItemStack();
        inventory.addItem(cc7);
        inventory.addItem(s82);
        return inventory;
    }

    private static Inventory getPlayerInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Überwache: " + ChatColor.BLUE + player.getName());
        ItemStack head = new ItemBuilder(Material.PLAYER_HEAD)
                .setSkullOwner(player.getName())
                .addLoreLine(ChatColor.GRAY + "Leben: " + ChatColor.BLUE + player.getHealth() / 2 + " Herzen")
                .addLoreLine(ChatColor.GRAY + "Hunger: " + ChatColor.BLUE + player.getFoodLevel() + " Hungerkeulen")
                .addLoreLine(ChatColor.GRAY + "Item in der Mainhand: " + ChatColor.BLUE + player.getInventory().getItemInMainHand().getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Item in der Offhand: " + ChatColor.BLUE + player.getInventory().getItemInOffHand().getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Helm: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getHelmet()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Brustplatte: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getChestplate()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Hose: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getLeggings()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Schuhe: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getBoots()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "X-Koordinate: " + ChatColor.BLUE + player.getLocation().getBlockX())
                .addLoreLine(ChatColor.GRAY + "Y-Koordinate: " + ChatColor.BLUE + player.getLocation().getBlockY())
                .addLoreLine(ChatColor.GRAY + "Z-Koordinate: " + ChatColor.BLUE + player.getLocation().getBlockZ())
                .setName(player.getDisplayName())
                .toItemStack();
        ItemStack refresh = new ItemBuilder(Material.STRUCTURE_VOID)
                .setName(ChatColor.BLUE + "Neu laden")
                .addLoreLine(ChatColor.GRAY + "Klicke, um die Daten neu zu laden")
                .toItemStack();
        ItemStack inv = new ItemBuilder(Material.CHEST)
                .setName(ChatColor.BLUE + "Inventar ansehen")
                .addLoreLine(ChatColor.GRAY + "Klicke, um das Inventar anzusehen")
                .toItemStack();
        inventory.setItem(1, inv);
        inventory.setItem(4, head);
        inventory.setItem(7, refresh);
        return inventory;
    } private static void getPlayerInventory(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack head = new ItemBuilder(Material.PLAYER_HEAD)
                .setSkullOwner(player.getName())
                .addLoreLine(ChatColor.GRAY + "Leben: " + ChatColor.BLUE + player.getHealth() / 2 + " Herzen")
                .addLoreLine(ChatColor.GRAY + "Hunger: " + ChatColor.BLUE + player.getFoodLevel() + " Hungerkeulen")
                .addLoreLine(ChatColor.GRAY + "Item in der Mainhand: " + ChatColor.BLUE + player.getInventory().getItemInMainHand().getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Item in der Offhand: " + ChatColor.BLUE + player.getInventory().getItemInOffHand().getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Helm: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getHelmet()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Brustplatte: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getChestplate()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Hose: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getLeggings()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "Schuhe: " + ChatColor.BLUE + Objects.requireNonNull(player.getInventory().getBoots()).getType().getKey().getKey())
                .addLoreLine(ChatColor.GRAY + "X-Koordinate: " + ChatColor.BLUE + player.getLocation().getBlockX())
                .addLoreLine(ChatColor.GRAY + "Y-Koordinate: " + ChatColor.BLUE + player.getLocation().getBlockY())
                .addLoreLine(ChatColor.GRAY + "Z-Koordinate: " + ChatColor.BLUE + player.getLocation().getBlockZ())
                .setName(player.getDisplayName())
                .toItemStack();
        ItemStack refresh = new ItemBuilder(Material.STRUCTURE_VOID)
                .setName(ChatColor.BLUE + "Neu laden")
                .addLoreLine(ChatColor.GRAY + "Klicke, um die Daten neu zu laden")
                .toItemStack();
        ItemStack inv = new ItemBuilder(Material.CHEST)
                .setName(ChatColor.BLUE + "Inventar ansehen")
                .addLoreLine(ChatColor.GRAY + "Klicke, um das Inventar anzusehen")
                .toItemStack();
        inventory.setItem(1, inv);
        inventory.setItem(4, head);
        inventory.setItem(7, refresh);
    }

    @EventHandler
    public void onCloe(InventoryCloseEvent event) {
        if (event.getView().getTitle().startsWith(ChatColor.GRAY + "Überwache: " + ChatColor.BLUE)) {
            Player player = (Player) event.getPlayer();
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.openInventory(getInventory());
                }
            }.runTaskLater(Main.getPlugin(Main.class), 5);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory() == null) return;
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Fußfessel-Menü")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Überwache " + ChatColor.GREEN + "saturn_82")) {
                Player target = Bukkit.getPlayer("saturn_82");
                if (target == null) {
                    player.sendMessage(Main.getErrorPrefix() + "Der Spieler ist nicht online!");
                } else {
                    player.openInventory(getPlayerInventory(target));
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Überwache " + ChatColor.GREEN + "CrazyCreeper_7")) {
                Player target = Bukkit.getPlayer("CrazyCreeper_7");
                if (target == null) {
                    player.sendMessage(Main.getErrorPrefix() + "Der Spieler ist nicht online!");
                } else {
                    player.openInventory(getPlayerInventory(target));
                }
            }
        } else if (event.getView().getTitle().startsWith(ChatColor.GRAY + "Überwache: " + ChatColor.BLUE)) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null && event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            ItemStack head = event.getInventory().getItem(4);
            if (head == null || head.getType() != Material.PLAYER_HEAD) return;
            SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
            assert skullMeta != null;
            OfflinePlayer offlineTarget = skullMeta.getOwningPlayer();
            if (offlineTarget == null || !offlineTarget.isOnline()) return;
            Player target = offlineTarget.getPlayer();
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Inventar ansehen")) {
                Inventory targetInventory = target.getInventory();
                Inventory showInventory = Bukkit.createInventory(null, 36, ChatColor.GRAY + "Inventar von " + ChatColor.BLUE + target.getName());
                showInventory.setContents(targetInventory.getContents());
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Neu laden")) {

            }
        } else if (event.getView().getTitle().startsWith(ChatColor.GRAY + "Inventar von " + ChatColor.BLUE)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getName().equalsIgnoreCase("saturn_82") || event.getPlayer().getName().equalsIgnoreCase("CrazyCreeper_7")) {
            event.getPlayer().sendMessage(Main.getPrefix() + "Du wirst aktuell mit einer Fußfessel überwacht! ");
        }
    }
}
