package co.alphacraft.listners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import co.alphacraft.main.Plugin;
import co.alphacraft.manager.GamemodeManager;

public class PvpListener implements Listener{
	
	public Plugin p;
	
	public PvpListener(Plugin instance) {
		p = instance;
	}

	@EventHandler
	public void onCreativePvP(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player){
			if (e.getDamager() instanceof Player){
				Player d = (Player) e.getDamager();
					if (!d.hasPermission("alpha.creative.bypass")){
						if (GamemodeManager.isCreative(d)){
							d.sendMessage(ChatColor.RED + "You may not PVP while in creative mode!");
							return;
						}
				}
			}
		}
	}
	
	
	
	

}
