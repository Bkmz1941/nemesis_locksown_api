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
    private final Boolean computer;
    private TreeSet<RoomAction> actions = new TreeSet<>(Comparator.comparingInt(RoomAction::getId));

    public RoomResource(Room room) {
        this.id = room.getId();
        this.type = room.getType();
        this.computer = room.getComputer();
        this.actions = room.getActions();
        this.name = room.getName();
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

    public TreeSet<RoomAction> getActions() {
        return actions;
    }
}
