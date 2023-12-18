package com.simpleauth.CommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Help implements CommandExecutor ,Listener{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;

        if(command.getName().equalsIgnoreCase("help authme")) {
            player.sendMessage("/help authme to show available commands");
            player.sendMessage("/register <password> to register");
            player.sendMessage("/login <password> to login");
            return true;
        }
        return false;
    }
}
