package co.alphacraft.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;

import co.alphacraft.listners.DeathListener;
import co.alphacraft.main.Plugin;







public class KillCommand implements CommandExecutor {
	
	public Plugin plugin;

	public KillCommand(Plugin instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("kill")) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					((Player) sender).setHealth(0.0);
					DeathListener.autoRespawn((Player) sender);
					return true;
				} else if (args.length == 1) {
					if (!sender.hasPermission("aplha.kill.other")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "You don't have permissions to do this!");
						return true;
					}
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(ChatColor.RED + "Player not found");
						return true;
					}
					target.setHealth(0.0);
					DeathListener.autoRespawn(target);
					return true;
				}
			}
		}
		
		
		if (cmd.getName().equalsIgnoreCase("killall")){
			int t = 0;
			if (!sender.hasPermission("aplha.killall")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			Player player = (Player) sender;
			
			if (args.length == 0){
			for(Entity en : Bukkit.getServer().getWorld(player.getWorld().getName()).getEntitiesByClasses(Spider.class, Creeper.class, Skeleton.class, 
																						Enderman.class, EnderDragon.class, Slime.class, 
																						Zombie.class)){
				en.remove();
				t++;
			}
			sender.sendMessage(ChatColor.AQUA +""+ t + ChatColor.GOLD + " Entities have been removed!");
			t = 0;
			return true;
			}
			if (args[0].equalsIgnoreCase("-a")){
				for(Entity e : Bukkit.getServer().getWorld(player.getWorld().getName()).getEntities()){
					e.remove();
					t++;
				}
				sender.sendMessage(ChatColor.AQUA +""+ t + ChatColor.GOLD + " Entities have been removed!");
				t = 0;
				return true;
				}
			}
		return false;

	}
}
