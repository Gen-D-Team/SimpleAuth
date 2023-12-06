package com.simpleauth;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import com.simpleauth.CommandHandler.LogIn;

/*
 * simpleauthme java plugin
 */
public class Plugin extends JavaPlugin{
  public static Logger LOGGER=Logger.getLogger("simpleauthme");
  private LogIn commandHanlder;
  public void onEnable()
  {
    LOGGER.info("simpleauthme enabled");
    commandHanlder = new LogIn();
    getCommand("register").setExecutor(commandHanlder);
    getCommand("login").setExecutor(commandHanlder);
    getCommand("addemail").setExecutor(commandHanlder);
    getServer().getPluginManager().registerEvents(commandHanlder, this);
    getLogger().info("SimpleAuth-0.1 Enabled");
  }

  public void onDisable()
  {
    LOGGER.info("simpleauthme disabled");
  }
}
