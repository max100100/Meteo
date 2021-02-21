package fr.max.Meteo.Utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import fr.max.Meteo.Meteo;

public class ConfigurationManager {

	private static Meteo plugin;
	private static File configf;
	static Integer versionConfig = 3;
	
	public ConfigurationManager(Meteo instance) {plugin = instance;}
	
	public static void loadconfig() 
	{
		configf = new File(plugin.getDataFolder() + "config.yml");
		if(!configf.exists())
		{
			try
			{
				plugin.saveDefaultConfig();
			}
			catch(Exception e)
			{
				Bukkit.getLogger().severe(ChatColor.DARK_RED + Meteo.GetPrefix() + "Could not create config.yml");
				plugin.getServer().getPluginManager().disablePlugin(plugin);
			
			}
		}
	}
	
	public static void updateConfig()
    {
    	try
    	{
    		Integer oldconfigversion = plugin.getConfig().getInt("version");
    		Integer newconfigversion = versionConfig;
    		if(oldconfigversion != newconfigversion)
    		{
    			Bukkit.getLogger().warning(ChatColor.RED + Meteo.GetPrefix() + "Updating Config");
    			Bukkit.getLogger().warning(ChatColor.RED + Meteo.GetPrefix() + "Old config version: " + oldconfigversion + "New config version:" + newconfigversion);
    			configf.delete();
    			plugin.saveResource("config.yml", true);
    		}
    		else
    		{
    			Bukkit.getLogger().info(ChatColor.GREEN + Meteo.GetPrefix() + "The configuration file is already up to date");
    		}
    	}
    	catch(Exception e)
    	{
    		Bukkit.getLogger().severe(ChatColor.DARK_RED + Meteo.GetPrefix() + "Could not update config");
    		plugin.getServer().getPluginManager().disablePlugin(plugin);
    	}
    	
    }
}
