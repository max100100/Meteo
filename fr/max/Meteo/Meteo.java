package fr.max.Meteo;

import org.bukkit.plugin.java.JavaPlugin;

import fr.max.Meteo.Utils.ConfigurationManager;
import fr.max.Meteo.Utils.LanguageManager;
import fr.max.Meteo.Utils.Updater;
import fr.max.Meteo.commands.CommandMeteo;
import fr.max.Meteo.commands.TabComplete;


public class Meteo extends JavaPlugin
{
	
    private CommandMeteo cmdMeteo;
    private TabComplete tcMeteo;
    
    static String prefix = "[Meteo] ";
 
    public void onDisable()
    {
    	System.out.println(prefix + LanguageManager.getString("System-unload"));
    	LanguageManager.ClearLang();
    }

    public void onEnable()
    {
    	new ConfigurationManager(this);
        ConfigurationManager.loadconfig();
        ConfigurationManager.updateConfig();
        
        new LanguageManager(this);
        LanguageManager.loadLanguage();
    	
    	cmdMeteo = new CommandMeteo(this);
        tcMeteo = new TabComplete(this);
        getCommand("meteo").setExecutor(cmdMeteo);
        getCommand("meteo").setTabCompleter(tcMeteo);
        
        System.out.println(prefix + LanguageManager.getString("System-load"));
        
        new Updater(this);
        Updater.CheckUpdate();
    }
    
    public static String GetPrefix()
    {
    	return prefix;
    }
}
