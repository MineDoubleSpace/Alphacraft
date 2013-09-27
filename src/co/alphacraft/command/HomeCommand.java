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

public class HomeCommand implements CommandExecutor {

	public Plugin plugin;

	public HomeCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("home")) {
				if (args.length == 0) {
					if (s.getHome().getConfigurationSection(
							player.getName() + ".home") == null) {
						player.sendMessage(ChatColor.RED
								+ "Could not find your Home, Please set your home using "
								+ ChatColor.AQUA + "'/home set'");
						return true;
					} else {
						World w = Bukkit.getServer().getWorld(
								s.getHome().getString(
										player.getName() + ".home.world"));
						double x = s.getHome().getInt(
								player.getName() + ".home.x"), y = s.getHome()
								.getInt(player.getName() + ".home.y"), z = s
								.getHome().getInt(player.getName() + ".home.z"), yaw1 = s
								.getHome().getInt(
										player.getName() + ".home.yaw"), pitch1 = s
								.getHome().getInt(
										player.getName() + ".home.pitch");
						float yaw = (float) yaw1;
						float pitch = (float) pitch1;
						player.teleport(new Location(w, x, y, z, yaw, pitch));
						player.sendMessage(ChatColor.GOLD
								+ "Teleporting to home!");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("set")) {
					s.getHome().set(player.getName() + ".home.world",
							player.getLocation().getWorld().getName());
					s.getHome().set(player.getName() + ".home.x",
							player.getLocation().getBlockX());
					s.getHome().set(player.getName() + ".home.y",
							player.getLocation().getBlockY());
					s.getHome().set(player.getName() + ".home.z",
							player.getLocation().getBlockZ());
					s.getHome().set(player.getName() + ".home.yaw",
							player.getLocation().getYaw());
					s.getHome().set(player.getName() + ".home.pitch",
							player.getLocation().getPitch());
					s.saveHome();
					player.sendMessage(ChatColor.GOLD
							+ "Welcome to your new home!");
				}
			}
		}
		return true;
	}
}
