package me.plushymold2011.eyecatcher.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EssentialsCommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase(); // Convert the command to lowercase
        String[] args = message.split(" ");
        if (args.length >= 2 && args[0].equalsIgnoreCase("/tp")) {
            Player player = event.getPlayer();
            if (player.hasPermission("EyeCatcher.admin.CommandSnooper")) {
                // Get the target player's name
                String targetPlayerName = args[1];
                // Iterate through online players and send notification to those with the permission
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    if (onlinePlayer.hasPermission("EyeCatcher.admin.CommandSnooper")) {
                        onlinePlayer.sendMessage("§7§o[§7§o" + player.getName() + ": §e§ois using /tp to teleport to " + targetPlayerName + "§7§o]");
                    }
                }
            }
        }
    }
}
