package com.simpleauth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.HashSet;

public class LogIn implements CommandExecutor, Listener {
    private HashMap<String, String> playerData = new HashMap<>();
    private HashSet<String> loggedInPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        String playerName = player.getName();
        String password = args[0];

        if (command.getName().equalsIgnoreCase("register")) {
            HandleRegister(player, playerName, password);
            return true;
        } else if (command.getName().equalsIgnoreCase("login")) {
            HandleLogin(player, playerName, password);
            return true;
        }
        return false;
    }

    public void HandleRegister(Player player, String playerName, String password) {
        if (playerData.containsKey(playerName)) {
            player.sendMessage("§aYou have been registered");
        } else {
            playerData.put(playerName, password);
            player.sendMessage("§aRegistering, just a few seconds..");
            Plugin.LOGGER.info(playerName + "Registered");
        }
    }

    public void HandleLogin(Player player, String playerName, String password) {
        if (!playerData.containsKey(playerName)) {
            player.sendMessage("§cYou need to register before login");
        } else if (!playerData.get(playerName).equals(password)) {
            player.sendMessage("§4Incorrect Password");
        } else {
            loggedInPlayers.add(playerName);
            player.sendMessage("§aLogin Successfully");
            Plugin.LOGGER.info(playerName + "logged in");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (!loggedInPlayers.contains(playerName)) {
            event.setCancelled(true);
            player.sendMessage("§cYou must login before move");
        }
    }
}
