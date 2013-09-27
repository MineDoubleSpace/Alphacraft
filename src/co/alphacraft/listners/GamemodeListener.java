package co.alphacraft.listners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import co.alphacraft.main.Plugin;

public class GamemodeListener implements Listener {
	
	public Plugin plugin;

	public GamemodeListener(Plugin instance) {
		plugin = instance;
	}

	@EventHandler()
	public void DropItem(PlayerDropItemEvent event) {
		if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
			if (!event.getPlayer().hasPermission("alpha.creative.bypass")) {
				event.getPlayer().sendMessage(
						ChatColor.RED + "You may not drop items in creative!");
				event.setCancelled(true);
				return;
			}

		}
	}

	@EventHandler()
	public void onPlayerGameModeEvent(PlayerGameModeChangeEvent event) {
		Player player = event.getPlayer();
			if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
				if (!event.getPlayer().hasPermission("alpha.creative.bypass")) {
					event.getPlayer().getInventory().clear();
					player.getInventory().clear();
					player.getInventory().setHelmet(null);
					player.getInventory().setChestplate(null);
					player.getInventory().setLeggings(null);
					player.getInventory().setBoots(null);
					return;
			}

		}
	}


	
	@EventHandler
	public void inventoryEvent(InventoryClickEvent event){
	    if (event.getWhoClicked().getGameMode() == GameMode.CREATIVE) {
	        if(event.getInventory().getType() != InventoryType.CREATIVE && event.getInventory().getType() != InventoryType.PLAYER){
	            if (!event.getWhoClicked().hasPermission("alpha.creative.bypass")) {
	                 event.setCancelled(true);
	            }
	        }
	    }
	}
}
