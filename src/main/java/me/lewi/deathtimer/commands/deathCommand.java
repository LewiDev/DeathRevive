package me.lewi.deathtimer.commands;

import me.lewi.deathtimer.Death;
import me.lewi.deathtimer.managers.ReviveManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deathCommand implements CommandExecutor {

    private Death plugin = Death.plugin;

    private String noperms = ChatColor.RED + "You do not have permission to use this command";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if(sender.hasPermission("DeathTimer.Reload")) {
                        plugin.reloadConfig();
                        sender.sendMessage(ChatColor.GREEN + "reloaded config!");
                    } else {
                        sender.sendMessage(noperms);
                        return true;
                    }
                }
            } else if (args.length == 2) {
                if(args[0].equalsIgnoreCase("give")) {
                    Player p = Bukkit.getPlayer(args[1]);
                    if(!sender.hasPermission("DeathTimer.Give")) {
                        sender.sendMessage(noperms);
                        return true;
                    }
                    if(p != null) {
                        p.getInventory().addItem(ReviveManager.getItem());
                        sender.sendMessage(ChatColor.GREEN + "Item has been given");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Not a valid player");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid Usage: /death <give/reload> <player>");
            }
        }

        return false;
    }

}
