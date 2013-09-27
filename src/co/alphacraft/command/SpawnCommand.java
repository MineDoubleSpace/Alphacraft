package co.alphacraft.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.coins.CoinsManager;
import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class SpawnCommand implements CommandExecutor {

	public Plugin plugin;

	public SpawnCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("spawn")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					World w = Bukkit.getServer().getWorld(
							s.getHome().getString("spawn.world"));
					double x = s.getHome().getInt("spawn.x"), y = s.getHome()
							.getInt("spawn.y"), z = s.getHome().getInt(
							"spawn.z"), yaw1 = s.getHome().getInt("spawn.yaw"), pitch1 = s
							.getHome().getInt("spawn.pitch");
					float yaw = (float) yaw1;
					float pitch = (float) pitch1;
					player.sendMessage(ChatColor.GOLD + "Teleporting to Spawn!");
					player.teleport(new Location(w, x, y, z, yaw, pitch));
					return true;
				} else if (args[0].equalsIgnoreCase("set")) {
					if (!sender.hasPermission("alpha.spawn.set")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "You don't have permissions to do this!");
						return true;
					}
					s.getHome().set("spawn.world",
							player.getLocation().getWorld().getName());
					s.getHome()
							.set("spawn.x", player.getLocation().getBlockX());
					s.getHome()
							.set("spawn.y", player.getLocation().getBlockY());
					s.getHome()
							.set("spawn.z", player.getLocation().getBlockZ());
					s.getHome().set("spawn.yaw", player.getLocation().getYaw());
					s.getHome().set("spawn.pitch",
							player.getLocation().getPitch());
					s.saveHome();
					player.sendMessage(ChatColor.AQUA + "Spawn set!");
					return true;
				}

			}

		}

		return false;
	}

}
