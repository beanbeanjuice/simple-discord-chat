package com.beanbeanjuice.simplediscordchat;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Helper {

    public static MessageEmbed serverStartedEmbed() {
        return new EmbedBuilder()
                .setAuthor("✅ Server has started!")
                .setTimestamp(new Date().toInstant())
                .setColor(Color.GREEN)
                .build();
    }

    public static MessageEmbed serverShutdownEmbed() {
        return new EmbedBuilder()
                .setAuthor("\uD83D\uDED1 Server has stopped.")
                .setTimestamp(new Date().toInstant())
                .setColor(Color.RED)
                .build();
    }

    public static Color getRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    public static String getPlayerHeadURL(@NotNull UUID UUID) {
        return "https://mc-heads.net/avatar/" + UUID;
    }

}
