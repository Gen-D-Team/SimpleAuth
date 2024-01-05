package com.simpleauth;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.simpleauth.CommandHandler.Email;
import com.simpleauth.CommandHandler.LogIn;

public class Plugin extends JavaPlugin {
    public static Logger LOGGER = Logger.getLogger("simpleauthme");
    private LogIn logIn;
    private Email email;

    public void onEnable() {

        LOGGER.info("simpleauthme-0.2-SNAPSHOT enabled");
        
        saveDefaultConfig();

        logIn = new LogIn();
        email = new Email();
        try {
            logIn.loadDataFromFile();
            email.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getCommand("authme").setExecutor(logIn);
        getCommand("register").setExecutor(logIn);
        getCommand("login").setExecutor(logIn);
        getCommand("addemail").setExecutor(email);
        getServer().getPluginManager().registerEvents(logIn, this);
        getLogger().info("simpleauthme-0.2-SNAPSHOT Enabled");
    }

    public void onDisable() {
        LOGGER.info("simpleauthme-0.2-SNAPSHOT disabled");
    }

    public static JavaPlugin getInstance() {
        return null;
    }
}
