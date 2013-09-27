package co.alphacraft.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class RulesCommand implements CommandExecutor {

	public Plugin plugin;

	public RulesCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("rules")){
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7    ======= &cRules &7======="));
			for (String rules: s.getRules().getStringList("rules")){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', rules));
		}
		}
		return false;
	}

}
