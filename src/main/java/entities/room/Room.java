package entities.room;

import com.google.gson.Gson;
import helpers.FileHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Room {
    private final int id;
    private final String systemName;
    private final RoomType type;
    private final Boolean computer;
    private final TreeSet<RoomAction> actions = new TreeSet<>(Comparator.comparingInt(RoomAction::getId));

    public Room(int id, String systemName, String type, Boolean computer) {
        this.id = id;
        this.systemName = systemName;
        this.type = detectRoomType(type);
        this.computer = computer;
    }

    public String getName() {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        try {
            File file = FileHelper.streamToFile(getClass().getResourceAsStream("/translate/ru/rooms.json"));
            assert file != null;
            String jsonString = new String(Files.readAllBytes(file.toPath()));
            map = new Gson().fromJson(jsonString, HashMap.class);
            map2 = new Gson().fromJson((String) map.get("emergency_room"), HashMap.class);
//            map = new Gson().fromJson(map.get("emergency_room"), HashMap.class);
            System.out.println(jsonString);
            System.out.println(map2);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public int getId() {
        return id;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public RoomType getType() {
        return this.type;
    }

    public Boolean getComputer() {
        return this.computer;
    }

    public TreeSet<RoomAction> getActions() {
        return actions;
    }

    public void addRoomAction(RoomAction roomAction) {
        actions.add(roomAction);
    }

    private RoomType detectRoomType(String type) {
        RoomType result = null;
        switch (type) {
            case "basic":
                result = RoomType.BASIC;
                break;
            case "additional":
                result = RoomType.ADDITIONAL;
                break;
            case "special":
                result = RoomType.SPECIAL;
                break;
        }
        return result;
    }
}
