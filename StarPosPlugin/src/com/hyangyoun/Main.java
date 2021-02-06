package com.hyangyoun;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    Logger logger = getServer().getLogger();

    @Override
    public void onEnable() {
        logger.info("SPP 플러그인 활성화");
        Bukkit.getPluginManager().registerEvents(new EventManager(this),this);
    }

    @Override
    public void onDisable() {
        logger.info("SPP 플러그인 비활성화");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;

        return false;
    }
}
