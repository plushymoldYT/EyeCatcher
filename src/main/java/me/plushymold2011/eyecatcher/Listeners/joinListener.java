package me.plushymold2011.eyecatcher.Listeners;

import me.plushymold2011.eyecatcher.EyeCatcher;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener {

    private final EyeCatcher plugin;

    public joinListener(EyeCatcher plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        //Grab Config File

        //Check if the join-message is enabled
        String JoinMessageStatus = this.plugin.getConfig().getString("join-message-enabled");

        if (JoinMessageStatus.equals("true")){
            String JoinMessage = this.plugin.getConfig().getString("join-message");
            if (JoinMessage != null){
                JoinMessage = JoinMessage.replace("%player", e.getPlayer().getDisplayName());
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage));
            }
        }
    }

}

