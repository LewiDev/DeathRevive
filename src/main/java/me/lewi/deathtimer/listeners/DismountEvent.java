package me.lewi.deathtimer.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class DismountEvent implements Listener {

    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        if (e.getEntity() instanceof Player && e.getDismounted().equals(EntityType.ARMOR_STAND)) {
            e.setCancelled(true);
        }
    }

}
