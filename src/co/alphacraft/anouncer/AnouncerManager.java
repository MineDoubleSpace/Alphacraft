package co.alphacraft.anouncer;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import co.alphacraft.main.Plugin;

public class AnouncerManager {
	
	private AnouncerManager(){}
	
	static AnouncerManager instance = new AnouncerManager();
	
	public static AnouncerManager getInstance(){
		return instance;
	}
	
	Plugin p;
	FileConfiguration anouncement;
	File afile;
	
	public void anouncerSetup (Plugin p){
		
	afile = new File(p.getDataFolder(), "anouncement.yml");
		
		if (!afile.exists()){
			try {
				afile.createNewFile();
			}catch (IOException e){
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create anouncement.yml");
			}
		}
		anouncement = YamlConfiguration.loadConfiguration(afile);
	}
	
	public FileConfiguration getAnouncer(){
		return anouncement;
	}
	
	public void saveConfig(){
		try {
			anouncement.save(afile);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.DARK_RED + "Could not save Anouncement.yml");
		}
	}
	
	public void reloadConfig() {
		anouncement = YamlConfiguration.loadConfiguration(afile);
	}

}
