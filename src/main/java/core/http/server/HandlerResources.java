package core.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;

public class HandlerResources implements HttpHandler {

    public static File streamToFile(InputStream in) {
        if (in == null) {
            return null;
        }

        try {
            File f = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            f.deleteOnExit();

            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            return f;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            File file = streamToFile(getClass().getClassLoader().getResourceAsStream("images/main.jpg"));
            exchange.sendResponseHeaders(200, file.length());
            OutputStream outputStream = exchange.getResponseBody();
            Files.copy(file.toPath(), outputStream);
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
