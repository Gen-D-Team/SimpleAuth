package com.simpleauth;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class freeze extends JavaPlugin implements Listener{
    private boolean isRegistrationsMode = false; // This var will check if user is in registration mode or not

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (isRegistrationsMode && !isPlayerRegistered(event.getPlayer().getName())){
            // If user is in registration mode then cancel user movement
            event.setCancelled(true);
            event.getPlayer().sendMessage("You have to register first!");
        }
    }

    // This method check this if user registered or not
    public boolean isPlayerRegistered(String playername){

        return false;
    }

    // This method use to turn on/off the registration mode
    public boolean setRegistrationsMode(boolen registrationMode){
        this.isRegistrationsMode = registrationMode;
    }
}
