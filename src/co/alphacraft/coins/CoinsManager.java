package co.alphacraft.coins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.manager.SettingsManager;

public class CoinsManager {

	private CoinsManager() {
	}

	static CoinsManager instance = new CoinsManager();

	SettingsManager s = SettingsManager.getInstance();

	public static CoinsManager getInstance() {
		return instance;

	}

	public void sendmsgG(Player player, String msg) {
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_GREEN + "◯"
				+ ChatColor.GOLD + "]" + msg);
	}

	public void sendmsgR(Player player, String msg) {
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + "◯"
				+ ChatColor.GOLD + "]" + msg);
	}

	public void getCoins(CommandSender sender) {
		s.getPData().getInt(sender.getName() + ".coins");

	}

	public int getCoinsShop(Player sender) {
		return s.getPData().getInt(sender.getName() + ".coins");

	}

	public void sendTotal(Player getCoinsFrom) {
		getCoinsFrom.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD
				+ "Total Coins : " + ChatColor.AQUA + "" + ChatColor.BOLD
				+ s.getPData().getInt(getCoinsFrom.getName() + ".coins"));
		return;

	}

	public void sendTotalOffline(OfflinePlayer getFrom, CommandSender sendTo) {
		sendTo.sendMessage(ChatColor.AQUA + getFrom.getName() + "'s"
				+ ChatColor.GOLD + " Total Coins : "
				+ s.getPData().getInt(getFrom.getName() + ".coins"));
		return;

	}

	public void coinSet(CommandSender sender, String addToPlayer, String howMuch) {
		String str = howMuch;
		try {
			int acoins = Integer.parseInt(str);
			s.getPData().set(addToPlayer + ".coins", acoins);
			s.savePData();
			sender.sendMessage(ChatColor.GOLD + "Coin set " + ChatColor.AQUA
					+ howMuch + ChatColor.GOLD + " for " + ChatColor.AQUA
					+ addToPlayer);
			return;
		} catch (NumberFormatException e) {
			sender.sendMessage(ChatColor.DARK_RED + "[Error] " + ChatColor.AQUA
					+ "/coins add <Player> <amount>");
		}
		return;

		// /coins add Mine 100
	}

	public void coinSetConsole(String addToPlayer, String howMuch) {
		String str = howMuch;
		try {
			int acoins = Integer.parseInt(str);
			s.getPData().set(addToPlayer + ".coins", acoins);
			s.savePData();
			return;
		} catch (NumberFormatException e) {
		}
		return;

		// /coins add Mine 100
	}

	public void coinAdd(CommandSender sender, String addToPlayer, String howMuch) {
		String str = howMuch;
		try {
			final Player thisPlayer = Bukkit.getServer().getPlayer(addToPlayer);
			int ccoins = s.getPData().getInt(thisPlayer.getName() + ".coins");
			int acoins = Integer.parseInt(str);
			int tcoins = ccoins + acoins;
			s.getPData().set(thisPlayer.getName() + ".coins", tcoins);
			s.savePData();
			sender.sendMessage(ChatColor.AQUA + howMuch + ChatColor.GOLD
					+ " Coins added to " + ChatColor.AQUA + addToPlayer);
			return;
		} catch (NumberFormatException e) {
			sender.sendMessage(ChatColor.DARK_RED + "[Error] " + ChatColor.AQUA
					+ "/coins add <Player> <amount>");
		}
	}

	public void coinAddNull(String addToPlayer, String howMuch) {
		String str = howMuch;
		try {
			final Player thisPlayer = Bukkit.getServer().getPlayer(addToPlayer);
			int ccoins = s.getPData().getInt(thisPlayer.getName() + ".coins");
			int acoins = Integer.parseInt(str);
			int tcoins = ccoins + acoins;
			s.getPData().set(thisPlayer.getName() + ".coins", tcoins);
			s.savePData();
			return;
		} catch (NumberFormatException e) {
		}
	}

	// /pay mine 100
	/*
	 * public void coinPay(Player coinSender, Player coinReceiver, Integer
	 * howMuch) { int ccoins = s.getPData().getInt(coinSender.getName() +
	 * ".coins"); int rcoins = s.getPData().getInt(coinReceiver.getName() +
	 * ".coins"); int pay = howMuch; final Player target =
	 * Bukkit.getServer().getPlayer(coinReceiver.getName()); if (target ==
	 * null){ coinSender.sendMessage(ChatColor.RED + "Could not find player " +
	 * ChatColor.AQUA + coinReceiver.getName()); return; }else if (ccoins >=
	 * pay){ int trcoins = pay + rcoins; int tscoins = pay - ccoins;
	 * s.getPData().set(coinReceiver.getName() + ".coins", trcoins);
	 * s.getPData().set(coinSender.getName() + ".coins", tscoins);
	 * coinReceiver.sendMessage(ChatColor.GOLD + "You received " +
	 * ChatColor.AQUA + howMuch + "from " + ChatColor.AQUA
	 * +coinSender.getName());
	 * 
	 * } }
	 */

	public void coinPay(Player coinSender, String coinReceiver, String howMuch) {
		final Player target = Bukkit.getServer().getPlayerExact(coinReceiver);
		if (!(coinSender.getServer().getPlayer(coinReceiver) != null)) {
			coinSender.sendMessage(ChatColor.RED + "Could not find player "
					+ ChatColor.AQUA + coinReceiver);
		} else {
			try {
				String str = howMuch;
				int ccoins = s.getPData().getInt(
						coinSender.getName() + ".coins");
				int rcoins = s.getPData().getInt(target.getName() + ".coins");
				int pay = Integer.parseInt(str);
				if (ccoins >= pay) {
					int trcoins = pay + rcoins;
					int tscoins = ccoins - pay;
					s.getPData().set(target.getName() + ".coins", trcoins);
					s.getPData().set(coinSender.getName() + ".coins", tscoins);
					s.savePData();
					target.sendMessage(ChatColor.GOLD + "You received "
							+ ChatColor.AQUA + howMuch + ChatColor.GOLD
							+ " Coins from " + ChatColor.AQUA
							+ coinSender.getName());
					return;
				} else {
					coinSender.sendMessage(ChatColor.GOLD
							+ "You dont have enough COINS to send to "
							+ ChatColor.AQUA + coinReceiver);
					return;
				}
			} catch (NumberFormatException e) {
				coinSender.sendMessage(ChatColor.DARK_RED + "[Error] "
						+ ChatColor.GOLD + " Please use the command: "
						+ ChatColor.AQUA + "'/pay <Player> <amount>'");
			}
		}
	}

	// [Shop] || Diamond || ID || Price
	/*
	 * public void signShop(Player buyer, Sign sign){ try { String idName =
	 * Material.getMaterial(id).getData().getName(); int price =
	 * Integer.parseInt(str); int coins = s.getPData().getInt(buyer.getName() +
	 * ".coins"); if (coins <= price){ buyer.sendMessage(ChatColor.RED +
	 * "You dont have enough COINS to buy " + idName); }
	 * buyer.getInventory().addItem();
	 * 
	 * }catch (NumberFormatException nfe){ } }
	 */

	public void signShopCreate(Sign sign) {
		try {
			String L2 = sign.getLine(2);
			String sprice = sign.getLine(3);
			String str = sprice;
			int price = Integer.parseInt(str);
			String idName = Material.getMaterial(L2).getData().getName();
			sign.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN
					+ "Shop" + ChatColor.DARK_GRAY + "]");
			sign.setLine(1, ChatColor.BLUE + idName);
			sign.setLine(2, ChatColor.BLUE + "ID : " + L2);
			sign.setLine(3, ChatColor.BLUE + "Coins : " + price);
		} catch (NumberFormatException nfe) {
			sign.setLine(0, ChatColor.DARK_RED + "[Error]");
		}

	}
}