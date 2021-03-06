package core.http.resources;

import models.rooms.Room;
import models.rooms.RoomGroup;
import models.rooms.RoomType;

public class RoomResource {
    private final int id;
    private final RoomType type;
    private final RoomGroup group;
    private final String name;
    private final String description;
    private final Boolean computer;

    public RoomResource(Room room) {
        this.id = room.getId();
        this.type = room.getType();
        this.group = room.getGroup();
        this.computer = room.getComputer();
        this.name = room.getName();
        this.description = room.getDescription();
    }

    public int getId() {
        return id;
    }

    public RoomGroup getGroup() {
        return group;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Boolean getComputer() {
        return computer;
    }

    public RoomType getType() {
        return type;
    }
}
