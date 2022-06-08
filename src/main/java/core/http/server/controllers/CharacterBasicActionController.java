package core.http.server.controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import entities.charcter.Character;
import entities.charcter.CharacterBasicAction;
import services.dao.CharacterBasicActionsDAO;
import services.dao.CharacterDAO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.SortedSet;

public class CharacterBasicActionController implements HttpHandler {
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
        SortedSet<CharacterBasicAction> characterBasicActions = CharacterBasicActionsDAO.getAll();

        return new Gson().toJson(characterBasicActions);
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
