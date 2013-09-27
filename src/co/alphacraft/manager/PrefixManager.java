package co.alphacraft.manager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PrefixManager {
	
	private PrefixManager() {
	}

	static PrefixManager instance = new PrefixManager();

	public static PrefixManager getInstance() {
		return instance;
	}
	
	public String getPrefix(Player p){

		if (p.hasPermission("alpha.rank.dev")) {
			String pre = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[☢]";
			return pre;
		}
		if (p.hasPermission("alpha.rank.admin")) {
			String pre = ChatColor.DARK_BLUE +  "" + ChatColor.BOLD + "[✤]";
			return pre;
		}
		if (p.hasPermission("alpha.rank.mod")) {
			String pre = ChatColor.DARK_AQUA +  "" + ChatColor.BOLD + "[✥]";
			return pre;
		}
		if (p.hasPermission("alpha.rank.donator")) {
			String pre = ChatColor.GREEN +  "" + ChatColor.BOLD + "[♥]";
			return pre;
		}
		if (p.hasPermission("alpha.rank.donatorp")) {
			String pre = ChatColor.DARK_GREEN +  "" + ChatColor.BOLD + "[♥]";
			return pre;
		}
		if (p.hasPermission("alpha.rank.member")) {
			String pre = "";
			return pre;
		}
		else {
			return "";
		}
		
		
	}
	
	

}
