package app;

import core.http.server.HttpServer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        HttpServer.getInstance().start();
    }
}

