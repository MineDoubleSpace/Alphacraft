package co.alphacraft.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {

	private SettingsManager() {
	}

	static SettingsManager instance = new SettingsManager();

	public static SettingsManager getInstance() {
		return instance;
	}

	Plugin p;

	FileConfiguration config;
	File cfile;
	
	FileConfiguration rules;
	File rulefile;

	FileConfiguration pdata;
	File pdfile;

	FileConfiguration warp;
	File wfile;

	FileConfiguration home;
	File hfile;

	FileConfiguration report;
	File rfile;

	public void setup(Plugin p) {
		cfile = new File(p.getDataFolder(), "config.yml");
		config = p.getConfig();
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		pdfile = new File(p.getDataFolder(), "playerData.yml");

		if (!pdfile.exists()) {
			try {
				pdfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer()
						.getLogger()
						.severe(ChatColor.RED
								+ "Could not create playerData.yml");
			}
		}

		wfile = new File(p.getDataFolder(), "warp.yml");

		if (!wfile.exists()) {
			try {
				wfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create warp.yml");
			}
		}
		hfile = new File(p.getDataFolder(), "homes.yml");

		if (!hfile.exists()) {
			try {
				hfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create warp.yml");
			}
		}

		rfile = new File(p.getDataFolder(), "reports.yml");

		if (!rfile.exists()) {
			try {
				rfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create reports.yml");
			}
		}
		rulefile = new File(p.getDataFolder(), "rules.yml");

		if (!rulefile.exists()) {
			try {
				rulefile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create rules.yml");
			}
		}

		pdata = YamlConfiguration.loadConfiguration(pdfile);
		warp = YamlConfiguration.loadConfiguration(wfile);
		home = YamlConfiguration.loadConfiguration(hfile);
		report = YamlConfiguration.loadConfiguration(rfile);
		rules = YamlConfiguration.loadConfiguration(rulefile);
	}

	// get YAML FILE
	public FileConfiguration getConfig() {
		return config;
	}
	
	public FileConfiguration getRules() {
		return rules;
	}

	public FileConfiguration getReport() {
		return report;
	}

	public FileConfiguration getHome() {
		return home;
	}

	public FileConfiguration getPData() {
		return pdata;
	}

	public FileConfiguration getWarp() {
		return warp;
	}

	// Save File
	public void saveConfig() {
		try {
			config.save(cfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.DARK_RED + "Could not save config.yml");
		}
	}

	public void saveReport() {
		try {
			report.save(rfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.DARK_RED + "Could not save reports.yml");
		}
	}

	public void savePData() {
		try {
			pdata.save(pdfile);
		} catch (IOException e) {
			Bukkit.getServer()
					.getLogger()
					.severe(ChatColor.DARK_RED
							+ "Could not save playerData.yml");
		}
	}

	public void saveWarp() {
		try {
			warp.save(wfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.DARK_RED + "Could not save warp.yml");
		}
	}

	public void saveHome() {
		try {
			home.save(hfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.DARK_RED + "Could not save home.yml");
		}
	}

	// reload file
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(cfile);
	}

	public void reloadReport() {
		report = YamlConfiguration.loadConfiguration(rfile);
	}

	public void reloadData() {
		pdata = YamlConfiguration.loadConfiguration(pdfile);
	}

	public void reloadWarp() {
		warp = YamlConfiguration.loadConfiguration(wfile);
	}

	public void reloadHome() {
		home = YamlConfiguration.loadConfiguration(hfile);
	}

	// Other
	public PluginDescriptionFile getDesc() {
		return p.getDescription();
	}
}
