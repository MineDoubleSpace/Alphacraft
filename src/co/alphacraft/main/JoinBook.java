package co.alphacraft.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import co.alphacraft.coins.CoinsManager;
import co.alphacraft.manager.SettingsManager;

public class JoinBook implements Listener{
	
	public Plugin plugin;

	public JoinBook(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();
	
	@EventHandler
	public void onPlayerJoinBook(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if (s.getPData().getConfigurationSection(player.getName()) != null) {
			return;
		}
	}
	
	public void giveJoinBook(){
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Welcome to AlphaCraft");
		meta.setAuthor(ChatColor.GOLD + "AlphaCraft");
		
		meta.addPage(ChatColor.BLUE + "" + ChatColor.BOLD + "   Welcome to AlphaCraft\n" + " \n" +
					ChatColor.BLACK + "This book contains "
				);
		
		 book.setItemMeta(meta);
		
	}
	

}
