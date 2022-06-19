package core.http.server.controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Controller implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            setHeaders(exchange);
            sendResponse(exchange, makeResponse(exchange));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    protected String makeResponse(HttpExchange exchange) {
        return "";
    }

    protected Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    protected void sendResponse(HttpExchange exchange, String response) throws IOException {
        final byte[] rawResponseBody = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, rawResponseBody.length);
        exchange.getResponseBody().write(rawResponseBody);
        exchange.close();
    }

    protected void setHeaders(HttpExchange exchange) {
        final Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");
        headers.set("Access-Control-Allow-Origin", "*");
    }
}
