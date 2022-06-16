package app;

import core.http.server.HttpServer;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        HttpServer.getInstance().start();
    }
}

