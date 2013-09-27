package co.alphacraft.coins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class SignShop implements Listener {

	public Plugin plugin;

	public SignShop(Plugin instance) {
		plugin = instance;
	}

	CoinsManager c = CoinsManager.getInstance();
	SettingsManager sm = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@EventHandler()
	public void onNewSign(final SignChangeEvent sign) {
		if (sign.getLine(0).equalsIgnoreCase("[shop]")) {
			if (sign.getPlayer().hasPermission("alpha.shop.create")) {
				try {
					String L2 = sign.getLine(2);
					String sprice = sign.getLine(3);
					String str = sprice;
					String name = sign.getLine(1);
					int price = Integer.parseInt(str);
					sign.setLine(0, ChatColor.GREEN + "[" + ChatColor.AQUA
							+ "SHOP" + ChatColor.GREEN + "]");
					sign.setLine(1, ChatColor.DARK_BLUE + name);
					sign.setLine(2, ChatColor.DARK_BLUE + "ID : " + L2);
					sign.setLine(3, ChatColor.GOLD + "" + ChatColor.BOLD + "Coins : " + price);
					c.sendmsgG(sign.getPlayer(), ChatColor.GREEN
							+ "Shop created!");
				} catch (NumberFormatException nfe) {
					sign.setLine(0, ChatColor.DARK_RED + "[Error]");
					sign.setLine(1, ChatColor.DARK_RED + "[Error]");
					sign.setLine(2, ChatColor.DARK_RED + "[Error]");
					sign.setLine(3, ChatColor.DARK_RED + "[Error]");
					c.sendmsgR(sign.getPlayer(), ChatColor.DARK_RED
							+ "You did something wrong!");
//					delay break
					Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
						
						@Override
						public void run() {
							sign.getBlock().breakNaturally();
						}
					}, 20L);
					return;
				}
			} else {
				c.sendmsgR(sign.getPlayer(), ChatColor.DARK_RED
						+ "You don't have permissions to create a shop!");
//				delay break
				Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
					
					@Override
					public void run() {
						sign.getBlock().breakNaturally();
					}
				}, 20L);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler()
	public void onsignClick(PlayerInteractEvent event) {
		if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		if (event.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) event.getClickedBlock().getState();
			if (s.getLine(0).equalsIgnoreCase(
					ChatColor.GREEN + "[" + ChatColor.AQUA + "SHOP"
							+ ChatColor.GREEN + "]")) {
				String price = s.getLine(3);
				String ID = s.getLine(2);
				String[] i = ID.split(" : ");
				String[] p = price.split(" : ");
				int coins = c.getCoinsShop(event.getPlayer());
				int cost = Integer.parseInt(p[1]);
				int id = Integer.parseInt(i[1]);
				if (cost <= coins) {
					if (event.getPlayer().getInventory().firstEmpty() != -1) {
						event.getPlayer().getInventory()
								.addItem(new ItemStack(id, 1));
						event.getPlayer().updateInventory();
						event.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-" + cost + " Gold Coins" );
						int t = coins - cost;
						sm.getPData().set(
								event.getPlayer().getName() + ".coins", t);
						sm.savePData();
						return;
					} else {
						c.sendmsgR(event.getPlayer(), ChatColor.RED
								+ "Inventory full! you must have a free slot!");
					}
				} else {
					c.sendmsgR(event.getPlayer(), ChatColor.RED
							+ "You dont have enough Gold Coins!");
				}
			}
		}

	}

}
