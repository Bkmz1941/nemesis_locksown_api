package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.http.resources.CharacterResource;
import entities.charcter.Character;
import services.dao.CharacterDAO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharacterController implements HttpHandler {
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
        SortedSet<Character> characters = CharacterDAO.getAll();
        SortedSet<CharacterResource> characterResource = new TreeSet<>(Comparator.comparingInt(CharacterResource::getId));

        for (Character character: characters) {
            characterResource.add(new CharacterResource(character));
        }

        return new Gson().toJson(characterResource);
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
//        headers.set("Access-Control-Allow-Methods", "GET, OPTIONS");
//        headers.set("Access-Control-Allow-Headers", "Content-Type,Authorization");

//        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
//
//        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
//            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
//            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
//            httpExchange.sendResponseHeaders(204, -1);
//            return;
//        }
    }
}
