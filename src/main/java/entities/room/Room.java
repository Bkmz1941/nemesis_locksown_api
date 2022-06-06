package entities.room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import helpers.FileHelper;
import helpers.FileWithTranslate;

import java.io.*;
import java.nio.file.Files;
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
        return FileWithTranslate.getKey("ru", "rooms", this.systemName, "name");
    }
    public String getDescription() {
        return FileWithTranslate.getKey("ru", "rooms", this.systemName, "description");
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
