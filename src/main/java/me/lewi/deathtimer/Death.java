package me.lewi.deathtimer;

import me.lewi.deathtimer.commands.deathCommand;
import me.lewi.deathtimer.listeners.DamageEvent;
import me.lewi.deathtimer.listeners.DismountEvent;
import me.lewi.deathtimer.listeners.InteractEvent;
import me.lewi.deathtimer.managers.DeathFreezeManager;
import me.lewi.deathtimer.managers.DeathManager;
import me.lewi.deathtimer.managers.DeathTimerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Death extends JavaPlugin {

    public static Death plugin;

    private DeathFreezeManager deathFreezeManager;
    private DeathManager deathManager;
    private DeathTimerManager deathTimerManager;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();

        deathFreezeManager = new DeathFreezeManager();
        deathManager = new DeathManager();
        deathTimerManager = new DeathTimerManager();

        this.getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DismountEvent(), this);
        this.getServer().getPluginManager().registerEvents(new InteractEvent(), this);

        this.getCommand("death").setExecutor(new deathCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DeathFreezeManager getDeathFreezeManager() {
        return deathFreezeManager;
    }

    public DeathManager getDeathManager() {
        return deathManager;
    }

    public DeathTimerManager getDeathTimerManager() {
        return deathTimerManager;
    }
}
