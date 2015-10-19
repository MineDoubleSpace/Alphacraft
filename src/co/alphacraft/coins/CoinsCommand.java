package co.alphacraft.coins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class CoinsCommand implements CommandExecutor {

	public Plugin plugin;

	public CoinsCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if (cmd.getName().equalsIgnoreCase("coins")) {
			if (args.length == 0) {
				Player player = (Player) sender;
				c.sendTotal(player);
				return true;
			}
			final OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);

			if (args.length == 1) {
				if (s.getPData().getString(target.getName() + ".coins") == null) {
					sender.sendMessage(ChatColor.DARK_RED + "Player " + ChatColor.GOLD + "" + ChatColor.BOLD + args[0] + ChatColor.DARK_RED + " Not Found");
					return true;
				} else if (args.length == 1) {
					c.sendTotalOffline(target, sender);
				}

			}
			// /coins set minedoublespace 1000
			if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
				if (sender.hasPermission("alpha.coins.set")) {
					c.coinSet(sender, args[1], args[2]);
					return true;
				}
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permissions to do this!");
				return true;
			}

			if (args.length == 3 && args[0].equalsIgnoreCase("add")) {
				if (sender.hasPermission("alpha.coins.add")) {
					c.coinAdd(sender, args[1], args[2]);
					return true;
				}
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permissions to do this!");
				return true;
			}

		}
		if (cmd.getName().equalsIgnoreCase("pay")) {
			Player player = (Player) sender;
			if (sender instanceof Player) {
				if (args.length == 2) {
					c.coinPay(player, args[0], args[1]);
					return true;
				}
			}
		}

		return true;
	}

}
