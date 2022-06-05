package services.dao;

import core.database.DatabaseConnection;
import entities.room.Room;
import entities.room.RoomAction;

import java.sql.ResultSet;
import java.util.*;

public class RoomDAO {
    public static SortedSet<Room> getAll() {
        SortedSet<Room> rooms = new TreeSet<>(Comparator.comparingInt(Room::getId));
        try {
            ResultSet rows = DatabaseConnection.getInstance().execute("SELECT r.id, r.system_name, r.type, r.computer, ra.system_name AS action_system_name, ra.cost, ra.room_id FROM rooms r LEFT JOIN room_actions ra ON r.id = ra.room_id;");

            while (rows.next()) {
                int roomId = rows.getInt("room_id");
                int id = rows.getInt("id");
                String systemName = rows.getString("system_name");
                String type = rows.getString("type");
                Boolean computer = rows.getBoolean("computer");

                Room room = new Room(id, systemName, type, computer);
                rooms.add(room);

                for (Room r : rooms) {
                    if (r.getId() == roomId) {
                        int roomActionId = r.getActions().size() + 1;
                        String roomActionSystemName = rows.getString("action_system_name");
                        int roomActionCost = rows.getInt("cost");
                        r.addRoomAction(new RoomAction(roomActionId, roomActionSystemName, roomActionCost));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rooms;
    }

//    private HashSet<RoomAction> buildRoomActions(ResultSet row) {
//        HashSet<RoomAction> result = new HashSet<RoomAction>();
//    }
}
