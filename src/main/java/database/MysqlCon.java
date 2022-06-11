package database;

import java.sql.*;

public class MysqlCon {
    public void run() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nemesis_lockdown", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from characters");
            while (rs.next())
                System.out.println(rs.getString("id"));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
