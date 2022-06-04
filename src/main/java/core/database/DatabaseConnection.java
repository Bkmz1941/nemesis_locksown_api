package core.database;

import java.sql.*;

public class DatabaseConnection {
    private final String host = "localhost";
    private final String port = "3306";
    private final String user = "root";
    private final String password = "7wiD*FGP%gUt68#2NGyy*";
    private final String database = "nemesis_lockdown";
    private final String url;
    private static DatabaseConnection instance;
    private DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
    }

    public static DatabaseConnection getInstance() {
        try {
            if (DatabaseConnection.instance == null) {
                DatabaseConnection.instance = new DatabaseConnection();
            }
        } catch (Exception e) {
            System.out.println("К базе данных подключиться не получилось");
        }
        return DatabaseConnection.instance;
    }

    public ResultSet execute(String statement) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(statement);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Выполнить запрос не получилось");
        }
        return null;
    }
}
