package de.slevermann.minecraft.flachwitze;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class FlachwitzCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            new FlachwitzFetcher().getFlachwitz();
        } catch (MalformedURLException ex) {
            Bukkit.getLogger().log(Level.WARNING, "Malformed URL. This is a bug in the plugin.");
            commandSender.sendMessage("Plugin ist karp0tt!");
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.INFO, "Could not fetch joke from URL");
            commandSender.sendMessage("Flachwitz-URL ist tot");
        }
        return true;
    }
}
