package app;

import core.database.DatabaseConnection;
import core.http.server.HttpServer;
import entities.room.Room;
import services.dao.RoomDAO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.util.HashSet;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        HttpServer.getInstance().start();
    }
}

