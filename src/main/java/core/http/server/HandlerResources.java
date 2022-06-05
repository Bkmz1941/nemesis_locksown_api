package core.http.server;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            String uri = String.valueOf(exchange.getRequestURI());
            String[] arrOfStr = uri.split("/resources");
            String resource = arrOfStr[1];

            File file = streamToFile(getClass().getResourceAsStream(resource));
            if (file == null) {
                final Headers headers = exchange.getResponseHeaders();
                headers.set("Content-Type", "application/json");
                headers.set("Accept", "application/json");

                HashMap<String, String> data = new HashMap<>();
                data.put("data", "");
                data.put("message", "Resource isn`t found");

                Gson gson = new Gson();
                String jsonString = gson.toJson(data);

                final byte[] rawResponseBody = jsonString.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(404, rawResponseBody.length);
                exchange.getResponseBody().write(rawResponseBody);
                exchange.close();
            } else {
                exchange.sendResponseHeaders(200, file.length());
                OutputStream outputStream = exchange.getResponseBody();
                Files.copy(file.toPath(), outputStream);
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (InputStream in = getResourceAsStream(path); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
//        final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
//        Class c = getClass();
//        InputStream i = c.getResourceAsStream(".");
        return getClass().getResourceAsStream("/images");
    }

}
