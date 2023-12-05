package com.simpleauth;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * simpleauthme java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("simpleauthme");

  public void onEnable()
  {
    LOGGER.info("simpleauthme enabled");
  }

  public void onDisable()
  {
    LOGGER.info("simpleauthme disabled");
  }
}
