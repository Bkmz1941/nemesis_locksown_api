package services.dao;

import core.database.DatabaseConnection;
import models.charcter.Character;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharacterDAO {

    public static SortedSet<Character> getAll() {
        SortedSet<Character> characters = new TreeSet<>(Comparator.comparingInt(Character::getId));
        try {
            ResultSet rows = DatabaseConnection.getInstance().execute("SELECT * FROM characters");
            while (rows.next()) {
                int id = rows.getInt("id");
                String systemName = rows.getString("system_name");
                String color = rows.getString("color");
                Character character = new Character(id, systemName, color);
                characters.add(character);
            }
//            while (rows.next()) {
//                int roomId = rows.getInt("room_id");
//                int id = rows.getInt("id");
//                String systemName = rows.getString("system_name");
//                String type = rows.getString("type");
//                Boolean computer = rows.getBoolean("computer");
//
//                Room room = new Room(id, systemName, type, computer);
//                rooms.add(room);
//
//                for (Room r : rooms) {
//                    if (r.getId() == roomId) {
//                        int roomActionId = r.getActions().size() + 1;
//                        String roomActionSystemName = rows.getString("action_system_name");
//                        int roomActionCost = rows.getInt("cost");
//                        r.addRoomAction(new RoomAction(roomActionId, roomActionSystemName, roomActionCost));
//                        break;
//                    }
//                }
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return characters;
    }
}
