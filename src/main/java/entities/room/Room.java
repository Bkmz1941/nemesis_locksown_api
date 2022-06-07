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
    private final RoomGroup group;
    private final Boolean computer;
    private final TreeSet<RoomAction> actions = new TreeSet<>(Comparator.comparingInt(RoomAction::getId));

    public Room(int id, String systemName, String group, String type, Boolean computer) {
        this.id = id;
        this.systemName = systemName;
        this.type = detectRoomType(type);
        this.group = detectRoomGroup(group);
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

    public RoomGroup getGroup() {
        return group;
    }

    public TreeSet<RoomAction> getActions() {
        return actions;
    }

    public void addRoomAction(RoomAction roomAction) {
        actions.add(roomAction);
    }

    private RoomType detectRoomType(String type) {
        return switch (type) {
            case "technical" -> RoomType.TECHNICAL;
            case "armory" -> RoomType.ARMORY;
            case "medical" -> RoomType.MEDICAL;
            case "multi" -> RoomType.MULTI;
            case "unknown" -> RoomType.UNKNOWN;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private RoomGroup detectRoomGroup(String group) {
        return switch (group) {
            case "basic" -> RoomGroup.BASIC;
            case "additional" -> RoomGroup.ADDITIONAL;
            case "special" -> RoomGroup.SPECIAL;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
