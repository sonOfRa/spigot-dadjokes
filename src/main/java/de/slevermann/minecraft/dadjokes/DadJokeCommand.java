package de.slevermann.minecraft.dadjokes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DadJokeCommand implements CommandExecutor {

    private DadJokeFetcher fetcher;

    public DadJokeCommand(DadJokeFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        if (args.length != 0) {
            return false;
        }
        fetcher.getDadJokeAsync(commandSender);
        return true;
    }
}
