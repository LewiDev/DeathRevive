package me.lewi.deathtimer.managers;

import me.lewi.deathtimer.Death;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class DeathTimerManager {

    private final Death plugin = Death.plugin;

    public static HashMap<UUID, Integer> timer = new HashMap<>();


    public void startTimer(Player p, Integer length) {
        BukkitTask timerRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getDeathManager().leaveDeathState(p, false);
            }
        }.runTaskLater(plugin, length * 20L);
        timer.put(p.getUniqueId(), timerRunnable.getTaskId());
    }

    public void cancelTimer(Player p) {
        Bukkit.getScheduler().cancelTask(timer.get(p.getUniqueId()));
        timer.remove(p.getUniqueId());
    }

}
