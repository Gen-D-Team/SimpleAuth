package com.simpleauth.CommandHandler;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.simpleauth.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;

public class LogIn implements CommandExecutor, Listener {
    private HashMap<String, String> playerData = new HashMap<>();
    private HashSet<String> loggedInPlayers = new HashSet<>();
    private HashMap<String, Long> LoginTimestamps = new HashMap<>();

    public LogIn(){
        new BukkitRunnable() {
            @Override
            public void run() {
                final long currentTime = System.currentTimeMillis();
                LoginTimestamps.entrySet().removeIf(new Predicate<Map.Entry<String, Long>>(){
                    @Override
                    public boolean test(Map.Entry<String, Long> entry) {
                        if (currentTime - entry.getValue() > 60000) {
                            Player player = Bukkit.getPlayer(entry.getKey());
                            if (player != null) {
                                player.kickPlayer("Your time is out");
                            }
                            return true;
                        }
                        return false;
                    }
                });
            }
        }.runTaskTimer((Plugin) Bukkit.getPluginManager().getPlugin("simpleauthme"), 0L, 20L);
    }

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
            Plugin.LOGGER.info(playerName + " Registered");
        }
    }

    public void HandleLogin(Player player, String playerName, String password) {
        if (!playerData.containsKey(playerName)) {
            player.sendMessage("§cYou need to register before login");
        } else if (!playerData.get(playerName).equals(password)) {
            player.sendMessage("§4Incorrect Password");
        } else {
            loggedInPlayers.add(playerName);
            LoginTimestamps.remove(playerName);
            player.sendMessage("§aLogin Successfully");
            Plugin.LOGGER.info(playerName + " logged in");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        if (!loggedInPlayers.contains(playerName)) {
            event.setCancelled(true);
            LoginTimestamps.put(playerName, System.currentTimeMillis());
            player.sendMessage("§cYou must login before move");
        }
    }

    private void saveDataToFile() {
        File dataFile = new File("D:/Dev4future/MinecraftPluginWorkSpace/SimpleAuth/src/main/java/com/simpleauth/data/database/players.player");
        try (FileWriter writer = new FileWriter(dataFile)) {
            for (Map.Entry<String, String> entry : playerData.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {
        File dataFile = new File("D:/Dev4future/MinecraftPluginWorkSpace/SimpleAuth/src/main/java/com/simpleauth/data/database/players.player");
        if (dataFile.exists()){
            
        }
    }
}
