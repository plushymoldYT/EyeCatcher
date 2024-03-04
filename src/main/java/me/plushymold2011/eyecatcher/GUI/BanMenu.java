package me.plushymold2011.eyecatcher.GUI;

import me.plushymold2011.eyecatcher.EyeCatcher;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BanMenu {

    private final EyeCatcher plugin;

    public BanMenu(EyeCatcher plugin) {
        this.plugin = plugin;
    }

    public void openBanMenu(Player player, String playerName) {
        Inventory banMenu = Bukkit.createInventory(player, 9, "Ban Menu for " + playerName);

        if (plugin == null) {
            // Plugin instance is null, handle the situation accordingly
            return;
        }

        FileConfiguration banConfig = plugin.getBanConfig();
        List<String> banOptions;
        if (banConfig == null || !banConfig.contains("ban-reasons")) {
            // If the ban configuration is null or does not contain the ban-reasons section, provide default ban reasons
            banOptions = getDefaultBanOptions();
        } else {
            // Get ban reasons from the ban-config.yml file
            banOptions = banConfig.getStringList("ban-reasons");
        }

        // Populate ban menu with ban options
        for (String banReason : banOptions) {
            ItemStack banOptionItem = new ItemStack(Material.PAPER);
            ItemMeta meta = banOptionItem.getItemMeta();
            meta.setDisplayName(banReason);
            banOptionItem.setItemMeta(meta);
            banMenu.addItem(banOptionItem);
        }

        player.openInventory(banMenu);
    }

    // Provide default ban reasons if ban-config.yml or ban-reasons section is not found
    private List<String> getDefaultBanOptions() {
        // You can customize default ban options here
        return List.of("Hack Client");
    }
}
