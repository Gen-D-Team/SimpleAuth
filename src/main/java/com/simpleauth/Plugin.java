package com.simpleauth;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import com.simpleauth.CommandHandler.Email;
import com.simpleauth.CommandHandler.Help;
import com.simpleauth.CommandHandler.LogIn;

/*
 * simpleauthme java plugin
 */

public class Plugin extends JavaPlugin {
    public static Logger LOGGER = Logger.getLogger("simpleauthme");
    private LogIn logIn;
    private Help help;
    private Email email;

    public void onEnable() {

        LOGGER.info("simpleauthme-0.2-SNAPSHOT enabled");
        logIn = new LogIn();
        help = new Help();
        email = new Email();
        try {
            logIn.loadDataFromFile();
            email.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getCommand("authme").setExecutor(help);
        getCommand("register").setExecutor(logIn);
        getCommand("login").setExecutor(logIn);
        getCommand("addemail").setExecutor(email);

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(logIn, this);
        getLogger().info("simpleauthme-0.2-SNAPSHOT Enabled");
    }

    public void onDisable() {
        LOGGER.info("simpleauthme-0.2-SNAPSHOT disabled");
    }

    public static JavaPlugin getInstance() {
        return null;
    }

    @EventHandler
    public void onPerm(Player player) {
        String message = "You don't have permission to use this command";

        
    }
}
