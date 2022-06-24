package services.dao;

import core.database.DatabaseConnection;
import models.rooms.Room;

import java.sql.ResultSet;
import java.util.*;

public class RoomDAO {
    public static SortedSet<Room> getAll() {
        SortedSet<Room> rooms = new TreeSet<>(Comparator.comparingInt(Room::getId));
        try {
            ResultSet rows = DatabaseConnection.getInstance().execute("SELECT * FROM rooms;");

            while (rows.next()) {
//                int roomId = rows.getInt("room_id");
                int id = rows.getInt("id");
                String systemName = rows.getString("system_name");
                String type = rows.getString("type");
                String group = rows.getString("group");
                Boolean computer = rows.getBoolean("computer");

                Room room = new Room(id, systemName, group, type, computer);
                rooms.add(room);

//                for (Room r : rooms) {
//                    if (r.getId() == roomId) {
//                        int roomActionId = r.getActions().size() + 1;
//                        String roomActionSystemName = rows.getString("action_system_name");
//                        int roomActionCost = rows.getInt("cost");
//                        r.addRoomAction(new RoomAction(roomActionId, roomActionSystemName, roomActionCost));
//                        break;
//                    }
//                }
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return rooms;
    }

//    private HashSet<RoomAction> buildRoomActions(ResultSet row) {
//        HashSet<RoomAction> result = new HashSet<RoomAction>();
//    }
}
