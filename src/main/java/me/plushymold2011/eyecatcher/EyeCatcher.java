package me.plushymold2011.eyecatcher;

import me.plushymold2011.eyecatcher.Handlers.BanMenuHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.plushymold2011.eyecatcher.Commands.ReloadCommand;
import me.plushymold2011.eyecatcher.Commands.guiCommand;
import me.plushymold2011.eyecatcher.Listeners.AdminLoginListener;
import me.plushymold2011.eyecatcher.Listeners.BukkitCommandListener;
import me.plushymold2011.eyecatcher.Listeners.EssentialsCommandListener;
import me.plushymold2011.eyecatcher.Listeners.JoinListener;

public class EyeCatcher extends JavaPlugin {
    private FileConfiguration config;
    private static FileConfiguration banConfig;

    // Declare JoinListener field
    private JoinListener joinListener;
    private AdminLoginListener adminLoginListener;

    public static EyeCatcher getPlugin() {
        return (EyeCatcher) Bukkit.getPluginManager().getPlugin("EyeCatcher");
    }

    @Override
    public void onEnable() {
        // Load configurations
        config = loadConfig("config.yml");

        // Register listeners
        getServer().getPluginManager().registerEvents(new EssentialsCommandListener(), this);

        // Initialize JoinListener only once
        joinListener = new JoinListener(this);
        getServer().getPluginManager().registerEvents(joinListener, this);

        adminLoginListener = new AdminLoginListener(this);
        getServer().getPluginManager().registerEvents(adminLoginListener, this);

        // Register commands
        guiCommand guiCmd = new guiCommand(this);
        getCommand("ban-gui").setExecutor(guiCmd);
        getServer().getPluginManager().registerEvents(new BanMenuHandler(this), this); // Pass EyeCatcher instance here

        ReloadCommand reloadCmd = new ReloadCommand(this);
        getCommand("reload-config").setExecutor(reloadCmd);

        getLogger().info("EyeCatcher has been enabled successfully!");
    }


    @Override
    public void onDisable() {
        getLogger().info("EyeCatcher has been disabled.");
    }

    private FileConfiguration loadConfig(String fileName) {
        saveDefaultConfig();
        return getConfig();
    }
}
