package dev.just.explorethebuild.utils;

import au.com.origma.perspectiveapi.v1alpha1.models.AnalyzeCommentRequest;
import au.com.origma.perspectiveapi.v1alpha1.models.AttributeType;
import au.com.origma.perspectiveapi.v1alpha1.models.ContentType;
import au.com.origma.perspectiveapi.v1alpha1.models.Entry;
import dev.just.explorethebuild.Main;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;

public class AntiNazi {
    public static String getBlockReason(String message) {
        if (Main.pAPI != null) {
            float identity_attack_probability = Main.pAPI.analyze(new AnalyzeCommentRequest.Builder()
                    .addRequestedAttribute(AttributeType.IDENTITY_ATTACK, null)
                    .addLanguage("de")
                    .comment(new Entry.Builder()
                            .type(ContentType.PLAIN_TEXT)
                            .text(message)
                            .build())
                    .build()).getAttributeScore(AttributeType.IDENTITY_ATTACK).getSummaryScore().getValue();
            if (identity_attack_probability > Config.getDouble("perspectiveAPI.blockThreshold", 0.75)) {
                return "PerspectiveAPI with probability " + identity_attack_probability;
            }
        }
        for (String blockedRegex: Config.getStringList("chat.blockedWords", new ArrayList<>())) {
            if (!blockedRegex.startsWith(".*")) blockedRegex = ".*" + blockedRegex;
            if (!blockedRegex.endsWith(".*")) blockedRegex = blockedRegex + ".*";
            if (message.toLowerCase().matches(blockedRegex)) {
                return "regex rule " + blockedRegex;
            }
        }
        return null;
    }

    public static void logBlock(String message, String userName, String type, String reason) {
        Bukkit.getLogger().warning("Blocked following " + type + " by " + userName + ": " + message + " because of " + reason);
        String disocrd_Webhook_URL = Config.getString("chat.discordWebhookURL", "INSERT_WEBHOOK_URL_HERE");
        if (disocrd_Webhook_URL.equals("INSERT_WEBHOOK_URL_HERE")) {
            Bukkit.getLogger().warning("Please insert a Discord Webhook URL in the config to log blocked messages");
            return;
        }
        DiscordWebhook webhook = new DiscordWebhook(disocrd_Webhook_URL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Neue Meldung")
                .addField("Spieler", "`" + userName + "`", true)
                .addField("Typ", "`" + type + "`", true)
                .addField("Nachricht", "`" + message + "`", false)
                .addField("Grund", "`" + reason + "`", false));
        try {
            webhook.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
