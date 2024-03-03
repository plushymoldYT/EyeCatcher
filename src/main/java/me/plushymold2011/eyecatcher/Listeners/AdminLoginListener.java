package me.plushymold2011.eyecatcher.Listeners;

import me.plushymold2011.eyecatcher.EyeCatcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class AdminLoginListener implements Listener {

    private final EyeCatcher plugin;

    public AdminLoginListener(EyeCatcher plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        // Retrieve configuration value for staff login notifications
        boolean staffLoginNotificationsEnabled = this.plugin.getConfig().getBoolean("staff-login-notifications-enabled");
        String staffLoginNotification = this.plugin.getConfig().getString("staff-login-notification-message");

        // Check if staff login notifications are enabled in the configuration
        if (staffLoginNotificationsEnabled) {
            // Get the player who logged in
            Player player = event.getPlayer();

            // Check if the player has the permission
            if (player.hasPermission("EyeCatcher.notification.login")) {
                // Replace placeholders in the notification message
                String notificationMessage = staffLoginNotification.replace("%event-player%", player.getDisplayName());

                // Send the notification message to players with the permission
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', notificationMessage));
            }
        }
    }
}
