package me.plushymold2011.eyecatcher.Handlers;

import me.plushymold2011.eyecatcher.EyeCatcher;
import me.plushymold2011.eyecatcher.GUI.BanMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BanMenuHandler implements Listener {

    private final EyeCatcher plugin;
    private final BanMenu banMenu;

    public BanMenuHandler(EyeCatcher plugin) {
        this.plugin = plugin;
        this.banMenu = new BanMenu(plugin);
    }

    public Player getPlayerByName(String playerName) {
        // Search online players first
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return; // Check if clicked inventory is not null

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        // Check if the clicked item is not null and is a player head
        if (clickedItem != null && clickedItem.getType() == Material.PLAYER_HEAD) {
            event.setCancelled(true); // Cancel the event to prevent taking/dropping the item
            ItemMeta meta = clickedItem.getItemMeta();
            if (meta != null) {
                String playerName = meta.getDisplayName();
                Player playerClicked = getPlayerByName(playerName);
                // Open a menu for the clicked player
                if (playerClicked != null) {
                    banMenu.openBanMenu(player, playerClicked.getName());
                }
            }
        }
    }
}
