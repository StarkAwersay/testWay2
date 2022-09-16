package helpers;

import properties.Properties;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlConnection {
    public static void urlConnection() throws IOException {
        String urlAdress = Properties.URL_HUB_GRID_SERVER;
        HttpURLConnection connection = null;
        URL url = null;
        try {
            url = new URL(urlAdress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            connection.setConnectTimeout(5000);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        }
    }
}
