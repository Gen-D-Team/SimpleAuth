package com.simpleauth.CommandHandler;

import java.util.regex.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import com.simpleauth.Plugin;

public class Email implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player)sender;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String email = args[0];

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean matcherFound = matcher.find();
        
        if (command.getName().equalsIgnoreCase("addemail") && sender instanceof Player) {
            
            if (matcherFound) {
                player.sendMessage("Successfully adding Email.");

                Plugin.LOGGER.info("Email " + email + " successfully added");
                return true;
            }
            else {
                player.sendMessage("Invalid email. Please try again.");
                Plugin.LOGGER.info("Add email failed.");
                return false;
            }
        }
        return false;
    }
}
