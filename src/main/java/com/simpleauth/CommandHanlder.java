package com.simpleauth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHanlder implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player player = (Player)sender;
        String email = args[0];
        String password = args[1];
        String[] errorMessage = {"§cInvalid Input!", "§cPlease try again ..."};
        String[] successfullyMessage = {"§aSuccessfully.", "Your email had been added!"};
        if (command.getName().equalsIgnoreCase("register") && sender instanceof Player){
            player.sendMessage("§aTrying To Register Account...");
            Plugin.LOGGER.info("registerring player " + player.getName() + "with password " + password);
            return true;
        }

        if (command.getName().equalsIgnoreCase("addemail") && sender instanceof Player) {
                
    
                if (password == null || email == null) {
                    player.sendMessage(errorMessage);
                    return false;
                } else {
                    player.sendMessage(successfullyMessage);
                    Plugin.LOGGER.info("Email player " + player.getName() + " has been added.");
                    return true;
                }
    
            }
        return false;
    }
}
