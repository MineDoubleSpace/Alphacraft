package co.alphacraft.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.alphacraft.coins.CoinsManager;
import co.alphacraft.main.Plugin;
import co.alphacraft.manager.SettingsManager;

public class ReportCommand implements CommandExecutor {

	public Plugin plugin;

	public ReportCommand(Plugin instance) {
		plugin = instance;
	}

	SettingsManager s = SettingsManager.getInstance();
	CoinsManager c = CoinsManager.getInstance();
	List<String> report = new ArrayList<String>();
	ArrayList<Player> pl = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("report")) {
			if (sender instanceof Player) {
				final Player player = (Player) sender;
				if (pl.contains(player)){
					player.sendMessage(ChatColor.RED + "Please do not spam the report feature!");
					return true;
				}
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Please specify a reason!");
					return true;

				} else if (args.length >= 1) {
					String reason = StringUtils.join(args, " ", 0, args.length);
					for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
						if (!onlinePlayer.hasPermission("alpha.report.get"))
							continue;
						onlinePlayer.sendMessage(ChatColor.RED + "[Report]"
								+ ChatColor.AQUA + player.getName() + ": "
								+ ChatColor.GOLD + reason);
						report.add(player.getName() + ": " + reason);
						s.getReport().set("reports", report);
						s.saveReport();
//						Delay task
						
						pl.add(player);
						Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								pl.remove(player);
								
							}
						}, 200L);
						
					}
				}
			}
		}else if (cmd.getName().equalsIgnoreCase("read")){
			if (!sender.hasPermission("aplha.report.read")) {
				sender.sendMessage(ChatColor.DARK_RED
						+ "You don't have permissions to do this!");
				return true;
			}
			sender.sendMessage(ChatColor.DARK_RED + "Reports: ");
			for (String report1 : s.getReport().getStringList("reports")){
				String[] p = report1.split(": ");
				sender.sendMessage(ChatColor.AQUA + p[0]+ ": " + ChatColor.GOLD + p[1]);
			}
		}
			
		
		return true;
	}
}