package com.simpleauth.CommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class Help implements CommandExecutor ,Listener{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
        // Check if object is a player or not
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;

        if(command.getName().equalsIgnoreCase("authme")) {
            player.sendMessage(ChatColor.GOLD + "/authme to show available commands");
            player.sendMessage(ChatColor.GOLD + "/register <password> to register");
            player.sendMessage(ChatColor.GOLD + "/login <password> to login");
            return true;
        }
        return false;
    }
}
