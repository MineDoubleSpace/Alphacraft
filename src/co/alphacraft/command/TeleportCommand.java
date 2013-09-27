package co.alphacraft.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor{
	
	HashMap<Player, Player> req = new HashMap<Player, Player>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		if (!(sender instanceof Player)){ return true; }
		if (cmd.getName().equalsIgnoreCase("tpa")){
			Player player = (Player) sender;
			if (args.length == 0){
				player.sendMessage(ChatColor.RED + "/tpa <player>");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null){
				player.sendMessage(ChatColor.RED + "Player not found");
				return true;
			}
			req.put(target, player);
			player.sendMessage(ChatColor.GOLD + "Teleport request sent to " + ChatColor.AQUA + target.getName());
			target.sendMessage(ChatColor.GOLD + "You recived a teleport request from " + player.getName());
			target.sendMessage(ChatColor.GOLD + "Use /tpaccept to accept the teleport request!");
			return true;
			}
		if (cmd.getName().equalsIgnoreCase("tpaccept")){
			Player target = (Player) sender;
			if (!req.containsKey(target)){
				target.sendMessage(ChatColor.RED + "You do not have any teleport requests");
				return true;
			}
			Player player = Bukkit.getServer().getPlayerExact(req.get(target).getName());
			player.teleport(target);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("tp")){
			Player player = (Player) sender;
			if (!player.hasPermission("aplha.teleport")) {
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permissions to do this!");
				return true;
			}
			if (args.length == 1){
				Player target = Bukkit.getServer().getPlayer(args[0]);
				player.teleport(target);
				player.sendMessage(ChatColor.GOLD + "Teleporting to " + ChatColor.AQUA + target.getName());
				return true;
			}if (!player.hasPermission("aplha.teleport.other")) {
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permissions to do this!");
				return true;
			}
				Player t1 = Bukkit.getServer().getPlayer(args[0]);
				Player t2 = Bukkit.getServer().getPlayer(args[1]);
				if (t1 == null || t2 == null){
					player.sendMessage(ChatColor.RED + "Player not found");
					return true;
				}
				t2.teleport(t1);
		}
		return true;
	}
}
