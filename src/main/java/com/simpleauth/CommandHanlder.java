package com.simpleauth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHanlder implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        String password = args[0];
        if (command.getName().equalsIgnoreCase("register") && sender instanceof Player){
            Player player = (Player)sender;
            player.sendMessage("§aTrying To Register Account...");
            Plugin.LOGGER.info("registerring player " + player.getName() + " with password " + password);
            return true;
        }
        else if (command.getName().equalsIgnoreCase("login") && sender instanceof Player){
            Player player = (Player)sender;
            String loginpassword = args[0];
            if (loginpassword == password){
                player.sendMessage("§fLogin Successfully!");
                Plugin.LOGGER.info("registerring player " + player.getName() + " with password " + loginpassword);
                return true;
            }
            else if (loginpassword != password){
                player.sendMessage("§4Wrong Password!!");
            }
        }
        return false;
    }
}
