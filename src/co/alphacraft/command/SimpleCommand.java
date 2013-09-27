package co.alphacraft.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class SimpleCommand implements CommandExecutor {

	public Plugin plugin;

	public SimpleCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("heal")) {
			if (!sender.hasPermission("alpha.heal")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					player.setHealth(20.0);
					player.setFoodLevel(20);
					player.setFireTicks(0);
					player.sendMessage(ChatColor.AQUA + "Healed!");
					return true;
				}
				if (args.length >= 1) {
					if (!sender.hasPermission("alpha.heal.other")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "You don't have permissions to do this!");
						return true;
					}
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED
								+ "Could not find player " + ChatColor.AQUA
								+ args[0] + ".");
					} else {
						target.setHealth(20.0);
						target.setFoodLevel(20);
						target.setFireTicks(0);
						target.sendMessage(ChatColor.GOLD
								+ "You were healed by " + ChatColor.AQUA
								+ player.getName() + ChatColor.GOLD + ".");
						sender.sendMessage(ChatColor.GOLD + "Healed player "
								+ ChatColor.AQUA + args[0] + ChatColor.GOLD
								+ ".");
						return true;
					}
				}
			}

		}
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					if (!sender.hasPermission("alpha.fly")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "You don't have permissions to do this!");
						return true;
					}
					if (player.getAllowFlight() == true) {
						player.setAllowFlight(false);
						player.sendMessage(ChatColor.GOLD
								+ "Fly mode disabled!");
					} else {
						player.setAllowFlight(true);
						player.sendMessage(ChatColor.GOLD + "Fly mode enabled!");
						return true;
					}

				}
			}
			if (args.length >= 1) {
				if (!sender.hasPermission("alpha.fly.other")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED + "Could not find player "
							+ ChatColor.AQUA + args[0] + ".");
					return true;

				} else if (target.getAllowFlight() == true) {
					target.setAllowFlight(false);
					target.sendMessage(ChatColor.GOLD + "Fly mode disabled by "
							+ ChatColor.AQUA + sender.getName() + ".");
					sender.sendMessage(ChatColor.GOLD + "Fly mode disabled to"
							+ ChatColor.AQUA + target.getName() + ".");
				} else {
					target.setAllowFlight(true);
					target.sendMessage(ChatColor.GOLD + "Fly mode enabled by "
							+ ChatColor.AQUA + sender.getName() + ".");
					sender.sendMessage(ChatColor.GOLD + "Fly mode enabled to "
							+ ChatColor.AQUA + target.getName() + ".");

					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("motd")) {
			if (args.length == 0) {
				if (s.getConfig().getString("server.motd") == null) {
					sender.sendMessage(ChatColor.RED + "MOTD not found!");
					return true;
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', s.getConfig().getString("server.motd")));
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("set")) {
				if (!sender.hasPermission("alpha.motd.set")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
					return true;
				}
				String motd = "";
				for (int i = 1; i < args.length; i++) {
					motd += args[i] + " ";
					s.getConfig().set("server.motd", motd);
					s.saveConfig();
				}
			} return true;
		}
		if (cmd.getName().equalsIgnoreCase("broadcast")){
			if (!sender.hasPermission("alpha.broadcast")){
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			if (args.length == 0){
				sender.sendMessage(ChatColor.RED + "Did you forget the message?");
				return true;
			}
			String msg = StringUtils.join(args, " ", 0, args.length);
			Bukkit.broadcastMessage(ChatColor.GOLD + "[Broadcast]"+ ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', msg));
			return true;
			
		}
		return true;
	}
}
