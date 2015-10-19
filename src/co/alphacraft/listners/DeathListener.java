package co.alphacraft.listners;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import co.alphacraft.main.Plugin;

public class DeathListener implements Listener {

	public static Plugin plugin;

	public DeathListener(Plugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getDeathMessage().contains("hit the ground")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " apparently can't fly. ");
		} else if (event.getDeathMessage().contains("fell from a high place")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " believed he could fly. ");
		} else if (event.getDeathMessage().contains("tried to swim in lava")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " really loves lava. ");
		} else if (event.getDeathMessage().contains("zombie")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED
					+ " learnt dating zombies isn't a good idea");
		} else if (event.getDeathMessage().contains("blown up")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " Hugged a creeper");
		} else if (event.getDeathMessage().contains("blew")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " please keep out from kids!");
		} else if (event.getDeathMessage().contains("died")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " gave up on life!");
		} else if (event.getDeathMessage().contains("flames")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " obviously isn't fireproof ");
		} else if (event.getDeathMessage().contains("burned")) {
			event.setDeathMessage(ChatColor.GOLD + event.getEntity().getName()
					+ ChatColor.RED + " obviously isn't fireproof ");
		} else {
			String d = event.getDeathMessage();
			event.setDeathMessage(ChatColor.RED + d);
		}
	}
	
	

}
