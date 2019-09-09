package de.slevermann.minecraft.dadjokes;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class DadJokeFetcher {
    private static final String DAD_JOKE_URL = "https://icanhazdadjoke.com/";

    private DadJokePlugin plugin;

    public DadJokeFetcher(DadJokePlugin plugin) {
        this.plugin = plugin;
    }

    public void getDadJoke() throws IOException {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) new URL(DAD_JOKE_URL).openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("Accept", "text/plain");
            connection.addRequestProperty("User-Agent", "Spigot Dad Joke Plugin " +
                    "(https://github.com/sonOfRa/spigot-dadjokes) @ " + Bukkit.getServer().getIp());
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("Fetching dad Joke failed with: " + responseCode + " " +
                        connection.getResponseMessage());
            }

            InputStream is = connection.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }

            String dadJoke = baos.toString();
            for (String line : dadJoke.split("\\R")) {
                Bukkit.broadcastMessage(line);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public void getDadJokeAsync(CommandSender commandSender) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                getDadJoke();
            } catch (MalformedURLException ex) {
                Bukkit.getLogger().log(Level.WARNING, "Malformed URL. This is a bug in the plugin.");
                commandSender.sendMessage("Malformed URL. This is a bug in the plugin.");
            } catch (IOException ex) {
                Bukkit.getLogger().log(Level.INFO, "Could not fetch joke from URL");
                commandSender.sendMessage("Dad joke URL is down");
            }
        });
    }
}
