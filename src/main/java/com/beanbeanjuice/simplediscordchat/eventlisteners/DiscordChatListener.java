package com.beanbeanjuice.simplediscordchat.eventlisteners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class DiscordChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot())
            Bukkit.broadcastMessage("[Discord] <" + event.getAuthor().getName() + "> " + event.getMessage().getContentStripped());
    }

}
