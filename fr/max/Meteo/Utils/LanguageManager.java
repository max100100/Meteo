package fr.max.Meteo.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.max.Meteo.Meteo;

public class LanguageManager {
	
	private static Meteo plugin;
	
	private static YamlConfiguration langconfig;
	private static YamlConfiguration langDefault;
	
	public LanguageManager(Meteo instance) {plugin = instance;}
	
	public static void loadLanguage() {
		String lang = plugin.getConfig().getString("lang");
		
		langconfig = loadLang(lang);
		langDefault = loadLang("fr_FR");
		
		if (langconfig == null) langconfig = langDefault;
		else Bukkit.getLogger().info(Meteo.GetPrefix() + getString("Lang-load"));
	}
	
	public static String getString(String path){
		String result = langconfig.getString(path);
		if (result == null) {
			result = langDefault.getString(path);
		}
		return result;
	}
	
	public static void ClearLang(){
		langconfig = null;
		langDefault = null;	
	}
	
	private static YamlConfiguration loadLang(String lang){
		InputStream stream = LanguageManager.class.getResourceAsStream("/lang/" + lang + ".yml");
		if (stream == null) return null;
		InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
		
		YamlConfiguration file = YamlConfiguration.loadConfiguration(reader);
		try {
			reader.close();
			stream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
