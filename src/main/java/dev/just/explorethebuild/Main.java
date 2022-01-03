package dev.just.explorethebuild;

import dev.just.explorethebuild.commands.ChangeEndEventCommand;
import dev.just.explorethebuild.elytra.commands.SetSpawnCommand;
import dev.just.explorethebuild.elytra.listeners.BlockBreakListener;
import dev.just.explorethebuild.elytra.listeners.EntityToggleGlideListener;
import dev.just.explorethebuild.elytra.listeners.MoveListener;
import dev.just.explorethebuild.elytra.teleportup.TeleportUp;
import dev.just.explorethebuild.events.AntiMobSpawn;
import dev.just.explorethebuild.events.DamageBeforeStart;
import dev.just.explorethebuild.events.EndBlockListener;
import dev.just.explorethebuild.start.commands.StartCommand;
import dev.just.explorethebuild.status.commands.StatusCommand;
import dev.just.explorethebuild.status.listeners.ChatListener;
import dev.just.explorethebuild.status.listeners.JoinListener;
import dev.just.explorethebuild.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        Config.registerConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getErrorPrefix() {
        return ChatColor.DARK_GRAY + "┃ " + ChatColor.DARK_RED + "ERROR" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getServerPrefix() {
        return  ChatColor.DARK_GRAY + "┃ " + ChatColor.BLUE  + "Server" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getPrefix() {
        return getServerPrefix();
    }
    public static String getPrefix(String name) {
        return  ChatColor.DARK_GRAY + "┃ " + ChatColor.BLUE  + name + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getNoPermission() {
        return getErrorPrefix() + "Du hast keine Rechte für diesen Befehl";
    }
    public static String getNoPlayer() {
        return getErrorPrefix() + "Du musst für diese Aktion ein Spieler sein";
    }

    private void registerCommands() {
        getCommand("status").setExecutor(new StatusCommand());
        getCommand("start").setExecutor(new StartCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("settpup").setExecutor(new TeleportUp());
        getCommand("toggleend").setExecutor(new ChangeEndEventCommand());
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new StatusCommand(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new EndBlockListener(), this);
        pluginManager.registerEvents(new DamageBeforeStart(), this);
        pluginManager.registerEvents(new AntiMobSpawn(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new EntityToggleGlideListener(), this);
    }
}
