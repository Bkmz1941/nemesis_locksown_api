package core.http.server;

import core.http.server.controllers.RoomsController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {
    static com.sun.net.httpserver.HttpServer server;

    public static com.sun.net.httpserver.HttpServer getInstance() throws IOException {
        if (server == null) {
            server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            handlerRoutes();
        }
        return server;
    }
    public void start() {
        server.start();
    }

    private static void handlerRoutes() {
        System.out.println(server.getAddress());
        server.createContext("/resources", new HandlerResources());
        server.createContext("/api/rooms", new RoomsController());
    }
}
