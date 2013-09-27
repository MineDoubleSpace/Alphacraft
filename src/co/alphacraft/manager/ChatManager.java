package co.alphacraft.manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.alphacraft.main.Plugin;

public class ChatManager implements Listener{
	
	HashMap<Player, ArrayList<Player>> ignore = new HashMap<Player, ArrayList<Player>>();
	
	public Plugin plugin;
	
	public ChatManager(Plugin instance) {
		plugin = instance;
	}
	
	PrefixManager pm = PrefixManager.getInstance();
	
	
	
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
    		Player p = e.getPlayer();
    		String name = p.getName();
    		String pre = pm.getPrefix(e.getPlayer());
    		String msg = e.getMessage();
    		if (p.hasPermission("aplha.chat.color")){
    			e.setFormat(pre + ChatColor.GRAY + name + ": " + ChatColor.translateAlternateColorCodes('&', msg));
    			return;
    		}
    			e.setFormat(pre + ChatColor.GRAY + name +  ": " + msg);
    			return;
    }

}

