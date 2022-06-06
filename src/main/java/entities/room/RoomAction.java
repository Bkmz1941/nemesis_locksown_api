package entities.room;

import com.google.gson.Gson;
import helpers.FileHelper;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

public class RoomAction {
    private final int id;
    private final String systemName;
    private final int cost;

    public RoomAction(int id, String systemName, int cost) {
        this.id = id;
        this.systemName = systemName;
        this.cost = cost;
    }

    public String getName() {
        HashMap<String, String> map = new HashMap<>();
        try {
            File file = FileHelper.streamToFile(getClass().getResourceAsStream("/translate/ru/room_actions.json"));
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
        return systemName;
    }

    public int getCost() {
        return cost;
    }
}
