package fr.max.Meteo.commands;

import fr.max.Meteo.Meteo;
import fr.max.Meteo.Utils.LanguageManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMeteo implements CommandExecutor
{
  private Meteo plugin;
  String prefix = "[Meteo] ";
  
  public CommandMeteo(Meteo meteo) { plugin = meteo; }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (label.equalsIgnoreCase("meteo")) {
      if (sender.hasPermission("meteo.cmd.meteo")) {
        if ((sender instanceof Player)) {
          Player p = (Player)sender;
          if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "/meteo <sun|rain|day|night|storm>");
          }
          else if (args[0].equalsIgnoreCase("sun")) {
            if (p.hasPermission("meteo.cmd.sun")) {
              p.getWorld().setThundering(false);
              p.getWorld().setStorm(false);
              p.sendMessage(ChatColor.GREEN + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("msg-sun")).toString());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("rain")) {
            if (p.hasPermission("meteo.cmd.rain")) {
              p.getWorld().setThundering(true);
              p.getWorld().setStorm(true);
              p.sendMessage(ChatColor.GREEN + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("msg-rain")).toString());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("day")) {
            if (p.hasPermission("meteo.cmd.day")) {
              p.getWorld().setTime(plugin.getConfig().getInt("timeday"));
              p.sendMessage(ChatColor.GREEN + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("msg-day")).toString());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("night")) {
            if (p.hasPermission("meteo.cmd.night")) {
              p.getWorld().setTime(plugin.getConfig().getInt("timenight"));
              p.sendMessage(ChatColor.GREEN + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("msg-night")).toString());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("help")) {
            if (p.hasPermission("meteo.cmd.help")) {
              p.sendMessage(ChatColor.DARK_RED + LanguageManager.getString("msg-help-header"));
              p.sendMessage("/meteo help " + LanguageManager.getString("msg-help-help"));
              p.sendMessage("/meteo rain " + LanguageManager.getString("msg-help-rain"));
              p.sendMessage("/meteo sun " + LanguageManager.getString("msg-help-sun"));
              p.sendMessage("/meteo day " + LanguageManager.getString("msg-help-day"));
              p.sendMessage("/meteo night " + LanguageManager.getString("msg-help-night"));
              p.sendMessage("/meteo storm " + LanguageManager.getString("msg-help-storm"));
              p.sendMessage("/meteo version " + LanguageManager.getString("msg-help-version"));
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("version")) {
            if (p.hasPermission("meteo.cmd.version")) {
              p.sendMessage(prefix + "Version : " + plugin.getDescription().getVersion());
              p.sendMessage(prefix + LanguageManager.getString("msg-version-l2") + plugin.getDescription().getAuthors());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("storm")) {
            if (p.hasPermission("meteo.cmd.storm")) {
              p.getWorld().setStorm(true);
              p.sendMessage(ChatColor.GREEN + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("msg-storm")).toString());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else if (args[0].equalsIgnoreCase("reload")) {
            if (p.hasPermission("meteo.cmd.reload")) {
              plugin.reloadConfig();
              try {
                LanguageManager.loadLanguage();
              }
              catch (Exception e) {
                e.printStackTrace();
              }
              p.sendMessage(ChatColor.GREEN + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("msg-reload")).toString());
            }
            else {
              p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
            }
          }
          else {
            p.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("error-not-found")).toString());
            p.sendMessage(ChatColor.RED + "/meteo help");
          }
        }
        else {
          sender.sendMessage(LanguageManager.getString("msg-ingame-only"));
        }
      } else {
        sender.sendMessage(ChatColor.RED + new StringBuilder(String.valueOf(prefix)).append(LanguageManager.getString("no-perm")).toString());
      }
    }
    return true;
  }
}