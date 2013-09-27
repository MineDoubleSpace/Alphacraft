package co.alphacraft.anouncer;

import java.util.List;

public class AnouncerMessages {
	
	private AnouncerMessages(){}
	
	static AnouncerMessages instance = new AnouncerMessages(); 
	
	public static AnouncerMessages getInstance(){
		return instance;
	}
	AnouncerManager s =  AnouncerManager.getInstance();
	
	
	
	
	public List<String> getAnouncements(){
		List<String> a = s.getAnouncer().getStringList("messages");
			return a;
		}
	
	
	
	
	
	public int getSize(){
		List<String> size = s.getAnouncer().getStringList("messages");
		int anouncements = size.size();
		return anouncements;
	}
	
	public String getPrefix(){
		if (s.getAnouncer().getString("prefix") != null){
		return s.getAnouncer().getString("prefix");
		}
		return "";
	}
	
	public long getDelay(){
		long delay1 = AnouncerManager.getInstance().getAnouncer().getLong("delay");
		long delay = delay1*20;
		return delay;
		
	}
	
	public boolean isEnabled(){
		s.reloadConfig();
		if (s.getAnouncer().getBoolean("enabled") == true){
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	

}
