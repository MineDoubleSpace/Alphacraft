package co.alphacraft.listners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import co.alphacraft.main.Plugin;

public class ExplosionListener implements Listener{
	
	public Plugin p;
	
	public ExplosionListener(Plugin i) {
		p = i;
	}

	@EventHandler
	public void onExplodeEvent (EntityExplodeEvent e){
		e.setCancelled(true);
	}

}
