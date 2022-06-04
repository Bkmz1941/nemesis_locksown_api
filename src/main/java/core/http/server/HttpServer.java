package core.http.server;

import database.MyHttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {


    HttpServer() throws IOException {
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/test", new MyHttpHandler());
        server.start();
    }
}
