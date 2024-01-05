package com.simpleauth.CommandHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.simpleauth.Plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private final String dataFileName = "players.txt";

    public LogIn() {
        new BukkitRunnable() {
            @Override
            public void run() {
                final long currentTime = System.currentTimeMillis();
                LoginTimestamps.entrySet().removeIf(new Predicate<Map.Entry<String, Long>>() {
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

        if (!player.hasPermission("authme.use")) {
            player.sendMessage("You don't have permission to use this commands");
            return false;
        }

        if (command.getName().equalsIgnoreCase("register")) {
            String password = args[0];

            if (playerData.containsKey(playerName)) {
                player.sendMessage("§aYour account has been registered!");
                return true;
            }

            if (password.length() < 6) {
                player.sendMessage(ChatColor.RED + "Your password is too short. Please try again at least 6 characters!");
                return false;
            }

            playerData.put(playerName, password);

            try {
                saveDataToFile();
                player.sendMessage("§aRegister Successfully");
                Plugin.LOGGER.info(playerName + " is registered!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Login Commands
        } else if (command.getName().equalsIgnoreCase("login")) {
            String password = args[0];

            if (!playerData.containsKey(playerName)) {
                player.sendMessage("§cYou need to register before login");
                return true;
            }

            if (!playerData.get(playerName).equals(password)) {
                player.sendMessage("§4Incorrect password");
                return true;
            }

            loggedInPlayers.add(playerName);
            LoginTimestamps.remove(playerName);
            player.sendMessage("§aLogin Successfully");
        }

        if (command.getName().equalsIgnoreCase("authme")) {
            if (args[0].equalsIgnoreCase("help")) {
                player.sendMessage(ChatColor.GOLD + "/authme help to show all commands");
                player.sendMessage(ChatColor.GOLD + "/register <password> to register your account");
                player.sendMessage(ChatColor.GOLD + "/login <password> to login into server");
                player.sendMessage(ChatColor.GOLD + "/addemail <email> to add email");
                return true;
            }
        }

        return false;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        if (!loggedInPlayers.contains(playerName)) {
            event.setCancelled(true);
            LoginTimestamps.put(playerName, System.currentTimeMillis());
            player.sendMessage("§cYou must login");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        loggedInPlayers.remove(playerName);
    }

    public void saveDataToFile() throws IOException {
        File dataFolder = new File("plugins/simpleauthme/");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File dataFile = new File(dataFolder, dataFileName);

        FileWriter writer = new FileWriter(dataFile);
        for (String playerName : playerData.keySet()) {
            writer.write(playerName + ":" + playerData.get(playerName) + "\n");
        }
        writer.close();
    }

    public void loadDataFromFile() throws IOException {
        File dataFolder = new File("plugins/simpleauthme/");
        File dataFile = new File(dataFolder, dataFileName);
        if (!dataFile.exists() || !dataFile.isFile()) {
            dataFile.createNewFile();

        } else {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String playerName = parts[0];
                String hashedPassword = parts[1];
                if (parts.length == 2) {
                    playerData.put(playerName, hashedPassword);
                }
            }
            reader.close();
        }
    }
}
