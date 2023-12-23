package com.simpleauth.CommandHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import com.simpleauth.Plugin;

public class Email implements CommandExecutor {
    private HashMap<String, String> playerData = new HashMap<>();
    private final String dataFileName = "gmail.txt";
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

    public void saveData() throws IOException {
        File dataFolder = new File("plugins/SimpleAuthConfig/");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File(dataFolder, dataFileName);

        FileWriter writer = new FileWriter(dataFile);
        for (String playerName : playerData.keySet()) {
            writer.write (playerData.get(email))
        }
    }

    public void loadData() throws IOException {
        
    }
}
