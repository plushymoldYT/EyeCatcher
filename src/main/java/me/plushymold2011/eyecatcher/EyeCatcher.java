package me.plushymold2011.eyecatcher;

import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import me.plushymold2011.eyecatcher.Commands.ReloadCommand;
import me.plushymold2011.eyecatcher.Commands.guiCommand;
import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import me.plushymold2011.eyecatcher.Listeners.BukkitCommandListener;
import me.plushymold2011.eyecatcher.Listeners.EssentialsCommandListener;
import me.plushymold2011.eyecatcher.Listeners.JoinListener;

public class EyeCatcher extends JavaPlugin {
    private FileConfiguration config;
    private static FileConfiguration banConfig;

    // Declare JoinListener, BukkitCommandListener, and EssentialsCommandListener fields
    private JoinListener joinListener;
    private BukkitCommandListener bukkitCommandListener;
    private EssentialsCommandListener essentialsCommandListener;

    public static EyeCatcher getPlugin() {
        return (EyeCatcher) Bukkit.getPluginManager().getPlugin("EyeCatcher");
    }

    @Override
    public void onEnable() {
        // Load configurations
        config = loadConfig("config.yml");
        banConfig = loadConfig("ban-config.yml");

        // Register listeners
        essentialsCommandListener = new EssentialsCommandListener(this);
        getServer().getPluginManager().registerEvents(essentialsCommandListener, this);

        bukkitCommandListener = new BukkitCommandListener(this);
        getServer().getPluginManager().registerEvents(bukkitCommandListener, this);

        // Initialize JoinListener only once
        joinListener = new JoinListener(this);
        getServer().getPluginManager().registerEvents(joinListener, this);

        // Register commands
        guiCommand guiCmd = new guiCommand(this);
        getCommand("ban-gui").setExecutor(guiCmd);
        getServer().getPluginManager().registerEvents(new BanMenuHandeler(), this);

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

    public void reloadBanConfig() {
        banConfig = loadConfig("ban-config.yml");
    }

    public static FileConfiguration getBanConfig() {
        return banConfig;
    }
}
