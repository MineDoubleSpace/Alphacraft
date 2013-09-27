package co.alphacraft.listners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import co.alphacraft.command.AfkCommand;
import co.alphacraft.main.Plugin;

public class AfkListener implements Listener {

	public Plugin plugin;

	public AfkListener(Plugin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onAfkMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (AfkCommand.afk.contains(player.getName())) {
			AfkCommand.afk.remove(player.getName());
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
					+ ChatColor.GOLD + " is no longer afk!");
		}
	}

	@EventHandler
	public void onAfkChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (AfkCommand.afk.contains(player.getName())) {
			AfkCommand.afk.remove(player.getName());
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
					+ ChatColor.GOLD + " is no longer afk!");
		}
	}

	@EventHandler
	public void onAfkInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (AfkCommand.afk.contains(player.getName())) {
			AfkCommand.afk.remove(player.getName());
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
					+ ChatColor.GOLD + " is no longer afk!");
		}
	}

	@EventHandler
	public void onAfkMove(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (AfkCommand.afk.contains(player.getName())) {
			AfkCommand.afk.remove(player.getName());
		}
	}

}
