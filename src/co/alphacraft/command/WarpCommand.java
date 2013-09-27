package co.alphacraft.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class WarpCommand implements CommandExecutor {

	public Plugin plugin;

	public WarpCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();

	// Permissions :
	// alpha.warp.create
	// alpha.warp.remove
	// alpha.warp.teleport

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("setwarp")) {
				if (!sender.hasPermission("alpha.warp.create")) {
					sender.sendMessage(ChatColor.DARK_RED
							+ "You don't have permissions to do this!");
				}
					if (args.length == 0) {
						player.sendMessage(ChatColor.AQUA
								+ "Please specify a name!");
						return true;
					}
					s.getWarp().set(
							"warps." + args[0].toUpperCase() + ".world",
							player.getLocation().getWorld().getName());
					s.getWarp().set("warps." + args[0].toUpperCase() + ".x",
							player.getLocation().getX());
					s.getWarp().set("warps." + args[0].toUpperCase() + ".y",
							player.getLocation().getY());
					s.getWarp().set("warps." + args[0].toUpperCase() + ".z",
							player.getLocation().getZ());
					s.getWarp().set("warps." + args[0].toUpperCase() + ".yaw",
							player.getLocation().getYaw());
					s.getWarp().set(
							"warps." + args[0].toUpperCase() + ".pitch",
							player.getLocation().getPitch());
					s.saveWarp();
					player.sendMessage(ChatColor.GREEN + "Warp "
							+ ChatColor.GOLD + args[0].toUpperCase()
							+ ChatColor.GREEN + " created!");
					return true;
			}

			if (cmd.getName().equalsIgnoreCase("warp")) {
					if (args.length == 0) {
						player.sendMessage(ChatColor.AQUA
								+ "Please specify a name!");
						return true;
					}
					if (s.getWarp().getConfigurationSection(
							"warps." + args[0].toUpperCase()) == null) {
						player.sendMessage(ChatColor.RED + "Warp "
								+ ChatColor.GOLD + args[0].toUpperCase()
								+ ChatColor.RED + " does not exist");
						return true;
					}
					World w = Bukkit.getServer().getWorld(
							s.getWarp()
									.getString(
											"warps." + args[0].toUpperCase()
													+ ".world"));
					double x = s.getWarp().getDouble(
							"warps." + args[0].toUpperCase().toUpperCase()
									+ ".x");
					double y = s.getWarp().getDouble(
							"warps." + args[0].toUpperCase() + ".y");
					double z = s.getWarp().getDouble(
							"warps." + args[0].toUpperCase() + ".z");
					double yaw1 = s.getWarp().getDouble(
							"warps." + args[0].toUpperCase() + ".yaw");
					double pitch1 = s.getWarp().getDouble(
							"warps." + args[0].toUpperCase() + ".pitch");
					float yaw = (float) yaw1;
					float pitch = (float) pitch1;
					player.teleport(new Location(w, x, y, z, yaw, pitch));
					player.sendMessage(ChatColor.AQUA + "Warping to "
							+ ChatColor.GOLD + "" + ChatColor.BOLD
							+ args[0].toLowerCase());
				}

		if (cmd.getName().equalsIgnoreCase("delwarp")) {
			if (player.hasPermission("alpha.warp.remove")) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.AQUA
							+ "Please specify a name!");
					return true;
				}
				if (s.getWarp().getConfigurationSection(
						"warps." + args[0].toUpperCase()) == null) {
					player.sendMessage(ChatColor.RED + "Warp " + ChatColor.GOLD
							+ args[0].toUpperCase() + ChatColor.RED
							+ " does not exist");
					return true;
				}
				s.getWarp().set("warps." + args[0].toUpperCase(), null);
				s.saveWarp();
				player.sendMessage(ChatColor.RED + "Warp " + ChatColor.GOLD
						+ args[0].toUpperCase() + ChatColor.RED + " Removed!");
			}
		}
		}
		return true;
	}
}
