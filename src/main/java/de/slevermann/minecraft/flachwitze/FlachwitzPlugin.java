package de.slevermann.minecraft.flachwitze;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class FlachwitzPlugin extends JavaPlugin {
    private static final int TICKS_PER_SECOND = 20;

    private BukkitTask task;

    @Override
    public void onEnable() {
        getLogger().info("Flachwitze loaded");
        this.getCommand("flachwitz").setExecutor(new FlachwitzCommand());
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    new FlachwitzFetcher().getFlachwitz();

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
        getLogger().info("Flachwitze unloaded");
    }
}
