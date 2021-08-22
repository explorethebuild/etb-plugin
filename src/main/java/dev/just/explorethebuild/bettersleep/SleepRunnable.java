package dev.just.explorethebuild.bettersleep;

import dev.just.explorethebuild.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import static dev.just.explorethebuild.bettersleep.SleepEvent.*;

public class SleepRunnable {
    public static void main() {
        World world = Bukkit.getWorld("world");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getSleepingPlayers() >= 1 && isNightTime(world.getTime(), world)) {
                    if (areEnoughPlayersInBed()) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                skipNight(world);
                            }
                        }.runTask(Main.getPlugin(Main.class));
                    } else {
                        sendInformation();
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 10);
    }
}
