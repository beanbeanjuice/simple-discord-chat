package com.beanbeanjuice.simplediscordchat;

import com.beanbeanjuice.simplediscordchat.eventlisteners.DiscordChatListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

public class Bot {

    private final String TOKEN;
    private final String GUILD_ID;
    private final String CHANNEL_ID;
    private final String ACTIVITY_MESSAGE;
    private JDA bot;
    private Guild guild;
    private TextChannel channel;

    public Bot(@NotNull String botToken, @NotNull String guildID, @NotNull String channelID, @NotNull String activityMessage) throws LoginException, InterruptedException {
        TOKEN = botToken;
        GUILD_ID = guildID;
        CHANNEL_ID = channelID;
        ACTIVITY_MESSAGE = activityMessage;

        bot = JDABuilder.createDefault(TOKEN)
                .setActivity(Activity.playing(ACTIVITY_MESSAGE))
                .setStatus(OnlineStatus.ONLINE)
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.MESSAGE_CONTENT
                )
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .build()
                .awaitReady();

        guild = bot.getGuildById(guildID);
        channel = guild.getTextChannelById(channelID);
        check();
    }

    /**
     * Starts the discord {@link net.dv8tion.jda.api.hooks.ListenerAdapter ListenerAdapter}.
     */
    public void startDiscordChatEventListener() {
        bot.addEventListener(new DiscordChatListener());
    }

    private boolean check() {
        if (guild == null) {
            Bukkit.getLogger().log(Level.SEVERE, "The guild: \"" + GUILD_ID + "\" does not exist.");
            return false;
        }

        if (channel == null) {
            Bukkit.getLogger().log(Level.SEVERE, "The text channel: \"" + CHANNEL_ID + "\" does not exist.");
            return false;
        }
        return true;
    }

    /**
     * Send a {@link MessageEmbed} in the specified {@link TextChannel}.
     * @param embed The {@link MessageEmbed} to send.
     */
    public void sendEmbed(@NotNull MessageEmbed embed) {
        if (check())
            channel.sendMessageEmbeds(embed).queue();
    }

    /**
     * Shutdown the bot and send an offline server message.
     */
    public void shutdown() {
        channel.getManager().setTopic("Server is offline.").queue();
        bot.shutdown();
    }

    /**
     * Updates the {@link TextChannel}'s topic.
     * @param onlinePlayers The {@link Integer online players}.
     * @param maxPlayers The {@link Integer max players}.
     */
    public void updateChannelDescription(@NotNull Integer onlinePlayers, @NotNull Integer maxPlayers) {
        channel.getManager().setTopic(onlinePlayers + "/" + maxPlayers + " online.").queue();
    }

}
