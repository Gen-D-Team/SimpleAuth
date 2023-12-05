package com.simpleauth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHanlder implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equalsIgnoreCase("register") && sender instanceof Player){
            Player player = (Player)sender;
            String password = args[0];
            player.sendMessage("§aTrying To Register Account...");
            Plugin.LOGGER.info("registerring player " + player.getName() + "with password " + password);
            return true;
        }
        return false;
    }
}
