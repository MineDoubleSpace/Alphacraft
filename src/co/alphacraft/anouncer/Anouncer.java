package co.alphacraft.anouncer;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import co.alphacraft.main.Plugin;

public class Anouncer {
	
	public Plugin plugin;
	
	private Anouncer(){}
	
	static Anouncer instance = new Anouncer();
	
	public static Anouncer getInstance(){
		return instance;
	}
	AnouncerMessages am = AnouncerMessages.getInstance();
	List<String> a = am.getAnouncements(); 
	int t = a.size();
	int t1 = t;
	

	
	public void startAnouncer(Plugin p){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(p, new Runnable() {
			
			
			@Override
			public void run() {
				if (!am.isEnabled()){
					return;
				}
				if (t == 0){
					return;
				}
				if (t1 == 0){
					t1 = t;
					return;
				}
				String msg = a.get(t1-1);
				t1--;
				Bukkit.broadcastMessage(
						ChatColor.translateAlternateColorCodes('&',am.getPrefix() + msg));
				
		}
		}, am.getDelay(), am.getDelay());
		
		
	}

}
