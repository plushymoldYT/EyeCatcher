package me.plushymold2011.eyecatcher;

import me.plushymold2011.eyecatcher.Commands.guiCommand;
import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import me.plushymold2011.eyecatcher.Listeners.essentialsCommandListener;
import me.plushymold2011.eyecatcher.Listeners.joinListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EyeCatcher extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.AQUA + "EyeCatcher Has Loaded Successfully");

        // Register Listeners
        getServer().getPluginManager().registerEvents(new essentialsCommandListener(), this);

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new joinListener(this),this);

        // Register commands
        guiCommand guiCmd = new guiCommand(this); // Pass the plugin instance to guiCommand
        getCommand("ban-gui").setExecutor((CommandExecutor) guiCmd);
        getServer().getPluginManager().registerEvents(new BanMenuHandeler(), this); // Fix typo here
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(ChatColor.AQUA + "EyeCatcher Has Disabled Successfully");

    }
}
