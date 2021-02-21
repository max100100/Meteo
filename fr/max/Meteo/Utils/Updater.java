package fr.max.Meteo.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import fr.max.Meteo.Meteo;

public class Updater {
	private static Meteo plugin;
	
	public Updater(Meteo instance) {plugin = instance;}
	
	public static void CheckUpdate()
	{
		try
		{
			HttpURLConnection con = (HttpURLConnection) (new URL("https://api.spigotmc.org/legacy/update.php?resource=43384")).openConnection();
			String newversion = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
			String currentversion =	plugin.getDescription().getVersion();
			if (!plugin.getDescription().getVersion().equalsIgnoreCase(newversion))
			{
				Bukkit.getLogger().warning(ChatColor.RED + Meteo.GetPrefix() + LanguageManager.getString("Update-found-msg1").replaceFirst("%newversion", newversion).replaceFirst("%currentversion", currentversion));
				Bukkit.getLogger().warning(ChatColor.RED + Meteo.GetPrefix() + LanguageManager.getString("Update-found-msg2") + "https://www.spigotmc.org/resources/meteo.43384/");
				
			}
			else
			{
				Bukkit.getLogger().warning(ChatColor.GREEN + Meteo.GetPrefix() + LanguageManager.getString("Update-no-found"));
			}
		}
		catch(IOException e)
		{
			Bukkit.getLogger().warning(ChatColor.DARK_RED + Meteo.GetPrefix() + "Fail to check update !");
			Bukkit.getLogger().warning(ChatColor.DARK_RED + Meteo.GetPrefix() + "Please report this problem on the spigot page of the plugin");
		}
	}
}
