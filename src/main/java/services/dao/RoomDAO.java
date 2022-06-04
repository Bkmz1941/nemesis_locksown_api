package services.dao;

import core.database.DatabaseConnection;
import entities.room.Room;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RoomDAO {
    public static HashSet<Room> getAll() {
        HashSet<Room> result = new HashSet<>();
        try {
            ResultSet set = DatabaseConnection.getInstance().execute("SELECT * FROM rooms");
            while (set.next()) {
                String systemName = set.getString("system_name");
                String type = set.getString("type");
                Boolean computer = set.getBoolean("computer");
                Room room = new Room(systemName, type, computer);
                result.add(room);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
