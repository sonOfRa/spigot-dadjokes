package de.slevermann.minecraft.dadjokes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class DadJokePlugin extends JavaPlugin {
    private static final int TICKS_PER_SECOND = 20;

    private BukkitTask task;

    @Override
    public void onEnable() {
        this.getCommand("dadjoke").setExecutor(new DadJokeCommand());
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    new DadJokeFetcher().getDadJoke();

                } catch (MalformedURLException ex) {
                    Bukkit.getLogger().log(Level.WARNING, "Malformed URL. This is a bug in the plugin.");
                } catch (IOException ex) {
                    Bukkit.getLogger().log(Level.INFO, "Could not fetch joke from URL");
                }
            }
        }.runTaskTimer(this, TICKS_PER_SECOND * 60, TICKS_PER_SECOND * 60 * 10);
    }

    @Override
    public void onDisable() {
        this.task.cancel();
    }
}
