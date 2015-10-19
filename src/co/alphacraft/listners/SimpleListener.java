package co.alphacraft.listners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import co.alphacraft.coins.CoinsManager;
import co.alphacraft.command.GodCommand;
import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class SimpleListener implements Listener {

	public Plugin plugin;

	public SimpleListener(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (s.getPData().getConfigurationSection(player.getName()) != null) {
			s.getPData().set(player.getName() + ".ip", event.getPlayer().getAddress().getHostName());
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a✱&7] " + "&6" + player.getName() + " &ajoined AlphaCraft!"));
			c.coinAddNull(player.getName(), "2");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "2 GOLD COINS added!");
			return;
		} else {
			World w = Bukkit.getServer().getWorld(s.getHome().getString("spawn.world"));
			double x = s.getHome().getInt("spawn.x"), y = s.getHome().getInt("spawn.y"), z = s.getHome().getInt("spawn.z"), yaw1 = s.getHome().getInt("spawn.yaw"),
					pitch1 = s.getHome().getInt("spawn.pitch");
			float yaw = (float) yaw1;
			float pitch = (float) pitch1;
			player.teleport(new Location(w, x, y, z, yaw, pitch));
			player.sendMessage(ChatColor.GREEN + "Hi " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName() + ChatColor.GREEN + " Welcome to Alphacraft! ");
			c.coinAddNull(player.getName(), "30");
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "30 GOLD COINS added!" + ChatColor.AQUA + "" + ChatColor.ITALIC + " '/coins' to check your GOLD COINS'");
			event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a✱&7] " + "&6" + player.getName() + " &ajoined AlphaCraft!"));
			s.getPData().set(player.getName() + ".ip", event.getPlayer().getAddress().getHostName());
			starterKit(player);
			s.savePData();
			return;
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&7[&c✱&7] " + "&6" + player.getName() + " &cleft AlphaCraft!"));
	}

	// God mode check
	@EventHandler()
	public void godModeCheck(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (GodCommand.isGod(player)) {
				event.setCancelled(true);
			}
		}
	}

	// MotdCheck Event
	@EventHandler()
	public void motdCheck(ServerListPingEvent event) {
		event.setMotd(ChatColor.translateAlternateColorCodes('&', s.getConfig().getString("server.motd")));
	}

	@EventHandler
	public void opcheck(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().contains("/op")) {
			if (!event.getPlayer().getName().equalsIgnoreCase("MineDoubleSpace")) {
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "You may not take or give OP!");
				event.setCancelled(true);
				return;
			}

		}
	}

	private void starterKit(Player p) {
		p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
		p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		p.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
		p.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
		ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta meta = sword.getItemMeta();
		meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Sword of " + ChatColor.GOLD + "" + ChatColor.GOLD + p.getName() + "!");
		sword.setItemMeta(meta);
		p.getInventory().addItem(sword);
		p.getInventory().addItem(new ItemStack(Material.BAKED_POTATO, 28));
		//		update call
		p.updateInventory();
	}

	BukkitScheduler b = Bukkit.getScheduler();

	@EventHandler
	public void onPlayerJoinGod(PlayerJoinEvent e) {
		final Player player = e.getPlayer();
		if (s.getPData().getConfigurationSection(player.getName()) != null) {
			GodCommand.setGod(player);
			b.scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {
					GodCommand.removeGod(player);
				}
			}, 200L);

		} else {
			player.sendMessage(ChatColor.BLUE + "You are invulnerable for 30 minutes!");
			GodCommand.setGod(player);
			b.scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {
					GodCommand.removeGod(player);
				}
			}, 36000L);
		}

	}

	@EventHandler
	public void onPlayerleaveGod(PlayerQuitEvent event) {
		GodCommand.removeGod(event.getPlayer());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		return;
	}
}
