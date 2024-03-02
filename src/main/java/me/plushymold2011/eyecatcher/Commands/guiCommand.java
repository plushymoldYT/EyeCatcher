package me.plushymold2011.eyecatcher.Commands;

import me.plushymold2011.eyecatcher.EyeCatcher;
import me.plushymold2011.eyecatcher.Handlers.BanMenuHandeler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class guiCommand implements CommandExecutor {
    private static final int ITEMS_PER_PAGE = 9;

    private final EyeCatcher plugin;

    public guiCommand(EyeCatcher plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {


        if (sender instanceof Player) {
            Player p = (Player) sender;

            // Register the inventory click event listener
            Bukkit.getPluginManager().registerEvents(new BanMenuHandeler(), this.plugin);

            // Calculate number of pages needed based on online players count
            int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
            int totalPages = (int) Math.ceil((double) onlinePlayersCount / ITEMS_PER_PAGE);

            // Check if the current page is within bounds
            int currentPage = 0;

            // Create GUI
            Inventory gui = Bukkit.createInventory(p, getInventorySize(onlinePlayersCount), "EyeCatcher Ban Menu - Page " + (currentPage + 1));

            // Populate GUI with player heads
            int startIndex = currentPage * ITEMS_PER_PAGE;
            int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, onlinePlayersCount);

            int index = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (index >= startIndex && index < endIndex) {
                    ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
                    skullMeta.setOwningPlayer(player);
                    skullMeta.setDisplayName(player.getName());
                    item.setItemMeta(skullMeta);
                    gui.addItem(item);
                }
                index++;
            }

            // Add next page button
            if (totalPages > 1 && currentPage < totalPages - 1) {
                ItemStack nextPageButton = new ItemStack(Material.ARROW);
                ItemMeta nextPageMeta = nextPageButton.getItemMeta();
                nextPageMeta.setDisplayName("Next Page");
                nextPageButton.setItemMeta(nextPageMeta);
                gui.setItem(gui.getSize() - 1, nextPageButton);
            }

            p.openInventory(gui);
        }
        return true;
    }

    // Calculate inventory size based on number of players
    private int getInventorySize(int playerCount) {
        int size = (int) Math.ceil((double) playerCount / 9) * 9;
        return Math.min(size, 54); // Limit size to maximum of 54 slots
    }
}
