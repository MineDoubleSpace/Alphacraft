package co.alphacraft.main;

import org.bukkit.plugin.java.JavaPlugin;

import co.alphacraft.anouncer.Anouncer;
import co.alphacraft.anouncer.AnouncerCommand;
import co.alphacraft.anouncer.AnouncerManager;
import co.alphacraft.coins.CoinsCommand;
import co.alphacraft.coins.CoinsPrizeKill;
import co.alphacraft.coins.SignShop;
import co.alphacraft.command.AfkCommand;
import co.alphacraft.command.GamemodeCommand;
import co.alphacraft.command.GiveCommand;
import co.alphacraft.command.GodCommand;
import co.alphacraft.command.HomeCommand;
import co.alphacraft.command.IpCommand;
import co.alphacraft.command.KillCommand;
import co.alphacraft.command.ReportCommand;
import co.alphacraft.command.RulesCommand;
import co.alphacraft.command.SimpleCommand;
import co.alphacraft.command.SpawnCommand;
import co.alphacraft.command.TimeCommand;
import co.alphacraft.command.WarpCommand;
import co.alphacraft.listners.AfkListener;
import co.alphacraft.listners.DeathListener;
import co.alphacraft.listners.ExplosionListener;
import co.alphacraft.listners.GamemodeListener;
import co.alphacraft.listners.PvpListener;
import co.alphacraft.listners.SimpleListener;
import co.alphacraft.manager.ChatManager;
import co.alphacraft.manager.SettingsManager;
import co.alphacraft.manager.SignManager;

public class Plugin extends JavaPlugin {

	@Override
	public void onEnable() {
//		Setup
		SettingsManager.getInstance().setup(this);
		AnouncerManager.getInstance().anouncerSetup(this);
		AnouncerManager.getInstance().saveConfig();
		Anouncer.getInstance().startAnouncer(this);
//		Events
		
		
		getServer().getPluginManager().registerEvents(new SimpleListener(this), this);
		getServer().getPluginManager().registerEvents(new DeathListener(this),this);
		getServer().getPluginManager().registerEvents(new AfkListener(this),this);
		getServer().getPluginManager().registerEvents(new SignShop(this), this);
		getServer().getPluginManager().registerEvents(new ChatManager(this), this);
		getServer().getPluginManager().registerEvents(new JoinBook(this), this);
		getServer().getPluginManager().registerEvents(new GamemodeListener(this), this);
		getServer().getPluginManager().registerEvents(new SignManager(this), this);
		getServer().getPluginManager().registerEvents(new CoinsPrizeKill(this), this);
		getServer().getPluginManager().registerEvents(new PvpListener(this), this);
		getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);

		
//		Commands
		getCommand("coins").setExecutor(new CoinsCommand(this));
		getCommand("heal").setExecutor(new SimpleCommand(this));
		getCommand("fly").setExecutor(new SimpleCommand(this));	
		getCommand("motd").setExecutor(new SimpleCommand(this));
		getCommand("warp").setExecutor(new WarpCommand(this));
		getCommand("setwarp").setExecutor(new WarpCommand(this));
		getCommand("delwarp").setExecutor(new WarpCommand(this));
		getCommand("pay").setExecutor(new CoinsCommand(this));
		getCommand("home").setExecutor(new HomeCommand(this));
		getCommand("ip").setExecutor(new IpCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("report").setExecutor(new ReportCommand(this));
		getCommand("read").setExecutor(new ReportCommand(this));
		getCommand("god").setExecutor(new GodCommand(this));
		getCommand("afk").setExecutor(new AfkCommand(this));
		getCommand("gmc").setExecutor(new GamemodeCommand(this));
		getCommand("gms").setExecutor(new GamemodeCommand(this));
		getCommand("gamemode").setExecutor(new GamemodeCommand(this));
		getCommand("rules").setExecutor(new RulesCommand(this));
		getCommand("kill").setExecutor(new KillCommand(this));
		getCommand("killall").setExecutor(new KillCommand(this));
		getCommand("day").setExecutor(new TimeCommand(this));
		getCommand("night").setExecutor(new TimeCommand(this));
		getCommand("fp").setExecutor(new ForcePlayer(this));
		getCommand("broadcast").setExecutor(new SimpleCommand(this));
		getCommand("anouncer").setExecutor(new AnouncerCommand(this));
		getCommand("i").setExecutor(new GiveCommand(this));
	}
}