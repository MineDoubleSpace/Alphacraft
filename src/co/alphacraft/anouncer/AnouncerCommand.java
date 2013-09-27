package co.alphacraft.anouncer;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.alphacraft.main.Plugin;

public class AnouncerCommand implements CommandExecutor{
	
	AnouncerMessages am = AnouncerMessages.getInstance();
	AnouncerManager manager = AnouncerManager.getInstance();
	
	public Plugin plugin;
	
	public AnouncerCommand(Plugin instance) {
		plugin = instance;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("anouncer")){
			if (!sender.hasPermission("aplha.anouncer")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			if (args.length == 0){
				sendHelp(sender);
				return true;
			}if (args[0].equalsIgnoreCase("messages")){
				List<String> a = am.getAnouncements();
				sender.sendMessage(ChatColor.GREEN + "    ======================================");
				sender.sendMessage("");
				for (String s: a){
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
			                	am.getPrefix() + s));
				}
				sender.sendMessage("");
				sender.sendMessage(ChatColor.RED + "    ======================================");
				return true;
			}
			if (args[0].equalsIgnoreCase("delay")){
			if (args.length == 1){
					sendHelp(sender);
					return true;
				}
				try {
					String str = args[1];
					int delay = Integer.parseInt(str);
					manager.getAnouncer().set("delay", delay);
					manager.saveConfig();
					sender.sendMessage(ChatColor.GREEN + "Anouncement delay set to: " + ChatColor.AQUA + delay);
				} catch (Exception e){
					sender.sendMessage(ChatColor.RED + "Error! did you do something wrong?");
					sendHelp(sender);
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("disable")){
				sender.sendMessage(ChatColor.RED + "Anouncer Disabled!");
				manager.getAnouncer().set("enabled", false);
				manager.saveConfig();
				return true;
			}
			if (args[0].equalsIgnoreCase("enable")){
				sender.sendMessage(ChatColor.GREEN + "Anouncer Enabled!");
				manager.getAnouncer().set("enabled", true);
				manager.saveConfig();
				return true;
			}else {
				sender.sendMessage("de-bug");
				sendHelp(sender);
			}
			
		}
		return false;
	}
	
	
	public void sendHelp(CommandSender p){
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&7  =====  &aAlphacraft Anouncer &7 ====="));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&cmessages       &8 || &7Send listed anouncements"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&cdelay [Seconds] &8|| &7Set delay for messages"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&cdisable         &8|| &7Disable anouncements"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&cenable          &8|| &7Enable anouncements"));
	}

}
