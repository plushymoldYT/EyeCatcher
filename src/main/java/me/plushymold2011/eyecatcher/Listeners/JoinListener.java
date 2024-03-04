package me.plushymold2011.eyecatcher.Listeners;

import me.plushymold2011.eyecatcher.EyeCatcher;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final EyeCatcher plugin;

    public JoinListener( EyeCatcher plugin ) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        // Check if the join-message is enabled
        boolean joinMessageEnabled = this.plugin.getConfig().getBoolean("join-message-enabled");

        if (joinMessageEnabled) {
            String joinMessage = this.plugin.getConfig().getString("join-message");
            if (joinMessage != null) {
                joinMessage = joinMessage.replace("%player%", e.getPlayer().getDisplayName());
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
            }

        }
    }

}
