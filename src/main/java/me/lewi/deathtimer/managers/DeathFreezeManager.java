package me.lewi.deathtimer.managers;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DeathFreezeManager {

    private HashMap<UUID, ArmorStand> armorstands = new HashMap<>();

    public ArmorStand createArmorStand(Player p, Location loc) {
        ArmorStand as = (ArmorStand)  p.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);

        as.setInvulnerable(true);
        as.setInvisible(true);
        as.setSmall(true);

        armorstands.put(p.getUniqueId(), as);

        return as;
    }

    public ArmorStand getPlayerAS(Player p) {
        if(armorstands.containsKey(p.getUniqueId())) {
            return armorstands.get(p.getUniqueId());
        } else {
            return null;
        }
    }

    public void removeAllStands() {
        armorstands.forEach(((uuid, armorStand) -> {
            armorStand.remove();
        }));
    }

}
