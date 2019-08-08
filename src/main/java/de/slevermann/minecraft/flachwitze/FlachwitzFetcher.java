package de.slevermann.minecraft.flachwitze;

import org.bukkit.Bukkit;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FlachwitzFetcher {
    private static final String FLACHWITZ_URL = "https://api.opossum.media/streamacademy/commands/fun/flachwitz.php";

    public void getFlachwitz() throws IOException {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) new URL(FLACHWITZ_URL).openConnection();

            connection.setRequestMethod("GET");
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

            Bukkit.broadcastMessage(baos.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();

            }
        }
    }
}
