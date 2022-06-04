package app;

import com.sun.net.httpserver.HttpServer;
import database.MyHttpHandler;
import database.MysqlCon;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {


        MysqlCon m = new MysqlCon();
        m.run();
    }
}

