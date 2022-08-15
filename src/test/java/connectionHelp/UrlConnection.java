package connectionHelp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlConnection {
    public static void urlConnection() throws IOException {
        String urlAdress = "http://26.115.101.38:4444/ui#/sessions";
        HttpURLConnection connection = null;
        URL url = null;
        try {
            url = new URL(urlAdress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
            } catch (IOException x) {
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                } catch (IOException c) {
                }
            }
        }
    }
}
