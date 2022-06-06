package core.http.resources;

import entities.room.Room;
import entities.room.RoomAction;
import entities.room.RoomType;

import java.util.Comparator;
import java.util.TreeSet;

public class RoomResource {
    private final int id;
    private final RoomType type;
    private final String name;
    private final String description;
    private final Boolean computer;
    private TreeSet<RoomActionResource> actions = new TreeSet<>(Comparator.comparingInt(RoomActionResource::getId));

    public RoomResource(Room room) {
        this.id = room.getId();
        this.type = room.getType();
        this.computer = room.getComputer();
        for (RoomAction ra: room.getActions()) {
            this.actions.add(new RoomActionResource(ra));
        }
        this.name = room.getName();
        this.description = room.getDescription();
    }

    public int getId() {
        return id;
    }

    public Boolean getComputer() {
        return computer;
    }

    public RoomType getType() {
        return type;
    }

    public TreeSet<RoomActionResource> getActions() {
        return actions;
    }
}
