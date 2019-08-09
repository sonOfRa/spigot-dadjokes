package de.slevermann.minecraft.dadjokes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class DadJokeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            new DadJokeFetcher().getFlachwitz();
        } catch (MalformedURLException ex) {
            Bukkit.getLogger().log(Level.WARNING, "Malformed URL. This is a bug in the plugin.");
            commandSender.sendMessage("Malformed URL. This is a bug in the plugin.");
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.INFO, "Could not fetch joke from URL");
            commandSender.sendMessage("Dad joke URL is down");
        }
        return true;
    }
}
