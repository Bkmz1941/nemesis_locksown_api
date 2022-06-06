package core.http.resources;

import entities.room.Room;
import entities.room.RoomAction;

public class RoomActionResource {
    private final int id;
    private final String name;
    private final int cost;

    public RoomActionResource(RoomAction roomAction) {
        this.id = roomAction.getId();
        this.name = roomAction.getName();
        this.cost = roomAction.getCost();
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
