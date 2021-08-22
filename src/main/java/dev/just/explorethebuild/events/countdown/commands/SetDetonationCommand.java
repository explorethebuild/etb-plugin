package dev.just.explorethebuild.events.countdown.commands;

import dev.just.explorethebuild.Main;
import dev.just.explorethebuild.events.countdown.Countdown;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SetDetonationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Countdown.isAllowed(sender)) {
            if (args.length == 6) {
                String detonation = args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5];
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH mm ss dd MM yyyy")
                        .withLocale(Locale.GERMANY)
                        .withZone(ZoneId.systemDefault());

                Instant event = fmt.parse(detonation, Instant::from);
                Countdown.eventTime = event;
                sender.sendMessage(Main.getPrefix() + "Detonation was set to " + ChatColor.BLUE + event.toString());
                Countdown.startTime = Instant.now();

//                Instant now = Instant.now();
//                Duration diff = Duration.between(now, event);
//                long sec = diff.toSeconds();
//
            } else {
                sender.sendMessage(Main.getErrorPrefix() + "Usage: /setdetonation <H> <MI> <S> <D> <MO> <Y>");
            }
        } else {
            sender.sendMessage(Main.getNoPermission());
        }
        return false;
    }
}
