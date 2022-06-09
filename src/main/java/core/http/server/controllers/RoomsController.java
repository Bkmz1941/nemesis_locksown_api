package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.source.tree.Tree;
import core.http.resources.RoomResource;
import entities.room.Room;
import entities.room.RoomAction;
import services.dao.RoomDAO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class RoomsController implements HttpHandler {
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
        SortedSet<Room> rooms = RoomDAO.getAll();
        SortedSet<RoomResource> roomResources = new TreeSet<>(Comparator.comparingInt(RoomResource::getId));

        for (Room room: rooms) {
            roomResources.add(new RoomResource(room));
        }

        return new Gson().toJson(roomResources);
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
        headers.set("Access-Control-Allow-Origin", "*");
    }
}
