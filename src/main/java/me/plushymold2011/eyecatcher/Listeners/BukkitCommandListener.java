package me.plushymold2011.eyecatcher.Listeners;

import me.plushymold2011.eyecatcher.EyeCatcher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BukkitCommandListener implements Listener {

    private final EyeCatcher plugin;

    public BukkitCommandListener(EyeCatcher plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        boolean commandSnooperStatus = this.plugin.getConfig().getBoolean("commandSnooper-enabled");
        if (commandSnooperStatus) {
            String message = event.getMessage().toLowerCase(); // Convert the command to lowercase
            if (message.startsWith("/") || message.startsWith("//")) {
                Player player = event.getPlayer();
                if (player.hasPermission("EyeCatcher.admin.CommandSnooper")) {
                    // Get the command
                    String command = message.split(" ")[0].substring(1); // Remove the leading "/"
                    // Notify online players with permission
                    for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                        if (onlinePlayer.hasPermission("EyeCatcher.admin.CommandSnooper")) {
                            onlinePlayer.sendMessage("§7[§e" + player.getName() + "§7 is using /" + command + "§7]");
                        }
                    }
                }
            }
        }
    }
}
