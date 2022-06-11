package core.http.server;

import core.http.server.controllers.CharactersActionCardsController;
import core.http.server.controllers.CharactersBasicActionsController;
import core.http.server.controllers.CharactersController;
import core.http.server.controllers.RoomsController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {
    static com.sun.net.httpserver.HttpServer server;
    public static final String port = "http";

    public static com.sun.net.httpserver.HttpServer getInstance() throws IOException {
        try {
            if (server == null) {
                server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
                handlerRoutes();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return server;
    }
    public void start() {
        server.start();
    }

    private static void handlerRoutes() {
        server.createContext("/resources", new HandlerResources());
        server.createContext("/api/rooms", new RoomsController());
        server.createContext("/api/characters", new CharactersController());
        server.createContext("/api/characters/actions/basic", new CharactersBasicActionsController());
        server.createContext("/api/characters/actions/cards", new CharactersActionCardsController());
    }
}
