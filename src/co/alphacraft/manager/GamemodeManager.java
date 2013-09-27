package co.alphacraft.manager;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeManager {
	
	public static boolean isCreative(Player p){
		if (p.getGameMode() == GameMode.CREATIVE){
			return true;
		}else {
			return false;
		}

	

}
}
