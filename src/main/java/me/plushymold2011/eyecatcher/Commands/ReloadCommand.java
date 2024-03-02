package me.plushymold2011.eyecatcher.Commands;

import me.plushymold2011.eyecatcher.EyeCatcher;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final EyeCatcher plugin;

    public ReloadCommand(EyeCatcher plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // Reload main configuration
            plugin.reloadConfig();
            // Reload ban configuration
            plugin.reloadBanConfig();

            sender.sendMessage(ChatColor.GREEN + "Successfully reloaded EyeCatcher configurations!");
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /reload-config");
        }
        return true;
    }
}
