package com.simpleauth;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * simpleauthme java plugin
 */
public class Plugin extends JavaPlugin{
  public static Logger LOGGER=Logger.getLogger("simpleauthme");
  private CommandHanlder commandHanlder;
  public void onEnable()
  {
    LOGGER.info("simpleauthme enabled");
    commandHanlder = new CommandHanlder();
    getCommand("register").setExecutor(commandHanlder);
  }

  public void onDisable()
  {
    LOGGER.info("simpleauthme disabled");
  }
}
