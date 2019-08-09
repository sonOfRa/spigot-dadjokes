package de.slevermann.minecraft.dadjokes;

import org.bukkit.Bukkit;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DadJokeFetcher {
    private static final String DAD_JOKE_URL = "https://icanhazdadjoke.com/";

    public void getFlachwitz() throws IOException {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) new URL(DAD_JOKE_URL).openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("Accept", "text/plain");
            connection.addRequestProperty("User-Agent", "Spigot Dad Joke Plugin " +
                    "(https://github.com/sonOfRa/spigot-dadjokes");
            connection.connect();

            if (connection.getResponseCode() != 200) {
                throw new IOException();
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
}
