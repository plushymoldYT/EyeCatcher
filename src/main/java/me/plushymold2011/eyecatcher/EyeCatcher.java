package me.plushymold2011.eyecatcher;

import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.plushymold2011.eyecatcher.Commands.ReloadCommand;
import me.plushymold2011.eyecatcher.Commands.guiCommand;
import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import me.plushymold2011.eyecatcher.Listeners.AdminLoginListener;
import me.plushymold2011.eyecatcher.Listeners.BukkitCommandListener;
import me.plushymold2011.eyecatcher.Listeners.EssentialsCommandListener;
import me.plushymold2011.eyecatcher.Listeners.JoinListener;

public class EyeCatcher extends JavaPlugin {

    private FileConfiguration config;
    private static FileConfiguration banConfig;

    public static EyeCatcher getPlugin() {
        return (EyeCatcher) Bukkit.getPluginManager().getPlugin("EyeCatcher");
    }

    @Override
    public void onEnable() {
        // Load configurations
        config = loadConfig("config.yml");
        banConfig = loadConfig("ban-config.yml");

        // Register listeners
        loadListeners();

        // Register commands
        loadCommands();

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

    private void loadListeners() {
        // Register your listeners here
        getServer().getPluginManager().registerEvents(new EssentialsCommandListener(), this);
        getServer().getPluginManager().registerEvents((Listener) new AdminLoginListener(), this);
        getServer().getPluginManager().registerEvents((Listener) new BukkitCommandListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    private void loadCommands() {
        // Register commands
        guiCommand guiCmd = new guiCommand(this);
        getCommand("ban-gui").setExecutor(guiCmd);
        getServer().getPluginManager().registerEvents(new BanMenuHandeler(), this);

        ReloadCommand reloadCmd = new ReloadCommand(this);
        getCommand("reload-config").setExecutor(reloadCmd);
    }

    public void reloadBanConfig() {
        banConfig = loadConfig("ban-config.yml");
    }
    public static FileConfiguration getBanConfig() {
        return banConfig;
    }
}
