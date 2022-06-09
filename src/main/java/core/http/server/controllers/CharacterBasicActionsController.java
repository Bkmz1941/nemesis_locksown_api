package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.http.resources.CharacterBasicActionsResource;
import entities.charcter.CharacterBasicAction;
import services.dao.CharacterBasicActionsDAO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharacterBasicActionsController implements HttpHandler {
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
        Set<CharacterBasicAction> characterBasicActions = CharacterBasicActionsDAO.getAll();
        Set<CharacterBasicActionsResource> characterBasicActionsResource = new TreeSet<>(Comparator.comparingInt(CharacterBasicActionsResource::getId));

        characterBasicActions.forEach(el -> characterBasicActionsResource.add(new CharacterBasicActionsResource(el)));

        return new Gson().toJson(characterBasicActionsResource);
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
