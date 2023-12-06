package com.simpleauth;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * simpleauthme java plugin
 */
public class Plugin extends JavaPlugin{
  public static Logger LOGGER=Logger.getLogger("simpleauthme");
  private CommandHanlder commandHanlder;
  private Email email;
  public void onEnable()
  {
    LOGGER.info("simpleauthme enabled");
    commandHanlder = new CommandHanlder();
    email = new Email();
    getCommand("register").setExecutor(commandHanlder);
    getCommand("addemail").setExecutor(email);
  }

  public void onDisable()
  {
    LOGGER.info("simpleauthme disabled");
  }
}
