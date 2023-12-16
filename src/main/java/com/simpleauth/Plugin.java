package com.simpleauth;

import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import com.simpleauth.CommandHandler.LogIn;

/*
 * simpleauthme java plugin
 */
public class Plugin extends JavaPlugin {
  public static Logger LOGGER = Logger.getLogger("simpleauthme");
  private LogIn logIn;

  public void onEnable() {
    LOGGER.info("simpleauthme enabled");
    logIn = new LogIn();
    getCommand("register").setExecutor(logIn);
    getCommand("login").setExecutor(logIn);
    getCommand("addemail").setExecutor(logIn);
    try {
      logIn.loadDataFromFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    getServer().getPluginManager().registerEvents(logIn, this);
    getLogger().info("SimpleAuth-0.2-SNAPSHOT Enabled");
  }

  public void onDisable() {
    LOGGER.info("simpleauthme disabled");
  }

  public static JavaPlugin getInstance() {
    return null;
  }
}
