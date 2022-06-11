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

public class RoomsController extends Controller {
    @Override
    protected String makeResponse(HttpExchange exchange) {
        String response = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            response = index();
        }
        return response;
    }

    private String index() {
        try {
            SortedSet<Room> rooms = RoomDAO.getAll();
            SortedSet<RoomResource> roomResources = new TreeSet<>(Comparator.comparingInt(RoomResource::getId));

            for (Room room: rooms) {
                roomResources.add(new RoomResource(room));
            }

            return new Gson().toJson(roomResources);
        } catch (Exception ignored) {}

        return null;
    }
}
