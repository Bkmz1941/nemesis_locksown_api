package entities.room;

import com.google.gson.Gson;

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

    public static File streamToFile(InputStream in) {
        if (in == null) {
            return null;
        }

        try {
            File f = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            f.deleteOnExit();

            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            return f;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public String getName() {
        HashMap<String, String> map = new HashMap<>();
        try {
            File file = streamToFile(getClass().getResourceAsStream("/translate/ru/rooms.json"));
            assert file != null;
            String jsonString = new String(Files.readAllBytes(file.toPath()));
            map = new Gson().fromJson(jsonString, HashMap.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return map.get(this.systemName);
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
