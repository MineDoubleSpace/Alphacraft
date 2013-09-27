package co.alphacraft.coins;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import co.alphacraft.main.Plugin;

public class CoinsPrizeKill implements Listener{
	
	HashMap<Entity, Player> kill = new HashMap<Entity, Player>();
	
	public Plugin plugin;
	
	public CoinsPrizeKill(Plugin instance) {
		plugin = instance;
	}
	
	CoinsManager c = CoinsManager.getInstance();

	@EventHandler
	public void onKillCoinsAdd (EntityDeathEvent e){
		if (kill.containsKey(e.getEntity())){
			Player killer1 = kill.get(e.getEntity());
			String name = killer1.getName();
			Player killer = Bukkit.getServer().getPlayerExact(name);
			if (killer == null){
				return;
			}if (e.getEntity() instanceof Player){
				killer.sendMessage(ChatColor.GOLD + "+5 Gold Coins!");
				c.coinAddNull(name, "5");				
			}if (e.getEntity() instanceof Zombie){
				killer.sendMessage(ChatColor.GOLD + "+2 Gold Coins!");
				c.coinAddNull(name, "2");	
			}
		}
		
		
	}
	
	@EventHandler
	public void deathCheck(final EntityDamageByEntityEvent e){
		if (e.getDamager() instanceof Player){
			Player d = (Player) e.getDamager();
			if (d.getGameMode() != GameMode.CREATIVE || d.hasPermission("alpha.creative.bypass")){
			if (kill.get(e.getEntity()) != e.getDamager()){
			kill.put(e.getEntity(), (Player) e.getDamager());
//			 delay remove
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					kill.remove(e.getEntity());
				}
			}, 100L);
		}
		}
		}
		
	}
}
