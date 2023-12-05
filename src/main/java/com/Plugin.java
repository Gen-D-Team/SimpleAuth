package com;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * simpleauth java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("simpleauth");

  public void onEnable()
  {
    LOGGER.info("simpleauth enabled");
  }

  public void onDisable()
  {
    LOGGER.info("simpleauth disabled");
  }
}
