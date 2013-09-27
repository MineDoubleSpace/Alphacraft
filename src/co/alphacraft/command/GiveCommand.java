package co.alphacraft.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.alphacraft.main.Plugin;



public class GiveCommand implements CommandExecutor{
	
	public Plugin plugin;
	
	public GiveCommand(Plugin instance) {
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("i")){
			if (!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "You must have a life!");
				return true;
			}
			if (!sender.hasPermission("aplha.give")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			if (args.length == 0){
				sender.sendMessage(ChatColor.RED + "/i <ItemID> <Amount>");
				return true;
			}
			try {
				if (args.length == 2){
					int id = Integer.parseInt(args[0]);
					int a = Integer.parseInt(args[1]);
					ItemStack i = new ItemStack(id, a);
					Player p = (Player) sender;
					p.getInventory().addItem(i);
					if (a > 1){
					sender.sendMessage(ChatColor.GOLD + "Giving " +  ChatColor.AQUA + a +" "
										+ Material.getMaterial(id).toString()+ "S" + ChatColor.GOLD + "!" );
					return true;
					}
					sender.sendMessage(ChatColor.GOLD + "Giving " +  ChatColor.AQUA + a +" "
							+ Material.getMaterial(id).toString() + ChatColor.GOLD + "!" );
					return true;
				}else{
				int id = Integer.parseInt(args[0]);
				ItemStack i = new ItemStack(id, 64);
				Player p = (Player) sender;
				p.getInventory().addItem(i);
				sender.sendMessage(ChatColor.GOLD + "Giving " +  ChatColor.AQUA + "64 " + Material.getMaterial(id).toString()+ "S" + ChatColor.GOLD + "!" );
				}
			} catch (Exception e){
				sender.sendMessage(ChatColor.RED + "[Error] " + ChatColor.AQUA + "Please use /i <ItemID> <Amount>");
			}
			
			
		}
		return false;
	}

}
