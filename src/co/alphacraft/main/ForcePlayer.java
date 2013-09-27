package co.alphacraft.main;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForcePlayer implements CommandExecutor{
	
	
	public Plugin plugin;

	public ForcePlayer(Plugin instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("fp")){
		if (!sender.hasPermission("alpha.forceplayer")){
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
		
			if (args.length == 0){
				sender.sendMessage(ChatColor.RED +  "Please specify a Player");
				return true;
			}
			
			if (args.length == 1){
				sender.sendMessage(ChatColor.RED + "Please specity the action");
				return true;
				
			}
			
			if (args.length == 2){
				Player t = Bukkit.getServer().getPlayer(args[0]);
				if (t == null){
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				String c = StringUtils.join(args, " ", 1, args.length);
				t.chat(c);
				return true;
			}
				
				
				
				
				
			}
			
		return false;
	}

}
