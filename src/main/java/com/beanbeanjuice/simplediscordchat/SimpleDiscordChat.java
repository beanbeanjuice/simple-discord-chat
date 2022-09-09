package com.beanbeanjuice.simplediscordchat;

import com.beanbeanjuice.simplediscordchat.eventlisteners.MinecraftChatListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public final class SimpleDiscordChat extends JavaPlugin {

    FileConfiguration config = getConfig();

    Timer timer;
    TimerTask timerTask;

    private final String BOT_TOKEN = config.getString("BOT_TOKEN");
    private final String DISCORD_SERVER_ID = config.getString("DISCORD_SERVER_ID");
    private final String DISCORD_CHANNEL_ID = config.getString("DISCORD_CHANNEL_ID");
    private final String ACTIVITY_MESSAGE = config.getString("ACTIVITY_MESSAGE");
    Bot bot;

    private void createTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                int onlinePlayers = Bukkit.getOnlinePlayers().size();
                int maxPlayers = Bukkit.getMaxPlayers();

                bot.updateChannelDescription(onlinePlayers, maxPlayers);
            }
        };
        timer.schedule(timerTask, 0, TimeUnit.SECONDS.toMillis(5));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        // config.yml stuff.
        config.options().copyDefaults(true);
        saveConfig();
        Bukkit.getLogger().log(Level.INFO, "Starting Simple Discord Chat...");

        try {
            bot = new Bot(BOT_TOKEN, DISCORD_SERVER_ID, DISCORD_CHANNEL_ID, ACTIVITY_MESSAGE);
            Bukkit.getLogger().log(Level.INFO, "Simple Discord Chat has started!");
            bot.sendEmbed(Helper.serverStartedEmbed());
            bot.startDiscordChatEventListener();
            Bukkit.getPluginManager().registerEvents(new MinecraftChatListener(bot), this);
            createTimer();
        } catch (LoginException | InterruptedException e) {
            Bukkit.getLogger().log(Level.INFO, "Simple Discord Chat has had errors starting...");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();

        bot.sendEmbed(Helper.serverShutdownEmbed());
        bot.shutdown();
    }

    public FileConfiguration getConfigFile() {
        return config;
    }
}
