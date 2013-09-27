package co.alphacraft.manager;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import co.alphacraft.main.Plugin;

public class SignManager implements Listener{
	
	public Plugin plugin;

	public SignManager(Plugin instance) {
		plugin = instance;
	}
	SettingsManager s = SettingsManager.getInstance();
	
	@EventHandler()
	public void onPlayerChangeSignEvent(SignChangeEvent event) {
		if (event.getPlayer().hasPermission("aplha.sign.color")) {
			String l0 = event.getLine(0), l1 = event.getLine(1), l2 = event
					.getLine(2), l3 = event.getLine(3);
			event.setLine(0, ChatColor.translateAlternateColorCodes('&', l0));
			event.setLine(1, ChatColor.translateAlternateColorCodes('&', l1));
			event.setLine(2, ChatColor.translateAlternateColorCodes('&', l2));
			event.setLine(3, ChatColor.translateAlternateColorCodes('&', l3));
		}

	}

}
