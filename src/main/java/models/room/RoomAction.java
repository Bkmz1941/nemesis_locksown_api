package models.room;

import helpers.FileWithTranslate;

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
        return FileWithTranslate.getKey("ru", "room_actions", this.systemName, "name");
    }

    public String getDescription() {
        return FileWithTranslate.getKey("ru", "room_actions", this.systemName, "description");
    }

    public String getHints() {
        return FileWithTranslate.getKey("ru", "room_actions", this.systemName, "hints");
    }

    public String getConditions() {
        return FileWithTranslate.getKey("ru", "room_actions", this.systemName, "conditions");
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
