package me.lewi.deathtimer.listeners;

import me.lewi.deathtimer.Death;
import me.lewi.deathtimer.managers.ReviveManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class InteractEvent implements Listener {

    private Death plugin = Death.plugin;


    @EventHandler
    public void InteractEvent(PlayerInteractEntityEvent e) {
        if(e.getRightClicked() instanceof Player) {
            Player p = (Player) e.getRightClicked();
            if(e.getPlayer().getInventory().getItemInMainHand().equals(ReviveManager.getItem())) {
                plugin.getDeathManager().leaveDeathState(p, true);
                ReviveManager.useItem(e.getPlayer());
                plugin.getDeathTimerManager().cancelTimer(p);
            }
        }
    }
}
