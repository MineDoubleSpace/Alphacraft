package co.alphacraft.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.coins.CoinsManager;
import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class GodCommand implements CommandExecutor {

	public Plugin plugin;

	public GodCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();

	public static ArrayList<String> god = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("god")) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					if (sender.hasPermission("aplha.god")) {
						Player player = (Player) sender;
						if (!god.contains(player.getName())) {
							god.add(player.getName());
							player.sendMessage(ChatColor.GOLD
									+ "God mode enabled!");
							return true;
						} else {
							god.remove(player.getName());
							player.sendMessage(ChatColor.GOLD
									+ "God mode disabled!");
							return true;
						}
					}
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;

				} else if (args.length > 0) {
					if (!sender.hasPermission("aplha.god.other")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "You don't have permissions to do this!");
						return true;
					}
					Player player = (Player) sender;
					final Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED + "Player not found!");
					}
					if (!god.contains(target.getName())) {
						god.add(target.getName());
						target.sendMessage(ChatColor.GOLD + "God mode enabled!");
						player.sendMessage(ChatColor.GOLD
								+ "God mode enabled to " + ChatColor.AQUA
								+ target.getName() + ChatColor.GOLD + "!");
						return true;
					} else {
						god.remove(target.getName());
						target.sendMessage(ChatColor.GOLD
								+ "God mode disabled!");
						player.sendMessage(ChatColor.GOLD
								+ "God mode disabled to " + ChatColor.AQUA
								+ target.getName() + ChatColor.GOLD + "!");
						return true;
					}

				}
			}
		}
		return true;
	}
	
	public static boolean isGod(Player p){
		if (god.contains(p.getName())){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setGod(Player p){
		god.add(p.getName());
	}
	
	
	public static void removeGod(Player p){
		try{
		god.remove(p.getName());
		} catch (Exception e) {}
	}
	
	
	
}