package fr.max.Meteo.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import fr.max.Meteo.Meteo;

public class TabComplete implements TabCompleter{
	
	public TabComplete(Meteo meteo) { }
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> result = new ArrayList<>();
		if(cmd.getName().equalsIgnoreCase("meteo")){
			if(sender.hasPermission("meteo.cmd.meteo")){	
				if(args.length == 1)
				{
					if(args[0].equals(""))
					{
					if(sender.hasPermission("meteo.cmd.day")) { result.add("day"); }
					if(sender.hasPermission("meteo.cmd.help")) { result.add("help"); }
					if(sender.hasPermission("meteo.cmd.night")) { result.add("night"); }
					if(sender.hasPermission("meteo.cmd.rain")) { result.add("rain"); }
					if(sender.hasPermission("meteo.cmd.storm")) {result.add("storm"); }
					if(sender.hasPermission("meteo.cmd.sun")) {result.add("sun"); }
					if(sender.hasPermission("meteo.cmd.version")) { result.add("version"); }
					if(sender.hasPermission("meteo.cmd.reload")){ result.add("reload"); }
					}
				}
			}
		}
		return result;
	}

}
