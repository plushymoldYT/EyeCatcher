package me.plushymold2011.eyecatcher.Handlers;

import me.plushymold2011.eyecatcher.GUI.BanMenu;
import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class BanMenuHandeler implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return; // Check if clicked inventory is not null

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        // Check if the clicked item is not null and is a player head
        if (clickedItem != null && clickedItem.getType() == Material.PLAYER_HEAD) {
            event.setCancelled(true); // Cancel the event to prevent taking/dropping the item
            String playerName = clickedItem.getItemMeta().getDisplayName();
            // Open a menu for the clicked player
            BanMenu.openBanMenu(player, playerName);
        }
    }
}
