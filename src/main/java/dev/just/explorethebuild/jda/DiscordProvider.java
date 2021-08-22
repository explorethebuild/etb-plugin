package dev.just.explorethebuild.jda;

import dev.just.explorethebuild.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordProvider {
    public static JDA jda;
    public static void init() {
        try {
            jda = JDABuilder.createDefault(Config.getString("discord.token")).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
