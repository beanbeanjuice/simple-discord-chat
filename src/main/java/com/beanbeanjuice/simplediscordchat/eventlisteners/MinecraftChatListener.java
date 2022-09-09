package com.beanbeanjuice.simplediscordchat.eventlisteners;

import com.beanbeanjuice.simplediscordchat.Bot;
import com.beanbeanjuice.simplediscordchat.Helper;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MinecraftChatListener implements Listener {

    private Bot bot;

    public MinecraftChatListener(@NotNull Bot bot) {
        this.bot = bot;
    }

    @EventHandler
    public void onPlayerChat(@NotNull AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        bot.sendEmbed(
                new EmbedBuilder()
                        .setAuthor(player.getName(), null, Helper.getPlayerHeadURL(player.getUniqueId()))
                        .setDescription(event.getMessage())
                        .setColor(Helper.getRandomColor())
                        .build()
        );
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        bot.sendEmbed(
                new EmbedBuilder()
                        .setAuthor(player.getName() + " has joined the server!", null, Helper.getPlayerHeadURL(player.getUniqueId()))
                        .setColor(Color.GREEN)
                        .build()
        );
    }

    @EventHandler
    public void onPlayerLeave(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();

        bot.sendEmbed(
                new EmbedBuilder()
                        .setAuthor(player.getName() + " has left the server!", null, Helper.getPlayerHeadURL(player.getUniqueId()))
                        .setColor(Color.RED)
                        .build()
        );
    }

    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent event) {
        Player player = event.getEntity();

        bot.sendEmbed(
                new EmbedBuilder()
                        .setAuthor(event.getDeathMessage() + "!", null, Helper.getPlayerHeadURL(player.getUniqueId()))
                        .setColor(Color.ORANGE)
                        .build()
        );
    }

    @EventHandler
    public void onPlayerAdvancement(@NotNull PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        try {
            String advancementString = player.getName() + " has made the advancement [" + event.getAdvancement().getDisplay().getTitle() + "]!";

            bot.sendEmbed(
                    new EmbedBuilder()
                            .setAuthor(advancementString, null, Helper.getPlayerHeadURL(player.getUniqueId()))
                            .setColor(Helper.getRandomColor())
                            .build()
            );
        } catch (NullPointerException ignored) { }
    }

}
