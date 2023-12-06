package com.simpleauth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.regex.*;

public class CommandHanlder implements CommandExecutor{

    public boolean getEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.find();

        if (matchFound) {return true;}
        else {return false;}
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player player = (Player)sender;
        String email = args[0];
        String password = args[1];
        
        if (command.getName().equalsIgnoreCase("register") && sender instanceof Player){
            player.sendMessage("§aTrying To Register Account...");
            Plugin.LOGGER.info("registerring player " + player.getName() + "with password " + password);
            return true;
        }

        // add email to restore account if users have stolen the password
        if (command.getName().equalsIgnoreCase("addemail") && sender instanceof Player) {
                
                if (email != null) {
                    player.sendMessage("§aSuccessfully.");
                    player.sendMessage("Your email had been added!");
                    Plugin.LOGGER.info("Email player " + player.getName() + " has been added.");
                    return true;
                } else {
                    player.sendMessage("§cInvalid Input!");
                    player.sendMessage("§cPlease try again ...");
                    return false;
                }
    
            }
        return false;
    }
}
