package com.simpleauth.CommandHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import com.simpleauth.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Email implements CommandExecutor {
    private HashMap<String, String> playerData = new HashMap<>();
    private final String dataFileName = "email.txt";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String email = args[0];
        String playerName = player.getName();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean matcherFound = matcher.find();

        if (command.getName().equalsIgnoreCase("addemail") && sender instanceof Player) {

            if (matcherFound) {
                try {
                    if (playerData.containsKey(playerName)) {
                        player.sendMessage(ChatColor.RED + "This email is already in use for this account. Please try another email.");
                    } else {
                        playerData.put(playerName, email);
                        saveData();
                        player.sendMessage(ChatColor.GREEN + "Successfully adding Email.");
                        Plugin.LOGGER.info(ChatColor.GREEN + "Email " + email + " successfully added");
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid email. Please try again.");
                return false;
            }
        }
        return false;
    }

    public void saveData() throws IOException {
        File dataFolder = new File("plugins/simpleauthme/");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File(dataFolder, dataFileName);

        FileWriter writer = new FileWriter(dataFile);
        for (String playerName : playerData.keySet()) {
            writer.write(playerName + ":" + playerData.get(playerName) + "\n");
        }
        writer.close();
    }

    public void loadData() throws IOException {
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
                String email = parts[1];
                if (parts.length == 2) {
                    playerData.put(playerName, email);
                }
            }
            reader.close();
        }
    }
}
