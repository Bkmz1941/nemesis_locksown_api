package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entities.room.Room;
import org.apache.commons.lang3.StringEscapeUtils;
import services.dao.RoomDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class Controller implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        setHeaders(exchange);

        String response = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            response = index();
        }

        sendResponse(exchange, response);
    }

    private String index() throws IOException {
        return new Gson().toJson(RoomDAO.getAll());
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        final byte[] rawResponseBody = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, rawResponseBody.length);
        exchange.getResponseBody().write(rawResponseBody);
        exchange.close();
    }

    private void setHeaders(HttpExchange exchange) {
        final Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");
    }
}
