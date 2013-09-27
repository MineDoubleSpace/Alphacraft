package co.alphacraft.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.main.Plugin;

public class AfkCommand implements CommandExecutor {
	public Plugin plugin;

	public AfkCommand(Plugin instance) {
		plugin = instance;
	}

	public static ArrayList<String> afk = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("afk")) {
			Player player = (Player) sender;
			if (!afk.contains(player.getName())) {
				afk.add(player.getName());
				Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
						+ ChatColor.GOLD + " is now AFK!");
			} else {
				afk.remove(player.getName());
				Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
						+ ChatColor.GOLD + " is no longer afk!");
			}
		}
		return false;
	}

}
