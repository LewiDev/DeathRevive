package me.lewi.deathtimer.managers;

import me.lewi.deathtimer.Death;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeathManager {

    private Death plugin = Death.plugin;

    public static List<UUID> playersInDeathState = new ArrayList<>();

    private boolean blind = plugin.getConfig().getBoolean("dead.setblind");

    public boolean playerInDeathState(Player p) {
        return playersInDeathState.contains(p.getUniqueId());
    }

    public void enterDeathState(Player p) {
        if(p.hasPermission("DeathTimer.NoTimer")) {p.setHealth(0); return;}

        playersInDeathState.add(p.getUniqueId());
        p.setHealth(plugin.getConfig().getDouble("dead.health"));
        p.playEffect(p.getLocation(), (Effect) plugin.getConfig().get("dead.soundeffect"), 1);
        if(blind) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60 * 20, 10));
        }

        ArmorStand as = plugin.getDeathFreezeManager().createArmorStand(p, p.getLocation());
        as.setPassenger(p);

    }

    public void leaveDeathState(Player p, Boolean revived) {
        p.removePotionEffect(PotionEffectType.BLINDNESS);
        plugin.getDeathFreezeManager().getPlayerAS(p).remove();
        playersInDeathState.remove(p.getUniqueId());
        if(revived) {
            p.setHealth(plugin.getConfig().getDouble("revive.health"));
            p.playEffect(p.getLocation(), (Effect) plugin.getConfig().get("revive.soundeffect"), 1);
        } else {
            p.setHealth(0);
        }
    }

}
