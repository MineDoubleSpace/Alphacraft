package co.alphacraft.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.main.Plugin;

public class GamemodeCommand implements CommandExecutor {
	
	public Plugin plugin;

	public GamemodeCommand(Plugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("gmc")) {
			if (args.length == 0) {
				if (!sender.hasPermission("aplha.gamemode")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				if (sender instanceof Player) {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.GOLD + "Gamemode set to "
							+ ChatColor.AQUA + "Creative!");
					player.setGameMode(GameMode.CREATIVE);
					return true;
				}
				sender.sendMessage(ChatColor.RED
						+ "You must be online to do this!");
				return true;
			} else if (args.length == 1) {
				if (!sender.hasPermission("aplha.gamemode.other")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Player not Found!");
					return true;
				}
				target.setGameMode(GameMode.CREATIVE);
				target.sendMessage(ChatColor.GOLD + "Gamemode set to "
						+ ChatColor.AQUA + "Creative!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("gms")) {
			if (args.length == 0) {
				if (!sender.hasPermission("aplha.gamemode")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				if (sender instanceof Player) {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.GOLD + "Gamemode set to "
							+ ChatColor.AQUA + "Survival!");
					player.setGameMode(GameMode.SURVIVAL);
					return true;
				}
				sender.sendMessage(ChatColor.RED
						+ "You must be online to do this!");
				return true;
			} else if (args.length == 1) {
				if (!sender.hasPermission("aplha.gamemode.other")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Player not Found!");
					return true;
				}
				target.setGameMode(GameMode.SURVIVAL);
				target.sendMessage(ChatColor.GOLD + "Gamemode set to "
						+ ChatColor.AQUA + "Survival!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")){
			if (!sender.hasPermission("aplha.gamemode")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			if (args.length == 0){
				sender.sendMessage(ChatColor.RED + "Please specify a Gamemode!");
				return true;
			}
			if (args.length == 1){
				Player player = (Player) sender;
				if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GOLD + "Gamemode set to "
							+ ChatColor.AQUA + "Creative!");
					return true;
				}else if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.GOLD + "Gamemode set to "
							+ ChatColor.AQUA + "Survival");
					return true;
				}
			}
			if (args.length == 2){
				if (!sender.hasPermission("aplha.gamemode.other")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Player not Found!");
					return true;
				}
				if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")){
					target.setGameMode(GameMode.CREATIVE);
					target.sendMessage(ChatColor.GOLD + "Gamemode set to "
							+ ChatColor.AQUA + "Creative!");
					return true;
				}else if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")){
					target.setGameMode(GameMode.SURVIVAL);
					target.sendMessage(ChatColor.GOLD + "Gamemode set to "
							+ ChatColor.AQUA + "Survival");
					return true;
				}
				
				
			}
		}
		return false;
	}
}
