package co.alphacraft.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.main.Plugin;

public class TimeCommand implements CommandExecutor{
	public Plugin plugin;
	
	public TimeCommand(Plugin instnace){
		plugin = instnace;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player){
		if (cmd.getName().equalsIgnoreCase("day")){
			if (!sender.hasPermission("aplha.time")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			Player p = (Player) sender;
			World w = p.getWorld();
			w.setTime(0);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("night")){
			if (!sender.hasPermission("aplha.time")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			Player p = (Player) sender;
			World w = p.getWorld();
			w.setTime(13500);
			return true;
		}
		
		
		}
		
		return false;
	}

}
