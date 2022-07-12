package me.lewi.deathtimer.listeners;

import me.lewi.deathtimer.Death;
import me.lewi.deathtimer.managers.DeathManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    private Death plugin = Death.plugin;

    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player) {
            Player deadPlayer = (Player) e.getEntity();

            if(deadPlayer.getHealth() - e.getDamage() <= 0) {
                e.setCancelled(true);
                if(plugin.getDeathManager().playerInDeathState(deadPlayer)) {
                    plugin.getDeathManager().leaveDeathState(deadPlayer, false);
                    return;
                } else {
                    plugin.getDeathManager().enterDeathState(deadPlayer);
                    plugin.getDeathTimerManager().startTimer(deadPlayer, plugin.getConfig().getInt("dead.time"));
                }
            }
        }

    }

}
