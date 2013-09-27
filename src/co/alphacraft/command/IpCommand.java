package co.alphacraft.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.alphacraft.coins.CoinsManager;
import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class IpCommand implements CommandExecutor {

	public Plugin plugin;

	public IpCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("ip")) {
			if (!sender.hasPermission("aplha.ip")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Please specify a Player");
				return true;
				
				
			} sender.sendMessage(getPlayerIp(args[0]));
					return true;
				}
		
		
		
		
		return true;
	}
	
	public String getPlayerIp(String name){
		String ip = s.getPData().getString(name + ".ip");
		if (ip != null){
			return ip;
		} else {
			return ChatColor.RED + "Player not found";
		}
	}

}
